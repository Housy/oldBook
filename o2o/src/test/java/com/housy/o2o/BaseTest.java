package com.housy.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * ����spring��junit���ϣ�junit����ʱ����springIOC����
 */

//����spring��ʲô���ܵ�Ԫ����
@RunWith(SpringJUnit4ClassRunner.class)
//����junit spring�����ļ���λ��
@ContextConfiguration({"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml",
	"classpath:spring/spring-redis.xml"})
public class BaseTest {

}
