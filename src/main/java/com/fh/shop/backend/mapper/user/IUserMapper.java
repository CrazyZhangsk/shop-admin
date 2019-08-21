/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:IUserMapper.java 
 * 包名:com.fh.shop.backend.mapper.user 
 * 创建日期:2019年1月7日下午4:06:26 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.user;

import com.fh.shop.backend.po.user.User;

import java.util.List;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：IUserMapper    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月7日 下午4:06:26    
 * 修改人：zsk     
 * 修改时间：2019年1月7日 下午4:06:26    
 * 修改备注：       
 * @version </pre>    
 */
public interface IUserMapper {

	/** <pre>userLogin(这里用一句话描述这个方法的作用)   
	 * 创建人：zsk 
	 * 联系方式：Zhangsk_t@163.com
	 * 创建时间：2019年1月7日 下午4:24:02    
	 * 修改人：zsk    
	 * 修改时间：2019年1月7日 下午4:24:02    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	User userLogin(User user);

    void addUser(User user);

    void updateUser(User userDB);

    void updateUserErrorCount(User userDB);

	void updateUserStatus(User userDB);

    Long findTotalCount(User user);

	List<User> findUserList(User user);

    User findUpdateUserInfo(Integer id);

	void updateUserInfo(User user);

    void updateBatchUserDept(User user);

    List<User> findUserInfoList(User user);
}
