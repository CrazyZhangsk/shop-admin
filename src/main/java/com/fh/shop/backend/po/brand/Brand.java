/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:Grand.java 
 * 包名:com.fh.shop.packend.po.grand 
 * 创建日期:2018年12月28日上午9:07:07 
 * Copyright (c) 2018, Zhangsk_t@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.brand;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fh.shop.backend.po.Page;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：Grand    
 * 类描述：    
 * 创建人：zsk    
 * 联系方式：Zhangsk_t@163.com
 * 创建时间：2018年12月28日 上午9:07:07    
 * 修改人：zsk     
 * 修改时间：2018年12月28日 上午9:07:07    
 * 修改备注：       
 * @version </pre>    
 */
public class Brand extends Page implements Serializable{

	
	private static final long serialVersionUID = 7548403963765706368L;

	private Integer id ;
	
	private String brandName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date entryTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date minUpdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date maxUpdate;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date minEntry;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date maxEntry;
	private String updateTimeStr;
	private String entryTimeStr;
	private String minEntryStr;
	private String maxEntryStr;


	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getMinEntryStr() {
		return minEntryStr;
	}
	public void setMinEntryStr(String minEntryStr) {
		this.minEntryStr = minEntryStr;
	}
	public String getMaxEntryStr() {
		return maxEntryStr;
	}
	public void setMaxEntryStr(String maxEntryStr) {
		this.maxEntryStr = maxEntryStr;
	}
	public String getEntryTimeStr() {
		return entryTimeStr;
	}
	public void setEntryTimeStr(String entryTimeStr) {
		this.entryTimeStr = entryTimeStr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getEntryTime() {
		return entryTime;
	}
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public Date getMinUpdate() {
		return minUpdate;
	}
	public void setMinUpdate(Date minUpdate) {
		this.minUpdate = minUpdate;
	}
	public Date getMaxUpdate() {
		return maxUpdate;
	}
	public void setMaxUpdate(Date maxUpdate) {
		this.maxUpdate = maxUpdate;
	}
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getMinEntry() {
		return minEntry;
	}
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setMinEntry(Date minEntry) {
		this.minEntry = minEntry;
	}
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getMaxEntry() {
		return maxEntry;
	}
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setMaxEntry(Date maxEntry) {
		this.maxEntry = maxEntry;
	}
}
