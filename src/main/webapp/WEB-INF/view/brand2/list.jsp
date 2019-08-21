<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询品牌页面</title>
</head>
<body>
	<div>
		<input id="insertBrandName" type="text"/>
		<input type="button" onclick="insertBrandName()" value="增加品牌"/>
	</div>
	
	<!-- brand展示 -->
	<div id="brandList">
		<jsp:include page="/WEB-INF/view/brand2/brandList.jsp"></jsp:include>
	</div>
	
<!-------------------------------------- JavaScript --------------------------------------------------------->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function editBrand(id) {
		var brandName = $("td[data-id=''"+id+"'']").text();
		$("td[data-id=''"+id+"'']").html("<input type='text' value='"+brandName+"'/><input type='button' onclick='canleUpdate(\""+id+"\",\""+brandName+"\")' value='取消'/><input type='button' onclick='updateBrand(\""+id+"\")' value='确认修改'/>")
	}
	
	function canleUpdate(id,brandName){
		$("td[data-id=''"+id+"'']").html(brandName)
	}
	
	function updateBrand(id){
		v_brandName = $("td[data-id=''"+id+"'']").find("input")[0].value;
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/brand/updateBrand.jhtml",
			data:{
				"brandName":v_brandName,
				"id":id
			},
			success:function(result){
				if(result.code==200){
					$("td[data-id=''"+id+"'']").html(v_brandName)
				}
			}
			
		})
		
	}
	
	/* 点击行变色，并且上下页能够回显  */
	var ids = [];
	function initCheck(){
		$("#brandTable tbody tr").each(function(){
			var v_checked = $(this).find("input[name='check']:checkbox");
			//给每一行添加点击事件
			$(this).bind("click",function(){
				if(v_checked.get(0).checked){
					$(this).css({"background":""});
					v_checked.prop({"checked":false});
					deleteId(v_checked.val());
				}else{
					$(this).css({"background":"pink"});
					v_checked.prop({"checked":true});
					ids.push(v_checked.val())
				}
			})
			//上一页下一页回显
			for(var i=0;i<ids.length;i++){
				if(ids[i]==v_checked.val()){
					v_checked.prop({"checked":true});
					$(this).css({"background":"pink"});
				}
			}
		})
	}
	
	/* 行没有颜色的时候需要给ids中的值去掉 */
	/* 删除的时候倒着删 */
	function deleteId(id){
		for(var i=ids.length-1;i>=0;i--){
			if(ids[i]==id){
				ids.splice(i, 1);
			}
		}
	}

	/* onready */
	$(function(){
		initCheck();
		changeColor();
	})
	
	//鼠标移上变色  移掉失去颜色
	function changeColor(){
		$("#brandTable tbody tr").each(function(){
			var v_checked = $(this).find("input[name='check']:checkbox")[0];
			$(this).bind("mouseover",function(){
				if(!v_checked.checked){
					$(this).css({"background":"#ccc"});
				}
			});
			$(this).bind("mouseout",function(){
				if(!v_checked.checked){
					$(this).css({"background":""});
				}
			});
		});
	}
	
	/* 查询 */
	function findBrandList(page){
		$.ajax({
			url:"${pageContext.request.contextPath}/brand/findBrandList2.jhtml",
			data:{
				"flag":1
			},
			type:"post",
			dataType:"text",
			async:true,
			success:function(result){
				$("#brandList").html(result)
				initCheck();
				changeColor();
			},
			error:function(){
				alert("哈哈哈哈，你真菜，ajax都失败")
			}
		})
	}
	
	/* 批量删除 */
	function deleteBatch(){
		if(ids.length==0){
			alert("请至少选择一项")
		}else{
			if(confirm("确定要删除么？")) {
				var v_ids = ids.join(",");
				$.ajax({
					url:"<%=request.getContextPath()%>/brand/deleteBatch.jhtml",
					data:{
						"ids":v_ids
					},
					type:"post",
					dataType:"json",
					async:true,
					success:function(result){
						if(result==200){
							alert(result.msg)
							findBrandList(1);
						}else if(result==2000){
							alert(result.msg)
						}else{
							alert(result.msg)
						}
					},
					error:function(){
						alert("哈哈哈哈，你真菜，ajax都失败")
					}
				});
			}
		}
	}
	
	
	/* 全选 */
	function checkAll(obj){
		$("input[name='check']").each(function(){
			$(this).prop({"checked":obj});
		})
	}
	
	/* 反选 */
	function reCheck(){
		$("input[name='check']").each(function(){
			$(this).prop({"checked":!$(this).prop("checked")});
		})
	}
	

</script>
</body>
</html>