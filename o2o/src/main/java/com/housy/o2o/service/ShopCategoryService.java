package com.housy.o2o.service;

import java.util.List;

import com.housy.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	public static final String SHOPCATEGORYKEY = "shopcategory";
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
