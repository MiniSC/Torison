$(document).ready(function(){
	
	$('#cstSignName').focusout(function(){
		if($.trim(this.value) == ''){
			return;
		}
		$.ajax({
			url:'merch/black/existInTBList.do',
			type:'POST',
			data:'cstSignName=' + this.value,
			dataType:'json',
			success:function(value){
				if(value.success){
					//merchManage.js中的变量修改
					parent.$.messager.alert("Tip","该商户对外商户名在黑名单中，谨慎修改!","error");
				}
			}
		});
	});
	
	
	//商户营业执照
	$('#cstBisnum').focusout(function(){
		if($.trim(this.value) == ''){
			return;
		}
		$.ajax({
			url:'merch/black/existInTBListByData.do',
			type:'POST',
			data:'data=' + '15_'+ this.value,
			dataType:'json',
			success:function(value){
				if(value.success){
					//merchManage.js中的变量修改
					parent.$.messager.alert("Tip","该商户营业执照号码在黑名单中，谨慎修改!","error");
				}
			}
		});
	});
	
	//发人身份证
	$('#cstVoucNum').focusout(function(){
		if($.trim(this.value) == ''){
			return;
		}
		$.ajax({
			url:'merch/black/existInTBListByData.do',
			type:'POST',
			data:'data=' + '01_' + this.value,
			dataType:'json',
			success:function(value){
				if(value.success){
					//merchManage.js中的变量修改
					parent.$.messager.alert("Tip","该商户法人身份证号码在黑名单中，谨慎修改!","error");
				}
			}
		});
	});
	
	//商户商业下拉列表  
	$("#businessScope").combobox({
		onSelect:function(record){
			var no = record.no;
			if(no != null && no != ""){
				$("#itemCategory").combobox('clear');
				$("#itemCategory").combobox('reload','merch/selectItemCategory.do?no=' + no);
			}
		}
	});
	
	
	
	//商户省份下拉列表单 数据加载
	$("#comProvince").combobox({
		onSelect:function(record){
			var proNo = record.no;
			//先清空城市列表的值
			$("#comCity").combobox('clear');
			$("#comCity").combobox('reload','merch/selectCityCat.do?no=' + proNo);
		}
	});
	
	
});


//营业执照,法人身份证照片的处理
$(document).ready(function(){
	
	 //图片的展示效果
	  $("#cstVoucFrontPicshow").rebox({zIndex:9600});
	  $("#cstVoucRearPicshow").rebox({zIndex:9600});
	  $("#cstBisnumPicshow").rebox({zIndex:9600});
	  $("#shopFacadePicshow").rebox({zIndex:9600});
	  $("#addrLocationSnapshotshow").rebox({zIndex:9600});
	  
	  //营业执照照片上传处理
	  $("#cstBisnumPicOc").upload({  
	        name: 'pic',
	        action: 'merch/uploadPic.do',
	        enctype: 'multipart/form-data',
	        autoSubmit: true, 
	        onComplete: function(response) {
	            var result = eval("("+response+")");
	            //传输失败
	            if(!result.success){
	            	parent.$.messager.alert("提示",result.msg,"warning");
	            	return;
	            }
	            $("#cstBisnumPicshow").html(result.msg.substring(17));
	            $("#cstBisnumPicshow").attr("href","merch/downloadPic.do?filename=" + result.msg);
	            var cstBisnumPic = $("#cstBisnumPic").val();
	            if(cstBisnumPic !=null && cstBisnumPic != ""){
	            	//此处异步发送请求删除之前传输的图片 
	            	$.ajax({
	            	   type: "POST",
					   url: "merch/deleteCstPic.do",
					   data: "filename=" + cstBisnumPic,
	            	});
	            }
	            $("#cstBisnumPic").val(result.msg);
	        }  
	    });  
	  
	//法人身份证正面照片
	  $("#cstVoucFrontPicOc").upload({  
	        name: 'pic',
	        action: 'merch/uploadPic.do',
	        enctype: 'multipart/form-data',
	        autoSubmit: true, 
	        onComplete: function(response) {
	            var result = eval("("+response+")");
	            //传输失败
	            if(!result.success){
	            	parent.$.messager.alert("提示",result.msg,"warning");
	            	return;
	            }
	            $("#cstVoucFrontPicshow").html(result.msg.substring(17));
	            $("#cstVoucFrontPicshow").attr("href","merch/downloadPic.do?filename=" + result.msg);
	            var cstVoucFrontPic = $("#cstVoucFrontPic").val();
	            if(cstVoucFrontPic !=null && cstVoucFrontPic != ""){
	            	//此处异步发送请求删除之前传输的图片 
	            	$.ajax({
	            	   type: "POST",
					   url: "merch/deleteCstPic.do",
					   data: "filename=" + cstVoucFrontPic,
	            	});
	            }
	            $("#cstVoucFrontPic").val(result.msg);
	        }  
	    });  
	//法人身份证背面照片
	  $("#cstVoucRearPicOc").upload({  
	        name: 'pic',
	        action: 'merch/uploadPic.do',
	        enctype: 'multipart/form-data',
	        autoSubmit: true, 
	        onComplete: function(response) {
	            var result = eval("("+response+")");
	            //传输失败
	            if(!result.success){
	            	parent.$.messager.alert("提示",result.msg,"warning");
	            	return;
	            }
	            $("#cstVoucRearPicshow").html(result.msg.substring(17));
	            $("#cstVoucRearPicshow").attr("href","merch/downloadPic.do?filename=" + result.msg);
	            var cstVoucRearPic = $("#cstVoucRearPic").val();
	            if(cstVoucRearPic !=null && cstVoucRearPic != ""){
	            	//此处异步发送请求删除之前传输的图片 
	            	$.ajax({
	            	   type: "POST",
					   url: "merch/deleteCstPic.do",
					   data: "filename=" + cstVoucRearPic,
	            	});
	            }
	            $("#cstVoucRearPic").val(result.msg);
	        }  
	    });  
	
	//商户门头照片
	  $("#shopFacadePicOc").upload({  
	        name: 'pic',
	        action: 'merch/uploadPic.do',
	        enctype: 'multipart/form-data',
	        autoSubmit: true, 
	        onComplete: function(response) {
	            var result = eval("("+response+")");
	            //传输失败
	            if(!result.success){
	            	parent.$.messager.alert("提示",result.msg,"warning");
	            	return;
	            }
	            $("#shopFacadePicshow").html(result.msg.substring(17));
	            $("#shopFacadePicshow").attr("href","merch/downloadPic.do?filename=" + result.msg);
	            var shopFacadePic = $("#shopFacadePic").val();
	            if(shopFacadePic !=null && shopFacadePic != ""){
	            	//此处异步发送请求删除之前传输的图片 
	            	$.ajax({
	            	   type: "POST",
					   url: "merch/deleteCstPic.do",
					   data: "filename=" + shopFacadePic,
	            	});
	            }
	            $("#shopFacadePic").val(result.msg);
	        }  
	    });  
	  
	//商户门头照片
	  $("#addrLocationSnapshotOc").upload({  
	        name: 'pic',
	        action: 'merch/uploadPic.do',
	        enctype: 'multipart/form-data',
	        autoSubmit: true, 
	        onComplete: function(response) {
	            var result = eval("("+response+")");
	            //传输失败
	            if(!result.success){
	            	parent.$.messager.alert("提示",result.msg,"warning");
	            	return;
	            }
	            $("#addrLocationSnapshotshow").html(result.msg.substring(17));
	            $("#addrLocationSnapshotshow").attr("href","merch/downloadPic.do?filename=" + result.msg);
	            var addrLocationSnapshot = $("#addrLocationSnapshot").val();
	            if(addrLocationSnapshot !=null && addrLocationSnapshot != ""){
	            	//此处异步发送请求删除之前传输的图片 
	            	$.ajax({
	            	   type: "POST",
					   url: "merch/deleteCstPic.do",
					   data: "filename=" + addrLocationSnapshot,
	            	});
	            }
	            $("#addrLocationSnapshot").val(result.msg);
	        }  
	    });  
	  
	//当返佣模式为分润返佣时，要求填写分润返佣比例
	  /*$("#commissionType").combobox({
	    	onSelect:function(){
	    		if($("#commissionType").combobox("getValue") == '3'){
	    			$("#commissionValueSpan").show();
	    		}else{
	    			$("#commissionValueSpan").hide();
	    		}
	    	}
	    });*/
});

