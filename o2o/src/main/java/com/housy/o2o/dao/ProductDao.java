package com.housy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.housy.o2o.entity.Product;

public interface ProductDao {
	/**
	 * ɾ����Ʒ���֮ǰ������Ʒ���ID��Ϊ��
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);
	/**
	 * ������Ʒ��Ϣ
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	/**
	 * ͨ��productId��ѯΨһ����Ʒ��Ϣ
	 * @param productId
	 * @return
	 */
	Product queryProductById(long productId);
	/**
	 * ��ѯ��Ʒ�б���ҳ��������������У���Ʒ������Ʒ״̬������Id����Ʒ���
	 * @param productCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition")Product productCondition,
			@Param("rowIndex")int rowIndex,@Param("pageSize") int pageSize);
	/**
	 * ��ѯ��Ӧ��Ʒ������
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition")Product productCondition);
	/**
	 * ������Ʒ
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
}
