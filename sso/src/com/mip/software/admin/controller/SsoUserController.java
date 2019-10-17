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

import com.mip.software.admin.entity.SsoFrontrole;
import com.mip.software.admin.entity.SsoUser;
import com.mip.software.admin.entity.SysStation;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.service.SsoFrontroleService;
import com.mip.software.admin.service.SsoUserService;
import com.mip.software.admin.service.SysStationService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;
/**
 * SSO登录统一账户
 */
@Controller
@RequestMapping("/admin/ssoUser")
public class SsoUserController extends BaseController {
	
public static JsonHelper util = new JsonHelper();
	
	@Autowired
	private  SsoUserService ssoUserService;
	@Autowired 
	private SsoFrontroleService ssoFrontroleService;
	@Autowired
	private  SysStationService sysStationService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/ssoUser/index";
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public String ssoUserList(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		//dto.put("areaCode", "34");
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		if(!"".equals(t.getBranchshopid())&&t.getBranchshopid()!=null)
		dto.put("branchshopid", t.getBranchshopid());
		List<SsoUser> list=ssoUserService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	@RequestMapping("/get")
	public String add(HttpServletRequest request,ModelMap model,Long ssouserid) {
		SsoUser ssoUser=null;
		if(ssouserid!=null)
		{
			ssoUser=ssoUserService.findById(ssouserid);
			
			
		}
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		Map map=new HashMap();
		map.put("branchshopid", t.getBranchshopid());
		map.put("frontrolestatus", 1);
		List<SsoFrontrole> rolelist=ssoFrontroleService.findList(map);
		
		Map mapstat=new HashMap();
		mapstat.put("branchshopid", t.getBranchshopid());
		mapstat.put("stationstatus", 1);
		List<SysStation> stationlist=sysStationService.findList(mapstat);
		model.addAttribute("bean",ssoUser);
		model.addAttribute("rolelist",rolelist);
		model.addAttribute("stationlist",stationlist);
		return "admin/ssoUser/ssoUser";
	}
	
	@RequestMapping("/save")
	public void saveSsoUser(HttpServletRequest request,HttpServletResponse response,
			SsoUser ssoUser) {
		try {
			
			ssoUserService.saveSsoUser(ssoUser, request);
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
	public void deleteSsoUser(HttpServletResponse response,String ids) throws Exception{
		try {
			String[] id=ids.split(",");
			for (String string : id) {
				ssoUserService.del(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}

}
