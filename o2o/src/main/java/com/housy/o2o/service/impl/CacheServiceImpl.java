package com.housy.o2o.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housy.o2o.cache.JedisUtil;
import com.housy.o2o.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	private JedisUtil.Keys jedisKeys;
	
	@Override
	public void removeFromCache(String keyPrefix) {
		/*
		 * 取出以shopcategory打头的键值信息
		 */
		Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
		for(String key : keySet) {
			//将每一个键值对都删除掉    
			jedisKeys.del(key);
		}
			
	}
}
