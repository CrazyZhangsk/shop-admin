<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<%--条件查询--%>
<div class="container-fluid">
    <div class="col-md-12" id="showPage">
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
                                        <label class="col-md-2 control-label" for="userName">会员名称</label>
                                        <div class="col-md-6">
                                            <input class="form-control" name="userName" id="userName" type="text"
                                                   placeholder="请输入用户名"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">会员生日</label>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <input type="text" readonly class="form_datetime input-md form-control"
                                                       id="minBirthday" name="minBirthday">
                                                <span class="input-group-addon">
								                    <i class="glyphicon glyphicon-calendar"></i>
                                                </span>
                                                <input type="text" readonly class="form_datetime input-md form-control"
                                                       id="maxBirthday" name="maxBirthday">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group" id="areaSelect">
                                        <label class="col-sm-2 control-label">地区:</label>
                                    </div>
                                    <div class="clearfix form-actions" style="text-align:center">
                                        <button class="btn btn-primary" type="button" onclick="initClient(1)">
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
    </div>
</div>
<%--会员展示--%>
<table id="memberTable" class="table table-hover table-bordered" width="500px">
        <thead>
            <th>会员姓名</th>
            <th>手机号</th>
            <th>生日</th>
            <th>地址</th>
            <th>操作</th>
        </thead>
        <tbody id="data">

        </tbody>
    </table>
<%--会员管理页面--%>
<div id="updateClient" style="display: none">

</div>
<script>

    $(function () {
        initCity(1);
        initClient();
        dateController();
    })

    function initCity(v_id, obj) {
        $.ajax({
            url: "/area/cityList.jhtml?pId="+v_id,
            type: "post",
            success: function (result) {
                if (result.code == 200) {
                    if (obj){
                        $(obj).parent().nextAll().remove();
                    }
                    var v_data = result.data;
                    if (v_data.length==0) {
                        return;
                    }
                    var content = "<div class=\"col-sm-2\" name=\"areaSelect\">\n" +
                        "                <select class=\"form-control\" name='areaSel' onchange=\"initCity(this.value, this)\">\n" +
                        "                    <option value=\"-1\">===请选择===</option>";

                    for (var i = 0; i < v_data.length; i++) {
                        content += "<option value='" + v_data[i].id + "'>" + v_data[i].areaName + "</option>";
                    }
                    content += "</select>\n" +
                        "            </div>";
                    $("#areaSelect").append(content)
                } else {
                    alert("失败了")
                }
            }
        })
    }

    //用户列表
    function initClient(obj) {
        var data = {}
        data.clientName = $("#userName").val();
        data.minBirthday = $("#minBirthday").val();
        data.maxBirthday = $("#maxBirthday").val();
        if (obj) {
            if ($("select[name='areaSel']").length == 2){
                data.shengId = $("select[name='areaSel']")[0].value;
            }
            if ($("select[name='areaSel']").length == 3){
                data.shengId = $("select[name='areaSel']")[0].value;

                data.shiId = $("select[name='areaSel']")[1].value;

                data.xianId = $("select[name='areaSel']")[2].value;
            }

        }
        $.ajax({
            url: "/client/findClientList.jhtml",
            type: "post",
            data:data,
            success: function (result) {
                if (result.code == 200) {
                    var item = "";
                    for (var i = 0; i < result.data.length; i++) {
                      item +=  "<tr><td>"+result.data[i].clientName+"</td>" +
                          "<td>"+result.data[i].phone+"</td><td>"+result.data[i].birthday+"</td>" +
                          "<td>"+result.data[i].address+"</td><td>" +
                          "<a href='javaScript:findDetailInfo("+result.data[i].id+")'>详细信息</a>" +
                          "<a href='javaScript:updateDetailInfo("+result.data[i].id+")'>信息修改</a>" +
                          "</td></tr>";
                    }
                    $("#data").html(item);
                }

            }
        })
    }

    function date2Str(date, a) {
        var dateInfo = new Date(date);
        var year = dateInfo.getFullYear();
        var months = dateInfo.getMonth() + 1;
        var day = dateInfo.getDate();
        var hours = dateInfo.getHours();
        var minutes = dateInfo.getMinutes();
        var seconds = dateInfo.getSeconds();
        if (months <= 9) {
            months = "0" + months;
        }
        if (day <= 9) {
            day = "0" + day;
        }
        if (hours <= 9) {
            hours = "0" + hours;
        }
        if (minutes <= 9) {
            minutes = "0" + minutes;
        }
        if (seconds <= 9) {
            seconds = "0" + seconds;
        }
        if (a) {
            return year + "-" + months + "-" + day;
        } else {
            return year + "-" + months + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
        }
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

    //个人信息查看
    function findDetailInfo(id){
        $.ajax({
            url:"<%=request.getContextPath()%>/client/findClientList.jhtml?id="+id,
            success:function (res) {
                console.log(res.data)
                if (res.code==200){
                    var data = res.data[0];
                    var count = "会员名称："+data.clientName+"<p>" +
                        "" +
                        "手机号："+data.phone+"<p>" +
                        "email："+data.email+"<p>" +
                        "生日："+data.birthday+"<p>" +
                        "注册时间："+date2Str(data.regTime)+"<p>" +
                        "地址："+data.address+"";
                   $("#updateClient").html(count);
                }

                 bootbox.dialog({
                    title: "个人信息",
                    message: $("#updateClient").html(),
                    size: 'small',
                })
            }
        })
    }

    //信息修改页面
    function updateDetailInfo(id) {
        location.href="<%=request.getContextPath()%>/response/client/updateDetailInfo.jhtml?id="+id;
    }



</script>
</body>
</html>
