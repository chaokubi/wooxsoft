package com.mip.software.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mip.software.admin.entity.SysMenu;
import com.mip.software.admin.entity.SysRole;
import com.mip.software.admin.entity.SysRoleMenu;
import com.mip.software.admin.service.TbMenuService;
import com.mip.software.admin.service.SysRoleService;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.JsonHelper;
import com.mip.software.common.util.WebUtils;

/**
 * 角色
 */
@Controller
@RequestMapping("/admin/tbRole")
public class TbRoleController extends BaseController {
		
	public static JsonHelper util = new JsonHelper();
	@Autowired
	private SysRoleService tbRoleService;
	@Autowired
	private TbMenuService tbMenuService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) {
		return "admin/tbrole/tblist";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String userList(HttpServletRequest request) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		List<SysRole> list=tbRoleService.findListPage(dto);
		return util.ObjecttoPageJson(list,dto);
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,ModelMap model) {
		Dto dto=WebUtils.getPraramsAsDto(request);
		List<SysMenu> list=tbMenuService.findList(dto);
		JSONArray jsonArray = new JSONArray();
		JSONObject temp = null;
		for (SysMenu sysMenuItem : list) {
			temp = new JSONObject();
			temp.put("id", sysMenuItem.getMenuid());
			temp.put("pId", null!=sysMenuItem.getMenu_parent()?sysMenuItem.getMenu_parent():0);
			temp.put("name", sysMenuItem.getMenuname());
			//判断是否有子节点
			if (existChildren(sysMenuItem, list)) {
				temp.put("open",true);
			}
			jsonArray.add(temp);
		}
		model.addAttribute("treebean",jsonArray);
		return "admin/tbrole/role";
	}
	
	 /**
		 * 判断有无子节点
		 * @param sysMenuItem
		 * @param sysList
		 * @return
	 */
	public static boolean existChildren(SysMenu sysMenuItem,List<SysMenu> sysList){
		for (SysMenu sysMenuItem2 : sysList) {
			if(null!=sysMenuItem2.getMenu_parent()){
				if(sysMenuItem2.getMenu_parent().equals(sysMenuItem.getMenuid())){
					return true;
				}
			}else{
			}
		}
		return false;
	}
		
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,ModelMap model,Long roleid) {
		
		List<SysRoleMenu> lRoleMenus=tbRoleService.findRoleMenusByRoleId(roleid);
		Dto dto=WebUtils.getPraramsAsDto(request);
		List<SysMenu> list=tbMenuService.findList(dto);
		JSONArray jsonArray = new JSONArray();
		JSONObject temp = null;
		for (SysMenu sysMenuItem : list) {
			temp = new JSONObject();
			temp.put("id", sysMenuItem.getMenuid());
			temp.put("pId", null!=sysMenuItem.getMenu_parent()?sysMenuItem.getMenu_parent():0);
			temp.put("name", sysMenuItem.getMenuname());
			//判断是否有子节点
			if (existChildren(sysMenuItem, list)) {
				temp.put("open",true);
			}
			//判断是否选中
			temp.put("checked",check(sysMenuItem.getMenuid(),lRoleMenus));
			jsonArray.add(temp);
		}
		model.addAttribute("treebean",jsonArray);
		model.addAttribute("bean",tbRoleService.findById(roleid));
		return "admin/tbrole/role";
	}
	
	
	 /**
		 * 判断是否选中
		 * @param sysMenuItem
		 * @param sysList
		 * @return
	 */
	public static boolean check(Long menuid,List<SysRoleMenu> list){
		for (SysRoleMenu tbRoleMenu : list) {
			if(tbRoleMenu.getMenuid().equals(menuid)){
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping("/addRole")
	public void addRole(HttpServletRequest request,HttpServletResponse response,
								SysRole tbRole) {
		try {
			if(null!=tbRole.getRoleid()){
				tbRoleService.update(tbRole);
			}else{
				tbRoleService.save(tbRole);
			}
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
	public void deleteUser(HttpServletResponse response,String ids) throws Exception{
		try {
			String[] id=ids.split(",");
			for (String string : id) {
				tbRoleService.del(Long.parseLong(string));
			}
			util.toJsonMsg(response,0,"");
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response,1,"");
		}
	}
	
	
}
