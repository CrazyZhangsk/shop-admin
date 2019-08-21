/** 
 * <pre>项目名称:admin_shop 
 * 文件名称:ILogMapper.java 
 * 包名:com.fh.shop.backend.mapper.log 
 * 创建日期:2019年1月10日下午8:19:22 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.log;

import com.fh.shop.backend.po.log.LogInfo;

/** 
 * <pre>项目名称：admin_shop    
 * 类名称：ILogMapper    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月10日 下午8:19:22    
 * 修改人：zsk     
 * 修改时间：2019年1月10日 下午8:19:22    
 * 修改备注：       
 * @version </pre>    
 */
public interface ILogMapper {

	/** <pre>insertLog(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2019年1月10日 下午8:30:06    
	 * 修改人：zsk    
	 * 修改时间：2019年1月10日 下午8:30:06    
	 * 修改备注： 
	 * @param log</pre>    
	 */
	void insertLog(LogInfo log);

}
