package com.mip.software.admin.controller;

import java.util.ArrayList;
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

import com.mip.software.admin.entity.BusinessChangeInfo;
import com.mip.software.admin.entity.BusinessCparameter;
import com.mip.software.admin.entity.BusinessCtrack;
import com.mip.software.admin.entity.BusinessCustomer;
import com.mip.software.admin.entity.SsoUser;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.service.BusinessChangeInfoService;
import com.mip.software.admin.service.BusinessCparameterService;
import com.mip.software.admin.service.BusinessCtrackService;
import com.mip.software.admin.service.BusinessCustomerService;
import com.mip.software.admin.service.SsoUserService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

/**
 * 客户管理
 */
@Controller
@RequestMapping("/admin/businessCustomer")
public class BusinessCustomerController extends BaseController {
	
	public static JsonHelper util = new JsonHelper();
	@Autowired
	private  BusinessCustomerService businessCustomerService;
	@Autowired
	private  SsoUserService ssoUserService;
	@Autowired
	private  BusinessCtrackService businessCtrackService;
	@Autowired
	private  BusinessChangeInfoService businessChangeInfoService;
	@Autowired
	private  BusinessCparameterService businessCparameterService;
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/businessCustomer/index";
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
	
	@RequestMapping("/get")
	public String add(HttpServletRequest request,ModelMap model,Long customerid) {
		BusinessCustomer businessCustomer=null;
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		if(customerid!=null)
		{
			businessCustomer=businessCustomerService.findById(customerid);
			
			
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("branchshopid", t.getBranchshopid());
		map.put("ssouser_status", 1);
		map.put("stationtypes", "1,7");
		List<SsoUser>  frontuserlist=ssoUserService.findList(map);
		
		Map<String,Object> mapyh=new HashMap<String, Object>();
		mapyh.put("branchshopid", t.getBranchshopid());
		mapyh.put("ssouser_status", 1);
		mapyh.put("stationtype", 3);
		List<SsoUser>  yhuserlist=ssoUserService.findList(mapyh);
		
		Map<String,Object> mapyw=new HashMap<String, Object>();
		mapyw.put("branchshopid", t.getBranchshopid());
		mapyw.put("ssouser_status", 1);
		mapyw.put("stationtypes", "1,7");
		List<SsoUser>  ywlist=ssoUserService.findList(mapyw);
		
		model.addAttribute("bean",businessCustomer);
		model.addAttribute("frontuserlist",frontuserlist);
		model.addAttribute("yhuserlist",yhuserlist);
		model.addAttribute("ywlist",ywlist);
		return "admin/businessCustomer/businessCustomer";
	}
	
	@RequestMapping("/save")
	public void saveBusinessCustomer(HttpServletRequest request,HttpServletResponse response,
			BusinessCustomer businessCustomer) {
		try {
			
			businessCustomerService.saveBusinessCustomer(businessCustomer, request);
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
	public void deleteBusinessCustomer(HttpServletResponse response,String ids) throws Exception{
		try {
			String[] id=ids.split(",");
			for (String string : id) {
				businessCustomerService.del(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
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
		model.addAttribute("customerid",customerid);
		return "admin/businessCustomer/trace";
	}
	/**
	 * 保存跟踪记录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveTrace")
	public void saveTrace(HttpServletRequest request,HttpServletResponse response,
			BusinessCtrack businessCtrack) {
		try {
			businessCtrackService.saveBusinessCtrack(businessCtrack, request);
			util.toJsonMsg(response, 0, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response, 1, "操作失败");
		}
	}
	
	/**
	 * 删除跟踪记录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delTrace")
	public void delTrace(HttpServletResponse response,String ids) throws Exception{
		try {
			String[] id=ids.split(",");
			for (String string : id) {
				businessCtrackService.del(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	/**
	 * 获取需要更换记录得客户列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findChangeList")
	@ResponseBody
	public String findChangeList(HttpServletRequest request,ModelMap model) throws Exception {
		List<BusinessCustomer> returnlist=new ArrayList<BusinessCustomer>();
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("branchshopid", t.getBranchshopid());
		map.put("changestatus", 3);
		List<BusinessCustomer> customlist=businessCustomerService.findChangeList(map);
		for(int i=0;i<customlist.size();i++){
			BusinessCustomer customer=customlist.get(i);
			Map<String,Object> mapchange=new HashMap<String, Object>();
			mapchange.put("customerid", customer.getCustomerid());
			mapchange.put("changestatus", 3);
			mapchange.put("changinfotypestr", "1,3,4,5,6,8");
			List<BusinessChangeInfo> changelist=businessChangeInfoService.findList(mapchange);
			customer.setChangelist(changelist);
			returnlist.add(customer);
		}
		return util.ObjecttoJson(returnlist);
	}
	
	
	/**
	 * 获取配货单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findPhList")
	@ResponseBody
	public String findPhList(HttpServletRequest request,ModelMap model) throws Exception {
		List<BusinessCustomer> returnlist=new ArrayList<BusinessCustomer>();
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("branchshopid", t.getBranchshopid());
		map.put("changestatus", 5);
		map.put("nocludeids", 1);
		List<BusinessCustomer> customlist=businessCustomerService.findChangeList(map);
		for(int i=0;i<customlist.size();i++){
			BusinessCustomer customer=customlist.get(i);
			Map<String,Object> mapchange=new HashMap<String, Object>();
			mapchange.put("customerid", customer.getCustomerid());
			mapchange.put("changestatus", 5);
			mapchange.put("changinfotypestr", "1,3,4,5,6,8");
			mapchange.put("branchshopid", t.getBranchshopid());
			mapchange.put("nocludeids", 1);
			List<BusinessChangeInfo> changelist=businessChangeInfoService.findList(mapchange);
			customer.setChangelist(changelist);
			customer.setChangenum(changelist.size());
			returnlist.add(customer);
		}
		return util.ObjecttoJson(returnlist);
	}
	
	
	
	/**
	 * 获取配送单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findPSList")
	@ResponseBody
	public String findPSList(HttpServletRequest request,ModelMap model,Long translateid) throws Exception {
		List<BusinessCustomer> returnlist=new ArrayList<BusinessCustomer>();
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("branchshopid", t.getBranchshopid());
		/*map.put("changestatus", 5);*/
		map.put("translateid", translateid);
		List<BusinessCustomer> customlist=businessCustomerService.findPsList(map);
		for(int i=0;i<customlist.size();i++){
			BusinessCustomer customer=customlist.get(i);
			Map<String,Object> mapchange=new HashMap<String, Object>();
			mapchange.put("customerid", customer.getCustomerid());
			/*mapchange.put("changestatus", 5);*/
			mapchange.put("changinfotypestr", "1,3,4,5,6,8");
			mapchange.put("translateid", translateid);
			List<BusinessChangeInfo> changelist=businessChangeInfoService.findList(mapchange);
			customer.setChangelist(changelist);
			customer.setChangenum(changelist.size());
			returnlist.add(customer);
		}
		return util.ObjecttoJson(returnlist);
	}
	
	
	/**
	 * 进入报价参数记录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bprameter")
	public String bprameter(HttpServletRequest request,ModelMap model,Long customerid) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("customerid", customerid);
		List<BusinessCparameter> parametlist=businessCparameterService.findList(map);
		model.addAttribute("parametlist",parametlist);
		model.addAttribute("customerid",customerid);
		return "admin/businessCustomer/bprameter";
	}

	/**
	 * 保存报价参数记录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveBprameter")
	public void saveBprameter(HttpServletRequest request,HttpServletResponse response,
			BusinessCparameter businessCparameter) {
		try {
			businessCparameterService.saveBusinessCustomer(businessCparameter, request);
			util.toJsonMsg(response, 0, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response, 1, "操作失败");
		}
	}
	
	/**
	 * 获取参数详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getParamerByid")
	@ResponseBody
	public void getParamerByid(HttpServletRequest request,HttpServletResponse response,
			Long cparametid) {
		try {
			BusinessCparameter businessCparameter=businessCparameterService.findById(cparametid);
			util.toAppJsonMsg(response, 0, businessCparameter);
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response, 1, "操作失败");
		}
	}
}
