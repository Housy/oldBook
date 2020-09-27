package com.housy.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.housy.o2o.BaseTest; 
import com.housy.o2o.entity.ShopCategory;

public class ShopCategoryServiceTest extends BaseTest {
	@Autowired
	ShopCategoryService shopCategoryService;
	
	@Test
	public void testGetShopCategoryList() {
		List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(null);
		System.out.println(shopCategoryList.get(0).getShopCategoryName());
		ShopCategory shopCategoryCondition = new ShopCategory();
		ShopCategory parent = new ShopCategory();
		parent.setShopCategoryId(6L);
		shopCategoryCondition.setParent(parent);
		shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
		System.out.println(shopCategoryList.get(0).getShopCategoryName());
	}
}
