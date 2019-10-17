package com.mip.software.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.service.SysUserService;


@Controller
public class BaseController {
	
	@Autowired
	private SysUserService tbUserService;
	
	@InitBinder
	protected void ininBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder
				.registerCustomEditor(Date.class, new CustomDateEditor(sdf,
						true));
	}
	
	/**
	 * 从session中提取登录人
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected SysUser getSysUserSession(HttpServletRequest request)throws Exception{
		return (SysUser)request.getSession().getAttribute("loginuser");
	}
	
}
