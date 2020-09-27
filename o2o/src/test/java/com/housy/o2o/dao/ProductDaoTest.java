package com.housy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;

import com.housy.o2o.BaseTest;
import com.housy.o2o.entity.Product;
import com.housy.o2o.entity.ProductCategory;
import com.housy.o2o.entity.ProductImg;
import com.housy.o2o.entity.Shop;

@FixMethodOrder
public class ProductDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void testAInsertProduct() throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(1L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(33L);
		// ��ʼ��������Ʒʵ������ӽ�shopIdΪ1�ĵ����
		// ͬʱ��Ʒ���IdҲΪ1
		Product product1 = new Product();
		product1.setProductName("����1");
		product1.setProductDesc("����Desc1");
		product1.setImgAddr("test1");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		Product product2 = new Product();
		product2.setProductName("����2");
		product2.setProductDesc("����Desc2");
		product2.setImgAddr("test2");
		product2.setPriority(2);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(pc1);
		Product product3 = new Product();
		product3.setProductName("test3");
		product3.setProductDesc("����Desc3");
		product3.setImgAddr("test3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop1);
		product3.setProductCategory(pc1);
		// �ж�����Ƿ�ɹ�
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product3);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testBQueryProductList() throws Exception {
		Product productCondition = new Product();
		//��ҳ��ѯ
		List<Product> productList = productDao.queryProductList(productCondition, 0,3);
		assertEquals(3, productList.size());
		//��ѯ����Ϊ���Ե���Ʒ����
		int count = productDao.queryProductCount(productCondition);
		assertEquals(4, count);
		//ʹ����Ʒģ����ѯ
		productCondition.setProductName("����");
		productList = productDao.queryProductList(productCondition, 0, 3);
		assertEquals(2, productList.size());
		count = productDao.queryProductCount(productCondition);
		assertEquals(2, count);
	}
	
	@Test
	@Ignore
	public void testCQueryProductByProductId() throws Exception {
		long productId = 65;
		//��ʼ��������Ʒ����ͼʵ����ΪproductIdΪ1����Ʒ�µ�����ͼƬ
		//�������뵽��Ʒ����ͼ��
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("ͼƬ1");
		productImg1.setImgDesc("����ͼƬ1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(productId);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("ͼƬ2");
		productImg2.setImgDesc("����ͼƬ2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(productId);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
		Product product = productDao.queryProductById(productId);
		assertEquals(2, product.getProductImgList().size());
		effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2, effectedNum);
	}
	@Test
	@Ignore
	public void testDUpdateProduct() throws Exception {
		Product product = new Product();
		ProductCategory pCategory = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(1L);
		pCategory.setProductCategoryId(39L);
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductName("���ĸ���Ʒ");
		product.setProductCategory(pCategory);
		int effectedNum = productDao.updateProduct(product);
		assertEquals(1, effectedNum);
	}
	@Test
	public void testEUpdateProductCategoryToNull() {
		int effected = productDao.updateProductCategoryToNull(33L);
		assertEquals(3, effected);
	}
}
