package com.mip.software.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mip.software.admin.entity.CommonVertify;
import com.mip.software.admin.service.CommonVertifyService;
import com.mip.software.common.util.JsonHelper;

/**
 * 审核日志
 */
@Controller
@RequestMapping("/admin/commonVertify")
public class CommonVertifyController extends BaseController{
	
	public static JsonHelper util = new JsonHelper();
	@Autowired 
	private CommonVertifyService commonVertifyService;
	
	/**
	 * 保存信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void saveCommonVertify(HttpServletRequest request,HttpServletResponse response,
			CommonVertify commonVertify) {
		try {
			//commonVertifyService.saveCommonVertify(commonVertify, request);
			util.toJsonMsg(response, 0, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response, 1, "操作失败");
		}
	}

}
