

package com.housy.o2o.service;

import static org.junit.Assert.assertEquals;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;

import com.housy.o2o.BaseTest;
import com.housy.o2o.dto.ImageHolder;
import com.housy.o2o.dto.ProductCategoryExecution;
import com.housy.o2o.dto.ProductExecution;
import com.housy.o2o.entity.Product;
import com.housy.o2o.entity.ProductCategory;
import com.housy.o2o.entity.Shop;
import com.housy.o2o.enums.ProductCategoryStateEnum;
import com.housy.o2o.exceptions.ShopOperationException;

public class ProductServiceTest extends BaseTest {
	@Autowired 
	ProductService productService;
	
	@Test
	@Ignore
	public void testAddProduct() throws ShopOperationException, FileNotFoundException {
		//����shopIdΪ1��productCategoryIdΪ33����Ʒʵ���������Ա����Ĭ�ϸ�ֵ
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(33L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("����1");
		product.setProductDesc("����1");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductCategoryStateEnum.SUCCESS.getState());
		//��������ͼ�ļ���
		File thumbnailFile = new File("D:/images/o2o/test.jpg");
		InputStream iStream = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), iStream);
		//����������Ʒ����ͼ�ļ�������������ӵ�����ͼ�б���
		File productImg1 = new File("D:/images/o2o/test.jpg");
		InputStream iStream1 = new FileInputStream(productImg1);
		File productImg2 = new File("D:/images/o2o/logo.jpg");
		InputStream iStream2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), iStream1));
		productImgList.add(new ImageHolder(productImg2.getName(), iStream2));
		//�����Ʒ����֤
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		assertEquals(ProductCategoryStateEnum.SUCCESS.getState(), pe.getState());
	}
	@Test
	public void testModifyProduct() throws ShopOperationException,FileNotFoundException {
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(39L);
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("��ʽ��Ʒ");
		product.setPriority(5);
		product.setProductDesc("��ʽ��Ʒ");
		//��������ͼ�ļ���
		File thumbnailFile = new File("D:/images/o2o/zhifu.jpg");
		InputStream iStream = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), iStream);
		//����������Ʒ����ͼ�ļ�������������ӵ�����ͼ�б���
		File productImg1 = new File("D:/images/o2o/test.jpg");
		InputStream iStream1 = new FileInputStream(productImg1);
		File productImg2 = new File("D:/images/o2o/zhifu.jpg");
		InputStream iStream2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), iStream1));
		productImgList.add(new ImageHolder(productImg2.getName(), iStream2));
		//�����Ʒ����֤
		ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
		assertEquals(ProductCategoryStateEnum.SUCCESS.getState(), pe.getState());
	}
}
