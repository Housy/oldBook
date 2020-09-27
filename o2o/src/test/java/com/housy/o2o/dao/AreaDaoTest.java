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
	
	//д���Է���
	@Test
	public void testQueryArea() {
		List<Area> areaList = areaDao.queryArea();
		
		//�ж�listȡ������Ŀ�������Ļ�����ô���Ծͳɹ�
		assertEquals(2, areaList.size());
	}
}
