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
import com.housy.o2o.dao.AreaDao;
import com.housy.o2o.entity.Area;
import com.housy.o2o.exceptions.AreaOperationException;
import com.housy.o2o.service.AreaService;


/**
 * ����springIOC AreaServiceImpl����Ҫ�йܵģ������������AreaService
 * springIOC�ᶯ̬�Ľ�AreaServiceImplע�뵽�ýӿ�����ȥ
 * @author Lucy
 *
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Autowired 
	private AreaDao areaDao;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	

	private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
	
	@Transactional
	@Override
	public List<Area> getAreaList() {
		String key = AREALISTKEY;
		List<Area> areaList = null;
		ObjectMapper mapper = new ObjectMapper();
		if(!jedisKeys.exists(key)) {
			areaList = areaDao.queryArea();
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(areaList);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new AreaOperationException(e.getMessage());
			}
			jedisStrings.set(key, jsonString);
		} else {
			String jsonString = jedisStrings.get(key);
			//第一参数是要转换的对象 第二个是要转换成的类型对象
			JavaType javaType = mapper.getTypeFactory().constructParametricType(
					ArrayList.class, Area.class);
			//将string的值转换成相关的对象
			try {
				areaList = mapper.readValue(jsonString, javaType);
			} catch (JsonParseException e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new AreaOperationException(e.getMessage());
			} catch (JsonMappingException e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new AreaOperationException(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new AreaOperationException(e.getMessage());
			}
		}
		return areaList;
	}
	
}
