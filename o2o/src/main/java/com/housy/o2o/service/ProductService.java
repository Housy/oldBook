package com.housy.o2o.service;

import java.util.List;

import com.housy.o2o.dto.ImageHolder;
import com.housy.o2o.dto.ProductExecution;
import com.housy.o2o.entity.Product;
import com.housy.o2o.exceptions.ProductOperationException;

public interface ProductService {
	
	/**
	 * ��ѯ��Ʒ�б���ҳ��������������У���Ʒ������Ʒ״̬������Id����Ʒ���
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
	/**
	 * �޸���Ʒ��Ϣ�Լ�ͼƬ����
	 * @param product
	 * @param thumbnail
	 * @param productImgHolder
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail,List<ImageHolder> productImgHolder)
	throws ProductOperationException;
	/**
	 * ͨ����ƷId��ѯ��Ʒ��Ϣ
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);
	
	/**
	 * �����Ʒ��Ϣ�Լ�ͼƬ����
	 * @param product
	 * @param thumbnail
	 * @param thumbnailName
	 * @param productImgList
	 * @param productImgNameList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product,ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws ProductOperationException;
}
