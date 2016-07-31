package com.jel.tech.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jel.tech.entity.Dept;
import com.jel.tech.message.ResponseMessage;
import com.jel.tech.message.ResponseStatus;
import com.jel.tech.service.DeptService;
import com.jel.tech.vo.Tree;

@Controller
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	private DeptService deptService;
	
	@ResponseBody
	@RequestMapping("/jstree.do")
	public ResponseMessage<List<Tree>> jstree() {
		
		ResponseMessage<List<Tree>> message = new ResponseMessage<>();
		message.setMsg("请求成功！");
		message.setStatus(ResponseStatus.STATUS_OK);
		
		List<Tree> deptTreeList = deptService.queryDepts();
		
		message.setOthers(deptTreeList);
		
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/datatable.do")
	public ResponseMessage<List<Dept>> datatable() {
		
		ResponseMessage<List<Dept>> message = new ResponseMessage<>();
		message.setMsg("请求成功！");
		message.setStatus(ResponseStatus.STATUS_OK);
		
		List<Dept> queryDeptList = deptService.queryDeptList();
		
		message.setOthers(queryDeptList);
		
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/datatable2.do")
	public ResponseMessage<String> datatable2() {
		
		ResponseMessage<String> message = new ResponseMessage<>();
		message.setMsg("请求成功！");
		message.setStatus(ResponseStatus.STATUS_OK);
		
		List<Dept> queryDeptList = deptService.queryDeptList();
		
		message.setOthers(JSONArray.fromObject(queryDeptList).toString());
		System.out.println(JSONArray.fromObject(queryDeptList).toString());
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/datatable3.do")
	public String tableDemoAjax(@RequestParam String aoData) {

		JSONArray jsonarray = JSONArray.fromObject(aoData);

		String sEcho = null;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数

		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho"))
				sEcho = obj.get("value").toString();

			if (obj.get("name").equals("iDisplayStart"))
				iDisplayStart = obj.getInt("value");

			if (obj.get("name").equals("iDisplayLength"))
				iDisplayLength = obj.getInt("value");
		}

		// 生成20条测试数据
		/*List<String[]> lst = new ArrayList<String[]>();
		for (int i = 0; i < 20; i++) {
			String[] d = { "co1_data" + i, "col2_data" + i };
			lst.add(d);
		}*/
		
		List<Dept> queryDeptList = deptService.queryDeptList();
		
		JSONObject getObj = new JSONObject();
		getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
		getObj.put("iTotalRecords", queryDeptList.size());//实际的行数
		getObj.put("iTotalDisplayRecords", queryDeptList.size());//显示的行数,这个要和上面写的一样
		
		getObj.put("aaData", queryDeptList.subList(iDisplayStart,iDisplayStart + iDisplayLength));//要以JSON格式返回
		return getObj.toString();
	}
	
	@RequestMapping(value="/upload.do", method=RequestMethod.POST)
	public String upload(HttpServletRequest request,@RequestParam CommonsMultipartFile [] files) throws Exception {
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		
		for(int i=0; i<files.length; i++) {
			files[i].transferTo(new File(rootDirectory + System.currentTimeMillis()+files[i].getName() + ".jpg"));
		}
		return "index";
	}
}
