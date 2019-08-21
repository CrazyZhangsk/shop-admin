/** 
 * <pre>项目名称:admin_shop 
 * 文件名称:ServerResponse.java 
 * 包名:com.fh.shop.backend.common 
 * 创建日期:2019年1月11日下午1:09:39 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.common;

import java.io.Serializable;

/** 
 * <pre>项目名称：admin_shop    
 * 类名称：ServerResponse    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月11日 下午1:09:39    
 * 修改人：zsk     
 * 修改时间：2019年1月11日 下午1:09:39    
 * 修改备注：       
 * @version </pre>    
 */
public class ServerResponse implements Serializable {

	private static final long serialVersionUID = -1258103569327407795L;

	private Integer code;
	private String message;
	private Object data;

	//共有的静态方法，供外部调用
	public static ServerResponse success(Integer code, String message){
		return new ServerResponse(code,message,null);
	}
	//逻辑错误
	public static ServerResponse error(Integer code, String message){
		return new ServerResponse(code,message,null);
	}
	//逻辑错误改成枚举
	public static ServerResponse error(ResponseEnum responseEnum){
		return new ServerResponse(responseEnum.getCode(),responseEnum.getMessage(),null);
	}
	//系统内部错误
	public static ServerResponse error(){
		return new ServerResponse(ResponseEnum.ERROR.getCode(),ResponseEnum.ERROR.getMessage(),null);
	}
	//成功不含数据
	public static ServerResponse success(){
		return new ServerResponse(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMessage(),null);
	}
	//成功含有数据
	public static ServerResponse success(Object data){
		return new ServerResponse(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMessage(),data);
	}

	//有参构造
	private ServerResponse(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	//无参构造
	public ServerResponse() {}

	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	
}
