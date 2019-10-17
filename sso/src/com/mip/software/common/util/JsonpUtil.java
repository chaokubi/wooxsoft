package com.mip.software.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ade
 */
public class JsonpUtil {

	public static String wrap(String rsp, HttpServletRequest request) {
		String callback = request.getParameter("callback");
		
		if(callback != null && !"".equals(callback.trim())) {
			rsp = callback + "(" + rsp + ")";
		}
		
		return rsp;
	}
}
