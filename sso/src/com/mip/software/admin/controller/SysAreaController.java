package com.mip.software.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mip.software.admin.entity.SysArea;
import com.mip.software.admin.service.SysAreaService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

@Controller
@RequestMapping("/admin/sysArea")
public class SysAreaController extends BaseController {
	
public static JsonHelper util = new JsonHelper();
	
	@Autowired
	private  SysAreaService sysAreaService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception {
		return "admin/sysArea/index";
	}
	
	@RequestMapping("/listpage")
	public String listPage(HttpServletRequest request,ModelMap model){
		String areaParentid=request.getParameter("areaParentid");
		model.addAttribute("areaParentid", areaParentid);
		return "admin/sysArea/list";
	}
	/**
	 * 树形结构  默认加载父id未空，再逐级加载
	 * */
	@RequestMapping("/getTreeData")
	public void getTreeData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<SysArea> list=new ArrayList<SysArea>();
		String areaid=request.getParameter("id");
		if("".equals(areaid)||areaid==null)
		list=sysAreaService.findSysAreaOfnull();
		else
			list=sysAreaService.findSysAreaByPId(Long.parseLong(areaid));
		List<HashMap<String,Object>> listtree = new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<list.size();i++)
		{
			HashMap<String,Object> hm = new HashMap<String,Object>(); 
			SysArea area=list.get(i);
			hm.put("id", area.getAreaid());//id属性  ，数据传递  
			hm.put("name", area.getAreaName()); //name属性，显示节点名称  
            if(area.getAreaLevel()==4)
            {
            	hm.put("pId", area.getAreaParentid());
    			hm.put("isParent", false);
            }
            else
            {
            	hm.put("pId", area.getAreaParentid());
    			hm.put("isParent", true);
            }
            listtree.add(hm);
		}
		JSONArray arr = new JSONArray(listtree);
		response.getWriter().write(arr.toString());
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String areaList(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		//dto.put("areaCode", "34");
		List<SysArea> list=sysAreaService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	@RequestMapping("/get")
	public String add(HttpServletRequest request,ModelMap model,Long areaid,Long areaParentid) {
		SysArea sysArea=null;
		if(areaid!=null)
		{
			sysArea=sysAreaService.findById(areaid);
		}
		model.addAttribute("areaParentid", areaParentid);
		model.addAttribute("bean",sysArea);
		return "admin/sysArea/area";
	}
	
	@RequestMapping("/save")
	public void saveArea(HttpServletRequest request,HttpServletResponse response,
			SysArea sysArea) {
		try {
			sysAreaService.saveSysArea(sysArea,request);
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
	@RequestMapping("/deleteArea")
	public void deleteArea(HttpServletResponse response,String ids) throws Exception{
		try {
			String[] id=ids.split(",");
			for (String string : id) {
				sysAreaService.del(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	

}
