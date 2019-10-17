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

import com.mip.software.admin.entity.SsoEntrance;
import com.mip.software.admin.entity.SsoFrontrole;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.service.SsoEntranceService;
import com.mip.software.admin.service.SsoFrontroleService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

/**
 * 前端角色
 */
@Controller
@RequestMapping("/admin/ssoFrontrole")
public class SsoFrontroleController extends BaseController {
	
	public static JsonHelper util = new JsonHelper();
	@Autowired 
	private SsoFrontroleService ssoFrontroleService;
	@Autowired
	private  SsoEntranceService ssoEntranceService;
	/**
	 * 前端角色首页
	 * */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/ssoFrontrole/index";
	}
	
	/**
	 * 加载前端角色列表数据   
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String infoAdertiseList(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		if(!"".equals(t.getBranchshopid())&&t.getBranchshopid()!=null)
		dto.put("branchshopid", t.getBranchshopid());
		List<SsoFrontrole> list=ssoFrontroleService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	/**
	 * 前端角色详情 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/get")
	public String add(HttpServletRequest request,ModelMap model,Long frontroleid) {
		SsoFrontrole ssoFrontrole=null;
		if(frontroleid!=null)
			ssoFrontrole=ssoFrontroleService.findById(frontroleid);
		model.addAttribute("bean",ssoFrontrole);
		return "admin/ssoFrontrole/ssoFrontrole";
	}
	/**
	 * 保存信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void saveSsoFrontrole(HttpServletRequest request,HttpServletResponse response,
			SsoFrontrole ssoFrontrole) {
		try {
			ssoFrontroleService.saveSsoFrontrole(ssoFrontrole, request);
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
	public void deleteSsoFrontrole(HttpServletResponse response,String ids) throws Exception{
		try {
			String[] id=ids.split(",");
			for (String string : id) {
				ssoFrontroleService.del(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	
	
	/**
	 * 配置业务系统入口 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toPzEnter")
	public String toPzEnter(HttpServletRequest request,ModelMap model,Long frontroleid) {
		Map map=new HashMap();
		map.put("frontroleid", frontroleid);
		List<SsoEntrance> enterlist=ssoEntranceService.findSelectedList(map);
		model.addAttribute("enterlist",enterlist);
		model.addAttribute("frontroleid",frontroleid);
		return "admin/ssoFrontrole/ssoEnterForm";
	}
	
	
	/**
	 * 保存前端角色管理业务系统角色信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveFrontEnter")
	public void saveFrontEnter(HttpServletRequest request,HttpServletResponse response,
			Long frontroleid,String entranceid) {
		try {
			ssoFrontroleService.delFrontEnter(frontroleid);
			if(!"".equals(entranceid)&&entranceid!=null){
				String[] str=entranceid.split(",");
				for( int i=0;i<str.length;i++)
				{
					SsoFrontrole frontrole=new SsoFrontrole();
					frontrole.setFrontroleid(frontroleid);
					frontrole.setEntranceid(Long.parseLong(str[i]));
					ssoFrontroleService.saveFrontEnter(frontrole, request);
				}
			}
			
  			System.out.println(frontroleid+"========================"+frontroleid);
			util.toJsonMsg(response, 0, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response, 1, "操作失败");
		}
	}
	
	

	
}
