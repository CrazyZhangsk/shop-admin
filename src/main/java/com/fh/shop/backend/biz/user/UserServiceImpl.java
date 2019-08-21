/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:UserServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.user 
 * 创建日期:2019年1月7日下午4:05:29 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.user;

import com.fh.shop.backend.biz.BaseBiz;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.SystemConstants;
import com.fh.shop.backend.controller.fileHandel.FileHandel;
import com.fh.shop.backend.vo.UserVO;
import com.fh.shop.utils.COSUtils;
import com.fh.shop.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.user.IUserMapper;
import com.fh.shop.backend.po.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：UserServiceImpl    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月7日 下午4:05:29    
 * 修改人：zsk     
 * 修改时间：2019年1月7日 下午4:05:29    
 * 修改备注：       
 * @version </pre>    
 */
@Service(value="userService")
public class UserServiceImpl extends BaseBiz implements IUserService {

	@Autowired
	private IUserMapper userMapper;

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.user.IUserService#userLogin(com.fh.shop.backend.po.user.User)    
	 */
	@Override
	public User userLogin(User user) {
		return userMapper.userLogin(user);
	}

	@Override
	public void addUser(User user) {
		user.setErrorCount(0);
		userMapper.addUser(user);
	}

	@Override
	public void updateUser(User userDB) {
		userDB.setLoginTime(DateUtils.getDateNow());
		userMapper.updateUser(userDB);
	}

	@Override
	public void updateUserErrorCount(User userDB) {
		userMapper.updateUserErrorCount(userDB);
	}

	@Override
	public void updateUserStatus(User userDB) {
		userMapper.updateUserStatus(userDB);
	}

	@Override
	public Long findTotalCount(User user) {
		String idsStr = user.getIdsStr();
		if (StringUtils.isNotEmpty(idsStr)){
			String[] split = idsStr.split(",");
			for (String s : split) {
				user.getIdsList().add(Integer.valueOf(s));
			}
		}
		return userMapper.findTotalCount(user);
	}

	@Override
	public List<User> findUserList(User user) {
		getUserIdsList(user);
		return userMapper.findUserList(user);
	}

	private void getUserIdsList(User user) {
		String idsStr = user.getIdsStr();
		if (StringUtils.isNotEmpty(idsStr)){
			String[] split = idsStr.split(",");
			for (String s : split) {
				user.getIdsList().add(Integer.valueOf(s));
			}
		}
	}

	@Override
	public User toUpdateUserInfo(Integer id) {
		User user= userMapper.findUpdateUserInfo(id);
		user.setBirthdayStr(DateUtils.date2Str(user.getBirthday(),DateUtils.y_M_d));
		return user;
	}

	@Override
	public void updateUserInfo(User user) {
		if (StringUtils.isEmpty(user.getHeaderImgPath())){
			user.setHeaderImgPath(user.getOldHeaderImgPath());
		}
		if (StringUtils.isNotEmpty(user.getOldHeaderImgPath())){
			COSUtils.delete(user.getOldHeaderImgPath());
		}
		userMapper.updateUserInfo(user);
	}

	@Override
	public void updateBatchUserDept(User user) {
		getUserIdsList(user);
		userMapper.updateBatchUserDept(user);
	}
	@Override
	public DataTableResult getUserDataTableResult(User user, Map<String, String[]> parameterMap) {
		//排序
		if (StringUtils.isNotEmpty(parameterMap.get(SystemConstants.ORDER_DIR)[0])){
			String orderColumn = parameterMap.get(SystemConstants.ORDER_COLUMN)[0];
			String orderDir = parameterMap.get(SystemConstants.ORDER_DIR)[0];
			String temp = parameterMap.get(getOrderColumnsData(orderColumn))[0];
			String field = (String) SystemConstants.FIELD_MAPPING_STATIC.get(temp);
			String sortField = StringUtils.isNotEmpty(field) ? field : temp;
			user.setSort(orderDir);
			user.setSortField(sortField);
		}
		//查询总条数
		Long totalCount = findTotalCount(user);
		//查询数据
		List<User> retList = findUserList(user);
		//PO转VO
		List<UserVO> userVOList = new ArrayList<>();
		for (User userInfo : retList) {
			UserVO userVO = new UserVO();
			userVO.setId(userInfo.getId());
			userVO.setUserName(userInfo.getUserName());
			userVO.setRealName(userInfo.getRealName());
			userVO.setDeptName(userInfo.getDept().getDeptName());
			userVO.setSalary(userInfo.getSalary());
			userVO.setSex(userInfo.getSex());
			userVO.setBirthdayStr(DateUtils.date2Str(userInfo.getBirthday(),DateUtils.y_M_d));
			userVOList.add(userVO);
			userVO.setHeaderImgPath(userInfo.getHeaderImgPath());
		}
		return DataTableResult.buildDataTableResult(userVOList, user.getDraw(), totalCount, totalCount);
	}

	@Override
	public List<User> findUserInfoList(User user) {
		List<Integer> idsList = new ArrayList<>();
		if (StringUtils.isNotEmpty(user.getIdsStr())){
			String[] split = user.getIdsStr().split(",");
			for (String s : split) {
				idsList.add(Integer.parseInt(s));
			}
		}
		user.setIdsList(idsList);
		return userMapper.findUserInfoList(user);
	}
}
