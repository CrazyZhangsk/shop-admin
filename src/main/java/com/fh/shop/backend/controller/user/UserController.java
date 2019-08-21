/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:UserController.java 
 * 包名:com.fh.shop.backend.controller.user 
 * 创建日期:2019年1月7日下午4:02:28 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.SystemConstants;
import com.fh.shop.backend.po.product.Product;
import com.fh.shop.utils.*;
import com.google.gson.Gson;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.fh.shop.backend.biz.user.IUserService;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.controller.BaseController;
import com.fh.shop.backend.po.user.User;

import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：UserController    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月7日 下午4:02:28    
 * 修改人：zsk     
 * 修改时间：2019年1月7日 下午4:02:28    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Resource(name="userService")
	private IUserService userService;

	@RequestMapping("addUserInfo")
	public @ResponseBody ServerResponse addUser(User user){
		String uuid = UUID.randomUUID().toString();
		String md5 = MD5Util.getStringMD5(uuid + user.getPassword());
		user.setSalt(uuid);
		user.setPassword(md5);
		userService.addUser(user);
		return ServerResponse.success();
	}

	/*public static void main(String[] args) {

		String uuid = UUID.randomUUID().toString();
		String md5 = MD5Util.getStringMD5(uuid + "qqq");
		System.out.println(uuid);
		System.out.println(md5);
	}*/

	@RequestMapping("toAddUserPage")
	public String toAddUserPage(){
		return "user/addUser";
	}

	@RequestMapping("updateBatchUserDept")
	@ResponseBody
	public ServerResponse updateBatchUserDept(User user){
		userService.updateBatchUserDept(user);
		return ServerResponse.success();
	}

	@RequestMapping("updateUserInfo")
	@ResponseBody
	public ServerResponse updateUserInfo(User user) {
		String uuid = UUID.randomUUID().toString();
		String md5 = MD5Util.getStringMD5(uuid + user.getPassword());
		user.setSalt(uuid);
		user.setPassword(md5);
		userService.updateUserInfo(user);
		return ServerResponse.success();
	}


	@RequestMapping("exportExcelByDept")
	public void exportExcelByDept(User user, HttpServletResponse response){
		String workBookName = user.getWorkBookName();
		String sheetName = user.getSheetName();
		String sheetContentIds = user.getSheetContentIds();
		List<String> sheetNameList = new ArrayList<>();
		List<String> sheetContentIdsList = new ArrayList<>();
		if (StringUtils.isNotEmpty(sheetName)) {
			String[] sheetNameStr = sheetName.split(",");
			for (String s : sheetNameStr) {
				sheetNameList.add(s);
			}
			String[] sheetContentIdsStr = sheetContentIds.split(":");
			for (String s : sheetContentIdsStr) {
				sheetContentIdsList.add(s);
			}
		}
		//创建workBook
		XSSFWorkbook workbook = new XSSFWorkbook();
		for (int i = 0; i < sheetNameList.size(); i++) {
			XSSFSheet sheet = workbook.createSheet(sheetNameList.get(i));
			user.setIdsStr(sheetContentIdsList.get(i));
			List<User> userList = userService.findUserInfoList(user);
			buildSheet(userList, workbook, sheet);
		}
		FileUtil.excelDownload(workbook, response);
	}
	private void buildSheet(List<User> userList, XSSFWorkbook workbook, XSSFSheet sheet) {
		//创建标题
		buildTitle(workbook, sheet);
		//创建标题行
		buildHeader(sheet, workbook);
		//给行内单元格赋值
		buildBody(userList, sheet, workbook);
	}
	private void buildBody(List<User> userList, XSSFSheet sheet, XSSFWorkbook workbook) {
		int startRow = 10;
		int startCol = 7;

		XSSFCellStyle cellCommon = workbook.createCellStyle();
		cellCommon.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd"));

		for (int i = 0; i < userList.size(); i++) {
			XSSFRow contentRow = sheet.createRow(i + startRow);
			XSSFCell idCell = contentRow.createCell(startCol);
			idCell.setCellValue(userList.get(i).getId());
			XSSFCell userNameCell = contentRow.createCell(startCol + 1);
			userNameCell.setCellValue(userList.get(i).getUserName());
			XSSFCell salaryCell = contentRow.createCell(startCol + 2);
			salaryCell.setCellValue(userList.get(i).getSalary());
			XSSFCell realNameCell = contentRow.createCell(startCol + 3);
			realNameCell.setCellValue(userList.get(i).getRealName());
			XSSFCell birthdayCell = contentRow.createCell(startCol + 4);
			birthdayCell.setCellValue(userList.get(i).getBirthday());
			XSSFCell deptCell = contentRow.createCell(startCol + 5);
			deptCell.setCellValue(userList.get(i).getDept().getDeptName());
			birthdayCell.setCellStyle(cellCommon);
		}
	}
	private XSSFCellStyle buildHeaderStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return cellStyle;
	}
	private void buildHeader(XSSFSheet sheet, XSSFWorkbook workbook) {
		String[] headerArr = {"编号", "用户名称", "薪资", "真实姓名", "生日", "部门"};
		int startRow = 9;
		int startCol = 7;
		XSSFCellStyle cellStyle = buildHeaderStyle(workbook);
		XSSFRow row = sheet.createRow(startRow);
		for (int i = 0; i < headerArr.length; i++) {
			String s = headerArr[i];
			XSSFCell headerCell = row.createCell(i + startCol);
			headerCell.setCellValue(s);
			headerCell.setCellStyle(cellStyle);
		}
	}
	private void buildTitle(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow titleRow = sheet.createRow(6);
		XSSFCell titleRowCell = titleRow.createCell(7);
		titleRowCell.setCellValue("用户展示");
		buildTitleStyle(workbook, sheet, titleRowCell);
	}
	private void buildTitleStyle(XSSFWorkbook workbook, XSSFSheet sheet, XSSFCell titleRowCell) {
		//合并单元格
		CellRangeAddress rangeAddress = new CellRangeAddress(6, 8, 7, 12);
		sheet.addMergedRegion(rangeAddress);
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		//背景颜色
		cellStyle.setFillForegroundColor(HSSFColor.PINK.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//水平居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		//垂直居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//字体大小
		XSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setFontHeight(26);
		font.setBold(true);
		titleRowCell.setCellStyle(cellStyle);
		cellStyle.setFont(font);
	}

	@RequestMapping("toUpdateUserInfo")
	@ResponseBody
	public ServerResponse toUpdateUserInfo(Integer id) {
		User user = userService.toUpdateUserInfo(id);
		return  ServerResponse.success(user);
	}

	@RequestMapping("login")
	public @ResponseBody ServerResponse login(User user,HttpServletRequest request,HttpServletResponse response){
		User userDB=null;
			String fh_id = CookieUtil.readCookie(request, SystemConstants.JSESSIONID);
		try {
			String password = user.getPassword();
			String userName = user.getUserName();
			String imageCode = user.getImageCode();
			if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(imageCode)) {
				return ServerResponse .error(ResponseEnum.USERNAME_PWD_EMPTY_IMAGECODE);
			}
			//验证码判断
			String sessionCode = RedisUtil.get(SystemConstants.SESSION_CODE + fh_id);
			if (!imageCode.equals(sessionCode)){
				return ServerResponse.error(ResponseEnum.IMAGECODE_ERROR);
			}
			userDB = userService.userLogin(user);
			//用户名判断
			if (userDB==null) {
				return ServerResponse .error(ResponseEnum.USERNAME_ERROR);
			}
			//验证该用户是否被锁定
			if(userDB.getStatus()==1){
				return ServerResponse.error(ResponseEnum.USER_LOCKED);
			}
			//用户密码判断
			String passwordDB = userDB.getPassword();
			String salt = userDB.getSalt();
			String md5 = MD5Util.getStringMD5(salt + password);
			if (!passwordDB.equals(md5)) {
				Date dateNow = DateUtils.getDate(new Date());
				Date lastErrorTime = DateUtils.getDate(userDB.getLastErrorTime());
				//如果上次错误时间为空，或者当前时间在上次登录时间之后，说明不是一天内的错误
				if (userDB.getLastErrorTime()==null || dateNow.after(lastErrorTime)){
					userDB.setLastErrorTime(new Date());
					userDB.setErrorCount(1);
					userService.updateUserErrorCount(userDB);
					return ServerResponse .error(ResponseEnum.PASSWORD_ERROR);
				}
				//一天内连续三次失败 锁定用户
				if (userDB.getErrorCount()==2){
					userDB.setLastLoginTime(new Date());
					userService.updateUserStatus(userDB);
				}else {
					userDB.setErrorCount(userDB.getErrorCount()+1);
					userDB.setLastLoginTime(new Date());
					userService.updateUserErrorCount(userDB);
				}
				return ServerResponse .error(ResponseEnum.PASSWORD_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse .error();
		}

		//设置上次登录时间 和 今天登陆的次数
		if (userDB.getLoginTime()!=null && userDB.getLastLoginTime()!=null){
			userDB.setLastLoginTime(userDB.getLoginTime());
			Date date = DateUtils.getDateNow();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateNow = DateUtils.parseDate(sdf.format(date));
			Date lastLoginTime = DateUtils.parseDate(sdf.format(userDB.getLastLoginTime()));
			if (dateNow.after(lastLoginTime)){
				userDB.setLoginCount(1);
			}else {
				userDB.setLoginCount(userDB.getLoginCount()+1);
			}
		}else {
			Date d = new Date();
			userDB.setLoginTime(d);
			userDB.setLastLoginTime(d);
			userDB.setLoginCount(1);
		}
		Gson gson = new Gson();
		userDB.setPassword("");
		userDB.setSalt("");
		String userJson = gson.toJson(userDB);
		RedisUtil.set(SystemConstants.SESSION_USER+fh_id, userJson);
		RedisUtil.expire(SystemConstants.SESSION_USER+fh_id, 30*60);
		RedisUtil.delete(SystemConstants.SESSION_CODE+fh_id);
		userService.updateUser(userDB);
		return ServerResponse .success();
	}

	@RequestMapping("loginOut")
	public String loginOut(HttpServletResponse response, HttpServletRequest request){
		String cookie = CookieUtil.readCookie(request, SystemConstants.JSESSIONID);
		if (StringUtils.isNotEmpty(cookie)){
			CookieUtil.delCookie(response, SystemConstants.JSESSIONID, SystemConstants.SESSION_DOMAIN);
			RedisUtil.delete(SystemConstants.SESSION_CODE+cookie);
			RedisUtil.delete(SystemConstants.SESSION_USER+cookie);
		}
		return "redirect:/login.jsp";
	}

	@RequestMapping("showInfoPage")
	public String showInfoPage(){
		return "index";
	}


	@RequestMapping("toUserList")
	public String toUserList(){
		return "user/userList";
	}

	@RequestMapping("findUserList")
	@ResponseBody
	public ServerResponse findUserList(User user,HttpServletRequest request){
		//获取前台传过来的排序信息
		Map<String, String[]> parameterMap = request.getParameterMap();
		//获取满足的数据
		DataTableResult dataTableResult = userService.getUserDataTableResult(user, parameterMap);
		return ServerResponse.success(dataTableResult);
	}

	/*@RequestMapping("login")
	public @ResponseBody ServerResponse login(User user,HttpServletRequest request){
		User userDB=null;
		try {
			String password = user.getPassword();
			String userName = user.getUserName();
			if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
				return ServerResponse .error(ResponseEnum.USERNAME_PWD_EMPTY.getCode(), ResponseEnum.USERNAME_PWD_EMPTY.getMessage());
			}
			userDB = userService.userLogin(user);
			if (userDB==null) {
				return ServerResponse .error(ResponseEnum.USERNAME_ERROR.getCode(), ResponseEnum.USERNAME_ERROR.getMessage());
			}
			String passwordDB = userDB.getPassword();
			if (!passwordDB.equals(password)) {
				return ServerResponse .error(ResponseEnum.PASSWORD_ERROR.getCode(), ResponseEnum.PASSWORD_ERROR.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse .error();
		}
		request.getSession().setAttribute("user", userDB);
		return ServerResponse .success();
	}*/
	
	
	/*@RequestMapping("login")
	public @ResponseBody ServerResponse login(User user,HttpServletRequest request){
		User userDB=null;
		try {
			String password = user.getPassword();
			String userName = user.getUserName();
			if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
				return ServerResponse .error(1000, "账户名或者密码不能为空");
			}
			userDB = userService.userLogin(user);
			if (userDB==null) {
				return ServerResponse .error(1001, "账户名错误");
			}
			String passwordDB = userDB.getPassword();
			if (!passwordDB.equals(password)) {
				return ServerResponse .error(1002, "密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse .error();
		}
		request.getSession().setAttribute("user", userDB);
		return ServerResponse .success();
	}*/
	
	/*@RequestMapping("login")
	public @ResponseBody ServerResponse login(User user,HttpServletRequest request){
		User userDB=null;
		try {
			String password = user.getPassword();
			String userName = user.getUserName();
			if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
				return ServerResponse .success(1000, "账户名或者密码不能为空");
			}
			userDB = userService.userLogin(user);
			if (userDB==null) {
				return ServerResponse .success(1001, "账户名错误");
			}
			String passwordDB = userDB.getPassword();
			if (!passwordDB.equals(password)) {
				return ServerResponse .success(1002, "账户名或者密码不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse .success(-1, "系统内部错误，请联系管理员");
		}
		request.getSession().setAttribute("user", userDB);
		return ServerResponse .success(200, "success");
	}*/
	
	
	
	/*@RequestMapping("login")
	public @ResponseBody Map<String,Object> login(User user,HttpServletRequest request){
		Map<String,Object> retMap = new HashMap<>();
		User userDB=null;
		try {
			String password = user.getPassword();
			String userName = user.getUserName();
			if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
				retMap.put("code", 1000);
				retMap.put("msg", "账户名或者密码不能为空");
				return retMap;
			}
			userDB = userService.userLogin(user);
			if (userDB==null) {
				retMap.put("code", 1001);
				retMap.put("msg", "账户名错误");
				return retMap;
			}
			String passwordDB = userDB.getPassword();
			if (!passwordDB.equals(password)) {
				retMap.put("code", 1002);
				retMap.put("msg", "密码错误");
				return retMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retMap.put("code", -1);
			retMap.put("msg", "系统内部错误，请联系管理员");
			return retMap;
		}
		retMap.put("code", 200);
		retMap.put("msg", "success");
		request.getSession().setAttribute("user", userDB);
		return retMap;
	}*/
	
	
}
