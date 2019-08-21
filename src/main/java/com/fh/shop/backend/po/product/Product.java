/** 
 * <pre>项目名称:shop_admin_t1 
 * 文件名称:Product.java 
 * 包名:com.fh.shop.packend.po.product 
 * 创建日期:2018年12月23日下午7:04:10 
 * Copyright (c) 2018, xxxxxx@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.product;

import java.io.Serializable;
import java.util.Date;

import com.fh.shop.backend.po.DataTablePage;
import org.springframework.format.annotation.DateTimeFormat;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop_admin_t1    
 * 类名称：Product    
 * 类描述：    
 * 创建人：zsk    
 * 创建时间：2018年12月23日 下午7:04:10    
 * 修改人：zsk     
 * 修改时间：2018年12月23日 下午7:04:10    
 * 修改备注：       
 * @version </pre>    
 */
public class Product extends DataTablePage implements Serializable{

	private static final long serialVersionUID = -6358681835639123692L;
	
	private Integer id;
	
	private String productName;

	private Float productPrice;
	
	private Float minPrice;
	
	private Float maxPrice;

	private Brand brand = new Brand();

	private String updateTimeStr;

	private String entryTimeStr;

	private String sortField;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date entryTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date minUpdateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date maxUpdateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date minEntryTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date maxEntryTime;

	private String sort;


	private String mainImagePath;

	private String childImagePath;

	private String productImagePath;

	private String imageIds;

	private String imagePaths;

	public String getImageIds() {
		return imageIds;
	}

	public String getImagePaths() {
		return imagePaths;
	}

	public void setImageIds(String imageIds) {
		this.imageIds = imageIds;
	}

	public void setImagePaths(String imagePaths) {
		this.imagePaths = imagePaths;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	public Date getEntryTime() {
		return entryTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public Date getMinUpdateTime() {
		return minUpdateTime;
	}
	public void setMinUpdateTime(Date minUpdateTime) {
		this.minUpdateTime = minUpdateTime;
	}
	public Date getMaxUpdateTime() {
		return maxUpdateTime;
	}
	public void setMaxUpdateTime(Date maxUpdateTime) {
		this.maxUpdateTime = maxUpdateTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	public Date getMinEntryTime() {
		return minEntryTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	public void setMinEntryTime(Date minEntryTime) {
		this.minEntryTime = minEntryTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	public Date getMaxEntryTime() {
		return maxEntryTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	public void setMaxEntryTime(Date maxEntryTime) {
		this.maxEntryTime = maxEntryTime;
	}
	public Float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
	public Float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getEntryTimeStr() {
		return entryTimeStr;
	}

	public void setEntryTimeStr(String entryTimeStr) {
		this.entryTimeStr = entryTimeStr;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getMainImagePath() {
		return mainImagePath;
	}

	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}

	public String getChildImagePath() {
		return childImagePath;
	}

	public void setChildImagePath(String childImagePath) {
		this.childImagePath = childImagePath;
	}
}
