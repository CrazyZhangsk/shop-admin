package com.fh.shop.backend.po.log;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LogInfo implements Serializable{
	private static final long serialVersionUID = 4318777952140509617L;
	private String id;
	private String userName;
	private String content;
	private String status;
	private long executeTime;
	private Date createTime;
	private String ipAdd;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date minCreateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date maxCreateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIpAdd() {
		return ipAdd;
	}

	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}

	public Date getMinCreateTime() {
		return minCreateTime;
	}

	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}

	public Date getManCreateTime() {
		return maxCreateTime;
	}

	public void setManCreateTime(Date manCreateTime) {
		maxCreateTime = manCreateTime;
	}

	public long getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(long executeTime) {
		this.executeTime = executeTime;
	}



	
}
