<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌修改页面</title>
</head>
<style>
	ul{
		list-style: none;
	}
</style>
<body>
	<form action="<%=request.getContextPath() %>/brand/updateBrand.jhtml" method="post">
		<ul>
			<li>品牌名称：<input type="text" name="brandName" value="${brand.brandName }"/></li>
			<li>
				<input name="id" type="hidden" value="${brand.id}"/>
			</li>
			<li><input type="submit" value="确认修改" /></li>
			<li><input type="reset" value="重置内容" /></li>
		</ul>
	</form>
</body>
</html>