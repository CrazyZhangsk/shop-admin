<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2019/2/17
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>产品字图片展示</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css"/>
</head>
<body>
<div class="row">
    <c:forEach items="${imageList}" var="images">
        <div class="col-xs-6 col-md-3">
            <a href="#" class="thumbnail">
                <img src="<%=request.getContextPath()%>${images.imagePath}"/>
            </a>
        </div>
    </c:forEach>
</div>
<%--<%
    int i = 0;
%>
<c:forEach items="${imageList}" var="images">
    <img src="<%=request.getContextPath()%>${images.imagePath}" width="200px" height="200px"/>
    <%
        i++;
        if (i!=0 && i%5==0){
    %>
    <br>
    <%
        }
    %>
</c:forEach>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
