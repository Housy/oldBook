package com.housy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.housy.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 批量新增商品类别
	 * @param productCategoryList
	 * @return int 影响的行数
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	/**
	 * 
	 * @param productCategoryCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	
	/**
	 * 删除指定的productCategory
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId, @Param("shopId")long shopId);
}
