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
@RequestMapping("/admin/tbUser")
public class SysUserController extends BaseController{
	
	public static JsonHelper util = new JsonHelper();
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService tbRoleService;
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/tbuser/tblist";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String userList(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		List<SysUser> list=sysUserService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,ModelMap model) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		//提取所有可用角色
		List<SysRole> list=tbRoleService.findAvailableList(dto);
		model.addAttribute("roleList",list);	
		return "admin/tbuser/user";
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
		return "admin/tbuser/user";
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
	 */
	@RequestMapping("/deleteUser")
	public void deleteUser(HttpServletResponse response,String ids,String status) throws Exception{
		try {
			String[] id=ids.split(",");
			Map<String,Object> map=new HashMap<String, Object>();
			for (String string : id) {
				map.put("user_status", status);
				map.put("userid", string);
				sysUserService.updateUserStatus(map);
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	
	/**
	 * 进入修改密码页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/changePsd")
	public String changePsd(HttpServletRequest request,HttpServletResponse response) {
		return "admin/tbuser/changePsd";
	}
	
	/**
	 * 验证输入旧密码是否正确
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/valiOldPsd")
	public void valiOldPsd(HttpServletRequest request,
							HttpServletResponse response,SysUser tuser) throws Exception{
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		try {
			String enterPsd=AESEnDecoder.encrypt(request.getParameter("oldPsd"), "HJ");
			if(t.getUser_pass().equals(enterPsd))
			util.toJsonMsg(response,0,"true");
			else
				util.toJsonMsg(response,0,"false");	
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	
	
	/**
	 * 修改密码
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/savePsd")
	public void savePsd(HttpServletRequest request,
							HttpServletResponse response,SysUser tuser) throws Exception{
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		try {
			String enterPsd=request.getParameter("newPsd");
			SysUser tuserx=sysUserService.findById(t.getUserid());
			tuserx.setUser_pass(AESEnDecoder.encrypt(enterPsd, "HJ"));
			sysUserService.updatePsd(tuserx);
			
				if(request.getSession().getAttribute("loginuser")!=null){
					request.getSession().removeAttribute("loginuser");
				}
			util.toJsonMsg(response,0,"");	
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	
	
	@RequestMapping("/companyindex")
	public String companyindex(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/sysCompanyUser/index";
	}
	
	@RequestMapping("/companylist")
	@ResponseBody
	public String companylist(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		dto.put("distrid", t.getDistrid());
		dto.put("branchshopid", t.getBranchshopid());
		List<SysUser> list=sysUserService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	@RequestMapping("/companyadd")
	public String companyadd(HttpServletRequest request,ModelMap model) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		//提取所有可用角色
		dto.put("remark", 1);
		List<SysRole> list=tbRoleService.findAvailableList(dto);
		model.addAttribute("roleList",list);	
		return "admin/sysCompanyUser/user";
	}
	
	@RequestMapping("/companyedit")
	public String companyedit(HttpServletRequest request,ModelMap model,Long userid) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		
		SysUser sysUser=sysUserService.findById(userid);
		sysUser.setUser_pass(AESEnDecoder.decrypt(sysUser.getUser_pass(), "HJ"));
		model.addAttribute("bean",sysUser);
		
		List<SysUserRole> urlist=tbRoleService.findUserRoleListByUserId(userid);
		model.addAttribute("urlist",urlist);
		//提取所有可用角色
		dto.put("remark", 1);
		List<SysRole> list=tbRoleService.findAvailableList(dto);
		model.addAttribute("roleList",list);	
		return "admin/sysCompanyUser/user";
	}
	
	
	@RequestMapping("/saveCompanyUser")
	public void saveCompanyUser(HttpServletRequest request,HttpServletResponse response,
								SysUser sysUser) {
		try {
			SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
			sysUser.setUser_pass(AESEnDecoder.encrypt(sysUser.getUser_pass(), "HJ"));
			sysUser.setDistrid(t.getDistrid());
			sysUser.setBranchshopid(t.getBranchshopid());
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

	
}
