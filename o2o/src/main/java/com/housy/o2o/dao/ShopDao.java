package com.housy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.housy.o2o.entity.ProductCategory;
import com.housy.o2o.entity.Shop;

public interface ShopDao {
	
	/**
	 * ��ҳ��ѯ���̣�������������У���������ģ����������״̬�������������Id��owner
	 * @Param shopCondition ��ѯ������
	 * @Param rowIndex �ӵڼ��п�ʼȡ����
	 * @Param pageSize ���ص�����
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,
			@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);
	/**
	 * ����queryShopList����
	 * 
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 * ͨ��id��ѯ����
	 * @param shop
	 * @return 
	 */
	Shop queryByShopId(long shopId);
	/**
	 * ��������
	 * @param shop
	 * @return 
	 */
	int insertShop(Shop shop);
	/**
	 * ���µ�����Ϣ
	 */
	int updateShop(Shop shop);
}
