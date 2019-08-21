/** 
 * <pre>项目名称:admin_shop 
 * 文件名称:ResponseEnum.java 
 * 包名:com.fh.shop.backend.common 
 * 创建日期:2019年1月11日下午1:52:14 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.common;

/** 
 * <pre>项目名称：admin_shop    
 * 类名称：ResponseEnum    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月11日 下午1:52:14    
 * 修改人：zsk     
 * 修改时间：2019年1月11日 下午1:52:14    
 * 修改备注：       
 * @version </pre>    
 */
public enum ResponseEnum {

	USER_LOCKED(1005,"该用户连续三次密码输入错误已被锁定"),
	USERNAME_PWD_EMPTY_IMAGECODE(1000,"账户名或者密码或验证码不能为空"),
	USERNAME_ERROR(1001,"账户名错误"),
	PASSWORD_ERROR(1002,"密码错误"),
	RELEVANCE_ERROR(1003,"请删除相关联的数据"),
	IMAGECODE_ERROR(1004,"验证码错误"),
	ERROR(-1,"系统内部错误"),
	SUCCESS(200,"success");
	
	private Integer code;
	private String message;
	
	private ResponseEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	
}
