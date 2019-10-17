package com.mip.software.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mip.software.admin.entity.BusinessCcontract;
import com.mip.software.admin.entity.BusinessCtrack;
import com.mip.software.admin.entity.BusinessCustomer;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.service.BusinessCcontractService;
import com.mip.software.admin.service.BusinessCtrackService;
import com.mip.software.admin.service.BusinessCustomerService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

/**
 * 客户管理
 */
@Controller
@RequestMapping("/admin/customerQuery")
public class BusinessCustomerQueryController extends BaseController {
	
	public static JsonHelper util = new JsonHelper();
	@Autowired
	private  BusinessCustomerService businessCustomerService;
	@Autowired
	private  BusinessCtrackService businessCtrackService;
	@Autowired
	private  BusinessCcontractService businessCcontractService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/customerQuery/index";
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		//dto.put("areaCode", "34");
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		if(!"".equals(t.getBranchshopid())&&t.getBranchshopid()!=null)
		dto.put("branchshopid", t.getBranchshopid());
		List<BusinessCustomer> list=businessCustomerService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	/**
	 * 进入跟踪记录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/trace")
	public String trace(HttpServletRequest request,ModelMap model,Long customerid) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("customerid", customerid);
		List<BusinessCtrack> tracklist=businessCtrackService.findList(map);
		model.addAttribute("tracklist",tracklist);
		return "admin/customerQuery/trace";
	}
	
	/**
	 * 进入合同记录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/htxx")
	public String htxx(HttpServletRequest request,ModelMap model,Long customerid) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("customerid", customerid);
		List<BusinessCcontract> htxxlist=businessCcontractService.findList(map);
		model.addAttribute("htxxlist",htxxlist);
		return "admin/customerQuery/htxx";
	}
	
	

}
