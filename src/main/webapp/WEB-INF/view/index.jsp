<%@ page import="com.fh.shop.backend.po.user.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
<title>选择</title>
</head>
<div class="container-fluid" style="margin-top: 51px;" id="showPage"></div>
<body>
<%--导航条--%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">管理系统</a>
        </div>

        <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav">
                <li><a href="javaScript:;" onclick="initPage('product')">商品管理<span class="sr-only">(current)</span></a>
                </li>
                <li><a href="javaScript:;" onclick="initPage('brand')">品牌管理</a></li>
                <li><a href="javaScript:;" onclick="initPage('user')">用户管理</a></li>
                <li><a href="javaScript:;" onclick="initPage('log')">日志管理</a></li>
                <li><a href="javaScript:;" onclick="initPage('area')">地区管理</a></li>
                <li><a href="javaScript:;" onclick="initPage('client')">会员管理</a></li>
            </ul>
            <div style="padding-left: 900px;padding-top: 15px">
                <%
                    User user = (User) request.getSession().getAttribute("user");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                %>
                <font color="#f8f8ff">欢迎<%=user.getUserName()%>今天第<%=user.getLoginCount()%>
                    次登录，上次登录时间为：<%=sdf.format(user.getLastLoginTime())%>
                </font>
                <a href="/user/loginOut.jhtml">退出</a>
            </div>
        </div>
    </div>

</nav>
<script type="text/javascript">
    //点击导航条进入不同页面
    function initPage(showName) {
        if (showName === "brand") {
            $.ajax({
                url: "/brand/toBrandList.jhtml",
                type: "post",
                success: function (data) {
                    $("#showPage").html(data)
                }
            })
        }
        if (showName === "product") {
            $.ajax({
                url: "/product/toProductList.jhtml",
                type: "post",
                success: function (data) {
                    $("#showPage").html(data)
                }
            })
        }
        if (showName === "user") {
            $.ajax({
                url: "/user/toUserList.jhtml",
                type: "post",
                success: function (data) {
                    $("#showPage").html(data)
                }
            })
        }
        if (showName === "log") {
            $.ajax({
                url: "/log/toLogList.jhtml",
                type: "post",
                success: function (data) {
                    $("#showPage").html(data)
                }
            })
        }
        if (showName === "area") {
            $.ajax({
                url: "/area/toAreaList.jhtml",
                type: "post",
                success: function (data) {
                    $("#showPage").html(data)
                }
            })
        }
        if (showName === "client") {
            $.ajax({
                url: "/request/toClientList.jhtml",
                type: "post",
                success: function (data) {
                    $("#showPage").html(data)
                }
            })
        }

    }
</script>
</body>
</html>