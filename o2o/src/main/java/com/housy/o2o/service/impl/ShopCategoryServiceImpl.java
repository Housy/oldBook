package com.housy.o2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.housy.o2o.cache.JedisUtil;
import com.housy.o2o.dao.ShopCategoryDao;
import com.housy.o2o.entity.ShopCategory;
import com.housy.o2o.exceptions.ShopCategoryOperationException;
import com.housy.o2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	
	
	private static Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);
	
	@Transactional
	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		//定义redis的key前缀
		String key = SHOPCATEGORYKEY;
		//定义接收对象
		List<ShopCategory> shopCategoryList = null;
		ObjectMapper mapper = new ObjectMapper();
		//拼接处redis的key
		if(shopCategoryCondition == null) {
			//若查询条件为空，则列出所有首页大类，即parentId为空的店铺类别
			key = key + "_allfirstlevel";
		} else if(shopCategoryCondition != null && shopCategoryCondition.getParent() != null
				&& shopCategoryCondition.getParent().getShopCategoryId() != null) {
			//若parentid为非空，则列出该parentId下的所有子类别
			key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
		} else if(shopCategoryCondition != null) {
			//列出所有的子类别，不曾其属于哪个类都列出来
			key = key + "_allsecondlevel";
		}
		//判断key是否存在
		if(!jedisKeys.exists(key)) {
			//若不存在，则从数据库里面取出相应的数据
			shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
			//将相关的实体类集合转换成string，存入redis里面对应的key中
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(shopCategoryList);
			} catch(JsonProcessingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new ShopCategoryOperationException(e.getMessage());
				
			}
			jedisStrings.set(key, jsonString);
		} else {
			//若存在，则直接从redis里面取出相应的数据
			String jsonString = jedisStrings.get(key);
			//指定要将string转换成集合类型
			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(ArrayList.class, ShopCategory.class);
			try {
				//将相关key对应的value里的string转换成对象的实体类集合
				shopCategoryList = mapper.readValue(jsonString, javaType);
			} catch (JsonParseException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new ShopCategoryOperationException(e.getMessage());
			} catch(JsonMappingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new ShopCategoryOperationException(e.getMessage());
			} catch(IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new ShopCategoryOperationException(e.getMessage());
			}
		}
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}
}
