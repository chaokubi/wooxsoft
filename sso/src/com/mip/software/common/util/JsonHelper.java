package com.mip.software.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Json处理器<br>
 * 
 * @author mip
 * @since 2011-08-15
 */
public class JsonHelper {

	private static Log log = LogFactory.getLog(JsonHelper.class);

//	/**
//	 * 将分页信息压入JSON字符串
//	 * @param jsonString
//	 * @param dto
//	 * @return
//	 */
//	public static String encodeJson2PageJson(String jsonString,Dto dto) {
//		jsonString = "{\"currentPage\":" + dto.getPage().getCurrentPage() + ",\"totalPage\":"
//				+ dto.getPage().getTotalPage() + ",\"pageSize\":" + dto.getPage().getPageSize() + ",\"count\":"
//				+ dto.getPage().getCount() + ",\"resultsetlist\":" + jsonString + "}";
//		if (log.isInfoEnabled()) {
//			log.info("合并后的JSON资料输出:\n" + jsonString);
//		}
//		return jsonString;
//	}
	   
	/**
	 * 最新修改2011-10-20 mip 将分页信息压入JSON字符串
	 * 
	 * @param jsonString
	 *            JSON字符串
	 * @param currentPage
	 *            当前页
	 * @param totalPage
	 *            总页数
	 * @param pageSize
	 *            每页显示条数
	 * @param count
	 *            总记录数
	 * @return 返回合并后的字符串
	 */
	public static String encodeJson2PageJsonnew(String jsonString,Dto dto) {
		jsonString = "{\"draw\":" + dto.getQueryCondition().get("draw")
				+ ",\"iTotalRecords\":" + dto.getPage().getCount()
				+ ",\"iTotalDisplayRecords\":" + dto.getPage().getCount()
				+ ",\"data\":" + jsonString + "}";
//		jsonString = "{\"iTotalRecords\":" + dto.getPage().getCount()
//				+ ",\"iTotalDisplayRecords\":" + dto.getPage().getCount()
//				+ ",\"data\":" + jsonString + "}";
		log.info("合并后的JSON资料输出:\n" + jsonString);
		return jsonString;
	}
	
	/**
	 * 将Object转换成JSON并压入到分页信息中
	 * @param object
	 * @param formdata 
	 * @param page
	 * @return
	 */
	public String ObjecttoPageJson(Object object,Dto dto){
		String jsonList=JSON.toJSONString(object,SerializerFeature.WriteMapNullValue);
		return encodeJson2PageJsonnew(jsonList,dto);
	}
	
	/**
	 * 将Object转换成JSON
	 * @param object
	 * @param formdata 
	 * @param page
	 * @return
	 */
	public String ObjecttoJson(Object object){
		String jsonList=JSON.toJSONString(object);
		return jsonList;
	}
	
	
	/**
	 * 将含有日期时间格式的Java对象系列化为Json资料格式<br>
	 * Json-Lib在处理日期时间格式是需要实现其JsonValueProcessor接口,所以在这里提供一个重载的方法对含有<br>
	 * 日期时间格式的Java对象进行序列化
	 * @param pObject
	 *            传入的Java对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final String encodeObject2Json(Object pObject,
			String pFormatString) {
		String jsonString = "[]";
		if (null == pObject) {
		} else {
			JsonConfig cfg = new JsonConfig();
			cfg.registerJsonValueProcessor(java.sql.Timestamp.class,
					new JsonValueProcessorImpl(pFormatString));
			cfg.registerJsonValueProcessor(java.util.Date.class,
					new JsonValueProcessorImpl(pFormatString));
			cfg.registerJsonValueProcessor(java.sql.Date.class,
					new JsonValueProcessorImpl(pFormatString));
			if (pObject instanceof ArrayList) {
				JSONArray jsonArray = JSONArray.fromObject(pObject, cfg);
				jsonString = jsonArray.toString();
			} else {
				JSONObject jsonObject = JSONObject.fromObject(pObject, cfg);
				jsonString = jsonObject.toString();
			}
		}
		return jsonString;
	}
	
	
	/**
     * 
     * <br>
     * <b>功能：</b>输出JSON<br>
     * @param response
     * @param type 1=成功 0=失败
     * @param msg
     */
	public void toAppJsonMsg(HttpServletResponse response, 
			int type,Object object) {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("result", type);
		if (type == 1) {
			jsonObject.put("msg","操作成功");
			if (object == null) {
				jsonObject.put("data",null);
			} else {
				jsonObject.put("data",object);
			}
		} else {
			jsonObject.put("msg","操作失败");
			jsonObject.put("data",object);
		}
		this.toJsonPrint(response,jsonObject.toString());
	}
	
	 /**
     * 
     * <br>
     * <b>功能：</b>输出JSON<br>
     * @param response
     * @param type 0=成功 其他=失败
     * @param msg
     */
	public void toJsonMsg(HttpServletResponse response, int type, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", type);
		if (type == 0) {
			map.put("success", true);
			if (msg == null) {
				map.put("msg", "成功");
			} else {
				map.put("msg", msg);
			}
		} else {
			map.put("success", false);
			if (msg == null) {
				map.put("msg", "失败");
			} else {
				map.put("msg", msg);
			}
		}
		this.toJsonPrint(response, JSON.toJSONString(map));
	}
	
	/**
	 * <br>
	 * <b>功能：</b>打印JSON<br>
	 * @param response
	 * @param str
	 */
	public void toJsonPrint(HttpServletResponse response, String str){
		this.writer(response, str);
	}
	
	/**
     * 
     * <br>
     * <b>功能：</b>打印<br>
     * @param response
     * @param str
     */
	public void writer(HttpServletResponse response, String str) {
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = null;
			out = response.getWriter();
			System.out.println("print:"+str);
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
