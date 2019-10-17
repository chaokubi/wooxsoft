package com.mip.software.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.entity.Tblanguage;
import com.mip.software.admin.service.TbLanguageService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

/**
 * 语种管理
 * @author mip
 * 2016 Mar 15, 2016 1:52:28 AM
 */
@Controller
@RequestMapping("/admin/lang")
public class TbLanguageController extends BaseController{
	
	public static JsonHelper util = new JsonHelper();
	@Autowired
	private TbLanguageService tbLanguageService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) {
		return "admin/lang/tblist";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String userList(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		List<Tblanguage> list=tbLanguageService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	@RequestMapping("/langInfo")
	public void add(HttpServletRequest request,HttpServletResponse response,
			Long id) {
		Tblanguage tbNetpoint=tbLanguageService.findById(id);
		util.toAppJsonMsg(response, 0,tbNetpoint);
	}
	
	@RequestMapping("/addLang")
	public void addNetpoint(HttpServletRequest request,HttpServletResponse response,
							Tblanguage tbNetpoint) {
		try {
			SysUser tbUser=this.getSysUserSession(request);
			tbNetpoint.setUserid(tbUser.getUserid());
			tbLanguageService.saveOrUpdate(tbNetpoint);
			util.toJsonMsg(response, 0, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response, 1, "操作失败");
		}
	}
	
	/**
	 * 验证名称是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping("/langExists")
	public void langExists(HttpServletRequest request,HttpServletResponse response,
							Integer flag){
		String oldname=request.getParameter("oldname");
		String name=request.getParameter("name");
		Integer i=tbLanguageService.languageIsExist(name);
		if(flag==1){
			if(i>0){
				util.writer(response, "false");
			}else {
				util.writer(response, "true");
			}
		}else {
			if(oldname.equals(name)){
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
	@RequestMapping("/deleteLang")
	public void deleteNetpoint(HttpServletResponse response,String ids) throws Exception{
		try {
			tbLanguageService.updateDelStatus(ids);
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
}
