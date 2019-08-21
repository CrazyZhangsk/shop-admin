<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <title>查询品牌页面</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<div class="container-fluid">
    <div class="panel panel-info">
        <div class="panel-heading">
            条件查询
        </div>
        <div class="panel-body">
            <div id="page-content-wrapper ">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-12">
                            <form class="form-horizontal" role="form" id="productForm">
                                <div class="form-group">
                                    <label class="col-md-2 control-label" for="brandName">品牌名称</label>
                                    <div class="col-md-2">
                                        <input class="form-control" id="brandName" type="text" placeholder="荣耀"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">修改时间</label>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <input type="text" readonly class="form_datetime input-sm form-control"
                                                   id="minUpdateTime">
                                            <span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
								</span>
                                            <input type="text" value="" readonly
                                                   class="form_datetime input-sm form-control"
                                                   id="maxUpdateTime">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">录入时间</label>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <input type="text" readonly class="form_datetime input-sm form-control"
                                                   id="minEntryTime">
                                            <span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
								</span>
                                            <input type="text" value="" readonly
                                                   class="form_datetime input-sm form-control"
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
    <div class="panel panel-info">
        <div class="panel-heading" style="text-align: right">
            <button type="button" class="btn btn-info" onclick="insertBrand()">
                <span class="glyphicon glyphicon-plus"></span>
                添加品牌
            </button>
            <button type="button" class="btn btn-danger" onclick="deleteBatch()">
                <span class="glyphicon glyphicon-remove"></span>
                批量删除
            </button>
        </div>
        <div class="panel-body">
            <table id="brandTable" class="table table-hover table-bordered" style="width:100%">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" class="checkAll"/>
                    </th>
                    <th>编号</th>
                    <th>品牌名称</th>
                    <th>录入时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>
                        <input type="checkbox" class="checkAll"/>
                    </th>
                    <th>编号</th>
                    <th>品牌名称</th>
                    <th>录入时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
<!-------------------------------------- JavaScript --------------------------------------------------------->
<script type="text/javascript">
    $(function () {
        initBrandTable();
        initEvent();
        dateController();
    });

    var retTable;

    function initBrandTable() {
        retTable = $('#brandTable').DataTable({
            "processing": true,
            "serverSide": true,
            "searching": false,
            "aLengthMenu": [5, 10, 15, 20],
            "language": {
                "url": "/js/datatable/Chinese.json"
            },
            "ajax": {
                "url": "<%=request.getContextPath()%>/brand/findBrandList.jhtml",
                "type": "post",
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
                    "sClass": "text-center",
                    "render": function (data, type, full, meta) {
                        return "<input type='checkbox'  class='checkChild' value='" + data + "'/>";
                    },
                    "bSortable": false
                },
                {"data": "id"},
                {
                    "data": "brandName"
                },
                {

                    "data": "entryTimeStr"
                },
                {
                    "data": "updateTimeStr",
                },
                {
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        return '<div class="btn-group"><button onclick="toUpdateBrand(' + data + ')" class="btn btn-xs btn-info "><span class="glyphicon glyphicon-pencil"></span>修改</button><button class="btn btn-xs btn-danger" onclick="deleteBatch(' + data + ')"><span class="glyphicon glyphicon-remove"></span>删除</button></div>';
                    }
                }
            ],
            "drawCallback": function () {
                $("#brandTable tbody tr input[type='checkbox']").each(function () {
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
        var v_brandName = $("#brandName").val();
        var v_minUpdateTime = $("#minUpdateTime").val();
        var v_maxUpdateTime = $("#maxUpdateTime").val();
        var v_minEntryTime = $("#minEntryTime").val();
        var v_maxEntryTime = $("#maxEntryTime").val();
        var param = {
            "maxEntry": v_maxEntryTime,
            "minEntry": v_minEntryTime,
            "maxUpdate": v_maxUpdateTime,
            "minUpdate": v_minUpdateTime,
            "brandName": v_brandName
        };
        retTable.settings()[0].ajax.data = param;
        retTable.ajax.reload();
    }

    //点击行变色 并回显
    var v_rowIds = [];

    function initEvent() {
        $("#brandTable tbody").on("click", "tr", function () {
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


    //鼠标移上变色  移掉失去颜色
    function changeColor() {
        $("#brandTable tbody tr").each(function () {
            var v_checked = $(this).find("input[name='check']:checkbox")[0];
            $(this).bind("mouseover", function () {
                if (!v_checked.checked) {
                    $(this).css({"background": "#ccc"});
                }
            });
            $(this).bind("mouseout", function () {
                if (!v_checked.checked) {
                    $(this).css({"background": ""});
                }
            });
        });
    }

    /* 批量删除 */
    function deleteBatch() {
        bootbox.confirm({
            message: "你确定要删除么？",
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
                        bootbox.alert("请选择要删除的数据")
                    } else {
                        var v_ids = v_rowIds.join(",");
                        $.ajax({
                            url: "<%=request.getContextPath()%>/brand/deleteBatch.jhtml",
                            data: {
                                "ids": v_ids
                            },
                            type: "post",
                            dataType: "json",
                            async: true,
                            success: function (result) {
                                if (result.code == 200) {
                                    alert(result.message)
                                    findBrandList(1);
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

    /* 增加数据 */
    function insertBrand() {
        location.href = "<%=request.getContextPath()%>/brand/toAdd.jhtml";
    }

    /* 根据id回显 */
    function toUpdateBrand(id) {
        location.href = "<%=request.getContextPath()%>/brand/toUpdateById/" + id + ".jhtml";
    }

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
            "format": 'yyyy-mm-dd hh:ii:00',
            "language": "zh-CN",
            "minView": 0,
            "clearBtn": "true",
            "autoclose": "true"
        });
        $("#maxEntryTime").datetimepicker({
            "format": 'yyyy-mm-dd hh:ii:00',
            "language": "zh-CN",
            "minView": 0,
            "clearBtn": "true",
            "autoclose": "true"
        });

    }
</script>
</body>
</html>