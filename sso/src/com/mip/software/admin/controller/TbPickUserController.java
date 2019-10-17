package com.mip.software.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mip.software.admin.entity.SysRole;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.entity.SysUserRole;
import com.mip.software.admin.service.SysRoleService;
import com.mip.software.admin.service.SysUserService;
import com.mip.software.common.util.AESEnDecoder;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;




@Controller
@RequestMapping("/admin/tbpickUser")
public class TbPickUserController extends BaseController{
	
	public static JsonHelper util = new JsonHelper();
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService tbRoleService;
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/tbpickuser/tblist";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String userList(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		dto.put("usertype", "1");
		List<SysUser> list=sysUserService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,ModelMap model) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		//提取所有可用角色
		List<SysRole> list=tbRoleService.findAvailableList(dto);
		model.addAttribute("roleList",list);	
		return "admin/tbpickuser/user";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,ModelMap model,Long userid) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		
		SysUser sysUser=sysUserService.findById(userid);
		sysUser.setUser_pass(AESEnDecoder.decrypt(sysUser.getUser_pass(), "HJ"));
		model.addAttribute("bean",sysUser);
		
		List<SysUserRole> urlist=tbRoleService.findUserRoleListByUserId(userid);
		model.addAttribute("urlist",urlist);
		//提取所有可用角色
		List<SysRole> list=tbRoleService.findAvailableList(dto);
		model.addAttribute("roleList",list);	
		return "admin/tbpickuser/user";
	}
	
	
	@RequestMapping("/addUser")
	public void addUser(HttpServletRequest request,HttpServletResponse response,
								SysUser sysUser) {
		try {
			sysUser.setUser_pass(AESEnDecoder.encrypt(sysUser.getUser_pass(), "HJ"));
			if(null!=sysUser.getUserid()){
				sysUserService.update(sysUser);
			}else{
				sysUserService.save(sysUser);
			}
			util.toJsonMsg(response, 0, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response, 1, "操作失败");
		}
	}

	
	/**
	 * 验证用户是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping("/userExist")
	public void userExist(HttpServletRequest request,HttpServletResponse response,Integer flag){
		Integer i=sysUserService.userLoginIsExist(request.getParameter("username"));
		if(flag==1){
			if(i>0){
				util.writer(response, "false");
			}else {
				util.writer(response, "true");
			}
		}else {
			if(i==1){
				util.writer(response, "true");
			}else {
				if(i==0){
					util.writer(response, "true");
				}else {
					util.writer(response, "false");
				}
			}
		}
	}
	
	
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping("/deleteUser")
	public void deleteUser(HttpServletResponse response,String ids) throws Exception{
		try {
			String[] id=ids.split(",");
			for (String string : id) {
				//sysUserService.updateDelStatus(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}*/
	
}
