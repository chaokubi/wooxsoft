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
import com.mip.software.admin.entity.SysDictContent;
import com.mip.software.admin.entity.SysDictType;
import com.mip.software.admin.service.SysDictService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;



/**
 * 字典管理
 */
@Controller
@RequestMapping("/admin/dict")
public class TbDictController extends BaseController{
	
	
	public static JsonHelper util = new JsonHelper();
	
	@Autowired
	private SysDictService sysDictService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		List<SysDictType> dataTypeList=sysDictService.findSysDictList();
		JSONArray jsonArray = new JSONArray();
		/*JSONObject nodejson = new JSONObject();
		nodejson.put("id","0");
		nodejson.put("pId","-1");
		nodejson.put("name","字典类型");
		nodejson.put("isParent", "true");
		nodejson.put("hasChild", "true");
		nodejson.put("open",true);
		jsonArray.add(nodejson);*/
		for(int i=0;i<dataTypeList.size();i++){
			JSONObject temp = new JSONObject();
			SysDictType sdt = dataTypeList.get(i);
			temp.put("id",sdt.getTypeid());
			temp.put("pId","-1");
			temp.put("name",sdt.getTypename());
			temp.put("isParent", "true");
			temp.put("hasChild", "false");
			temp.put("open", "true");
			temp.put("click","getTheDataContent("+sdt.getTypeid()+")");
			jsonArray.add(temp);
		}
		request.setAttribute("zTreeNodes", jsonArray.toString());
		return "admin/dict/index";
	}
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		request.setAttribute("typeid", request.getParameter("typeid"));
		return "admin/dict/list";
	}
	/**
	 * Ajax请求得到字典类型树
	 * @return
	 */
	@RequestMapping("/sysDictTypeJson")
	public @ResponseBody String datatendJson(){
		List<SysDictType> dataTypeList=sysDictService.findSysDictList();
		JSONArray jsonArray = new JSONArray();
		JSONObject nodejson = new JSONObject();
		nodejson.put("id","0");
		nodejson.put("pId","-1");
		nodejson.put("name","字典类型");
		nodejson.put("isParent", "true");
		nodejson.put("hasChild", "true");
		nodejson.put("open",true);
		jsonArray.add(nodejson);
		for(int i=0;i<dataTypeList.size();i++){
			JSONObject temp = new JSONObject();
			SysDictType sdt = dataTypeList.get(i);
			temp.put("id",sdt.getTypeid());
			temp.put("pId","0");
			temp.put("name",sdt.getTypename());
			temp.put("isParent", "false");
			temp.put("hasChild", "false");
			temp.put("click","getTheDataContent("+sdt.getTypeid()+")");
			jsonArray.add(temp);
		}
		return jsonArray.toString();
	}
	
	/**
	 * 添加或修改字典类型
	 */
	@RequestMapping("/addOrUpdatedatatype")
	public @ResponseBody String addOrUpdateDataType(SysDictType record){
		try {
			return sysDictService.saveSysDict(record);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	@RequestMapping("/getContent")
	public void getContent(HttpServletRequest request,HttpServletResponse response,
			Long dictionid) throws Exception{
		SysDictContent sysDictContent=sysDictService.getSysDictContentById(dictionid);
		util.toAppJsonMsg(response,1,sysDictContent);
	}
	
	/**
	 * 进入详情页 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/get")
	public String add(HttpServletRequest request,ModelMap model,Long dictionid,Long typeid) {
		SysDictContent sysDictContent=null;
		if(dictionid!=null){
			sysDictContent=sysDictService.getSysDictContentById(dictionid);
			model.addAttribute("typeid", sysDictContent.getTypeid());
		}else{
			model.addAttribute("typeid", request.getParameter("typeid"));
		}
		model.addAttribute("bean",sysDictContent);
		return "admin/dict/edit";
	}
	
	
	@RequestMapping("/dataContentlistJson")
	@ResponseBody 
	public String userList(HttpServletRequest request) throws Exception{
		Dto dto=WebUtils.getPraramsAsDto(request);
		List<SysDictContent> contentList=sysDictService.findListPage(dto);
		return util.ObjecttoPageJson(contentList,dto);
	}
	
	
	
	/**
	 * 删除字典类型
	 * @param id
	 */
	@RequestMapping("/deldatatype")
	public void delDataTypeById(HttpServletRequest request,
			HttpServletResponse response,String id){
		try {
			sysDictService.deleteSysDict(id);
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
		
	}
	
	/**
	 * 删除字典内容
	 * @param id
	 */
	@RequestMapping("/deldatacontent")
	public void deldatacontent(HttpServletResponse response,String ids){
		try {
			sysDictService.deleteSysDictContent(ids);
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	
	
	/**
	 * 添加或修改字典内容
	 */
	@RequestMapping("/addOrUpdatedatacontent")
	public void addOrUpdateDataContent(HttpServletResponse response,SysDictContent record){
		try {
			sysDictService.saveOrUpdateSysDictContent(record);
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	
	
	
}
