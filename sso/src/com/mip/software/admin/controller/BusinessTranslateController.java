package com.mip.software.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mip.software.admin.entity.BusinessChangeInfo;
import com.mip.software.admin.entity.BusinessCustomer;
import com.mip.software.admin.entity.BusinessTranslate;
import com.mip.software.admin.entity.SsoUser;
import com.mip.software.admin.entity.SysCar;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.service.BusinessChangeInfoService;
import com.mip.software.admin.service.BusinessCustomerService;
import com.mip.software.admin.service.BusinessTranslateService;
import com.mip.software.admin.service.SsoUserService;
import com.mip.software.admin.service.SysCarService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

/*
 * 配送管理
 * */
@Controller
@RequestMapping("/admin/translate")
public class BusinessTranslateController extends BaseController {
	
	public static JsonHelper util = new JsonHelper();
	@Autowired
	private  BusinessTranslateService businessTranslateService;
	@Autowired
	private  SysCarService sysCarService;
	@Autowired
	private  SsoUserService ssoUserService;
	@Autowired
	private  BusinessCustomerService businessCustomerService;
	@Autowired
	private  BusinessChangeInfoService businessChangeInfoService;
	
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/translate/index";
	}
   
   @RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		dto.put("branchshopid", t.getBranchshopid());
		List<BusinessTranslate> list=businessTranslateService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request,ModelMap model,Long translateid) {
		BusinessTranslate businessTranslate=null;
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		if(translateid!=null)
		{
			businessTranslate=businessTranslateService.findById(translateid);
		}
		//获取企业车辆信息
		Map<String,Object> mapcar=new HashMap<String, Object>();
		mapcar.put("carstatus", 1);
		mapcar.put("branchshopid", t.getBranchshopid());
		List<SysCar> carlist=sysCarService.findList(mapcar);
		//获取司机信息
		Map<String,Object> mapperson=new HashMap<String, Object>();
		mapperson.put("ssouser_status", 1);
		mapperson.put("branchshopid", t.getBranchshopid());
		List<SsoUser> userlist=ssoUserService.findList(mapperson);
		//获取人员信息
		
		
		model.addAttribute("bean",businessTranslate);
		model.addAttribute("carlist",carlist);
		model.addAttribute("userlist",userlist);
		return "admin/translate/translate";
	}
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response,
			BusinessTranslate businessTranslate) {
		try {
			businessTranslateService.saveBusinessTranslate(businessTranslate, request);
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
				businessTranslateService.del(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}   
	
	/**
	 * 跳转到tree页面   
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tree")
	public String tree(HttpServletRequest request,ModelMap model) {
		String translateid=request.getParameter("translateid");
		model.addAttribute("translateid",translateid);
		return "admin/translate/treeSelect";
	}
	
	
	/**
	 * 选择套餐产品树形结构，做成复选框形式
	 * */
	@RequestMapping("/treeSelect")
	public void treeSelect(HttpServletRequest request, HttpServletResponse response,Long translateid) throws ServletException, IOException{
		List<BusinessCustomer> list=new ArrayList<BusinessCustomer>();
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		BusinessTranslate businessTranslate=businessTranslateService.findById(translateid);
		Map<String,Object> map=new HashMap<String, Object>();
		
		map.put("branchshopid", t.getBranchshopid());
		map.put("changestatus", 5);
		list=businessCustomerService.findChangeList(map);
		List<HashMap<String,Object>> listtree = new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<list.size();i++)
		{
			HashMap<String,Object> hm = new HashMap<String,Object>(); 
			BusinessCustomer businessCustomer=list.get(i);
			if(!"".equals(businessTranslate.getChanginfoids())&&businessTranslate.getChanginfoids()!=null){
				Map<String,Object> mapselect=new HashMap<String, Object>();
				mapselect.put("changeids", businessTranslate.getChanginfoids());
				mapselect.put("customerid", businessCustomer.getCustomerid());
				int count=businessCustomerService.getSelectCountOf(mapselect);
				if(count>0) hm.put("checked", true);	
			}
			hm.put("id", businessCustomer.getCustomerid()+"_1");//id属性  ，数据传递  
			hm.put("name", businessCustomer.getCustomername()); //name属性，显示节点名称  
	        hm.put("pId", 0);
			hm.put("isParent", true);
            listtree.add(hm);
		}
		
		/*
		 *  对上述子节点的添加产品进去
		 * */
		for(int i=0;i<list.size();i++)
		{
			BusinessCustomer customer=list.get(i);
			Map<String,Object> mapchange=new HashMap<String, Object>();
			mapchange.put("customerid", customer.getCustomerid());
			mapchange.put("changestatus", 5);
			mapchange.put("changinfotypestr", "1,3,4,5,6,8");
			mapchange.put("translateid", translateid);
			List<BusinessChangeInfo> changelist=businessChangeInfoService.findIsSelectList(mapchange);
			for(int j=0;j<changelist.size();j++){
				BusinessChangeInfo changeinfo=changelist.get(j);
				HashMap<String,Object> hm = new HashMap<String,Object>(); 
				hm.put("id", changeinfo.getChanginfoid()+"_2");//id属性  ，数据传递  
				hm.put("name", changeinfo.getNewproductmodel()+"【"+changeinfo.getNewposition()+"】"); //name属性，显示节点名称  
				hm.put("pId", customer.getCustomerid()+"_1");
    			hm.put("isParent", false);
    			if(!"".equals(businessTranslate.getChanginfoids())&&businessTranslate.getChanginfoids()!=null&&businessTranslate.getChanginfoids().contains(changeinfo.getChanginfoid()+""))
    				hm.put("checked", true);	
    			if(changeinfo.getIsselect()==1)
    				hm.put("font", 1);
	            listtree.add(hm);
			}
		}
		
		JSONArray arr = new JSONArray(listtree);
		response.getWriter().write(arr.toString());
	}

	@RequestMapping("/translateReport")
	public String typeReport(HttpServletRequest request,ModelMap model) throws Exception {
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		model.addAttribute("companyname", t.getBranchshopname());
		String translateid=request.getParameter("translateid");
		model.addAttribute("translateid", translateid);
		return "admin/translate/translateReport";
	}
}
