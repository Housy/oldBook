package com.housy.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housy.o2o.dao.ProductCategoryDao;
import com.housy.o2o.dao.ProductDao;
import com.housy.o2o.dto.ProductCategoryExecution;
import com.housy.o2o.entity.ProductCategory;
import com.housy.o2o.enums.ProductCategoryStateEnum;
import com.housy.o2o.exceptions.ProductCategoryOperationException;
import com.housy.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired 
	private ProductDao productDao;
	
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}
	
	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId,
			long shopId) {
		try {
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if(effectedNum < 0) {
				throw new RuntimeException("��Ʒ������ʧ��");
			}
		} catch (Exception e) {
			throw new RuntimeException("deleteProductCategory error:" + e.getMessage());
		}
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectedNum <= 0) {
				throw new ProductCategoryOperationException("��Ʒ���ɾ��ʧ�ܣ�");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch(Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) 
	throws ProductCategoryOperationException{
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectedNum <= 0) {
					throw new ProductCategoryOperationException("������𴴽�ʧ��");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
				
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}
	

	
}
