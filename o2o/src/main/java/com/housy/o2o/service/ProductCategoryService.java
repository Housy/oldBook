package com.housy.o2o.service;

import java.util.List;

import com.housy.o2o.dto.ProductCategoryExecution;
import com.housy.o2o.entity.ProductCategory;
import com.housy.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * 查询某个店铺下的所有商品类别信息
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) 
	 throws ProductCategoryOperationException;
	/**
	 * 将此类别下的商品里的类别id置为空
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
	throws ProductCategoryOperationException;
	
}
