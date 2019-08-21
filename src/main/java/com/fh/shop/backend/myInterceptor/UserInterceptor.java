/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:UserInterceptor.java 
 * 包名:com.fh.shop.backend.myInterceptor 
 * 创建日期:2019年1月7日下午8:23:03 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.myInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.common.SystemConstants;
import com.fh.shop.utils.CookieUtil;
import com.fh.shop.utils.RedisUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fh.shop.backend.po.user.User;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：UserInterceptor    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月7日 下午8:23:03    
 * 修改人：zsk     
 * 修改时间：2019年1月7日 下午8:23:03    
 * 修改备注：       
 * @version </pre>    
 */
public class UserInterceptor implements HandlerInterceptor{

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)    
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)    
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)    
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String cookie = CookieUtil.readCookie(request, SystemConstants.JSESSIONID);
		if (StringUtils.isEmpty(cookie)){
			return false;
		}
		String userJson = RedisUtil.get(SystemConstants.SESSION_USER + cookie);
		if(StringUtils.isEmpty(userJson)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script type='text/javascript'>location.href='"+request.getContextPath()+"/login.jsp'</script>");
            return false;
        }else {
			//将用户信息存入到session中
			Gson gson = new Gson();
			User user = gson.fromJson(userJson, User.class);
			request.getSession().setAttribute("user",user);
			RedisUtil.expire(SystemConstants.SESSION_USER + cookie,30*60);
            return true;
        }
	}

}
