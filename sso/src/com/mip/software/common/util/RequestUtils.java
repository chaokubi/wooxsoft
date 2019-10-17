package com.mip.software.common.util;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author kjhe
 *
 */
public class RequestUtils {
    /**
     * 灏嗚姹備腑鐨勫弬鏁拌浆鍖栦负Map
     * @param request
     * @return
     */
   @SuppressWarnings("unchecked")
   public static Map<String,String> parameterToMap(HttpServletRequest request){
       Map<String , String> params = new HashMap<String, String>();
       Enumeration<String> paramNames = request.getParameterNames();
       while (paramNames.hasMoreElements()) {
           String paramName = paramNames.nextElement();
           params.put(paramName, request.getParameter(paramName));
       }
       return params;
   }
   
   public static Map<String,Object> parameter3Map(HttpServletRequest request){
       Map<String , Object> params = new HashMap<String, Object>();
       Enumeration<String> paramNames = request.getParameterNames();
       while (paramNames.hasMoreElements()) {
           String paramName = paramNames.nextElement();
           params.put(paramName, request.getParameter(paramName));
       }
       return params;
   }
   
   @SuppressWarnings("unchecked")
   public static Map<String,String> parameter2Map(HttpServletRequest request) throws Exception{
       Map<String , String> params = new HashMap<String, String>();
       Enumeration<String> paramNames = request.getParameterNames();
       while (paramNames.hasMoreElements()) {
           String paramName = paramNames.nextElement();
           params.put(paramName, !NullUtil.isNull(request.getParameter(paramName))?URLDecoder.decode(request.getParameter(paramName), "utf8"):null);
       }
       return params;
   }
   
   public static Map<String,String> parameterToMapCheck(HttpServletRequest request,String... paramter){
       Map<String , String> params = new HashMap<String, String>();
       Enumeration<String> paramNames = request.getParameterNames();
       while (paramNames.hasMoreElements()) {
           String paramName = paramNames.nextElement();
           params.put(paramName, request.getParameter(paramName));
       }
       if(NullUtil.isNull(paramter)){
    	   return null;
       }
       for (int i = 0; i < paramter.length; i++) {
		if(!params.containsKey(paramter[i])){
			return null;
		}
       }
       return params;
   }
}
