<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zh-CN">
<head>
    <title>用户展示</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>

</head>
<body>
<div class="container-fluid">
    <div class="col-md-3">
        <div class="panel panel-info">
            <div class="panel-heading">
                部门管理
                <button class="btn btn-info btn-sm" type="button" onclick="showAddDeptPage()">
                    <i class="glyphicon glyphicon-plus"></i>
                    新增
                </button>
                <button class="btn btn-success btn-sm" type="reset" onclick="showUpdateDeptPage()">
                    <i class="glyphicon glyphicon-pencil"></i>
                    修改
                </button>
                <button class="btn btn-danger btn-sm" type="reset" onclick="delDeptInfo()">
                    <i class="glyphicon glyphicon-remove"></i>
                    删除
                </button>
            </div>
            <div class="panel-body">
                <ul id="deptTree" class="ztree"></ul>
            </div>
        </div>
    </div>
    <div class="col-md-9" id="showPage">
        <%--条件查询--%>
        <div class="panel panel-info">
            <div class="panel-heading">
                条件查询
            </div>
            <div class="panel-body">
                <div id="page-content-wrapper ">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-sm-12">
                                <form class="form-horizontal" role="form" id="userForm">
                                    <div class="form-group">
                                        <label class="col-md-2 control-label" for="userName">用户名称</label>
                                        <div class="col-md-6">
                                            <input class="form-control" name="userName" id="userName" type="text"
                                                   placeholder="请输入用户名"/>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">产品价格</label>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <input type="text" class="input-sm form-control" name="minSalary"
                                                       id="minSalary"
                                                       placeholder="最低薪资"/>
                                                <span class="input-group-addon">
                                                    <i class="glyphicon glyphicon-yen"></i>
								                </span>
                                                <input type="text" class="input-sm form-control" name="maxSalary"
                                                       id="maxSalary"
                                                       placeholder="最高薪资"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">用户生日</label>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <input type="text" readonly class="form_datetime input-md form-control"
                                                       id="minBirthday" name="minBirthday">
                                                <span class="input-group-addon">
								                    <i class="glyphicon glyphicon-calendar"></i>
                                                </span>
                                                <input type="text" readonly class="form_datetime input-md form-control"
                                                       id="maxBirthday" name="maxBirthday">
                                                <input type="hidden" name="sheetName" id="sheetName"/>
                                                <input type="hidden" name="workBookName" id="workBookName"/>
                                                <input type="hidden" name="sheetContentIds" id="sheetContentIds"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix form-actions" style="text-align:center">
                                        <button class="btn btn-primary" type="button" onclick="initSearchDate()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            高级查询
                                        </button>
                                        <button class="btn btn-default" type="button" onclick="clearQueryInfo()">
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
        <%--产品列表--%>
        <div class="panel panel-info">
            <div class="panel-heading" style="text-align: right">
                <button type="button" class="btn btn-warning" onclick="showUserDept()">
                    <span class="glyphicon glyphicon-remove"></span>
                    修改部门
                </button>
                <button type="button" class="btn btn-info" onclick="addUserInfo()">
                    <span class="glyphicon glyphicon-plus"></span>
                    添加用户
                </button>
                <button type="button" class="btn btn-danger" onclick="">
                    <span class="glyphicon glyphicon-remove"></span>
                    批量删除
                </button>
                <button type="button" class="btn btn-info" onclick="exportExcelByDept()">
                    <span class="glyphicon glyphicon-arrow-down"></span>
                    导出Excel
                </button>
            </div>
            <table class="table table-striped table-bordered" id="userTable" style="width: 100%;">
                <thead>
                <tr>
                    <th>选择</th>
                    <th>编号</th>
                    <th>用户名称</th>
                    <th>真实姓名</th>
                    <th>图片</th>
                    <th>性别</th>
                    <th>生日</th>
                    <th>所属部门</th>
                    <th>薪资</th>
                    <th>用户状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>选择</th>
                    <th>编号</th>
                    <th>用户名称</th>
                    <th>真实姓名</th>
                    <th>图片</th>
                    <th>性别</th>
                    <th>生日</th>
                    <th>所属部门</th>
                    <th>薪资</th>
                    <th>用户状态</th>
                    <th>操作</th>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
