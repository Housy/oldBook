package com.housy.o2o.service;

import com.housy.o2o.entity.PersonInfo;

public interface PersonInfoService {

	/**
	 * 根据用户id获取personInfo信息
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);
	
	
}
