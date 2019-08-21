<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌增加</title>
</head>
<style>
	ul{
		list-style: none;
	}
</style>
<body>
	<form action="<%=request.getContextPath() %>/brand/add.jhtml" method="post">
		<ul>
			<li>产品名称：<input type="text" name="brandName" /></li>
			<li><input type="submit" value="确认增加" /></li>
			<li><input type="reset" value="重置内容" /></li>
		</ul>
	</form>
</body>
</html>