<%--增加页面--%>
<div id="addUserPage" style="display: none">
    <form class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-3 control-label">所属部门:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="add_user_deptName" readonly="readonly">
                <input type="hidden" class="form-control" id="add_deptId">
            </div>
            <div class="col-sm-2" style="padding-left: 0">
                <button type="button" class="btn btn-success" onclick="showDeptTree();"><span
                        class="glyphicon glyphicon-search" aria-hidden="true"></span> 选择部门
                </button>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">员工真实姓名:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="add_realName" id="add_realName">
                <input type="hidden" id="add_id">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">头像:</label>
            <div class="col-sm-5">
                <input type="file" name="file" id="add_fileInput">
                <input type="text" id="add_headerImagePath">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">注册登录名称:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="add_userName" id="add_userName">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">登录密码:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="add_password" id="add_password">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">确认登录密码:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="add_confirmPwd" id="add_confirmPwd">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">员工性别:</label>
            <div class="col-sm-6">
                <div class="radio">
                    <label>
                        <input type="radio" name="add_sex" value="1">男
                    </label>
                    <label>
                        <input type="radio" name="add_sex" value="0">女
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">员工生日:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" readonly="readonly" name="add_birthday" id="add_birthday">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">员工薪资:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="add_salary" id="add_salary">
            </div>
        </div>
    </form>
</div>
<%--修改页面--%>
<div id="upUserPage" style="display: none">
    <form class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-3 control-label">所属部门:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="up_deptName" readonly="readonly">
                <input type="hidden" class="form-control" id="up_deptId">
            </div>
            <div class="col-sm-2" style="padding-left: 0">
                <button type="button" class="btn btn-success" onclick="showDeptTree();"><span
                        class="glyphicon glyphicon-search" aria-hidden="true"></span> 选择部门
                </button>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">员工真实姓名:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="up_realName" id="up_realName">
                <input type="hidden" id="up_id">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">头像:</label>
            <div class="col-sm-5">
                <input type="file" name="file" id="update_fileInput">
                <input type="text" id="up_headerImagePath">
                <input type="text" id="up_oldHeaderImagePath">

            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">登录名称:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="up_userName" id="up_userName">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">旧密码:</label>
            <div class="col-sm-6">
                <input type="password" placeholder="验证旧密码" class="form-control" name="up_old_password"
                       id="up_old_password">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">新密码:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" placeholder="请输入新密码" name="up_password" id="up_password">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">确认密码:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="up_confirmPwd" placeholder="确认新密码" id="up_confirmPwd">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">员工性别:</label>
            <div class="col-sm-6">
                <div class="radio">
                    <label>
                        <input type="radio" name="up_sex" value="1">男
                    </label>
                    <label>
                        <input type="radio" name="up_sex" value="0">女
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">员工生日:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" readonly="readonly" name="up_birthday" id="up_birthday">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">员工薪资:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="up_salary" id="up_salary">
            </div>
        </div>
    </form>
</div>
<div id="userTreePage" style="display: none;"></div>
<%--部门增加页面--%>
<div id="addDeptPage" style="display: none">
    <div class="col-md-12">
        部门名称：<input class="form-control" type="text" id="add_deptName"/>
        <br>
        部门描述：<textarea id="add_remark" class="form-control" placeholder="请填写部门相关描述"></textarea>
    </div>
</div>
<input type="hidden" id="pId"/>
<input type="hidden" id="id"/>
<%--修改部门页面--%>
<div id="updateDeptPage" style="display: none">
    <div class="col-md-12">
        部门名称：<input class="form-control" type="text" id="update_deptName"/>
        <br>
        部门描述：<textarea id="update_remark" class="form-control"></textarea>
        <br>
    </div>
