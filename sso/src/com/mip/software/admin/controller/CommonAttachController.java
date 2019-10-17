package com.mip.software.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mip.software.admin.entity.CommonAttach;
import com.mip.software.admin.entity.SysDictContent;
import com.mip.software.admin.service.CommonAttachService;
import com.mip.software.admin.service.SysDictService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

/**
 * 附件管理
 */
@Controller
@RequestMapping("/admin/commonAttach")
public class CommonAttachController {
	public static JsonHelper util = new JsonHelper();
	@Autowired 
	private CommonAttachService commonAttachService;
	@Autowired 
	private SysDictService sysDictService;
	/**
	 * 附件管理首页
	 * */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/commonAttach/index";
	}
	
	/**
	 * 加载附件列表数据   
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String commonAttachList(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		List<CommonAttach> list=commonAttachService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	/**
	 * 附件详情 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/get")
	public String add(HttpServletRequest request,ModelMap model,Long attachid,Long typeid) {
		CommonAttach commonAttach=null;
		if(attachid!=null)
			commonAttach=commonAttachService.findById(attachid);
		model.addAttribute("bean",commonAttach);
		model.addAttribute("typeid",typeid);
		return "admin/commonAttach/commonAttach";
	}
	/**
	 * 保存信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void saveCommonAttach(HttpServletRequest request,HttpServletResponse response,
			CommonAttach commonAttach) {
		try {
			commonAttachService.saveCommonAttach(commonAttach, request);
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
	public void deleteCommonAttach(HttpServletResponse response,String ids) throws Exception{
		try {
			String[] id=ids.split(",");
			for (String string : id) {
				commonAttachService.delBId(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	
	/**
	 * Ajax请求得到会议信息
	 * @return
	 */
	@RequestMapping("/sysDictTypeJson")
	public @ResponseBody String datatendJson(HttpServletRequest request){
		List<SysDictContent> dataTypeList=sysDictService.findSysDictContentByTypeId(13);
		JSONArray jsonArray = new JSONArray();
		JSONObject nodejson = new JSONObject();
		nodejson.put("id","0");
		nodejson.put("pId","-1");
		nodejson.put("name","附件类型");
		nodejson.put("isParent", "true");
		nodejson.put("hasChild", "true");
		nodejson.put("open",true);
		jsonArray.add(nodejson);
		for(int i=0;i<dataTypeList.size();i++){
			JSONObject temp = new JSONObject();
			SysDictContent sdt = dataTypeList.get(i);
			temp.put("id",sdt.getDictionid());
			temp.put("pId","0");
			temp.put("name",sdt.getDictname());
			temp.put("isParent", "false");
			temp.put("hasChild", "false");
			jsonArray.add(temp);
		}
		return jsonArray.toString();
	}
	

}
