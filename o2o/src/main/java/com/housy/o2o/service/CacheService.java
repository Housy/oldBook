package com.housy.o2o.service;

public interface CacheService {

	/*
	 * 依据key前缀删除匹配该模式下的所有key-value如传入：shopcategory，则shopcategory_allfirstlevel等
	 * 以shopcategory大头的key_value都会被清空
	 */
	void removeFromCache(String keyPrefix);
}