</div>
<script type="text/javascript">

    $(function () {
        initUserTable();
        initEvent();
        dateController();
        initUserZtree();
        initDeptZtree();

    })

    //按照部门导出用户
    function exportExcelByDept() {
        var parentNodes = [];
        var childIds = [];
        var deptTree = $.fn.zTree.getZTreeObj("deptTree");
        var selectNodes = deptTree.getSelectedNodes()[0];
        var childS = selectNodes.children
        for (var i = 0 ; i < childS.length ; i++) {
            parentNodes.push(childS[i]);
            var childNodes = deptTree.transformToArray(childS[i]);
            var ids = [];
            for(var j = 0 ; j < childNodes.length ; j++) {
                ids.push(childNodes[j].id);
            }
            childIds.push(ids.join(","))
        }
        //转换成user所对应的
        var parentParam = [];
        for (var i = 0 ; i < parentNodes.length ; i++) {
            /*var param = {};
            param.name = parentNodes[i].name;*/
            parentParam.push(parentNodes[i].name);
        }
        /*var sheetName = JSON.stringify(parentParam);*/
        var sheetName = parentParam.join(",")
        var sheetContentIds = childIds.join(":");
        $("#sheetName").val(sheetName);
        $("#sheetContentIds").val(sheetContentIds);
        $("#workBookName").val(selectNodes.name);
        var v_userForm = document.getElementById("userForm")
        v_userForm.action = "<%=request.getContextPath()%>/user/exportExcelByDept.jhtml";
        v_userForm.submit();
    }

    //用户修改
    var updateUserDialog;
    function updateUserInfo(id) {
        var upUserPage = $("#upUserPage").html();
        var param = [];
        $.ajax({
            url: "<%=request.getContextPath()%>/user/toUpdateUserInfo.jhtml",
            type: "post",
            data: {
                "id": id,
            },
            success: function (result) {
                var user = result.data;
                $("#up_realName").attr({"value": user.realName});
                $("#up_deptName").attr({"value": user.dept.deptName});
                $("#up_deptId").attr({"value": user.dept.id});
                $("#up_id").attr({"value": user.id});
                $("#up_userName").attr({"value": user.userName});
                $("#up_salary").attr({"value": user.salary});
                $("#up_birthday").attr({"value": user.birthdayStr});
                $("#up_oldHeaderImagePath").attr({"value": user.headerImgPath});
                $("input[value='" + user.sex + "'][name='up_sex']").attr({"checked": "checked"})
                param.push(user.headerImgPath);
                updateHeaderImg(param);
                updateUserDialog = bootbox.dialog({
                    title: "用户修改",
                    message: $("#upUserPage form"),
                    size: 'large',
                    buttons: {
                        cancel: {
                            label: "取消",
                            className: 'btn-danger',
                            callback: function () {
                            }
                        },
                        ok: {
                            label: "确认修改",
                            className: 'btn-info',
                            callback: function () {
                                var param = {};
                                param["dept.deptName"] = $("#up_deptName", updateUserDialog).val();
                                param["dept.id"] = $("#up_deptId", updateUserDialog).val();
                                param.realName = $("#up_realName", updateUserDialog).val();
                                param.userName = $("#up_userName", updateUserDialog).val();
                                param.password = str_md5($("#up_password", updateUserDialog).val());
                                param.sex = $("input[name='up_sex']:checked", updateUserDialog).val();
                                param.birthday = $("#up_birthday", updateUserDialog).val();
                                param.salary = $("#up_salary", updateUserDialog).val();
                                param.id = $("#up_id", updateUserDialog).val();
                                param.headerImgPath = $("#up_headerImagePath",updateUserDialog).val();
                                param.oldHeaderImgPath = $("#up_oldHeaderImagePath",updateUserDialog).val();
                                $.ajax({
                                    url: "<%=request.getContextPath()%>/user/updateUserInfo.jhtml",
                                    type: "post",
                                    data: param,
                                    success: function (result) {
                                        if (result.code == 200) {
                                            retTable.ajax.reload();
                                        }
                                    }
                                })
                            }
                        }
                    }
                })
                $("#upUserPage").html(upUserPage);
                $("#up_birthday", updateUserDialog).datetimepicker({
                    "format": 'yyyy-mm-dd',
                    "language": "zh-CN",
                    "minView": "month",
                    "clearBtn": "true",
                    "autoclose": "true"
                });
            }
        })


    }

    //用户增加页面展示
    var addUserDialog;
    function addUserInfo() {
        var addUserPage = $("#addUserPage").html();
        initHeaderImg();
        addUserDialog = bootbox.dialog({
            title: "用户增加",
            message: $("#addUserPage form"),
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
                        var param = {};
                        param["dept.deptName"] = $("#add_user_deptName", addUserDialog).val();
                        param["dept.id"] = $("#add_deptId", addUserDialog).val();
                        param.realName = $("#add_realName", addUserDialog).val();
                        param.userName = $("#add_userName", addUserDialog).val();
                        param.password = str_md5($("#add_password", addUserDialog).val());
                        param.sex = $("input[name='add_sex']:checked", addUserDialog).val();
                        param.birthday = $("#add_birthday", addUserDialog).val();
                        param.salary = $("#add_salary", addUserDialog).val();
                        param.headerImgPath = $("#add_headerImagePath",addUserDialog).val()
                        $.ajax({
                            url: "<%=request.getContextPath()%>/user/addUserInfo.jhtml",
                            type: "post",
                            data: param,
                            success: function (result) {
                                if (result.code == 200) {
                                    retTable.ajax.reload();
                                }
                            }
                        })
                    }
                }
            }
        })

        $("#addUserPage").html(addUserPage);
        $("#add_birthday", addUserDialog).datetimepicker({
            "format": 'yyyy-mm-dd',
            "language": "zh-CN",
            "minView": "month",
            "clearBtn": "true",
            "autoclose": "true"
        });
    }

    //用户增加时候部门展示
    function showDeptTree() {
        initUserZtree();
        bootbox.dialog({
            title: "部门展示",
            message: $("#userTreePage ul"),
            size: 'small',
            buttons: {
                cancel: {
                    label: "取消",
                    className: 'btn-danger',
                },
                ok: {
                    label: "增加",
                    className: 'btn-info',
                    callback: function () {
                        var userTree = $.fn.zTree.getZTreeObj("userTree");
                        var selectedNodes = userTree.getSelectedNodes();
                        if (selectedNodes.length == 1) {
                            var v_selfId = selectedNodes[0].id;
                            var v_selfName = selectedNodes[0].name;
                            $("#add_user_deptName", addUserDialog).val(v_selfName);
                            $("#add_deptId", addUserDialog).val(v_selfId);
                            $("#up_deptName", updateUserDialog).val(v_selfName);
                            $("#up_deptId", updateUserDialog).val(v_selfId);

                        } else {
                            bootbox.alert({
                                message: "<font>请选择部门</font>",
                                title: "<font color='#db7093'>提示信息</font>",
                                size: 'small',
                                backdrop: false
                            })
                        }
                    }
                }
            }
        })
    }

    //时间控件
    function dateController() {
        $("#minBirthday").datetimepicker({
            "format": "yyyy-mm-dd",
            "language": "zh-CN",
            "minView": 'month',
            "clearBtn": "true",
            "autoclose": "true"
        });
        $("#maxBirthday").datetimepicker({
            "format": 'yyyy-mm-dd',
            "language": "zh-CN",
            "minView": "month",
            "clearBtn": "true",
            "autoclose": "true"
        });
    }

    //批量修改用户部门
    function showUserDept() {
        if(v_rowIds.length<=0){
            bootbox.alert({
                message: "<font>请选择要变更部门用户</font>",
                title: "<font color='#db7093'>提示信息</font>",
                size: 'small',
                backdrop: false
            })
        }else{
            initUserZtree();
            bootbox.dialog({
                title: "部门展示",
                message: $("#userTreePage ul"),
                size: 'small',
                buttons: {
                    cancel: {
                        label: "取消",
                        className: 'btn-danger',
                    },
                    ok: {
                        label: "确认",
                        className: 'btn-info',
                        callback: function () {
                            var userTree = $.fn.zTree.getZTreeObj("userTree");
                            var selectedNodes = userTree.getSelectedNodes();
                            if (selectedNodes.length == 1) {
                                var v_selfId = selectedNodes[0].id;
                                $.ajax({
                                    url:"<%=request.getContextPath()%>/user/updateBatchUserDept.jhtml",
                                    type:"post",
                                    data:{
                                        "idsStr":v_rowIds.join(","),
                                        "dept.id":v_selfId
                                    },
                                    success:function (result) {
                                        if (result.code==200){
                                            retTable.ajax.reload();
                                        }else{
                                            bootbox.alert("批量修改失败")
                                        }
                                    }
                                })
                            } else {
                                bootbox.alert({
                                    message: "<font>请选择要修改的部门</font>",
                                    title: "<font color='#db7093'>提示信息</font>",
                                    size: 'small',
                                    backdrop: false
                                })
                            }
                        }
                    }
                }
            })
        }


    }

    //一键解锁用户
    function openLock(id) {
        location.href = "<%=request.getContextPath()%>/user/updateUserStatus/" + id + ".jhtml";
    }

    //user增加修改时 加载部门zTree
    function initUserZtree() {
        //保证被剪切走之后还能找到该zTree
        $("#userTreePage").html('<ul id="userTree" class="ztree"></ul>');
        var setting = {
            check: {
                enable: false,//是否有显示复选框
                /*chkboxType: { "Y": "", "N": "" }*/
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                },
                key: {
                    name: "name"
                }
            }
        };
        $.ajax({
            url: "<%=request.getContextPath()%>/dept/findDeptList.jhtml",
            type: "post",
            success: function (result) {
                var zNodes = result.data;
                $.fn.zTree.init($("#userTree"), setting, zNodes);
            }
        })
    }

    //加载部门Ztree
    function initDeptZtree() {
        var setting = {
            check: {
                enable: false,//是否有显示复选框
                /*chkboxType: { "Y": "", "N": "" }*/
            },
            callback: {
                onClick: initSearchDate
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                },
                key: {
                    name: "name"
                }
            }
        };
        $.ajax({
            url: "<%=request.getContextPath()%>/dept/findDeptList.jhtml",
            type: "post",
            success: function (result) {
                var zNodes = result.data;
                $.fn.zTree.init($("#deptTree"), setting, zNodes);
            }
        })
    }

    //显示部门增加页面
    function showAddDeptPage() {
        var deptAddTree = $.fn.zTree.getZTreeObj("deptTree");
        var selectedNodes = deptAddTree.getSelectedNodes();
        if (selectedNodes.length == 1) {
            var v_parentId = selectedNodes[0].id;
            var v_parentName = selectedNodes[0].name;
            $("#pId").val(v_parentId);
            $("#deptAddName").attr({"placeholder": "请为" + v_parentName + "添加子部门"})
            var addDeptDialog = bootbox.dialog({
                title: "部门增加",
                message: $("#addDeptPage").html(),
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
                            addDeptInfo(addDeptDialog);
                        }
                    }
                }
            });
        } else {
            bootbox.alert({
                message: "<font>请选择一项</font>",
                title: "<font color='#db7093'>提示信息</font>",
                size: 'small',
                backdrop: false
            })
        }
    }

    //显示部门修改页面
    function showUpdateDeptPage() {
        var deptTree = $.fn.zTree.getZTreeObj("deptTree");
        var selectedNodes = deptTree.getSelectedNodes();
        if (selectedNodes.length == 1) {
            var v_selfId = selectedNodes[0].id;
            var v_selfName = selectedNodes[0].name;
            var v_selfRemark = selectedNodes[0].remark;
            $("#id").val(v_selfId);
            $("#update_deptName").attr({"value": v_selfName});
            $("#update_remark").text(v_selfRemark);
            var updateDeptDialog = bootbox.dialog({
                title: "部门修改",
                message: $("#updateDeptPage").html(),
                size: 'large',
                buttons: {
                    cancel: {
                        label: "取消",
                        className: 'btn-danger',
                        callback: function () {
                        }
                    },
                    ok: {
                        label: "修改",
                        className: 'btn-info',
                        callback: function () {
                            updateDeptInfo(updateDeptDialog, selectedNodes);
                        }
                    }
                }
            });
        } else {
            bootbox.alert({
                message: "<font>请选择一项进行修改</font>",
                title: "<font color='#db7093'>提示信息</font>",
                size: 'small',
                backdrop: false
            })
        }
    }

    //部门增加
    function addDeptInfo(addDeptDialog) {
        var v_deptName = $("#add_deptName", addDeptDialog).val();
        var v_pId = $("#pId").val();
        var v_remark = $("#add_remark", addDeptDialog).val();
        $.ajax({
            url: "<%=request.getContextPath()%>/dept/addDeptInfo.jhtml",
            type: "post",
            data: {
                "deptName": v_deptName,
                "pId": v_pId,
                "remark": v_remark,
            },
            success: function (result) {
                if (result.code == 200) {
                    var dept = result.data;
                    var treeObj = $.fn.zTree.getZTreeObj("deptTree");
                    var childNode = {};
                    childNode.id = dept.id;
                    childNode.pId = dept.pId;
                    childNode.name = dept.deptName;
                    childNode.remark = dept.remark;
                    var parentNode = treeObj.getNodeByParam("id", dept.pId, null)
                    treeObj.addNodes(parentNode, childNode, false)
                }
            }
        })
    }

    //部门修改
    function updateDeptInfo(updateDeptDialog, selectedNodes) {
        var v_deptName = $("#update_deptName", updateDeptDialog).val();
        var v_remark = $("#update_remark", updateDeptDialog).val();
        var v_id = $("#id").val();
        var param = {};
        param.deptName = v_deptName;
        param.id = v_id;
        param.remark = v_remark;
        $.ajax({
            url: "<%=request.getContextPath()%>/dept/updateDeptInfo.jhtml",
            type: "post",
            data: param,
            success: function (result) {
                if (result.code == 200) {
                    selectedNodes[0].name = v_deptName;
                    var treeObj = $.fn.zTree.getZTreeObj("deptTree");
                    treeObj.updateNode(selectedNodes[0]);
                }
            }
        })
    }

    //部门删除
    function delDeptInfo() {
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
                var v_ids = [];
                if (result) {
                    var deptTree = $.fn.zTree.getZTreeObj("deptTree");
                    var selectedNodes = deptTree.getSelectedNodes();
                    //遍历所有选中节点的子节点 返回所有节点的数组
                    var nodeArr = deptTree.transformToArray(selectedNodes);
                    for (var i = 0; i < nodeArr.length; i++) {
                        v_ids.push(nodeArr[i].id)
                    }
                    //遍历所有子节点
                    /*for (var i = 0;i<selectedNodes.length;i++){
                        v_ids.push(selectedNodes[i].id);
                        judgeIsParend(selectedNodes[i]);
                    }
                    function judgeIsParend(nodes){
                        if (nodes.isParent){
                            var childenNodes = nodes.children;
                            for (var j = 0;j<childenNodes.length;j++) {
                                v_ids.push(childenNodes[j].id);
                                judgeIsParend(childenNodes[j])
                            }
                        }
                    }*/
                    if (selectedNodes.length > 0) {
                        $.ajax({
                            url: "/dept/delDeptInfo.jhtml",
                            type: "post",
                            data: {
                                "idsStr": v_ids
                            },
                            success: function (result) {
                                if (result.code == 200) {
                                    for (var i = 0; i < selectedNodes.length; i++) {
                                        deptTree.removeNode(selectedNodes[i]);
                                    }
                                }
                            }
                        })
                    } else {
                        bootbox.alert("请选择一项进行删除");
                    }
                }
            }
        })
    }

    //条件查询
    function initSearchDate() {
        var v_deptIds =[];
        var deptTree = $.fn.zTree.getZTreeObj("deptTree");
        var treeNode = deptTree.getSelectedNodes();
        //遍历所有选中节点的子节点 返回所有节点的数组
        var nodeArr = deptTree.transformToArray(treeNode);
        for (var i = 0; i < nodeArr.length; i++) {
            v_deptIds.push(nodeArr[i].id)
        }
        var v_userName = $("#userName").val();
        var v_minBirthday = $("#minBirthday").val();
        var v_maxBirthday = $("#maxBirthday").val();
        var v_minSalary = $("#minSalary").val();
        var v_maxSalary = $("#maxSalary").val();
        var param = {};
        param.userName = v_userName;
        param.minBirthday = v_minBirthday;
        param.maxBirthday = v_maxBirthday;
        param.minSalary = v_minSalary;
        param.maxSalary = v_maxSalary;
        param.idsStr = v_deptIds.join(",");
        retTable.settings()[0].ajax.data = param;
        retTable.ajax.reload();
    }

    //清楚条件查询的条件
    function clearQueryInfo(){
        $("#userName").val("");
        $("#minBirthday").val("");
        $("#maxBirthday").val("");
        $("#minSalary").val("");
        $("#maxSalary").val("");
        var deptTree = $.fn.zTree.getZTreeObj("deptTree");
        deptTree.cancelSelectedNode();
        initSearchDate();
    }

    //加载用户列表
    var retTable;
    function initUserTable() {
        retTable = $('#userTable').DataTable({
            "processing": true,
            "serverSide": true,
            "searching": false,
            "paginationType": "full_numbers",
            "aLengthMenu": [5, 10, 15, 20],
            "language": {
                "url": "/js/datatable/Chinese.json"
            },
            "ajax": {
                "url": "<%=request.getContextPath()%>/user/findUserList.jhtml",
                "type": "post",
                "dataSrc": function (result) {
                    result.draw = result.data.draw;
                    result.recordsFiltered = result.data.recordsFiltered;
                    result.recordsTotal = result.data.recordsTotal;
                    return result.data.data;
                }
            },
            /*传的数据*/
            "columns": [
                {
                    "data": "id",
                    "bSortable": false,
                    "sClass": "text-center",
                    "render": function (data, type, full, meta) {
                        return "<input type='checkbox' onclick='this.checked=!this.checked'  class='checkChild' value='" + data + "'/>";
                    },
                },
                {"data": "id"},
                {"data": "userName"},
                {"data": "realName"},
                {
                    "data": "headerImgPath",
                    "render": function (data, type, row, meta) {
                        return "<image src='"+data+"' width='50px'/>"
                    }
                },
                {
                    "data": "sex",
                    "render": function (data, type, row, meta) {
                        if (data == 1) {
                            return "男";
                        }
                        if (data == 0) {
                            return "女";
                        }
                        return "";
                    }
                },
                {"data": "birthdayStr"},
                {"data": "deptName"},
                {"data": "salary"},
                {
                    "data": "status",
                    "render": function (data, type, row, meta) {
                        if (data == 1) {
                            return "锁定"
                        }
                        return "未锁定"
                    }
                },
                {
                    "data": "id",
                    "render": function (data, type, row, meta) {
                        return "<div class='btn-group' role='group'>" +
                            "<button type='button' class='btn btn-warning' onclick='openLock(" + data + ")'>一键解锁</button><button type='button' class='btn btn-info' onclick='updateUserInfo(" + data + ")'>修改</button>"
                            +
                            "</div>"
                    },
                },
            ],
            "drawCallback": function () {
                $("#userTable tbody tr input[type='checkbox']").each(function () {
                    if (isExist(this.value)) {
                        $(this).closest("tr").css({"background": "pink"})
                        this.checked = true;
                    }
                })
            }
        });
    }

    //点击行变色 并回显
    var v_rowIds = [];
    function initEvent() {
        $("#userTable tbody").on("click", "tr", function () {
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

    //上传头像
    function initHeaderImg(){
        //产品主图片
        $('#add_fileInput').fileinput({
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
            uploadUrl: "<%=request.getContextPath()%>/file/uploadFile.jhtml",
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
        $("#add_fileInput").on("fileuploaded", function (event, data, previewId, index) {
            console.log(data.response.data);
            $("#add_headerImagePath", addUserDialog).val(data.response.data)
        })
    }

    //修改头像
    function updateHeaderImg(param){
        //产品主图片
        $('#update_fileInput').fileinput({
            //初始化上传文件框
            language: "zh",//配置语言
            uploadAsync: true,//默认异步上传
            showCaption: true,//是否显示标题
            dropZoneEnabled: true,//是否显示拖拽区域
            initialPreview :param,
            initialPreviewAsData:true,
            uploadUrl: "<%=request.getContextPath()%>/file/uploadFile.jhtml",
            enctype: 'multipart/form-data',
            allowedFileExtensions: ["jpg", "png", "gif"], /*上传文件格式限制*/
            showBrowse: true,
            browseOnZoneClick: true,
            slugCallback: function (filename) {
                return filename.replace('(', '_').replace(']', '_');
            }
        })
        $("#update_fileInput").on("fileuploaded", function (event, data, previewId, index) {
            $("#up_headerImagePath", updateUserDialog).val(data.response.data)
        })
    }
</script>
</body>
</html>
