package com.housy.o2o.service;

import java.util.List;

import com.housy.o2o.dto.ProductCategoryExecution;
import com.housy.o2o.entity.ProductCategory;
import com.housy.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * ��ѯĳ�������µ�������Ʒ�����Ϣ
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) 
	 throws ProductCategoryOperationException;
	/**
	 * ��������µ���Ʒ������id��Ϊ��
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
	throws ProductCategoryOperationException;
	
}
