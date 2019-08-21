<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌展示页面</title>
</head>
<c:if test="${!empty brandList }">
	<table border="1px" cellpadding="0px" cellspacing="0px" width="70%" id="brandTable">
		<thead>
			<tr>
				<th width="200px">
					<a href="javaScript:checkAll(true)">全选</a>/
					<a href="javaScript:reCheck()">反选</a>/
					<a href="javaScript:deleteBatch()">批量删除</a>
				</th>
				<th>编号</th>
				<th>品牌名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${brandList}" var="brand">
				<tr>
					<td align="center"><input type="checkbox" name="check" value="${brand.id}" onclick="this.checked=!this.checked" /></td>
					<td>${brand.id}</td>
					<td data-id="'${brand.id}'">${brand.brandName}</td>
					<td>
						<a href="javascript:editBrand('${brand.id}')">修改</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
<c:if test="${empty brandList }">
<h1><font color="red">没有相关数据</font></h1>
</c:if>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
</html>