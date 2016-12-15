package com.jel.tech.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jel.tech.entity.Dept;
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
	
	@Test
	public void testPageHelper() {
		List<Dept> queryDeptList = deptService.queryDeptList();
		System.out.println(queryDeptList);
		
		PageHelper.startPage(0, 3);
		PageHelper.orderBy("dept_id desc");
		queryDeptList = deptService.queryDeptList();
		System.out.println(queryDeptList.size());
		System.out.println(queryDeptList);
		System.out.println(queryDeptList.get(0).getDeptName());
		//用PageInfo对结果进行包装
		PageInfo<Dept> page = new PageInfo<Dept>(queryDeptList);
		//测试PageInfo全部属性
		//PageInfo包含了非常全面的分页属性
		assertEquals(1, page.getPageNum());
		assertEquals(3, page.getPageSize());
		assertEquals(1, page.getStartRow());
		assertEquals(3, page.getEndRow());
		assertEquals(8, page.getTotal());
		assertEquals(3, page.getPages());
//		assertEquals(1, page.getFirstPage());
//		assertEquals(8, page.getLastPage());
		assertEquals(true, page.isIsFirstPage());
		assertEquals(false, page.isIsLastPage());
		assertEquals(false, page.isHasPreviousPage());
		assertEquals(true, page.isHasNextPage());
		
		List<Dept> list = page.getList();
		System.out.println(list);
		List<Dept> result = ((Page<Dept>)list).getResult();
		System.out.println(result);
		
	}

}