$(document).ready(function(){
	//代理商返佣模式 ,当为分润返佣的时候才会显示分润返佣比率
	var commissionType = $("select[name='commissionType'] option:selected").val();
	if(commissionType != '3'){
		$(".commissionTd").hide();
	}
	$("#commissionType").combobox({
		onSelect:function(record){
			if(record.value == '3'){
				$(".commissionTd").show();
			}else{
				$(".commissionTd").hide();
			}
		}
	});
	
});



//自定义的校验规则
$.extend($.fn.validatebox.defaults.rules, {
	chEngnum : {
		validator : function(value, param) {
			return /^[a-zA-Z\d\u4e00-\u9fa5]{1,500}$/.test(value);
		},
		message : '只能是汉字或数字或字母'
	}
});



$.extend($.fn.validatebox.defaults.rules, {
	engnumber : {
		validator : function(value, param) {
			return /^[a-zA-Z\d]{1,500}$/.test(value);
		},
		message : '只能是数字或字母'
	}
});

$.extend($.fn.validatebox.defaults.rules, {
	isCard : {
		validator : function(value, param) {
			return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
		},
		message : '身份证格式不正确'
	}
});

$.extend($.fn.validatebox.defaults.rules, {
	phone : {
		validator : function(value, param) {
			return /^((\+?86)|(\(\+86\)))?(13[0123456789][0-9]{8}|14[012356789][0-9]{8}|15[012356789][0-9]{8}|17[012356789][0-9]{8}|18[02356789][0-9]{8})$/.test(value);
		},
		message : '手机号码格式不正确'
	}
});

$.extend($.fn.validatebox.defaults.rules, {
	positiveNumber : {
		validator : function(value, param) {
			return /^\d+(\.{0,1}\d+){0,1}$/.test(value);
		},
		message : '只能是大于等于0的非负数'
	}
});


$.extend($.fn.validatebox.defaults.rules, {
	onlyNumber : {
		validator : function(value, param) {
			return /^\d*$/.test(value);
		},
		message : '只能由数字组成'
	}
});


$.extend($.fn.validatebox.defaults.rules, {
	mobileOrPhone : {
		validator : function(value, param) {
			return /^([0-9]{3,4}-)?[0-9]{7,8}$/.test(value) || /^((\+?86)|(\(\+86\)))?(13[0123456789][0-9]{8}|14[012356789][0-9]{8}|15[012356789][0-9]{8}|17[012356789][0-9]{8}|18[02356789][0-9]{8})$/.test(value);
		},
		message : '联系电话格式不正确'
	}
});

