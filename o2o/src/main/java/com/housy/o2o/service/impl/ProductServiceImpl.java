package com.housy.o2o.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.housy.o2o.dao.ProductCategoryDao;
import com.housy.o2o.dao.ProductDao;
import com.housy.o2o.dao.ProductImgDao;
import com.housy.o2o.dto.ImageHolder;
import com.housy.o2o.dto.ProductCategoryExecution;
import com.housy.o2o.dto.ProductExecution;
import com.housy.o2o.entity.Product;
import com.housy.o2o.entity.ProductImg;
import com.housy.o2o.enums.ProductStateEnum;
import com.housy.o2o.exceptions.ProductOperationException;
import com.housy.o2o.service.ProductService;
import com.housy.o2o.util.ImageUtil;
import com.housy.o2o.util.PageCalculate;
import com.housy.o2o.util.PathUtil;
import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired 
	private ProductImgDao productImgDao;

	
	@Override
	@Transactional
	//��������ͼ����ȡ����ͼ�����·������ֵ��product
	//��tb_productд����Ʒ��Ϣ����ȡproductId
	//���productId����������Ʒ����ͼ
	//����Ʒ����ͼ�б���������tb_product_img��
	public ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgHolderList) throws ProductOperationException {
		//��ֵ�ж�
		if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			//Ĭ��Ϊ�ϼ���״̬
			product.setEnableStatus(1);
			if(thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				int effectedNum = productDao.insertProduct(product);
				if(effectedNum <= 0) {
					throw new ProductOperationException("������Ʒʧ��");
				}
			} catch(Exception e) {
				throw new ProductOperationException("������Ʒʧ��" + e.toString());
			}
			// ����Ʒ����ͼ��Ϊ�������
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			// ����Ϊ���򷵻ؿ�ֵ������Ϣ
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	
	/**
	 * �������ͼ
	 * 
	 * @param product
	 * @param thumbnail
	 * @throws UnsupportedEncodingException 
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}
	
	/**
	 * �������ͼƬ
	 * 
	 * @param product
	 * @param productImgHolderList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		// ��ȡͼƬ�洢·��������ֱ�Ӵ�ŵ���Ӧ���̵��ļ��е���
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		// ����ͼƬһ��ȥ��������ӽ�productImgʵ������
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		// ���ȷʵ����ͼƬ��Ҫ��ӵģ���ִ��������Ӳ���
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("������Ʒ����ͼƬʧ��");
				}
			} catch (Exception e) {
				throw new ProductOperationException("������Ʒ����ͼƬʧ��:" + e.toString());
			}
		}
	}
	
	
	
	@Override
	public Product getProductById(long productId) {
		return productDao.queryProductById(productId);
	}
	@Override
	@Transactional
	//1��������ͼ������ֵ����������ͼ
	//��ԭ�ȴ�������ͼ����ɾ���������ͼ��֮���ȡ����ͼ���·�������Ƹ�product
	//2������Ʒ����ͼ�б������ֵ������Ʒ����ͼ�б����ͬ���Ĳ���
	//3����tb_product����img����ĸ���Ʒԭ�ȵ���Ʒ����ͼ��¼ȫ�����
	//4������tb����product����Ϣ
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgHolderList) throws ProductOperationException {
		//��ֵ�ж�
		if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			//����Ʒ����Ĭ������
			product.setLastEditTime(new Date());
			//����Ʒ����ͼ��Ϊ����ԭ�е�����ͼ��Ϊ����ɾ��ԭ�е�����ͼ�����
			if(thumbnail != null) {
				//�Ȼ�ȡһ��ԭ����Ϣ����Ϊԭ������Ϣ�����������ַ
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
			}
			//������´������Ʒ����ͼ����ԭ�ȵ�ɾ����������µ�ͼƬ
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImgHolderList);
			}
			try {
				//������Ʒ��Ϣ
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("��Ʒ����ʧ��");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch(Exception e) {
				throw new ProductOperationException("������Ʒ��Ϣʧ��" + e.toString());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	private void deleteProductImgList(long productId) {
		//����productId��ȡԭ����ͼƬ
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		//ɾ��ԭ����ͼƬ
		for(ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		//ɾ�����ݿ�ԭ�е�ͼƬ��Ϣ
		productImgDao.deleteProductImgByProductId(productId);
	}
	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		// ҳ��ת�������ݿ�����룬������dao��ȡ��ָ��ҳ�����Ʒ�б�
		int rowIndex = PageCalculate.claculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		// ����ͬ���Ĳ�ѯ�������ظò�ѯ�����µ���Ʒ����
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
	}

}
