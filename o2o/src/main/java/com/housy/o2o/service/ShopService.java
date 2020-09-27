package com.housy.o2o.service;

import java.io.InputStream;

import com.housy.o2o.dto.ImageHolder;
import com.housy.o2o.dto.ShopExecution;
import com.housy.o2o.entity.Shop;
import com.housy.o2o.exceptions.ShopOperationException;

public interface ShopService {
	/**
	 * ͨ������id��ȡ������Ϣ
	 * 
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	/**
	 * ����shopCondition��ҳ��������
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex,int pageSize);
	/**
	 * ���µ�����Ϣ��������ͼƬ�Ĵ���
	 * @param shop
	 * @param shopImg
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;
	/**
	 * 
	 * @param shop
	 * @param inputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;
}
