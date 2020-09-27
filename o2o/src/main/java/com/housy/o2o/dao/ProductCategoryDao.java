package com.housy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.housy.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * ����������Ʒ���
	 * @param productCategoryList
	 * @return int Ӱ�������
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
	 * ɾ��ָ����productCategory
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId, @Param("shopId")long shopId);
}
