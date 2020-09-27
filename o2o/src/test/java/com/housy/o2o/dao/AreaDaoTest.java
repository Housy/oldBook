package com.housy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.housy.o2o.BaseTest;
import com.housy.o2o.entity.Area;

public class AreaDaoTest extends BaseTest {
	@Autowired
	private AreaDao areaDao;
	
	//写测试方法
	@Test
	public void testQueryArea() {
		List<Area> areaList = areaDao.queryArea();
		
		//判断list取出的数目的两个的话，那么测试就成功
		assertEquals(2, areaList.size());
	}
}
