package com.mip.software.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import com.mip.software.common.page.PageQuery;
import com.mip.software.common.page.Pagination;
import static com.mip.software.common.page.SimplePage.cpn;

/**
 * Web层相关的实用工具类
 * @author 
 * @date 2013-3-17 下午3:14:59
 */
public class WebUtils {
	
	/**
	 * 将请求参数封装为Map<br>
	 * request中的参数t1=1&t1=2&t2=3<br>
	 * 形成的map结构：<br>
	 * key=t1;value[0]=1,value[1]=2<br>
	 * key=t2;value[0]=3<br>
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static HashMap<String, String> getPraramsAsMap(HttpServletRequest request) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		Map map = request.getParameterMap();
		Iterator keyIterator = (Iterator) map.keySet().iterator();
		String key,value=null;
		while (keyIterator.hasNext()) {
			key = (String) keyIterator.next();
			value = ((String[]) (map.get(key)))[0];
			if(!StringUtils.isEmpty(value)&&value.equals("undefined")){
				value="";
			}
			hashMap.put(key,value.trim());
		}
		if(StringUtils.isNotEmpty(hashMap.get("iDisplayStart"))){
			int start=1;
			int end=Integer.parseInt(hashMap.get("iDisplayLength"));
			hashMap.put("start",hashMap.get("iDisplayStart"));
			hashMap.put("end",String.valueOf(start+end));
		}
		return hashMap;
	}
	
	
	/**
	 * 将请求参数封装为PageParameter对象<br>
	 * request中的参数t1=1&t1=2&t2=3<br>
	 * 形成的map结构：<br>
	 * key=t1;value[0]=1,value[1]=2<br>
	 * key=t2;value[0]=3<br>
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Dto getPraramsAsDto(HttpServletRequest request) {
		Dto dto=new Dto();
		PageParameter pageParameter=new PageParameter();
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		Map map = request.getParameterMap();
		Iterator keyIterator = (Iterator) map.keySet().iterator();
		String key,value=null;
		while (keyIterator.hasNext()) {
			key = (String) keyIterator.next();
			value = ((String[]) (map.get(key)))[0];
			if(!StringUtils.isEmpty(value)&&value.equals("undefined")){
				value="";
			}
			hashMap.put(key,value.trim());
		}
		dto.setQueryCondition(hashMap);
		if(StringUtils.isNotEmpty((String) hashMap.get("start"))){
			//每页显示多少条
			int end=Integer.parseInt((String) hashMap.get("length"));
			int start=1;
			if(ProjectUtils.isNumeric((String) hashMap.get("start"))){
				//当前页数
				start=Integer.parseInt((String) hashMap.get("start"))/end+1;
			}
			
			pageParameter.setCurrentPage(start);
			pageParameter.setPageSize(end);
		}
		dto.setPage(pageParameter);
		return dto;
	}
	
	/**
	 * 只获取page信息 不获取其他信息
	 * @param request
	 * @param model
	 * @return
	 */
	public static PageQuery getPage(HttpServletRequest request, ModelMap model){
		PageQuery pageQuery = new PageQuery();
		//查询参数
		HashMap<String, Object> queryCondition = new HashMap<String, Object>();
		pageQuery.setQueryCondition(queryCondition);
		//分页信息
		String pageNo = request.getParameter("pageNo");
		Pagination pager = pageQuery.getPagination();
		if(null!=pageNo&&!"".equals(pageNo)&&!"null".equals(pageNo)){
			pager.setPageNo(cpn(Integer.parseInt(pageNo)));
		}else {
			pager.setPageNo(1);
		}
		String pageSize=request.getParameter("pageSize");
		if(null!=pageSize&&!"".equals(pageSize)&&!"null".equals(pageSize)){
			pager.setPageSize(Integer.parseInt(pageSize));
		}else {
			pager.setPageSize(10);
		}
		pageQuery.setPagination(pager);
		model.addAttribute("queryCondition", queryCondition);
		return pageQuery;
	}
	
}
