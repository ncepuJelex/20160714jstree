package com.jel.tech.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jel.tech.dao.DeptDao;
import com.jel.tech.entity.Dept;
import com.jel.tech.service.DeptService;
import com.jel.tech.vo.QueryVo;
import com.jel.tech.vo.Tree;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;
	
	@Override
	public List<Tree> queryDepts() {

		List<Dept> deptList = deptDao.queryDepts();
		
		//辅助工具
		Map<Long, Tree> map = new HashMap<>();
		List<Long> rootDeptIds = new ArrayList<>();
		List<Tree> treeList = new ArrayList<>();
		
		Tree tree = null;
		for(Dept dept : deptList) {
			tree = new Tree();
			tree.setId(dept.getDeptId());
			tree.setText(dept.getDeptName());
			tree.setIcon(dept.getIcon());
			tree.setOrder(dept.getOrder());
			tree.setParentId(dept.getParentId());
			
			map.put(tree.getId(), tree);
			
			if(tree.getParentId() == null) {
				rootDeptIds.add(tree.getId());
			}
			
			treeList.add(tree);
		}
		
		for(Tree tr : treeList) {
			Long parentId = tr.getParentId();
			Tree pTree = map.get(parentId);
			if(pTree != null) {
				pTree.getChildren().add(tr);
			}
		}
		
		List<Tree> list = new ArrayList<>();
		for(Long deptId : rootDeptIds) {
			list.add(map.get(deptId));
		}
		
		return list;
	}
	

	public List<Dept> queryDeptList() {
		return deptDao.queryDepts();
	}


	@Override
	public int queryDeptCount(String keywords) {
		return deptDao.queryDeptCount(keywords);
	}


	@Override
	public List<Dept> queryDeptsByKeywords(QueryVo vo) {
		return deptDao.queryDeptsByKeywords(vo);
	}


	/*@Override
	public List<Dept> queryDeptsByKeywords(String keywords, PageRequest pageable) {
		return deptDao.queryDeptsByKeywords(keywords, pageable);
	}*/
	

}
