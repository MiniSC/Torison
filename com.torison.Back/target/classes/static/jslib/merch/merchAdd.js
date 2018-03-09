

$(document).ready(function(){
	//判断商户是否在黑名单中的三个字段是商户对外名, 商户营业执照, 法人身份证
	//根据对外商户名判断商户是否在黑名单中,如果在提示无法录入
	$('#cstSignName').focusout(function(){
		if($.trim(this.value) == ''){
			return;
		}
		$.ajax({
			url:'${ctx}/merch/black/existInTBList.do',
			type:'POST',
			data:'cstSignName=' + this.value,
			dataType:'json',
			success:function(value){
				if(value.success){
					parent.$.messager.alert("Tip","该商户对外商户名在黑名单中，暂时无法录入!","error");
				}
			}
		});
	});
	
	
	//商户营业执照   '15_' 代表证件类型
	$('#cstBisnum').focusout(function(){
		if($.trim(this.value) == ''){
			return;
		}
		$.ajax({
			url:'${ctx}/merch/black/existInTBListByData.do',
			type:'POST',
			data:'data=' + '15_' +this.value,
			dataType:'json',
			success:function(value){
				if(value.success){
					parent.$.messager.alert("Tip","该商户营业执照号码在黑名单中，暂时无法录入!","error");
				}
			}
		});
	});
	
	//法人身份证  '01_'  代表证件类型
	$('#cstVoucNum').focusout(function(){
		if($.trim(this.value) == ''){
			return;
		}
		$.ajax({
			url:'${ctx}/merch/black/existInTBListByData.do',
			type:'POST',
			data:'data=' + '01_' +this.value,
			dataType:'json',
			success:function(value){
				if(value.success){
					parent.$.messager.alert("Tip","该商户法人身份证号码在黑名单中，暂时无法录入!","error");
				}
			}
		});
	});
	
	//商户商业下拉列表  
	$("#businessScope").combobox({
		onSelect:function(record){
			var no = record.value;
			if(no != null && no != ""){
				$("#itemCategory").combobox('clear');
				$("#itemCategory").combobox('reload','${ctx}/merch/selectItemCategory.do?no=' + no);
			}
		}
	});
	
	
	//企业征信按钮功能
	$("#comCredit").click(function(){
		parent.$.messager.alert("Tip","功能暂未开通!","info");
	});
	
	//银行省份下拉列表单 数据加载
	$("#bankProvince").combobox({
		onSelect:function(record){
			var proNo = record.no;
			//先清空城市列表的值
			$("#bankCity").combobox('clear');
			$("#bankCity").combobox('reload','${ctx}/merch/selectCityCat.do?no=' + proNo);
		}
	});
	
	//商户省份下拉列表单 数据加载
	$("#comProvince").combobox({
		onSelect:function(record){
			var proNo = record.no;
			//先清空城市列表的值
			$("#comCity").combobox('clear');
			$("#comCity").combobox('reload','${ctx}/merch/selectCityCat.do?no=' + proNo);
		}
	});
	

	//为计费项目添加改变事件  返回关联的计费方案
	$("#feeBelong0").combobox({
		onSelect:function(record){
			$("#freeCode0").combobox({
				valueField: 'freeCode',    
		        textField: 'freeName',    
		        url: '${ctx}/merch/fee/selectFeeComByFeeBelong.do?feeBelong=' + record.value,
			});
			
			
		}
	});
	
	
	
	
	//签约信息中商户签约产品的checkbox勾选联动
	$(".mallSignProductKuai").click(function(){
		
		var res = $(".mallSignProductKuai").attr("checked");
		if(res){
			$("#mallSignProductKuaiChu").attr("checked","checked");
			$("#mallSignProductKuaiXin").attr("checked","checked");
		}else{
			$("#mallSignProductKuaiChu").removeAttr("checked");
			$("#mallSignProductKuaiXin").removeAttr("checked");
		}
		
	 }
	);
	
	$("#mallSignProductKuaiChu").click(function(){
		var res = $("#mallSignProductKuaiChu").attr("checked");
		if(res){
			$(".mallSignProductKuai").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductKuaiXin").attr("checked");
			if(!res2){
				$(".mallSignProductKuai").removeAttr("checked");
			}
		}
	});
	$("#mallSignProductKuaiXin").click(function(){
		var res = $("#mallSignProductKuaiXin").attr("checked");
		if(res){
			$(".mallSignProductKuai").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductKuaiChu").attr("checked");
			if(!res2){
				$(".mallSignProductKuai").removeAttr("checked");
			}
		}
	});
	
	
	$(".mallSignProductPer").click(function(){
		
		var res = $(".mallSignProductPer").attr("checked");
		if(res){
			$("#mallSignProductPerChu").attr("checked","checked");
			$("#mallSignProductPerXin").attr("checked","checked");
		}else{
			$("#mallSignProductPerChu").removeAttr("checked");
			$("#mallSignProductPerXin").removeAttr("checked");
		}
		
	 }
	);
	
	$("#mallSignProductPerChu").click(function(){
		var res = $("#mallSignProductPerChu").attr("checked");
		if(res){
			$(".mallSignProductPer").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductPerXin").attr("checked");
			if(!res2){
				$(".mallSignProductPer").removeAttr("checked");
			}
		}
	});
	$("#mallSignProductPerXin").click(function(){
		var res = $("#mallSignProductPerXin").attr("checked");
		if(res){
			$(".mallSignProductPer").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductPerChu").attr("checked");
			if(!res2){
				$(".mallSignProductPer").removeAttr("checked");
			}
		}
	});
	
	
	$(".mallSignProductWeChat").click(function(){
		
		var res = $(".mallSignProductWeChat").attr("checked");
		if(res){
			$("#mallSignProductWeChatSao").attr("checked","checked");
			$("#mallSignProductWeChatH5").attr("checked","checked");
			$("#mallSignProductWeChatSDK").attr("checked","checked");
			$("#mallSignProductWeChatH5ZF").attr("checked","checked");
			$("#mallSignProductWeChatSDKZF").attr("checked","checked");
		}else{
			$("#mallSignProductWeChatSao").removeAttr("checked");
			$("#mallSignProductWeChatH5").removeAttr("checked");
			$("#mallSignProductWeChatSDK").removeAttr("checked");
			$("#mallSignProductWeChatH5ZF").removeAttr("checked");
			$("#mallSignProductWeChatSDKZF").removeAttr("checked");
		}
		
	 });
	
	$("#mallSignProductWeChatSao").click(function(){
		var res = $("#mallSignProductWeChatSao").attr("checked");
		if(res){
			$(".mallSignProductWeChat").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductWeChatH5").attr("checked");
			var res3 = $("#mallSignProductWeChatSDK").attr("checked");
			var res4 = $("#mallSignProductWeChatH5ZF").attr("checked");
			var res5 = $("#mallSignProductWeChatSDKZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductWeChat").removeAttr("checked");
			}
		}
	});
	$("#mallSignProductWeChatH5").click(function(){
		var res = $("#mallSignProductWeChatH5").attr("checked");
		if(res){
			$(".mallSignProductWeChat").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductWeChatSao").attr("checked");
			var res3 = $("#mallSignProductWeChatSDK").attr("checked");
			var res4 = $("#mallSignProductWeChatH5ZF").attr("checked");
			var res5 = $("#mallSignProductWeChatSDKZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductWeChat").removeAttr("checked");
			}
		}
	});
	
	$("#mallSignProductWeChatSDK").click(function(){
		var res = $("#mallSignProductWeChatSDK").attr("checked");
		if(res){
			$(".mallSignProductWeChat").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductWeChatSao").attr("checked");
			var res3 = $("#mallSignProductWeChatH5").attr("checked");
			var res4 = $("#mallSignProductWeChatH5ZF").attr("checked");
			var res5 = $("#mallSignProductWeChatSDKZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductWeChat").removeAttr("checked");
			}
		}
	});
	
	$("#mallSignProductWeChatH5ZF").click(function(){
		var res = $("#mallSignProductWeChatH5ZF").attr("checked");
		if(res){
			$(".mallSignProductWeChat").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductWeChatSao").attr("checked");
			var res3 = $("#mallSignProductWeChatH5").attr("checked");
			var res4 = $("#mallSignProductWeChatSDK").attr("checked");
			var res5 = $("#mallSignProductWeChatSDKZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductWeChat").removeAttr("checked");
			}
		}
	});
	
	$("#mallSignProductWeChatSDKZF").click(function(){
		var res = $("#mallSignProductWeChatSDKZF").attr("checked");
		if(res){
			$(".mallSignProductWeChat").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductWeChatSao").attr("checked");
			var res3 = $("#mallSignProductWeChatH5").attr("checked");
			var res4 = $("#mallSignProductWeChatSDK").attr("checked");
			var res5 = $("#mallSignProductWeChatH5ZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductWeChat").removeAttr("checked");
			}
		}
	});
	
	$(".mallSignProductAlipay").click(function(){
		
		var res = $(".mallSignProductAlipay").attr("checked");
		if(res){
			$("#mallSignProductAlipaySao").attr("checked","checked");
			$("#mallSignProductAlipayH5").attr("checked","checked");
			$("#mallSignProductAlipaySDK").attr("checked","checked");
			$("#mallSignProductAlipayH5ZF").attr("checked","checked");
			$("#mallSignProductAlipaySDKZF").attr("checked","checked");
		}else{
			$("#mallSignProductAlipaySao").removeAttr("checked");
			$("#mallSignProductAlipayH5").removeAttr("checked");
			$("#mallSignProductAlipaySDK").removeAttr("checked");
			$("#mallSignProductAlipayH5ZF").removeAttr("checked");
			$("#mallSignProductAlipaySDKZF").removeAttr("checked");
		}
		
	 }
	);
	
	$("#mallSignProductAlipaySao").click(function(){
		var res = $("#mallSignProductAlipaySao").attr("checked");
		if(res){
			$(".mallSignProductAlipay").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductAlipayH5").attr("checked");
			var res3 = $("#mallSignProductAlipaySDK").attr("checked");
			var res4 = $("#mallSignProductAlipayH5ZF").attr("checked");
			var res5 = $("#mallSignProductAlipaySDKZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductAlipay").removeAttr("checked");
			}
		}
	});
	$("#mallSignProductAlipayH5").click(function(){
		var res = $("#mallSignProductAlipayH5").attr("checked");
		if(res){
			$(".mallSignProductAlipay").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductAlipaySao").attr("checked");
			var res3 = $("#mallSignProductAlipaySDK").attr("checked");
			var res4 = $("#mallSignProductAlipayH5ZF").attr("checked");
			var res5 = $("#mallSignProductAlipaySDKZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductAlipay").removeAttr("checked");
			}
		}
	});
	
	$("#mallSignProductAlipaySDK").click(function(){
		var res = $("#mallSignProductAlipaySDK").attr("checked");
		if(res){
			$(".mallSignProductAlipay").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductAlipaySao").attr("checked");
			var res3 = $("#mallSignProductAlipayH5").attr("checked");
			var res4 = $("#mallSignProductAlipayH5ZF").attr("checked");
			var res5 = $("#mallSignProductAlipaySDKZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductAlipay").removeAttr("checked");
			}
		}
	});
	
	$("#mallSignProductAlipayH5ZF").click(function(){
		var res = $("#mallSignProductAlipayH5ZF").attr("checked");
		if(res){
			$(".mallSignProductAlipay").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductAlipaySao").attr("checked");
			var res3 = $("#mallSignProductAlipayH5").attr("checked");
			var res4 = $("#mallSignProductAlipaySDK").attr("checked");
			var res5 = $("#mallSignProductAlipaySDKZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductAlipay").removeAttr("checked");
			}
		}
	});
	
	$("#mallSignProductAlipaySDKZF").click(function(){
		var res = $("#mallSignProductAlipaySDKZF").attr("checked");
		if(res){
			$(".mallSignProductAlipay").attr("checked","checked");
		}else{
			var res2 = $("#mallSignProductAlipaySao").attr("checked");
			var res3 = $("#mallSignProductAlipayH5").attr("checked");
			var res4 = $("#mallSignProductAlipaySDK").attr("checked");
			var res5 = $("#mallSignProductAlipayH5ZF").attr("checked");
			if(!res2&&!res3&&!res4&&!res5){
				$(".mallSignProductAlipay").removeAttr("checked");
			}
		}
	});
	
	
	//微信,支付宝签约产品和其他产品为互斥关系
	/*$("#mallSignPro_td :checkbox").click(function(){
		
		var len1 = $(".mallSignPro_1 :checkbox[checked='checked']").length;
		var len2 = $(".mallSignPro_2 :checkbox[checked='checked']").length;
		
		if(len1 > 0){
			$(".mallSignPro_2 :checkbox").removeAttr("checked");
			$(".mallSignPro_2 :checkbox").attr("disabled","disabled");
		}else{
			$(".mallSignPro_2 :checkbox").removeAttr("disabled");
		}
		
		if(len2 > 0){
			$(".mallSignPro_1 :checkbox").removeAttr("checked");
			$(".mallSignPro_1 :checkbox").attr("disabled","disabled");
		}else{
			$(".mallSignPro_1 :checkbox").removeAttr("disabled");
		}
		
	});*/
	
	
	//增加收费信息的表格行数
	$("#addBtn").click(function(){
		
		tlen +=1 ;
		//表格
		var feeTab = $("#feeInfo");
		//添加新的一行
		var trNew = $("<tr>");
		
		
		//第1个单元格  计费项目
		var td02Text = $("<td class='tdl'><span class='blank'>*</span>计费项目：</td>");
		var td02 = $("<td>");
		var td02Sel = $("<select id='feeBelong"+tlen+"' name='feeCompanys["+tlen+"].feeBelong' class='easyui-combobox' onchange='feeChange(this)' data-options='editable:false,panelHeight:200'><option value=''>---请选择---</option><option value='JS'>T1结算</option>"+
				"<option value='ZF'>支付</option><option value='TK'>退款</option><option value='QF'>清分</option><option value='TX'>提现</option> <option value='FR'>分润手续费</option><option value='T0'>T0结算</option><option value='D0'>D0结算</option><option value='D1'>D1结算</option>" +"</select>");
		
		//第2个单元格    计费方案
		var td03Text = $("<td class='tdl'><span class='blank'>*</span>计费方案：</td>");
		var td03 = $("<td>");
		var td03Sel = $("<select id='freeCode"+tlen+"' class='easyui-combobox' name='feeCompanys["+ tlen +"].freeCode' data-options='delay:0'>");
		
		td02.append(td02Sel);
		td03.append(td03Sel);
		trNew.append(td02Text);
		trNew.append(td02);
		trNew.append(td03Text);
		trNew.append(td03);
		feeTab.append(trNew);
		
		$('#feeBelong'+tlen).combobox({
			onSelect:function(record){
				$('#freeCode'+tlen).combobox({
					valueField: 'freeCode',    
			        textField: 'freeName',    
			        url: '${ctx}/merch/fee/selectFeeComByFeeBelong.do?feeBelong=' + record.value,
				});
				
				
			}
		});
		$('#freeCode'+tlen).combobox();
		//$.parser.parse('#feeBelong'+tlen);
		//$.parser.parse('#freeCode'+tlen);
		
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
	        action: '${ctx}/merch/uploadPic.do',
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
	            $("#cstBisnumPicshow").attr("title",result.msg.substring(17));
	            $("#cstBisnumPicshow").attr("href","${ctx}/merch/downloadPic.do?filename=" + result.msg);
	            var cstBisnumPic = $("#cstBisnumPic").val();
	            if(cstBisnumPic !=null && cstBisnumPic != ""){
	            	//此处异步发送请求删除之前传输的图片 
	            	$.ajax({
	            	   type: "POST",
					   url: "${ctx}/merch/deleteCstPic.do",
					   data: "filename=" + cstBisnumPic,
	            	});
	            }
	            $("#cstBisnumPic").val(result.msg);
	        }  
	    });  
	  
	//法人身份证正面照片
	  $("#cstVoucFrontPicOc").upload({  
	        name: 'pic',
	        action: '${ctx}/merch/uploadPic.do',
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
	            $("#cstVoucFrontPicshow").attr("title",result.msg.substring(17));
	            $("#cstVoucFrontPicshow").attr("href","${ctx}/merch/downloadPic.do?filename=" + result.msg);
	            var cstVoucFrontPic = $("#cstVoucFrontPic").val();
	            if(cstVoucFrontPic !=null && cstVoucFrontPic != ""){
	            	//此处异步发送请求删除之前传输的图片 
	            	$.ajax({
	            	   type: "POST",
					   url: "${ctx}/merch/deleteCstPic.do",
					   data: "filename=" + cstVoucFrontPic,
	            	});
	            }
	            $("#cstVoucFrontPic").val(result.msg);
	        }  
	    });  
	//法人身份证背面照片
	  $("#cstVoucRearPicOc").upload({  
	        name: 'pic',
	        action: '${ctx}/merch/uploadPic.do',
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
	            $("#cstVoucRearPicshow").attr("title",result.msg.substring(17));
	            $("#cstVoucRearPicshow").attr("href","${ctx}/merch/downloadPic.do?filename=" + result.msg);
	            var cstVoucRearPic = $("#cstVoucRearPic").val();
	            if(cstVoucRearPic !=null && cstVoucRearPic != ""){
	            	//此处异步发送请求删除之前传输的图片 
	            	$.ajax({
	            	   type: "POST",
					   url: "${ctx}/merch/deleteCstPic.do",
					   data: "filename=" + cstVoucRearPic,
	            	});
	            }
	            $("#cstVoucRearPic").val(result.msg);
	        }  
	    });  
	  
	 //商户门头照片
	  $("#shopFacadePicOc").upload({  
	        name: 'pic',
	        action: '${ctx}/merch/uploadPic.do',
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
	            $("#shopFacadePicshow").attr("title",result.msg.substring(17));
	            $("#shopFacadePicshow").attr("href","${ctx}/merch/downloadPic.do?filename=" + result.msg);
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
	  
	//商户地址定位截图
	  $("#addrLocationSnapshotOc").upload({  
	        name: 'pic',
	        action: '${ctx}/merch/uploadPic.do',
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
	            $("#addrLocationSnapshotshow").attr("title",result.msg.substring(17));
	            $("#addrLocationSnapshotshow").attr("href","${ctx}/merch/downloadPic.do?filename=" + result.msg);
	            var addrLocationSnapshot = $("#addrLocationSnapshot").val();
	            if(addrLocationSnapshot !=null && addrLocationSnapshot != ""){
	            	//此处异步发送请求删除之前传输的图片 
	            	$.ajax({
	            	   type: "POST",
					   url: "${ctx}/merch/deleteCstPic.do",
					   data: "filename=" + addrLocationSnapshot,
	            	});
	            }
	            $("#addrLocationSnapshot").val(result.msg);
	        }  
	    });  
	  
	
});



