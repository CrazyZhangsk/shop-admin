function initBrandList(){
	$.ajax({
		url:"/brand/initBrandList.jhtml",
		type:"post",
		success:function(result){
			var v_brandList = result.data
			if(result.code==200){
				for(var i=0;i<v_brandList.length;i++){
					/*$("#brandId").append("<option value='"+v_brandList[i].id+"'>"+v_brandList[i].brandName+"</option>")*/
					$("select[name='brand.id']").append("<option value='"+v_brandList[i].id+"'>"+v_brandList[i].brandName+"</option>")
				}
			}
		}
	})
}