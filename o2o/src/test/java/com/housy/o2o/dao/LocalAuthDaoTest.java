package com.housy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.tomcat.jni.Local;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import com.housy.o2o.BaseTest;
import com.housy.o2o.entity.LocalAuth;
import com.housy.o2o.entity.PersonInfo;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest extends BaseTest {
	@Autowired
	private LocalAuthDao localAuthDao;
	private static final String username = "testusername";
	private static final String password = "testpassword";
	

	@Test
//	@Ignore
	public void testAInsertLocalAuth() throws Exception {
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setPassword(password);
		localAuth.setUsername(username);
		localAuth.setCreateTime(new Date());
		int effectedNum = localAuthDao.insertLocalAuth(localAuth);
		assertEquals(1, effectedNum);
	}
	@Test
	@Ignore
	public void testBQueryLocalByUserId() throws Exception {
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
		assertEquals("测试", localAuth.getPersonInfo().getName());
	} 
	@Test
	@Ignore
	public void testCQueryLocalByUserNameAndPwd() throws Exception {
		LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username, password);
		assertEquals("测试", localAuth.getPersonInfo().getName());
	}
	@Test
	public void testDUpdateLocalAuth() throws Exception {
		Date now = new Date();
		int effectedNum = localAuthDao.updateLocalAuth(1L, username, password, 
			password + "new111", now);
		assertEquals(1, effectedNum);
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
		System.out.println(localAuth.getPassword());
	}
}
