/** 
 * <pre>项目名称:admin_shop 
 * 文件名称:LogServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.log 
 * 创建日期:2019年1月10日下午8:17:23 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.log.ILogMapper;
import com.fh.shop.backend.po.log.LogInfo;

/** 
 * <pre>项目名称：admin_shop    
 * 类名称：LogServiceImpl    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月10日 下午8:17:23    
 * 修改人：zsk     
 * 修改时间：2019年1月10日 下午8:17:23    
 * 修改备注：       
 * @version </pre>    
 */
@Service("logService")
public class LogServiceImpl implements ILogService {

	@Autowired
	private ILogMapper logMapper;

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.log.ILogService#insertLog(com.fh.shop.backend.po.log.LogInfo)    
	 */
	@Override
	public void insertLog(LogInfo log) {
		logMapper.insertLog(log);
		
	}
}
