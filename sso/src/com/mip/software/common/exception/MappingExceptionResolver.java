package com.mip.software.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MappingExceptionResolver extends SimpleMappingExceptionResolver {
	
	final Logger logger=Logger.getLogger(MappingExceptionResolver.class);
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {	
		String accept = request.getHeader("accept");
		if(accept.indexOf("application/json")>-1){
			response.setHeader("sessionstatus", "error");//在响应头设置session状态
		}
		if (ex instanceof Exception) {
//			HttpSession session=request.getSession();
//			SysUser user = (SysUser) session.getAttribute("sessionSysUser");
//			String request_uri =request.getRequestURI();
//			String ctxPath = ((HttpServletRequest) request).getContextPath();
//			String uri = request_uri.substring(ctxPath.length());
//			HashMap<String,Object> map=new HashMap<String, Object>();
//			map.put("opuser",user.getUserid());
//			map.put("errorcont","路径:" +uri +"  ;错误信息:"+ex.getMessage());
////			logService.addSysErrorInfo(map);
//			logger.error(ex.getMessage());
			response.setStatus(HttpStatus.FORBIDDEN.value());			
		}
		return super.doResolveException(request, response, handler, ex);
	}
	
	protected ModelAndView getModelAndView(String viewName, Exception ex) {
		if(ex instanceof Exception){
			ex.printStackTrace();
		}
		return super.getModelAndView(viewName, ex);
	}

}