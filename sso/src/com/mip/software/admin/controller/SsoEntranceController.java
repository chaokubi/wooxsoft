package com.mip.software.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mip.software.admin.entity.SsoEntrance;
import com.mip.software.admin.service.SsoEntranceService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

/**
 * 入口功能
 */
@Controller
@RequestMapping("/admin/ssoEntrance")
public class SsoEntranceController extends BaseController {
	
	public static JsonHelper util = new JsonHelper();
	@Autowired 
	private SsoEntranceService ssoEntranceService;
	
	/**
	 * 入口功能首页
	 * */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>(); 
		map.put("appstatus", 1);
		return "admin/ssoEntrance/index";
	}
	
	/**
	 * 加载入口功能列表数据   
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String ssoAppList(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		List<SsoEntrance> list=ssoEntranceService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	/**
	 * 入口功能详情 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/get")
	public String add(HttpServletRequest request,ModelMap model,Long entranceid) {
		SsoEntrance ssoEntrance=null;
		if(entranceid!=null)
			ssoEntrance=ssoEntranceService.findById(entranceid);
		HashMap<String,Object> map = new HashMap<String,Object>(); 
		map.put("appstatus", 1);
		model.addAttribute("bean",ssoEntrance);
		return "admin/ssoEntrance/ssoEntrance";
	}
	/**
	 * 保存信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void saveSsoEntrance(HttpServletRequest request,HttpServletResponse response,
			SsoEntrance ssoEntrance) {
		try {
			ssoEntranceService.saveSsoEntrance(ssoEntrance, request);
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
	public void deleteSsoEntrance(HttpServletResponse response,String ids) throws Exception{
		try {
			String[] id=ids.split(",");
			for (String string : id) {
				ssoEntranceService.del(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}

}
