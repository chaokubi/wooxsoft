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
import com.mip.software.admin.entity.PrintType;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.service.BusinessChangeInfoService;
import com.mip.software.common.util.JsonHelper;

@Controller
@RequestMapping("/admin/changeinfo")
public class BusinessChangeInfoController extends BaseController {
	
	public static JsonHelper util = new JsonHelper();
	@Autowired
	private  BusinessChangeInfoService businessChangeInfoService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/changeinfo/index";
	}
	
	
	@RequestMapping("/getChangeList")
	@ResponseBody
	public String getChangeList(HttpServletRequest request,ModelMap model,Long customerid) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("customerid", customerid);
		map.put("changestatus", 3);
		map.put("changinfotypestr", "1,3,4,5,6,8");
		List<BusinessChangeInfo> changelist=businessChangeInfoService.findList(map);
		return util.ObjecttoJson(changelist);
	}
	
	
	/**
	 * 变更状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatestatus")
	public void updatestatus(HttpServletRequest request,HttpServletResponse response,
			BusinessChangeInfo businessChangeInfo) {
		try {
			businessChangeInfoService.saveBusinessChangeInfo(businessChangeInfo, request);
			util.toJsonMsg(response, 0, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response, 1, "操作失败");
		}
	}
	
	@RequestMapping("/customReport")
	public String customReport(HttpServletRequest request,ModelMap model) throws Exception {
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		model.addAttribute("companyname", t.getBranchshopname());
		return "admin/changeinfo/compprint";
	}
	
	
	/**
	 * 获取归类配货单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findTypeList")
	@ResponseBody
	public String findPhList(HttpServletRequest request,ModelMap model) throws Exception {
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		List<PrintType> typelist=new ArrayList<PrintType>();
		PrintType hh=new PrintType();
		//获取花卉调整单
		Map<String,Object> mapchange=new HashMap<String, Object>();
		mapchange.put("branchshopid", t.getBranchshopid());
		mapchange.put("changestatus", 5);
		mapchange.put("tznr", "1,3");
		mapchange.put("changinfotypestr", "1,3,4,6,8");
		mapchange.put("nocludeids", 1);
		List<BusinessChangeInfo> changelist=businessChangeInfoService.findtypeHHList(mapchange);
		hh.setTypename("花卉");
		hh.setChangelist(changelist);
		hh.setTypeid(1);
		hh.setChangenum(changelist.size());
		typelist.add(hh);
		
		PrintType pq=new PrintType();
		//获取盆器调整单
		Map<String,Object> mappq=new HashMap<String, Object>();
		mappq.put("branchshopid", t.getBranchshopid());
		mappq.put("changestatus", 5);
		mappq.put("tznr", "2,3");
		mappq.put("changinfotypestr", "1,3,4,6,8");
		mappq.put("nocludeids", 1);
		List<BusinessChangeInfo> pqlist=businessChangeInfoService.findtypePqList(mappq);
		pq.setTypename("盆器");
		pq.setChangelist(pqlist);
		pq.setTypeid(2);
		pq.setChangenum(pqlist.size());
		typelist.add(pq);
		return util.ObjecttoJson(typelist);
	}
	
	@RequestMapping("/typeReport")
	public String typeReport(HttpServletRequest request,ModelMap model) throws Exception {
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		model.addAttribute("companyname", t.getBranchshopname());
		return "admin/changeinfo/typeReport";
	}
	
}
