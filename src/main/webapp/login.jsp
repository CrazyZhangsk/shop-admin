<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>
    <style type="text/css">
        #canvs {
            background-color: yellow;
            width: 400px;
            margin: 100px auto;
            text-align: center;
            padding: 10px;
        }
    </style>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/bootstrap/css/htmleaf-demo.css">
    <link href="<%=request.getContextPath()%>/js/bootstrap/css/signin.css" rel="stylesheet">

</head>
<body>
<div id="content">
    <img src="" class="copyImage">
    <div class="htmleaf-container"  style="width: 300px; height: 100px">
        <%--<div class="signin">--%>
        <div class="signin-head" id="letter"><img src="images/test/head_120.png" alt="" class="img-circle"></div>
        <div><span id="warnSpan"></span></div>
        <form class="form-signin" role="form">
            <input type="text" id="userName" class="form-control" placeholder="用户名" required autofocus/>
            <input type="password" id="password" class="form-control" placeholder="密码" required/>
            <input type="password" id="imageCode" class="form-control" placeholder="验证码" required/>
            <img id="image" alt="验证码" src="<%=request.getContextPath()%>/image/code" onclick="checkImage()">&nbsp;&nbsp;&nbsp;&nbsp;<a
                href="JavaScript:checkImage()">看不清？换一张</a>
            <button id="login" class="btn btn-lg btn-warning btn-block" type="button">登录</button>
            <button onclick="addUser()" class="btn btn-lg btn-warning btn-block" type="button">注册</button>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记住我
            </label>
        </form>
    </div>
</div>
<div id="canvs">
    <div class="title">如梦令·昨夜雨疏风骤</div>
    <div>[宋] 李清照</div>
    <div>昨夜雨疏风骤，浓睡不消残酒，试问卷帘人，却道海棠依旧。</div>
    <div>知否，知否，应是绿肥红瘦。</div>
</div>
<div style="width: 400px;margin:50px auto;">
    <input class="button" type="button" value="button" onclick="downloadForJS()">测试</input>
</div>
<a id="dl-hidden" style="display:none;"></a>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/html2canvas.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/FileSaver.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/md5.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
</body>


<script type="text/javascript">
    $(function () {

        $("#login").bind("click", function () {
            var v_userName = $("#userName").val();
            var v_password = $("#password").val();
            var v_imageCode = $("#imageCode").val();
            $.ajax({
                url: "<%=request.getContextPath()%>/user/login.jhtml",
                data: {
                    "userName": v_userName,
                    /*"password":str_md5(v_password),*/
                    "password": v_password,
                    "imageCode": v_imageCode
                },
                type: "post",
                success: function (result) {
                    var v_code = result.code;
                    var v_msg = result.message;
                    if (v_code == 200) {
                        location.href = "<%=request.getContextPath()%>/user/showInfoPage.jhtml";
                    } else {
                        $("#warnSpan").html("<font color='red'>" + v_msg + "</font>")
                    }
                }
            })
        })

    })
    /*window.onload = function () {
        var IMAGE_URL;
        function takeScreenshot() {
            var shareContent = document.getElementById('letter');//需要截图的包裹的（原生的）DOM 对象
            var width = shareContent.offsetWidth; //获取dom 宽度
            var height = shareContent.offsetHeight; //获取dom 高度
            var canvas = document.createElement("canvas"); //创建一个canvas节点
            var scale = 1; //定义任意放大倍数 支持小数
            canvas.width = width * scale; //定义canvas 宽度 * 缩放
            canvas.height = height * scale; //定义canvas高度 *缩放
            canvas.getContext("2d").scale(scale, scale); //获取context,设置scale

            // var rect = shareContent.getBoundingClientRect();//获取元素相对于视察的偏移量
            // canvas.getContext("2d").translate(-rect.left,-rect.top);//设置context位置，值为相对于视窗的偏移量负值，让图片复位
            var opts = {
                scale: scale, // 添加的scale 参数
                canvas: canvas, //自定义 canvas
                logging: true, //日志开关
                width: width, //dom 原始宽度
                height: height, //dom 原始高度
                backgroundColor: 'transparent',
            };
            html2canvas(shareContent, opts).then(function (canvas) {
                IMAGE_URL = canvas.toDataURL("image/png");
                $('.copyImage').attr('src', IMAGE_URL);
            })
        }

        function dataURLtoBlob(dataurl) {
            var arr = dataurl.split(','),
                mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]),
                n = bstr.length,
                u8arr = new Uint8Array(n)
            while (n--) {
                u8arr[n] = bstr.charCodeAt(n)
            }
            return new Blob([u8arr], {type: mime})
        }

        function downloadBase64(dataUrl, filename) {
            var imageFile, href;
            var downloadLink = document.getElementById('dl-hidden');
            try {
                var blob = dataURLtoBlob(dataUrl)
                var href = window.URL.createObjectURL(blob)
                downloadLink.download = filename
                downloadLink.href = href
                downloadLink.click()
            } catch (err) {
            } finally {
                if (href) {
                    window.URL.revokeObjectURL(href)
                }
            }

        }
        takeScreenshot();
        $("#save-local").click(function(){
            downloadBase64(IMAGE_URL, '合成图.png')
            $('.copyImage').attr('src', "");
        })
    }*/

    //验证码切换
    function checkImage() {
        $("#image").get(0).src = "<%=request.getContextPath()%>/image/code?" + Math.random();
        takeScreenshot();
        console.log("刷新了")
    }
    //注册用户
    function addUser() {
        location.href = "<%=request.getContextPath()%>/user/toAddUserPage.jhtml"
    }


    function downloadForJS() {
        var shareContent = $("#canvs").get(0);//需要截图的包裹的（原生的）DOM 对象
        var width = shareContent.outerWidth; //获取dom 宽度offsetWidth
        var height = shareContent.outerHeight; //获取dom 高度offsetHeight
        var canvas = document.createElement("canvas"); //创建一个canvas节点
        var scale = 2; //定义任意放大倍数 支持小数
        canvas.width = width * scale; //定义canvas 宽度 * 缩放
        canvas.height = height * scale; //定义canvas高度 *缩放
        canvas.getContext("2d").scale(scale, scale); //获取context,设置scale

        var rect = shareContent.getBoundingClientRect();//获取元素相对于视察的偏移量
         canvas.getContext("2d").translate(-rect.left,-rect.top);//设置context位置，值为相对于视窗的偏移量负值，让图片复位
        var opts = {
            letterRendering: true,
            allowTaint:true,
            scale: scale, // 添加的scale 参数
            canvas: canvas, //自定义 canvas
            logging: true, //日志开关
            width: width, //dom 原始宽度
            height: height, //dom 原始高度
            useCORS:true,
            backgroundColor: 'transparent',
            timeout:500,
        };
        //使用html2canvas 转换html为canvas
        html2canvas(shareContent, opts).then(function (canvas) {
            var imgUri = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream"); // 获取生成的图片的url 　
            var saveLink = document.createElement('a');
            saveLink.href = imgUri;
            saveLink.download = 'downLoad.png';
            saveLink.click();
        });
    }




</script>
</html>
