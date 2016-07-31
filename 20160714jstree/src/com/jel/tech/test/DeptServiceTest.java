package com.jel.tech.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jel.tech.service.DeptService;

public class DeptServiceTest {

	private DeptService deptService = null;
	ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	
	@Before
	public void setUp() throws Exception {
		
		deptService = ac.getBean("deptService", DeptService.class);
	}

	@Test
	public void testQueryDeptCount() {
		int deptCount = deptService.queryDeptCount(null);
		System.out.println(deptCount);
	}

}
