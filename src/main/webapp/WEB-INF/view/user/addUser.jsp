<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
</head>
<body>
<form>
    <ul style="list-style: none;">
        <li>用户名:<input type="text" id="userName"/></li>
        <li>用户密码:<input type="password" id="password"/></li>
        <li>用户密码:<input type="text" id="imageCode"/></li>
        <li><img id="image" alt="验证码" src="<%=request.getContextPath()%>/image/code" onclick="checkImage()">&nbsp;&nbsp;&nbsp;&nbsp;<a href="JavaScript:checkImage()">看不清？换一张</a>
        <li>
            <input type="button" value="确认注册" onclick="addUser()"/>
            <input type="reset" value="重置"/>
        </li>
    </ul>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/md5.js"></script>
<script type="text/javascript">
    function addUser(){
        var v_userName = $("#userName").val();
        var v_password = $("#password").val();
        var v_imageCode = $("#imageCode").val();
        $.ajax({
            url:"<%=request.getContextPath()%>/user/addUser.jhtml",
            data:{
                "userName":v_userName,
                "password":str_md5(v_password),
                "imageCode":v_imageCode
            },
            type:"post",
            success:function(result){
                var v_code = result.code;
                var v_msg = result.message;
                if(v_code==200){
                    location.href="<%=request.getContextPath()%>/login.jsp";
                }else{
                    $("#warnSpan").html("<font color='red'>"+v_msg+"</font>")
                }
            }
        })
    }
    //验证码切换
    function checkImage(){
        $("#image").get(0).src="<%=request.getContextPath()%>/image/code?"+Math.random();
    }
</script>

</body>
</html>