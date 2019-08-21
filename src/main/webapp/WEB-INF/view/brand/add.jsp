<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌增加</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css"/>
</head>
<style>
	ul{
		list-style: none;
	}
</style>
<body>
	<form class="form-inline" action="<%=request.getContextPath() %>/brand/add.jhtml" method="post">
		<ul>
			<li>产品名称：<input class="form-control" type="text" name="brandName" /></li>
			<li><input class="btn btn-info" type="submit" value="确认增加" /></li>
			<li><input class="btn btn-default" type="reset" value="重置内容" /></li>
		</ul>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>