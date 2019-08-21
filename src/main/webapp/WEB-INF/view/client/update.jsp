<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员信息修改</title>
    <jsp:include page="/WEB-INF/common/shopAdmin_CSSAndJS.jsp"></jsp:include>
</head>
<body>
<ul style="list-style: none">
    <li>会员名：<span id="clientNameSpan">${clientInfo.clientName}</span><a href="javaScript:updateClient('updateName_div')">修改</a></li>
    <li>
        <div style="display: none" id="updateName_div">
                <input type="hidden" value="${clientInfo.id}">
                <input type="text" placeholder="请输入用户名" id="clientName" name="clientName"/><br>
                <input type="button" value="确认修改" onclick="updateClientName('${clientInfo.id}')"/>
                <input type="button" value="取消" onclick="cancleUpdate('updateName_div')"/><br>

        </div>
    </li>
    <li>密码：**********<a href="javaScript:updateClient('updatePwd_div')">修改</a></li>
    <div style="display: none" id="updatePwd_div">
            旧密码：<input type="password"  id="oldPwd"/><br>
            新密码：<input type="password"  id="password"/><br>
            确认密码：<input type="password" id="confirmPwd"/><br>
            <input type="button" value="确认修改" onclick="updatePwd('${clientInfo.id}')"/>
            <input type="button" value="取消" onclick="cancleUpdate('updatePwd_div')"/><br>
    </div>

    <li>Tel：${clientInfo.phone}</li>
    <div style="display: none" id="checkPhone_div">
            <input type="text" placeholder="请输入旧手机号" id="oldPhone"/><br>
            <input type="text" placeholder="验证码" id="oldTelCode"/><br>
            <input type="button" value="确认修改" onclick="updatePhone('${clientInfo.id}')"/>
            <input type="button" value="取消" onclick="cancleUpdate('checkPhone_div')"/><br>
    </div>
    <div style="display: none" id="updatePhone_div">
        <input type="text" placeholder="新手机号" id="phone"/><br>
        <input type="text" placeholder="验证码" id="telCode"/><br>
        <input type="button" value="确认修改" onclick="updatePhone('${clientInfo.id}')"/>
        <input type="button" value="取消" onclick="cancleUpdate('updatePhone_div')"/><br>
    </div>

    <li>email：<span id="clientEmailSpan">${clientInfo.email}</span><a href="javaScript:updateClient('updateEmail_div')">修改</a></li>
    <div style="display: none" id="updateEmail_div">
            <input type="text" placeholder="请输入邮箱" id="email" name="email"/><br>
            <input type="button" onclick="updateClientEmail('${clientInfo.id}')" value="确认修改"/>
            <input type="button" value="取消" onclick="cancleUpdate('updateEmail_div')"/><br>
    </div>
    <li>生日：${clientInfo.birthday}</li>
    <div style="display: none" id="updateBirthday_div">
            <input type="hidden" value="${clientInfo.id}">
            <input type="text" readonly="readonly" placeholder="选择日期" id="birthday" name="birthday"/><br>
            <input type="button" value="确认修改"/>
            <input type="button" value="取消" onclick="cancleUpdate('updateBirthday_div')"/><br>

    </div>
    <li>注册时间：<fmt:formatDate value="${clientInfo.regTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </li>

    <li>地址：<span id="clientAreaSpan">${clientInfo.address}</span><a href="javaScript:updateClient('updateAddress_div')">修改</a></li>
    <div style="display: none" id="updateAddress_div">
        <form method="post" action="">
            <div id="areaSelect"></div>
            <input type="button" onclick="updateArea('${clientInfo.id}')" value="确认修改"/>
            <input type="button" value="取消" onclick="cancleUpdate('updateAddress_div')"/><br>
        </form>
    </div>

</ul>
<script type="text/javascript">

    //页面加载事件
    $(function () {
        dateController();
        initCity(1);
    })

    //显示修改div
    function updateClient(obj) {
        $("#"+obj+"").css({"display":""})
    }
    //隐藏修改DIV
    function cancleUpdate(obj) {
        $("#"+obj+"").css({"display":"none"})
    }
    //时间控件
    function dateController() {
        $("#birthday").datetimepicker({
            "format": "yyyy-mm-dd",
            "language": "zh-CN",
            "minView": 'month',
            "clearBtn": "true",
            "autoclose": "true"
        });
    }
    //加载地区
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
    //修改密码
    function updatePwd(id){
        var v_password = $("#password").val();
        var v_oldPwd = $("#oldPwd").val();
        $.ajax({
            url:"<%=request.getContextPath()%>/client/updatePassword.jhtml",
            data:{
                "password":v_password,
                "oldPwd":v_oldPwd,
                "id":id
            },
            success:function (res) {
                alert(res.message)
                if (res.code==200) {
                    $("#updatePwd_div").css({"display":"none"})
                }

            }
        })
    }
    //修改手机号
    function updatePhone(id){
        $.ajax({
            url:"",
            data:{

            },
            success:function (res) {
                if (res.code==200){
                    alert(res.message)
                }else {
                    alert(res.message)
                }
            }
        })
    }
    //验证手机号
    function checkPhone(id){
        $.ajax({
            url:"",
            data:{

            },
            success:function (res) {
                if (res.code==200){
                    alert(res.message)
                }else {
                    alert(res.message)
                }
            }
        })
    }
    //修改姓名
    function updateClientName(id){
      var v_clientName = $("#clientName").val();
      $.ajax({
          url:"<%=request.getContextPath()%>/client/updateClientName.jhtml",
          data:{
            "clientName":v_clientName,
              "id":id
          },
          success:function (res) {
              if (res.code==200){
                    $("#clientNameSpan").text(v_clientName);
                  $("#updateName_div").css({"display":"none"})
              }else {
                  alert(res.message)
              }
          }
      })
    }
    //修改email
    function updateClientEmail(id){
        var v_email = $("#email").val();
        $.ajax({
            url:"<%=request.getContextPath()%>/client/updateClientEmail.jhtml",
            data:{
                "email":v_email,
                "id":id
            },
            success:function (res) {
                if (res.code==200){
                    $("#clientEmailSpan").text(v_email);
                    $("#updateEmail_div").css({"display":"none"})
                    alert(res.message)
                }else {
                    alert(res.message)
                }
            }
        })
    }
    //修改地区
    function updateArea(id){
        var param = {};
        if ($("select[name='areaSel']").length == 2){
            param.shengId = $("select[name='areaSel']")[0].value;
        }
        if ($("select[name='areaSel']").length == 3){
            param.shengId = $("select[name='areaSel']")[0].value;

            param.shiId = $("select[name='areaSel']")[1].value;

            param.xianId = $("select[name='areaSel']")[2].value;
        }
        param.id = id;
        var v_address = "";
        var v_addressIds = "";
        $("select[name='areaSel']").each(function(){
            v_address += this.options[this.selectedIndex].text;
            v_addressIds += this.value+",";
        })
        param.address = v_address;
        param.addressIds = v_addressIds;
        $.ajax({
            url:"<%=request.getContextPath()%>/client/updateClientArea.jhtml",
            data:param,
            success:function (res) {
                if (res.code==200){
                    $("#clientAreaSpan").text(v_address);
                    $("#updateAddress_div").css({"display":"none"})
                }else {
                    alert(res.message)
                }
            }
        })
    }
</script>
</body>
</html>
