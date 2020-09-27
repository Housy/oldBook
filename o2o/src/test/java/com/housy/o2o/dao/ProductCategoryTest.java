package com.housy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.housy.o2o.BaseTest;
import com.housy.o2o.entity.ProductCategory;
/**
 * ���Ʋ��Է�����ִ��
 * ���մ����ж���ķ���˳��ִ�� @FixMethodOrder(MethodSorters.JVM)�����ÿ���
 * ���շ�������˳��ִ�� @FixMethodOrder(MethodSorters.NAME_ASCENDING)
 * ����������ACB��ͷ��
 * 	testAlist
 * 	testBlist
 * @author Lucy
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryTest extends BaseTest {
	@Autowired
	ProductCategoryDao productCategoryDao;
	
	@Ignore
	@Test
	public void testBQueryProductCategoryList() throws Exception {
		long shopId = 1;
		List<ProductCategory> productList = productCategoryDao.queryProductCategoryList(shopId); 
		System.out.println("�õ����Զ�������С��" + productList.size());
	}
	
	@Test
	public void testABathInsertProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("��Ʒ���1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(1L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("��Ʒ���2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(1L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory2);
		productCategoryList.add(productCategory);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}
	
	@Test
	public void testCDeleteProductCategory() throws Exception {
		long shopId = 1;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for (ProductCategory pc : productCategoryList) {
			if ("��Ʒ���1".equals(pc.getProductCategoryName()) || "��Ʒ���2".equals(pc.getProductCategoryName())) {
				int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(),
						shopId);
				assertEquals(1, effectedNum);
			}
		}
	}
	

}
