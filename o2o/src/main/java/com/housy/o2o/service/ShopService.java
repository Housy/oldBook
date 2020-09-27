package com.housy.o2o.service;

import java.io.InputStream;

import com.housy.o2o.dto.ImageHolder;
import com.housy.o2o.dto.ShopExecution;
import com.housy.o2o.entity.Shop;
import com.housy.o2o.exceptions.ShopOperationException;

public interface ShopService {
	/**
	 * 通过店铺id获取店铺信息
	 * 
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	/**
	 * 根据shopCondition分页传入数据
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex,int pageSize);
	/**
	 * 更新店铺信息，包括对图片的处理
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
