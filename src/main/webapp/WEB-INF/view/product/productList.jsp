<%@ page import="com.fh.shop.backend.po.user.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>产品DataTable</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>

<%--展示页面--%>
<div class="container-fluid">
    <div class="col-md-12" >
        <%--条件查询--%>
        <div class="panel panel-info">
            <div class="panel-heading">
                条件查询
            </div>
            <div class="panel-body">
                <div id="page-content-wrapper ">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <form class="form-horizontal" role="form" id="productForm">
                                    <div class="form-group">
                                        <label class="col-md-2 control-label" for="productName">产品名称</label>
                                        <div class="col-md-2">
                                            <input class="form-control" id="productName" type="text"
                                                   placeholder="荣耀V20"/>
                                        </div>
                                        <label class="col-md-2 control-label">产品价格</label>
                                        <div class="col-md-4">
                                            <div class="input-group">
                                                <input type="text" class="input-sm form-control" name="start"
                                                       id="minPrice"
                                                       placeholder="最小价格"/>
                                                <span class="input-group-addon">
								<i class="glyphicon glyphicon-yen"></i>
								</span>
                                                <input type="text" class="input-sm form-control" name="end"
                                                       id="maxPrice"
                                                       placeholder="最大价格"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label" for="brandId">品牌名称</label>
                                        <div class="col-md-2">
                                            <select name="brand.id" id="brandId" class="input-md form-control">
                                                <option value="-1">===请选择===</option>
                                            </select>
                                        </div>
                                        <label class="col-md-2 control-label">修改时间</label>
                                        <div class="col-md-4">
                                            <div class="input-group">
                                                <input type="text" readonly class="form_datetime input-md form-control"
                                                       id="minUpdateTime">
                                                <span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
								</span>
                                                <input type="text" readonly class="form_datetime input-md form-control"
                                                       id="maxUpdateTime">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">录入时间</label>
                                        <div class="col-md-4">
                                            <div class="input-group">
                                                <input type="text" readonly class="form_datetime input-md form-control"
                                                       id="minEntryTime">
                                                <span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
								</span>
                                                <input type="text" readonly class="form_datetime input-md form-control"
                                                       id="maxEntryTime">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix form-actions" style="text-align:center">
                                        <button class="btn btn-primary" type="button" onclick="initSearchData()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            高级查询
                                        </button>
                                        <button class="btn btn-default" type="reset">
                                            <i class="glyphicon glyphicon-refresh"></i>
                                            重置
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%-- 你是魔鬼中的天使 所以送我心碎的方式  是让我笑到最后一秒为止  才发现自己胸口插了一把刀子--%>
        <%--添加&删除--%>
        <div class="panel panel-info">
            <div class="panel-heading" style="text-align: right">
                <button type="button" class="btn btn-info" onclick="showAddProductPage()">
                    <span class="glyphicon glyphicon-plus"></span>
                    添加产品
                </button>
                <button type="button" class="btn btn-danger" onclick="deleteBatch()">
                    <span class="glyphicon glyphicon-remove"></span>
                    批量删除
                </button>
                <button type="button" class="btn btn-info" onclick="exportProductExcel()">
                    <span class="glyphicon glyphicon-arrow-down"></span>
                    导出Excel
                </button>
                <button type="button" class="btn btn-info" onclick="exportExcelByType()">
                    <span class="glyphicon glyphicon-arrow-down"></span>
                    按类型导出Excel
                </button>
            </div>
            <div class="panel-body">
                <%--展示列表--%>
                <div class="panel panel-primary">
                    <table id="productTable" class="table table-hover table-bordered" style="width:100%">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox" class="checkAll"/>
                            </th>
                            <th>编号</th>
                            <th>产品名称</th>
                            <th>产品价格</th>
                            <th>修改时间</th>
                            <th>录入时间</th>
                            <th>品牌</th>
                            <th>图片</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>
                                <input type="checkbox" class="checkAll"/>
                            </th>
                            <th>编号</th>
                            <th>产品名称</th>
                            <th>产品价格</th>
                            <th>修改时间</th>
                            <th>录入时间</th>
                            <th>品牌</th>
                            <th>图片</th>
                            <th>操作</th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%--增加页面--%>
<div id="addProductPage" style="display: none">
    <form class="form-horizontal" id="addProductForm">
            <div class="form-group">
                <label class="col-sm-3 control-label">产品名称：</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="productName" id="add_productName">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">产品价格：</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="productPrice" id="add_productPrice">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">品牌名称：</label>
                <div class="col-sm-3">
                    <select class="form-control" name="brand.id" id="add_brandId">
                        <option value="-1">===请选择===</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">主图片：</label>
                <div class="col-sm-4">
                    <input id="mainImage" name="mainImage" type="file">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">子图片：</label>
                <div class="col-sm-8">
                    <input id="childImages" name="childImages" type="file" multiple>
                </div>
            </div>
            <input type="text" name="mainImagePath" id="mainImagePath">
            <input type="text" name="childImagePath" id="childImagePath">
        </form>
</div>



