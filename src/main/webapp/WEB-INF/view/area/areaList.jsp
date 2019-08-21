<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2019/3/12
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>地区</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<div class="container-fluid">
    <div class="col-md-3">
        <div class="panel panel-info">
            <div class="panel-heading">
                地区管理
                <button class="btn btn-info btn-sm" type="button" onclick="showAddAreaPage()">
                    <i class="glyphicon glyphicon-plus"></i>
                    新增
                </button>
                <button class="btn btn-success btn-sm" type="reset" onclick="showUpdateAreaPage()">
                    <i class="glyphicon glyphicon-pencil"></i>
                    修改
                </button>
                <button class="btn btn-danger btn-sm" type="reset" onclick="delAreaInfo()">
                    <i class="glyphicon glyphicon-remove"></i>
                    删除
                </button>
            </div>
            <div class="panel-body">
                <ul id="areaTree" class="ztree"></ul>
            </div>
        </div>
        <%--地区增加页面--%>
        <div id="addAreaPage" style="display: none">
            <div class="col-md-12">
                地区名称：<input class="form-control" type="text" id="add_areaName"/>
                <input type="hidden" id="add_pId"/>
            </div>
        </div>
        <%--地区修改页面--%>
        <div id="updateAreaPage" style="display: none">
            <div class="col-md-12">
                地区名称：<input class="form-control" type="text" id="update_areaName"/>
                <input type="hidden" id="update_id"/>
            </div>
        </div>
    </div>
    <div class="col-md-9"></div>
</div>

<script type="text/javascript">

    $(function () {
        //加载地区Ztree
        initAreaZtree();
    })

    //地区Ztree增加
    function showAddAreaPage() {
        var areaTree = $.fn.zTree.getZTreeObj("areaTree");
        var selectedNodes = areaTree.getSelectedNodes();
        var v_pId = selectedNodes[0].id
        $("#add_pId").val(v_pId)
        if (selectedNodes.length == 1) {
            var addAreaDialog =  bootbox.dialog({
                title: "地区增加",
                message: $("#addAreaPage").html(),
                size: 'large',
                buttons: {
                    cancel: {
                        label: "取消",
                        className: 'btn-danger',
                    },
                    ok: {
                        label: "确认增加",
                        className: 'btn-info',
                        callback: function () {
                            var param = {};
                            var v_areaName = $("#add_areaName", addAreaDialog).val();
                            param.areaName = v_areaName;
                            param.pId = v_pId;
                            $.ajax({
                                url: "<%=request.getContextPath()%>/area/addAreaInfo.jhtml",
                                type: "post",
                                data: param,
                                success: function (result) {
                                    if (result.code == 200) {
                                        var area = result.data;
                                        var childNode = {};
                                        childNode.id = area.id;
                                        childNode.pId = area.pId;
                                        childNode.areaName = area.areaName;
                                        var parentNode = areaTree.getNodeByParam("id", area.pId, null)
                                        areaTree.addNodes(parentNode, childNode, false)
                                    }
                                }
                            })
                        }
                    }
                }
            })

        }else {
            bootbox.alert({
                "title": "提示信息",
                "message": "选择一个部门"
            })
        }


    }

    //地区Ztree修改
    function showUpdateAreaPage() {
        //debugger;
        var areaTree = $.fn.zTree.getZTreeObj("areaTree");
        var selectedNodes = areaTree.getSelectedNodes();
        var v_selfId = selectedNodes[0].id;
        var v_selfName = selectedNodes[0].areaName;
        $("#update_areaName").attr({"value":v_selfName});
        $("#update_id").attr({"value":v_selfId});
        if (selectedNodes.length == 1) {
           var updateAreaDialog =  bootbox.dialog({
                title: "地区修改",
                message: $("#updateAreaPage").html(),
                size: 'large',
                buttons: {
                    cancel: {
                        label: "取消",
                        className: 'btn-danger',
                    },
                    ok: {
                        label: "确认修改",
                        className: 'btn-info',
                        callback: function () {
                            var param = {};
                            var v_areaName = $("#update_areaName", updateAreaDialog).val();
                            param.areaName = v_areaName;
                            param.id = v_selfId;
                            $.ajax({
                                url: "<%=request.getContextPath()%>/area/updateAreaInfo.jhtml",
                                type: "post",
                                data: param,
                                success: function (result) {
                                    if (result.code == 200) {
                                        selectedNodes[0].areaName = v_areaName;
                                        var treeObj = $.fn.zTree.getZTreeObj("areaTree");
                                        treeObj.updateNode(selectedNodes[0]);
                                    }
                                }
                            })
                        }
                    }
                }
            })
        } else {
            bootbox.alert({
                "title": "提示信息",
                "message": "请选择一个要修改的数据"
            })
        }

    }

    //地区Ztree删除
    function delAreaInfo() {
        var areaTree = $.fn.zTree.getZTreeObj("areaTree");
        var selectedNodes = areaTree.getSelectedNodes();
        if (selectedNodes.length != 0) {
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
                        var v_childNodes = areaTree.transformToArray(selectedNodes);
                        var v_ids = [];
                        for (var i = 0; i < v_childNodes.length; i++) {
                            v_ids.push(v_childNodes[i].id)
                        }
                    }
                    $.ajax({
                        url: "/area/delAreaInfo.jhtml",
                        type: "post",
                        data: {
                            "idsList": v_ids
                        },
                        success: function (result) {
                            if (result.code == 200) {
                                for (var i = 0; i < selectedNodes.length; i++) {
                                    areaTree.removeNode(selectedNodes[i]);
                                }
                            }
                        }
                    })

                }
            })
        } else {
            bootbox.alert({
                "title": "提示信息",
                "message": "请选择要删除的数据"
            })
        }
    }

    //加载地区Ztree
    function initAreaZtree() {
        var setting = {
            check: {
                enable: false,//是否有显示复选框
                /*chkboxType: { "Y": "", "N": "" }*/
            },
            /*callback: {
                onClick: //方法名
            },*/
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                },
                key: {
                    name: "areaName"
                }
            }
        };
        $.ajax({
            url: "<%=request.getContextPath()%>/area/findAreaList.jhtml",
            type: "post",
            success: function (result) {
                var zNodes = result.data;
                $.fn.zTree.init($("#areaTree"), setting, zNodes);
            }
        })
    }
</script>
</body>
</html>
