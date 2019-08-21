/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:BaseController.java 
 * 包名:com.fh.shop.backend.controller 
 * 创建日期:2018年12月26日下午5:35:41 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller;

import com.fh.shop.backend.po.FileInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController{

	public void outJson(String json, HttpServletResponse response) {
		//response.setContentType("application/json;charset=utf-8");//设置响应内容类型，并设置编码
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			 writer = response.getWriter();//通过response获得writer
			 writer.write(json);//通过write将json字符串响应到客户端
			 writer.flush();//清空
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				writer.close();
				writer = null;
			}
		}
	}
	
	
	public  String getIpAddress(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
	}

	/*获取项目根目录*/
	protected String getRootPath(HttpServletRequest request){
		String rootPath = request.getServletContext().getRealPath("/");
		return rootPath;
	}

	/*获取上传的文件*/
	protected FileInfo getUploadedFile(@RequestParam MultipartFile productImage) throws IOException {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(productImage.getOriginalFilename());
		fileInfo.setFileSize(productImage.getSize());
		fileInfo.setIs(productImage.getInputStream());
		return fileInfo;
	}

	/*获取上传的子文件*/
	protected List<FileInfo> getChildFileList(@RequestParam MultipartFile[] childImages) throws IOException {
		List<FileInfo> fileList = new ArrayList<>();
		for (MultipartFile childImage : childImages) {
			if (childImage.getSize()>0){
				FileInfo file = new FileInfo();
				file.setIs(childImage.getInputStream());
				file.setFileSize(childImage.getSize());
				file.setFileName(childImage.getOriginalFilename());
				fileList.add(file);
			}
		}
		return fileList;
	}
}
