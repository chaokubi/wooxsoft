package com.mip.software.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mip.software.admin.entity.BusinessCcontract;
import com.mip.software.admin.entity.BusinessCustomer;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.service.BusinessCcontractService;
import com.mip.software.admin.service.BusinessCustomerService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

/*
 * 合同管理
 * */
@Controller
@RequestMapping("/admin/contract")
public class BusinessCcontractController extends BaseController {
	 public static JsonHelper util = new JsonHelper();
	 @Autowired
	 private  BusinessCcontractService businessCcontractService;
	 @Autowired
	 private  BusinessCustomerService businessCustomerService;  
	 
	   @RequestMapping("/index")
		public String index(HttpServletRequest request,ModelMap model) throws Exception {
			return "admin/contract/index";
		}
	   
	   @RequestMapping("/list")
		@ResponseBody
		public String list(HttpServletRequest request) {
			Dto dto=WebUtils.getPraramsAsDto(request);
			SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
			dto.put("branchshopid", t.getBranchshopid());
			List<BusinessCcontract> list=businessCcontractService.findListPage(dto);
			return util.ObjecttoPageJson(list,dto);
		}
		
		@RequestMapping("/get")
		public String add(HttpServletRequest request,ModelMap model,Long ccontractid) {
			BusinessCcontract businessCcontract=null;
			SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
			if(ccontractid!=null)
			{
				businessCcontract=businessCcontractService.findById(ccontractid);
			}
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("branchshopid", t.getBranchshopid());
			List<BusinessCustomer> customerlist=businessCustomerService.findList(map);
			model.addAttribute("bean",businessCcontract);
			model.addAttribute("customerlist",customerlist);
			return "admin/contract/contract";
		}
		
		@RequestMapping("/save")
		public void save(HttpServletRequest request,HttpServletResponse response,
				BusinessCcontract businessCcontract) {
			try {
				businessCcontractService.saveBusinessCcontract(businessCcontract, request);
				util.toJsonMsg(response, 0, "操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				util.toJsonMsg(response, 1, "操作失败");
			}
		}
		
		/**
		 * 删除
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/del")
		public void delete(HttpServletResponse response,String ids) throws Exception{
			try {
				String[] id=ids.split(",");
				for (String string : id) {
					businessCcontractService.del(Long.parseLong(string));
				}
				util.toJsonMsg(response,0,"");
			} catch (Exception e) {
				e.printStackTrace();
				util.toJsonMsg(response,1,"");
			}
		}   

}
