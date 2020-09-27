package com.housy.o2o.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.housy.o2o.BaseTest;
import com.housy.o2o.entity.HeadLine;

public class HeadLineServiceTest extends BaseTest {
	@Autowired
	HeadLineService  headLineService;
	
	@Test
	public void testGetHeadLineList() {
		try {
			List<HeadLine> headLineList = headLineService.getHeadLineList(new HeadLine());
			System.out.println(headLineList.get(0).getLineName());
			HeadLine headLineCondition = new HeadLine();
			headLineCondition.setEnableStatus(1);
			headLineList = headLineService.getHeadLineList(headLineCondition);
			System.out.println(headLineList.get(0).getLineName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
