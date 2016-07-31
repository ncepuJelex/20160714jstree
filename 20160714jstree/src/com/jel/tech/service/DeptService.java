package com.jel.tech.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.jel.tech.entity.Dept;
import com.jel.tech.vo.QueryVo;
import com.jel.tech.vo.Tree;

/**
 * 大学部门接口
 * @author Administrator
 *
 */
public interface DeptService {

	public List<Tree> queryDepts();
	public List<Dept> queryDeptList();
//	public List<Dept> queryDeptsByKeywords(String keywords, PageRequest pageable);
	public List<Dept> queryDeptsByKeywords(QueryVo vo);
	public int queryDeptCount(String keywords);
}
