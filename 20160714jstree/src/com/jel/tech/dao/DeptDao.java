package com.jel.tech.dao;

import java.util.List;

import com.jel.tech.entity.Dept;
import com.jel.tech.vo.QueryVo;

public interface DeptDao {

	public List<Dept> queryDepts();
//	public List<Dept> queryDeptsByKeywords(String keywords, PageRequest pageable);
	public List<Dept> queryDeptsByKeywords(QueryVo vo);
	public int queryDeptCount(String keywords);
	
}
