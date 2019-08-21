/** 
 * <pre>项目名称:admin_shop 
 * 文件名称:LogManage.java 
 * 包名:com.fh.shop.backend.common 
 * 创建日期:2019年1月20日上午10:59:12 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.aspect;

import com.fh.shop.backend.common.WebContext;
import com.fh.shop.backend.po.user.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * <pre>项目名称：admin_shop    
 * 类名称：LogManage    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月20日 上午10:59:12    
 * 修改人：zsk     
 * 修改时间：2019年1月20日 上午10:59:12    
 * 修改备注：       
 * @version </pre>    
 */
public class LogAspect {

	private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);
	public Object doLog(ProceedingJoinPoint pjp){
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().getName();
		Object result = null;
		try {
			result = pjp.proceed();
			User user = (User) WebContext.getRequest().getSession().getAttribute("user");
			if (user!=null){
				LOG.info("{}执行{}类中的{}()方法成功",user.getUserName(),className,methodName);
			}
		} catch (Throwable e) {
			LOG.error("执行{}类中的{}()方法失败",new Object[]{className,methodName});
			e.printStackTrace();
		}
		return result;
	}
}
