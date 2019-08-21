<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>产品修改</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<form class="form-inline" action="<%=request.getContextPath() %>/product/updateProduct.jhtml" method="post" enctype="multipart/form-data">
    <div style="display: none">
        <input name="productImage" type="file" id="newFile" onchange="showPicture()"/>
    </div>
    <table class="table table-bordered" border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>产品名称：</td>
            <td><input type="text" class="form-control" name="productName" value="${product.productName }"/></td>
        </tr>
        <tr>
            <td>产品价格：</td>
            <td><input type="text" class="form-control" name="productPrice" value="${product.productPrice }"/></td>
        </tr>
        <tr>
            <td>品牌名：</td>
            <td>
                <select name="brand.id" id="brandId">
                    <option value="-1">===请选择===</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>产品图片：</td>
            <td>
                <img src="<%=request.getContextPath()%>${product.productImagePath}" alt="嗷，图片加载错误" width="100px"/>
                <%--<img id="showImg" src="<%=request.getContextPath()%>/images/add.png" title="请上传新图片" alt="嗷，图片加载错误"
                     width="100px" height="100px" onclick="addProductImage()"/>--%>
                <div class="row" style="width: 300px">
                    <input id="mainImage" name="mainImage" type="file">
                </div>
            </td>
        </tr>
        <tr data-flag="childImages">
            <td>产品子图片：</td>
            <td>
                <%--<input id="childImages" name="childImages" onchange="showChildImage(this)" type="file"/>
                <img width="20px" src="<%=request.getContextPath()%>/images/add.png" onclick="addInputFile()"/>--%>
                    <div class="row" style="width: 550px">
                        <input id="childImages" name="childImages" type="file" multiple class="file-loading">
                    </div>
            </td>
        </tr>
        <tr>
            <td>已有子图片：</td>
            <td>
                <c:forEach items="${imageList}" var="images" varStatus="image">
						<span data-id="${images.id}" onmouseover="showButton(this)">
							<img src="<%=request.getContextPath()%>${images.imagePath}" width="100px" height="100px"/>
						</span>
                    <c:if test="${image.count!=0&&image.count%5==0}">
                        <br>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        <%--<tr>
            <td>新增子图片：</td>
            <td id="childImagesTd">
            </td>
        </tr>--%>
        <tr>
            <td><input class="btn btn-info" type="submit" value="确认修改"/></td>
            <td><input class="btn btn-default" type="reset" value="重置内容"/></td>
        </tr>
    </table>
    <input name="id" type="text" value="${product.id}"/>
    <input name="productImagePath" type="text" value="${product.productImagePath}"/>
    <input name="imagePaths" type="text">
    <input name="imageIds" type="text">
    <input type="text" name="mainImagePath" id="mainImagePath">
    <input type="text" name="childImagePath" id="childImagePath">
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

    function initBrandList() {
        $.ajax({
            url: "/brand/initBrandList.jhtml",
            type: "post",
            success: function (result) {
                var v_brandList = result.data
                if (result.code == 200) {
                    for (var i = 0; i < v_brandList.length; i++) {
                        $("#brandId").append("<option value='" + v_brandList[i].id + "'>" + v_brandList[i].brandName + "</option>")
                    }
                    $("#brandId").val('${product.brand.id}')
                }
            }
        })
    }

    function addInputFile() {
        $("tr[data-flag='childImages']").last().after('<tr data-flag="childImages"><td>产品子图片：</td><td><input onchange="showChildImage(this)" name="childImages" type="file"/><img width="20px" src="<%=request.getContextPath()%>/images/del.png" onclick="delInputFile(this)"/></td></tr>');
    }

    function delInputFile(obj) {
        $(obj).parents("tr").remove()
    }

    var path = "";

    /*展示删除按钮*/
    function showButton(obj) {
        path = $(obj).children().attr("src")
        $(obj).html('<img src="<%=request.getContextPath()%>/images/del.png" width="100px" height="100px"/>')
        $(obj).attr({"onmouseout": "hiddenButton(this)"})
        $(obj).attr({"onclick": "changeImage(this)"})
        $(obj).attr({"onmouseover": ""})
    }

    /*展示原有图片*/
    function hiddenButton(obj) {
        $(obj).html("<img src='" + path + "' width='100px' height='100px'/>")
        $(obj).attr({"onmouseover": "showButton(this)"})
    }

    /*删除子图片*/
    var v_delImagesPath = [];
    var v_delImagesId = [];

    function changeImage(obj) {
        var v_imageId = $(obj).attr("data-id");
        v_delImagesId.push(Number(v_imageId))
        $(obj).remove()
        v_delImagesPath.push(path);
        $("input[name='imagePaths']").val(v_delImagesPath)
        $("input[name='imageIds']").val(v_delImagesId)
    }

    /*点击图片的时候触发上传图片按钮*/
    function addProductImage() {
        return $("#newFile").click();
    }

    function showPicture() {
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

    function showChildImage(obj) {
        /* 获取file对象 */
        var pointFile = obj.files[0];
        /* 创建 fileReader对象 */
        var reads = new FileReader();
        /* 通过fileReader对象的readAsDataURL方法 将文件读取为 url类型 */
        reads.readAsDataURL(pointFile);
        /* 加载方法 更新目标img标签的src路径 */
        reads.onload = function (e) {
            var v_path = this.result;
            $("#childImagesTd").append("<img width='100px' src='" + v_path + "'/>")
        }
    }
</script>
</body>
</html>