<script type="text/javascript">
    $(function () {
        initProductTable();
        initEvent();
        dateController();
        initBrandList();
    });

    var retTable;
    //datatable
    function initProductTable() {
        retTable = $('#productTable').DataTable({
            "processing": true,
            "serverSide": true,
            "searching": false,
            "paginationType": "full_numbers",
            "aLengthMenu": [5, 10, 15, 20],
            "bSortable": [],
            "language": {
                "url": "/js/datatable/Chinese.json",

            },
            "ajax": {
                "url": "<%=request.getContextPath()%>/product/searchData.jhtml",
                //将后台传到前台的数据与转换成组件能识别的格式
                "dataSrc": function (result) {
                    result.draw = result.data.draw;
                    result.recordsFiltered = result.data.recordsFiltered;
                    result.recordsTotal = result.data.recordsTotal;
                    return result.data.data;
                }
            },
            "columns": [
                {
                    "data": "id",
                    "bSortable": false,
                    "sClass": "text-center",
                    "render": function (data, type, full, meta) {
                        return "<input type='checkbox'  class='checkChild' value='" + data + "'/>";
                    },
                },
                {
                    "data": "id",
                    "bSortable": false,
                    "sClass": "text-center",
                },
                {
                    "data": "productName",
                    "bSortable": false,
                    "sClass": "text-center",
                },
                {
                    "data": "productPrice",
                    "sClass": "text-center",
                },
                {
                    "data": "updateTimeStr",
                    "sClass": "text-center",
                },
                {
                    "data": "entryTimeStr",
                    "sClass": "text-center",
                },
                {
                    "data": "brandName",
                    "bSortable": false,
                    "sClass": "text-center",
                },
                {
                    "data": "productImagePath",
                    "sClass": "text-center",
                    "bSortable": false,
                    "render": function (data, type, full, meta) {
                        return "<img src='<%=request.getContextPath()%>" + data + "'width='50px'/>";
                    },
                },
                {
                    "data": "id",
                    "sClass": "text-center",
                    "bSortable": false,
                    "render": function (data, type, full, meta) {
                        return '<div class="btn-group"><button onclick="toUpdateProduct(' + data + ')" class="btn btn-info "><span class="glyphicon glyphicon-pencil"></span>修改</button><button class="btn btn-danger" onclick="deleteProduct(' + data + ')"><span class="glyphicon glyphicon-remove"></span>删除</button><button class="btn btn-success" onclick="findProductImage(' + data + ')"><span class="glyphicon glyphicon-search"></span>子图片</button></div>';
                    }
                }
            ],
            "drawCallback": function () {
                $("#productTable tbody tr input[type='checkbox']").each(function () {
                    if (isExist(this.value)) {
                        $(this).closest("tr").css({"background": "pink"})
                        this.checked = true;
                    }
                })
            }
        });
    }

    //条件查询
    function initSearchData() {
        var v_productName = $("#productName").val();
        var v_minPrice = $("#minPrice").val();
        var v_maxPrice = $("#maxPrice").val();
        var v_minUpdateTime = $("#minUpdateTime").val();
        var v_maxUpdateTime = $("#maxUpdateTime").val();
        var v_minEntryTime = $("#minEntryTime").val();
        var v_maxEntryTime = $("#maxEntryTime").val();
        var v_brandId = $("#brandId").val();
        var param = {};
        param.maxEntryTime = v_maxEntryTime;
        param.minEntryTime = v_minEntryTime;
        param.maxUpdateTime = v_maxUpdateTime;
        param.minUpdateTime = v_minUpdateTime;
        param.maxPrice = v_maxPrice;
        param.minPrice = v_minPrice;
        param.productName = v_productName;
        param["brand.id"] = v_brandId;
        retTable.settings()[0].ajax.data = param;
        retTable.ajax.reload();
    }

    //点击行变色 并回显
    var v_rowIds = [];

    function initEvent() {
        $("#productTable tbody").on("click", "tr", function () {
            var checkProduct = $(this).find("input")[0];
            if (checkProduct.checked) {
                checkProduct.checked = false;
                $(this).css({"background": ""});
                deleteId(checkProduct.value)
            }
            else {
                checkProduct.checked = true;
                $(this).css({"background": "pink"});
                v_rowIds.push(checkProduct.value);
            }
        })

    }

    //回显的时候判断是否存在
    function isExist(id) {
        for (var i = 0; i < v_rowIds.length; i++) {
            if (id == v_rowIds[i]) {
                return true;
            }
        }
    }

    //删除id
    function deleteId(id) {
        for (var i = v_rowIds.length - 1; i >= 0; i--) {
            if (v_rowIds[i] == id) {
                v_rowIds.splice(i, 1);
                break;
            }
        }
    }

    //全选
    $(".checkAll").click(function () {
        var check = $(this).prop("checked");
        $(".checkChild").prop("checked", check);
    });

    //时间控件
    function dateController() {
        $("#minUpdateTime").datetimepicker({
            "format": "yyyy-mm-dd",
            "language": "zh-CN",
            "minView": 'month',
            "clearBtn": "true",
            "autoclose": "true"
        });
        $("#maxUpdateTime").datetimepicker({
            "format": 'yyyy-mm-dd',
            "language": "zh-CN",
            "minView": "month",
            "clearBtn": "true",
            "autoclose": "true"
        });
        $("#minEntryTime").datetimepicker({
            "format": 'yyyy-mm-dd hh:00',
            "language": "zh-CN",
            "minView": "day",
            "clearBtn": "true",
            "autoclose": "true"
        });
        $("#maxEntryTime").datetimepicker({
            "format": 'yyyy-mm-dd hh:00',
            "language": "zh-CN",
            "minView": "day",
            "clearBtn": "true",
            "autoclose": "true"
        });

    }

    //查找子图片
    function findProductImage(id) {
        location.href = "<%=request.getContextPath()%>/image/findProductImage/" + id + ".jhtml";
    }

    //增加数据
    function insertProduct() {
        location.href = "<%=request.getContextPath()%>/product/toAdd.jhtml";
    }

    //修改
    function toUpdateProduct(id) {
        location.href = "<%=request.getContextPath()%>/product/toUpdateById/" + id + ".jhtml";
    }

    /*导出Excel表格*/
    function exportProductExcel() {
        var v_productForm = document.getElementById("productForm")
        v_productForm.action = "<%=request.getContextPath()%>/product/exportProductExcel.jhtml";
        v_productForm.submit();
    }

    /*按类型导出Excel*/
    function exportExcelByType() {
        var v_productForm = document.getElementById("productForm")
        v_productForm.action = "<%=request.getContextPath()%>/product/exportExcelByType.jhtml";
        v_productForm.submit();
    }

    /* 单个删除 */
    function deleteProduct(id) {
        bootbox.confirm({
            message: "你确定定要删除么？",
            size: "small",
            title: "提示信息",
            buttons: {
                confirm: {
                    label: '<span class="glyphicon glyphicon-ok"></span>确定',
                    className: "btn-success"
                },
                cancel: {
                    label: '<span class="glyphicon glyphicon-remove"></span>取消',
                    className: "btn-danger"
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajax({
                        url: "<%=request.getContextPath()%>/product/deleteProduct.jhtml",
                        data: {
                            "id": id,
                        },
                        type: "post",
                        async: true,
                        success: function (result) {
                            if (result.code == 200) {
                                initSearchData();
                            } else {
                                bootbox.alert(result.message)
                            }
                        },
                    });
                }

            }
        })
    }

    /* 批量删除 */
    function deleteBatch() {
        bootbox.confirm({
            message: "你确定定要删除么？",
            size: "small",
            title: "提示信息",
            buttons: {
                confirm: {
                    label: '<span class="glyphicon glyphicon-ok"></span>确定',
                    className: "btn-success"
                },
                cancel: {
                    label: '<span class="glyphicon glyphicon-remove"></span>取消',
                    className: "btn-danger"
                }
            },
            callback: function (result) {
                if (result) {
                    if (v_rowIds.length == 0) {
                        bootbox.alert("请至少选择一项");
                    } else {
                        var ids = v_rowIds.join(",")
                        $.ajax({
                            url: "<%=request.getContextPath()%>/product/deleteBatch.jhtml",
                            data: {
                                "ids": ids
                            },
                            type: "post",
                            success: function (result) {
                                if (result.code == 200) {
                                    bootbox.alert({
                                        message: result.message,
                                        size: 'small',
                                        backdrop: true
                                    });
                                    initSearchData();
                                } else {
                                    alert(result.message)
                                }
                            },
                            error: function () {
                                alert("哈哈哈哈，你真菜，ajax都失败")
                            }
                        });
                    }
                }

            }
        })
    }

    //产品主图片
    function initMainImg(){
        //产品主图片
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
            allowedFileExtensions: ["jpg", "png", "gif"], /*上传文件格式限制*/
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            showBrowse: true,
            browseOnZoneClick: true,
            slugCallback: function (filename) {
                return filename.replace('(', '_').replace(']', '_');
            }
        })
        $("#mainImage").on("fileuploaded", function (event, data, previewId, index) {
            $("#mainImagePath", addProductDialog).val(data.response.uploadedName)
        })
    }

    //产品子图片
    function initChildImgs(){
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
            $("#childImagePath", addProductDialog).val($("#childImagePath",addProductDialog).val() + "," + data.response.uploadedName);
        })
    }



    //显示产品增加页面
    function showAddProductPage() {
        var  addProductHtml = $("#addProductPage").html()
        initMainImg();
        initChildImgs();
        addProductDialog = bootbox.dialog({
                title: "增加产品",
                message: $("#addProductPage form"),
                size: 'large',
                buttons: {
                    cancel: {
                        label: "取消",
                        className: 'btn-danger',
                        callback: function () {
                        }
                    },
                    ok: {
                        label: "增加",
                        className: 'btn-info',
                        callback: function () {
                            $.ajax({
                                url:"/product/add.jhtml",
                                data:$("#addProductForm",addProductDialog).serializeArray(),
                                success:function (result) {
                                    if(result.code==200){
                                        retTable.ajax.reload();
                                    }

                                }
                            })
                        }
                    }
                }
            });
        $("#addProductPage").html(addProductHtml)
    }

</script>
</body>
</html>
