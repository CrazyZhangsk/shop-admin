<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Insert title here</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<style>
    ul {
        list-style: none;
    }
</style>
<body>
<form class="form-inline" action="<%=request.getContextPath() %>/product/add.jhtml" method="post"
      enctype="multipart/form-data">
    <ul id="productUl">
        <li>产品名称：<input type="text" name="productName"/></li>
        <li>产品价格：<input type="text" name="productPrice"/></li>
        <li>品牌类型：
            <select name="brand.id" id="brandId">
                <option value="-1">===请选择===</option>
            </select>
        </li>
        <%--<li>
            产品图片：<input type="file" name="productImage" id="newFile" onchange="showPicture(this)"/><br>
            <img alt="请上传产品图片" width="100px" id="showImg">
        </li>
        <li>产品子图片：<input type="file" name="childImages"/><img width="20"
                                                              src="<%=request.getContextPath()%>/images/add.png"
                                                              onclick="addInputFile()"/></li>--%>
        <li>
            <div class="row" style="width: 300px">
                <input id="mainImage" name="mainImage" type="file">
            </div>
        </li>
        <li>
            <div class="row" style="width: 550px">
                <input id="childImages" name="childImages" type="file" multiple class="file-loading">
            </div>
        </li>


    </ul>
    <input type="text" name="mainImagePath" id="mainImagePath">
    <input type="text" name="childImagePath" id="childImagePath">
    <input type="submit" class="btn btn-info" value="确认增加"/>
    <input type="reset" class="btn btn-default" value="重置内容"/>
</form>

<script type="text/javascript">
    $(function () {
        initBrandList();
    })

    $('#mainImage').fileinput({
        //初始化上传文件框
        language: "zh",//配置语言
        showUpload: true, //显示整体上传的按钮
        showRemove: true,//显示整体删除的按钮
        uploadAsync: true,//默认异步上传
        uploadLabel: "上传",//设置整体上传按钮的汉字
        removeLabel: "移除",//设置整体删除按钮的汉字
        uploadClass: "btn btn-primary",//设置上传按钮样式
        showCaption: true,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        uploadUrl: "<%=request.getContextPath()%>/product/uploadMainImage.jhtml",
        maxFileSize: 9999,//文件大小限制
        maxFileCount: 1,//允许最大上传数，可以多个，
        enctype: 'multipart/form-data',
        allowedFileExtensions: ["jpg", "png", "gif", "docx", "zip", "xlsx", "txt"], /*上传文件格式限制*/
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        showBrowse: true,
        browseOnZoneClick: true,
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
    })
    $("#mainImage").on("fileuploaded", function (event, data, previewId, index) {
        console.log(data.response.uploadedName);
        $("#mainImagePath").val(data.response.uploadedName)
    })

    $('#childImages').fileinput({
        //初始化上传文件框
        language: "zh",//配置语言
        showUpload: true, //显示整体上传的按钮
        showRemove: true,//显示整体删除的按钮
        uploadAsync: true,//默认异步上传
        autoReplace: false,
        uploadLabel: "上传",//设置整体上传按钮的汉字
        removeLabel: "移除",//设置整体删除按钮的汉字
        uploadClass: "btn btn-primary",//设置上传按钮样式
        showCaption: true,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        uploadUrl: "<%=request.getContextPath()%>/product/uploadChildImage.jhtml",
        maxFileSize: 9999,//文件大小限制
        maxFileCount: 10,//允许最大上传数，可以多个，
        enctype: 'multipart/form-data',
        allowedFileExtensions: ["jpg", "png", "gif", "docx", "zip", "xlsx", "txt"], /*上传文件格式限制*/
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        initialPreviewAsData: true,
        validateInitialCount:true,
        showBrowse: true,
        browseOnZoneClick: true,
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
    })
    $("#childImages").on("fileuploaded", function (event, data, previewId, index) {
        $("#childImagePath").val($("#childImagePath").val() + "," + data.response.uploadedName);
        console.log(data.response.uploadedName);
    })


    function addInputFile() {
        $("#productUl").append('<li>产品子图片：<input type="file" name="childImages"/><img width="20" src="<%=request.getContextPath()%>/images/del.png" onclick="delInputFile(this)"/></li>')
    }

    function delInputFile(obj) {
        $(obj).parent().remove()
    }

    function showPicture(obj) {
        /* 获取file对象 */
        var pointFile = document.getElementById("newFile").files[0];
        /* 创建 fileReader对象 */
        var reads = new FileReader();
        /* 通过fileReader对象的readAsDataURL方法 将文件读取为 url类型 */
        reads.readAsDataURL(pointFile);
        /* 加载方法 更新目标img标签的src路径 */
        reads.onload = function (e) {
            document.getElementById("showImg").src = this.result;
        }
    }
</script>
</body>
</html>