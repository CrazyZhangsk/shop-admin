/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:User.java 
 * 包名:com.fh.shop.backend.po.user 
 * 创建日期:2019年1月7日下午3:59:01 
 * Copyright (c) 2019, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.user;

import com.fh.shop.backend.po.DataTablePage;
import com.fh.shop.backend.po.Page;
import com.fh.shop.backend.po.department.Department;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：User    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2019年1月7日 下午3:59:01    
 * 修改人：zsk     
 * 修改时间：2019年1月7日 下午3:59:01    
 * 修改备注：       
 * @version </pre>    
 */
public class User extends DataTablePage implements Serializable{

	private static final long serialVersionUID = 8427303360999186197L;

	private Integer id;

	private String userName;

	private String password;

	private String imageCode;

	private String salt;

	private Integer status;

	private Integer errorCount;

	private String headerImgPath;

	private String oldHeaderImgPath;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date minBirthday;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date maxBirthday;

	private String realName;

	private Double salary;

	private Double minSalary;

	private Double maxSalary;

	private Integer sex;

	private String birthdayStr;

	private String idsStr;

	private List<Integer> idsList = new ArrayList<>();

	private Department dept = new Department();

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastErrorTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastLoginTime;

	private Integer loginCount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date loginTime;

	private String sort;

	private String sortField;

	//导出Excel字段
	private String workBookName;

	private String sheetName;

	private String sheetContentIds;


	public String getWorkBookName() {
		return workBookName;
	}

	public void setWorkBookName(String workBookName) {
		this.workBookName = workBookName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getSheetContentIds() {
		return sheetContentIds;
	}

	public void setSheetContentIds(String sheetContentIds) {
		this.sheetContentIds = sheetContentIds;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public Date getLastErrorTime() {
		return lastErrorTime;
	}

	public void setLastErrorTime(Date lastErrorTime) {
		this.lastErrorTime = lastErrorTime;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getMinBirthday() {
		return minBirthday;
	}

	public void setMinBirthday(Date minBirthday) {
		this.minBirthday = minBirthday;
	}

	public Date getMaxBirthday() {
		return maxBirthday;
	}

	public void setMaxBirthday(Date maxBirthday) {
		this.maxBirthday = maxBirthday;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthdayStr() {
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}

	public Double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public List<Integer> getIdsList() {
		return idsList;
	}

	public void setIdsList(List<Integer> idsList) {
		this.idsList = idsList;
	}

	public String getIdsStr() {
		return idsStr;
	}

	public void setIdsStr(String idsStr) {
		this.idsStr = idsStr;
	}

	public String getHeaderImgPath() {
		return headerImgPath;
	}

	public void setHeaderImgPath(String headerImgPath) {
		this.headerImgPath = headerImgPath;
	}

	public String getOldHeaderImgPath() {
		return oldHeaderImgPath;
	}

	public void setOldHeaderImgPath(String oldHeaderImgPath) {
		this.oldHeaderImgPath = oldHeaderImgPath;
	}
}