//普通商户只有除去微信,支付宝之外的产品,平台商户支持全部产品
//同时还要注意普通二级商户的添加时,产品只能限制在父级商户内 
//平台一级商户的管理员登录名是admin这个是必须的.不可修改
$(document).ready(function(){
	
	//商户类型添加改变事件
	$("#comType").combobox({
			onSelect:function(){
				var value = $("#comType").combobox("getValue");
				if(value == "0"){
					//平台一级商户没有企业网银签约产品
					//$("#mallSignPro_td .mallSignPro_1").show();
					$("#span6").show();
					$("#loginName").val("");
					$("#loginName").removeAttr("readonly");
				}else{
					//$("#mallSignPro_td .mallSignPro_1").hide();
					$("#span6").hide();
					$("#loginName").val("admin");
					$("#loginName").attr("readonly","readonly");
				}
			}
	});
	
	
	//代理商返佣模式 ,当为分润返佣的时候才会显示分润返佣比率
	$(".commissionTd").hide();
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



//记录增加的收费信息行数
var tlen = 0;

//取消商户结算信息录入的按钮功能的实现
var clicklen = 1;
function nosettleinfo(){
	
	if(clicklen % 2 == 1){
		clicklen +=1;
		$("#settleFormInfo").hide();
		$("#nosettleinfo").linkbutton({"text":"录入结算信息"});
	}else{
		clicklen += 1;
		$("#settleFormInfo").show();
		$("#nosettleinfo").linkbutton({"text":"暂不录入结算信息"});
	}
	
	
}

//根据计费类型查询计费方案 级联查询
function feeChange(record){
	adtlen = $(record).attr("id").substr(9);
	//发送ajax 请求获得企业收费方案
	$.ajax({
		type:'POST',
		async:false,
		url:'${ctx}/merch/fee/selectFeeComByFeeBelong.do',
		data:'feeBelong=' + record.value,
		dataType:"json",
		success:function(data){
			//先清空之前的下拉数据然后再添加
			$("#freeCode"+ adtlen).empty();
			//遍历所有的对象添加到级联的下拉计费方案中
			var optFeeCode = $("#freeCode"+ adtlen);
			for(var i=0; i<data.length; ++i){
				optFeeCode.append("<option value='"+data[i].freeCode+"'>"+data[i].freeName+"</option>");
			}
		}
	});
}


//自定义的校验规则
$.extend($.fn.validatebox.defaults.rules, {
	chEngnum : {
		validator : function(value, param) {
			return /^[a-zA-Z\d\u4e00-\u9fa5]{1,500}$/.test(value);
		},
		message : '只能是汉字或数字或字母'
	}
});


//自定义的校验规则
$.extend($.fn.validatebox.defaults.rules, {
	chEngNumSpical : {
		validator : function(value, param) {
			return /^[a-zA-Z\d\u4e00-\u9fa5()（）]{1,}$/.test(value);
		},
		message : '只能是汉字或数字或字母和()'
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

