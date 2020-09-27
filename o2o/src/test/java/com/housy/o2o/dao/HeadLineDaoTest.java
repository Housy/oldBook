package com.housy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.housy.o2o.BaseTest;
import com.housy.o2o.entity.HeadLine;

public class HeadLineDaoTest extends BaseTest {  
	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	public void testQueryArea() {
		List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
		assertEquals(1, headLineList.size());
	}
}
