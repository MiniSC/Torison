/*
	商户信息管理界面的javascript
*/
var dataGrid;
var pCstNo;
$(document).ready(function(){
	
	$("#reset").click(function(){
		$("#countForm").form('clear');
	});
	
	pCstNo = $("#parentCstNo").val();
	
	//条件查询
	$("#select").click(function(){
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		//序列化表单中的参数,然后datagrid重新加载新的查询数据
		var json = $("#countForm").serializeArray();
		
		$("#comdg").datagrid("load", {
			//parentCstNo:json[0].value,
			cstName:json[1].value,
			businessScope:json[2].value,
			cstBisnumStatus:json[3].value,
			createTime:json[4].value,
			createTimeEnd:json[5].value,
			cstState:json[6].value,
			cstVoucStatus:json[7].value
		});
		parent.$.messager.progress('close');
	});
	
	$(document).keydown(function(event){
		
		if(event.keyCode == 13){
			$("#select").click();
		}
		
	 });
	
	
	//表格中的列定义
	var columns = [
	       [{field:'cstNo',title:'客户编号',width:100,align:'center'
	  },{
		field:'cstState',title:'商户状态',width:60,align:'center',
		formatter:function(value,row,index){
			if(value =='0'){
				return "正常"
			}else if(value =="1"){
				return "未激活";
			}else if(value =="2"){
				return "冻结";
			}else if(value =="3"){
				return "注销";
			}else if(value =="4"){
				return "待审核";
			}else if(value =="5"){
				return "暂时冻结";
			}else if(value == "6"){
				return "审核拒绝";
			}else if(value == "7"){
				return "待复核";
			}else{
				return "";
			}
		}
	},{
		field:'businessScope',title:'商户行业',width:100,align:'center',
		formatter:function(value,row,index){
			if(value == null || value == undefined){
				return "";
			}else {
				return value;
			}
		}
	},{
		field:'cstBisnum',title:'营业执照号码',width:120,align:'center',
	},{
		field:'cstBisnumTdate',title:'营业执照截止日期',width:100,align:'center',
		formatter:function(value,row,index){
			if(value == null || value == undefined){
				return "";
			}else {
				return value;
			}
		}
	},{
		field:'chairmanName',title:'企业法人',width:80,align:'center'
	},{
		field:'cstVoucNum',title:'法人证件号',width:140,align:'center'
	},{
		field:'taxAuthorityNo',title:'税务登记证号',width:120,align:'center'
	},{
		field:'comType',title:'商户类型',width:100,align:'center',
		formatter:function(value, row, index){
			if(value == null || value == '0'){
				return "普通商户";
			}else if(value == '1'){
				return "平台商户";
			}else if(value == '2'){
				return "入驻商户";
			}else{
				return value;
			}
		}
	},{
		field:'createTime',title:'客户注册时间',sortable:true,width:80,align:'center',
		formatter:function(value,row,index){
			if(value == null || value == undefined){
				return "";
			}else {
				var year = value.substr(0,4);
				var month = value.substr(4,2);
				var day = value.substr(6,2);
				return year + "-" + month + "-" + day;
			}
		}
	},{
		field:'operate',title:'操作',width:300,align:'center',
		formatter:function(value,row,index){
			var status = row.cstState;
			   var options = "";
			   if(status=='0'){//0：正常；
				   if($.cancelMerch){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>&nbsp;&nbsp;';
				   }
				   if($.freezeMerch){
					   options += '<a  onclick="freezeMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">冻结</a>';
				   }
				   if($.riskReg){
					   options += '&nbsp;&nbsp;<a  onclick="riskReg('+"'"+row.cstNo+"','" + row.cstName+"'"+')" href="javascript:void(0);">规则参数</a>';
				   }
				   if($.operatorManage){
						options += '&nbsp;&nbsp;<a  onclick="operatorManage('+"'"+ row.cstNo+"','" + row.loginName+"','" + row.cstName + "'"+')" href="javascript:void(0);">操作员管理</a>';
					}
				   if($.merchAccInfoIndex){
					   options += '&nbsp;&nbsp;<a  onclick="merchAccInfo('+"'"+ row.cstNo+"'" + ')" href="javascript:void(0);">账户信息</a>';
				   }
		       }else if(status=='1'){//1：未激活；
		    	   if($.cancelMerch){
		    		   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>&nbsp;&nbsp;';
				   }
		    	   if($.activateMerch){
		    		   options += '<a  onclick="activateMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">激活</a>';
		    	   }
			   }else if(status=='2'){//2：冻结
				   if($.cancelMerch){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>&nbsp;&nbsp;';
				   }
				   if($.thawMerch){
					   options += '<a  onclick="thawMerch('+"'"+row.cstNo+"'"+')"  href="javascript:void(0);">解冻</a>'; 
				   }
			   }else if(status=='3'){//3：注销
				// options += '<a  onclick="" href="javascript:void(0);">激活</a>';
			   }else if(status=='4'){//4:待审核
				   if($.reviewMerch){
					   options += '<a  onclick="reviewMerch('+"'"+row.cstNo+"','"+row.parentCstNo+"','"+row.comType+"'"+')" href="javascript:void(0);">审核</a>&nbsp;&nbsp;';
				   }
				   if($.cancelMerch){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>';
				   }
			   }else if(status=='5'){//5：暂时冻结
				   if($.cancelMerch){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>&nbsp;&nbsp;';
				   }
				   if($.thawMerch){
					   options += '<a  onclick="thawMerch('+"'"+row.cstNo+"'"+')"  href="javascript:void(0);">解冻</a>';
				   }
			   }else if(status=='6'){//6：审核不通过；
				   if($.cancelMerch){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>';
				   }
			   }else if(status=='7'){//6：审核不通过；
				   if($.reviewMerch){
					   options += '<a  onclick="reReviewMerch('+"'"+row.cstNo+"','"+row.parentCstNo+"','"+row.comType+"'"+')" href="javascript:void(0);">复核</a>&nbsp;&nbsp;';
				   }
				   if($.cancelMerch){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>';
				   }
			   }
			
			return options;
		}
	}]];
	
	
	

	//数据表格datagrid
	dataGrid = $("#comdg").datagrid({
		url:'selectAllMerchants.do?parentCstNo=' + pCstNo,
		columns:columns,
		pagination:true,
		striped:true,
		rownumbers:true,
		singleSelect:true,
		multiSort:true,
		fit:true,
		onDblClickCell:dbClick,
	    frozenColumns:[[
						{field:'id',title:'编号',checkbox:true,
						},{
							field:'cstName',title:'商户名称'
						}
	                ]],
		toolbar:'#tb',
		rowStyler: function(index,row){
			if(row.blacklist != '0'){
				return 'background-color:black;color:#fff;';
			}
			if(!stylefunction(row.cstBisnumTdate) || !stylefunction(row.cstVoucTdate)){
				return 'background-color:red;color:#fff;';
			}
		},
		onBeforeLoad:function(){
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
		},
		onLoadError:function(){
			parent.$.messager.progress("close");
		},
		onLoadSuccess:function(){
			parent.$.messager.progress("close");
			$(this).datagrid('tooltip',['cstName','cstShotname','cstBisnum','cstVoucNum','taxAuthorityNo']);
			$("#comdg").parent().find("div .datagrid-header-check").children("input[type=\"checkbox\"]").eq(0).attr("style", "display:none;");
		}

		
	});
	

});
	
	
//双击查看商户详细信息
function dbClick(index,field,value){
	var row = $("#comdg").datagrid("getSelected");
	if(row != null){
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		var cstNo = row.cstNo;
		//根据商户的编号查询商户的所有信息然后展示到一个页面
		parent.$.modalDialog({
			title : '查看商户信息',
			width : 820,
			height : 560,
			href : 'merch/merchView.do?cstNo=' + cstNo,
		});
		
		parent.$.messager.progress("close");
	}
}


//商户信息录入的弹出框
	
function append(){
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	if($.addMerchant){
		parent.$.modalDialog({
			title : '添加商户信息',
			width : 820,
			height : 560,
			href : 'secondMerch/merchInput.do?parentCstNo=' + pCstNo,
			buttons : [ {
				text : '保存',
				handler : savemerch
			}]
		});
	}else{
		parent.$.modalDialog({
			title : '添加商户信息',
			width : 820,
			height : 560,
			href : 'secondMerch/merchInput.do?parentCstNo=' + pCstNo,
		});
	}
	
	parent.$.messager.progress("close");
}	



//商户添加按钮功能的实现
function savemerch(){
	
	parent.$.modalDialog.openner_dataGrid = dataGrid;
	//第一个录入表单的校验  商户基本信息的校验
	//自己写的校验规则校验
	var flag1 = validateForm1();
	if(!flag1){
		return;
	}
	//easyui-form  规则的校验  整体的校验,排除几个无法获取的下拉框的元素信息
	var result = parent.$.modalDialog.handler.find('#merchantFormInfo').form("validate");
	if(!result){
		parent.$.messager.alert("提示","商户基本信息录入有误","warning");
		return;
	}
	
	
	//第二个表单的校验
	//获取是否录入商户结算信息
	var nosettleinfo = parent.$.modalDialog.handler.find('#nosettleinfo');
	if($(nosettleinfo).html().indexOf("不") >= 0){
		
		//需要检验结算信息
		var settleValidateflag = validateForm2();
		if(!settleValidateflag){
			return;
		}
		
		var result = parent.$.modalDialog.handler.find('#settleFormInfo').form("validate");
		if(!result){
			parent.$.messager.alert("提示","商户结算信息录入有误","warning");
			return;
		}
	}
	
	//第三个表单校验
	var flag3 = validateForm3();
	if(!flag3){
		return;
	}
	
	var result = parent.$.modalDialog.handler.find('#schemeFormInfo').form("validate");
	if(!result){
		parent.$.messager.alert("提示","商户签约信息录入有误","warning");
		return;
	}
	
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	//提交表单数据  先得到表单数据
	var params1 = parent.$.modalDialog.handler.find('#merchantFormInfo').serialize();
	var params2 = parent.$.modalDialog.handler.find('#settleFormInfo').serialize();
	var params3 = parent.$.modalDialog.handler.find('#schemeFormInfo').serialize();
	
	//这里需要一个特殊处理   商户的基本信息中paymentType字段是varchar2(5), 支持支付方式 (1000账户支付0100 储蓄卡支付 0010信用卡支付0001预付卡支付)
	//所以需要转化为一个值 1011 1001之类的数据去传递
	//商户支持的支持的支付方式
	var paymentTypevalue = getcheck("input[class='paymentTypecheck']");
	//商城支持的操作类型
	var operatTypevalue = getcheck("input[class='operatTypecheck']");
	//商户签约产品
	var mallSignProduct = getMallSignProductcheck("input[class='mallSignProduct']");
	
	var params = "";
	if($(nosettleinfo).html().indexOf("不") >= 0){
		params = params1 + "&paymentType="+ paymentTypevalue +"&"+ params2 + "&" + params3 + "&operatType=" + operatTypevalue + "&mallSignProduct="+ mallSignProduct + "&saveSettleData=1";
	}else{
		params = params1 + "&paymentType="+ paymentTypevalue + "&" + params3 + "&operatType=" + operatTypevalue + "&mallSignProduct="+ mallSignProduct + "&saveSettleData=0";
	}
	
	$.ajax({
		type:"POST",
		url:"addMerchant.do",
		data:params,
		success:function(result){
			var data = eval("("+result+")");
			if(!data.success){
				parent.$.messager.progress("close");
				parent.$.messager.alert("温馨提示",data.msg,"warning");
			}else{
				parent.$.messager.progress("close");
				parent.$.modalDialog.handler.dialog("close");
				//then refresh the datagrid
				parent.$.modalDialog.openner_dataGrid.datagrid("reload");
			}
		}
	
	});
	
}







//三个表单的校验方法

//第一个表单校验
function validateForm1(actionName){
	
	var cstName = parent.$.modalDialog.handler.find('#cstName');
	if(isBlank(cstName[0].value)){
		parent.$.messager.alert("提示","商户名称不能够为空","warning");
		return false;
	}
	
	if(!testFlexLengthText(cstName[0].value,200)){
		parent.$.messager.alert("提示","商户名称只能是0~200个英文,数字或0~100个汉字组成","warning");
		return false;
	}
	
	var cstEngName = parent.$.modalDialog.handler.find('#cstEngName');
	if(isBlank(cstEngName[0].value)){
		parent.$.messager.alert("提示","商户英文名称不能够为空","warning");
		return false;
	}
	if(!engNum(cstEngName[0].value)){
		parent.$.messager.alert("提示","商户英文名称只能是英文和数字组成","warning");
		return false;
	}
	
	var cstSignName = parent.$.modalDialog.handler.find('#cstSignName');
	if(isBlank(cstSignName[0].value)){
		parent.$.messager.alert("提示","对外商户名称不能够为空","warning");
		return false;
	}
	
	if(!testFlexLengthTextSpical(cstSignName[0].value,50)){
		parent.$.messager.alert("提示","对外商户名只能是0~25个汉字或0~50个英文,数字和()组成","warning");
		return false;
	}
	
	//如果是编辑商户时此字段在黑名单中则通过,添加商户则拒绝
	var cstSignNameInTBList = false;
	$.ajax({
		url:baseURL+'/merch/black/existInTBList.do',
		type:'POST',
		data:'cstSignName=' + cstSignName[0].value,
		dataType:'json',
		async:false,
		success:function(value){
			if(value.success){
				cstSignNameInTBList = true;
			}
		}
	});
	if(cstSignNameInTBList && actionName != "edit"){
			parent.$.messager.alert("Tip","该商户对外商户名在黑名单中，暂时无法录入!","error");
			return;
	}
	
	
	var cstBisnum = parent.$.modalDialog.handler.find('#cstBisnum');
	if(isBlank(cstBisnum[0].value)){
		parent.$.messager.alert("提示","营业执照号码不能够为空","warning");
		return false;
	}
	if(!engNum(cstBisnum[0].value)){
		parent.$.messager.alert("提示","营业执照号码只能是英文和数字组成","warning");
		return false;
	}
	
	//cstBisnumInTBList
	var cstBisnumInTBList = false;
	$.ajax({
		url:baseURL+'/merch/black/existInTBListByData.do',
		type:'POST',
		data:'data=' + '15_'+cstBisnum[0].value,
		dataType:'json',
		async:false,
		success:function(value){
			if(value.success){
				cstBisnumInTBList = true;
			}
		}
	});
	if(cstBisnumInTBList && actionName != "edit"){
		parent.$.messager.alert("提示","该商户营业执照号码在黑名单中,暂时拒绝录入","error");
		return;
	}
	
	
	var cstBisnumTdate = parent.$.modalDialog.handler.find('#cstBisnumTdate');
	if(isBlank(cstBisnumTdate[0].value)){
		parent.$.messager.alert("提示","营业执照到期时间不能够为空","warning");
		return false;
	}
	
	//营业执照照片
	var cstBisnumPicOc = parent.$.modalDialog.handler.find("#cstBisnumPic");
	if(isBlank(cstBisnumPicOc[0].value)){
		parent.$.messager.alert("提示","营业执照照片不能够为空","warning");
		return false;
	}
	if(!isPic(cstBisnumPicOc[0].value)){
		parent.$.messager.alert("提示","营业执照照片格式请选择 jpg,png,bmp,gif,jpeg,pcx,tiff","warning");
		return false;
	}
	
	
	
	var taxAuthorityNo = parent.$.modalDialog.handler.find('#taxAuthorityNo');
	if(isBlank(taxAuthorityNo[0].value)){
		parent.$.messager.alert("提示","税务登记证号码不能够为空","warning");
		return false;
	}
	if(!engNum(taxAuthorityNo[0].value)){
		parent.$.messager.alert("提示","税务登记证号码只能是英文和数字组成","warning");
		return false;
	}
	
	var chairmanName = parent.$.modalDialog.handler.find('#chairmanName');
	if(isBlank(chairmanName[0].value)){
		parent.$.messager.alert("提示","企业法人名称不能够为空","warning");
		return false;
	}
	
	if(!testFlexLengthText(chairmanName[0].value,50)){
		parent.$.messager.alert("提示","企业法人名称只能是0~50个数字,字母或者0~25个汉字组成","warning");
		return false;
	}
	
	var cstVoucNum = parent.$.modalDialog.handler.find('#cstVoucNum');
	if(isBlank(cstVoucNum[0].value)){
		parent.$.messager.alert("提示","法人身份证不能够为空","warning");
		return false;
	}
	if(!isCard(cstVoucNum[0].value)){
		parent.$.messager.alert("提示","法人身份证格式不正确","warning");
		return false;
	}
	
	//cstVoucNumInTBList
	var cstVoucNumInTBList = false;
	$.ajax({
		url:baseURL+'/merch/black/existInTBListByData.do',
		type:'POST',
		data:'data=' + '01_' + cstVoucNum[0].value,
		dataType:'json',
		async:false,
		success:function(value){
			if(value.success){
				cstVoucNumInTBList = true;
			}
		}
	});
	if(cstVoucNumInTBList && actionName != "edit"){
		parent.$.messager.alert("提示","该商户法人身份证号码在黑名单中,暂时拒绝录入","error");
		return;
	}
	
	
	var cstVoucTdate = parent.$.modalDialog.handler.find('#cstVoucTdate');
	if(isBlank(cstVoucTdate[0].value)){
		parent.$.messager.alert("提示","法人身份证到期时间不能够为空","warning");
		return false;
	}
	
	var cstVoucFrontPicOc = parent.$.modalDialog.handler.find('#cstVoucFrontPic');
	if(isBlank(cstVoucFrontPicOc[0].value)){
		parent.$.messager.alert("提示","法人身份证正面图片不能够为空","warning");
		return false;
	}
	if(!isPic(cstVoucFrontPicOc[0].value)){
		parent.$.messager.alert("提示","法人身份证正面图片格式请选择 jpg,png,bmp,gif,jpeg,pcx,tiff","warning");
		return false;
	}
	
	var cstVoucRearPicOc = parent.$.modalDialog.handler.find('#cstVoucRearPic');
	if(isBlank(cstVoucRearPicOc[0].value)){
		parent.$.messager.alert("提示","法人身份证背面图片不能够为空","warning");
		return false;
	}
	if(!isPic(cstVoucRearPicOc[0].value)){
		parent.$.messager.alert("提示","法人身份证背面图片格式请选择 jpg,png,bmp,gif,jpeg,pcx,tiff","warning");
		return false;
	}
	
	var bankLicence = parent.$.modalDialog.handler.find('#bankLicence');
	if(!isBlank(bankLicence[0].value)){
		if(!num(bankLicence[0].value)){
			parent.$.messager.alert("提示","银行开户许可证只能是数字","warning");
			return false;
		}
	}
	
	var kingDeeCode = parent.$.modalDialog.handler.find('#kingDeeCode');
	if(isBlank(kingDeeCode[0].value)){
		parent.$.messager.alert("提示","金蝶代码不能够为空","warning");
		return false;
	}
	if(!engNum(kingDeeCode[0].value)){
		parent.$.messager.alert("提示","金蝶代码只能是英文和数字组成","warning");
		return false;
	}
	
	
	
	//支付方式必填  四个单选框
	var paymentType  = parent.$.modalDialog.handler.find("input[class='paymentTypecheck']");
	var paymentTypelen = 0;
	for(var i = 0; i<paymentType.length; ++i){
		if(isChecked(paymentType[i])){
			paymentTypelen += 1;
		}
	}
	if(paymentTypelen == 0){
		parent.$.messager.alert("提示","支付方式为必选字段","warning");
		return false;
	}
	
	
	var accessSite = parent.$.modalDialog.handler.find("#accessSite");
	if(!isBlank(accessSite[0].value)){
		if(!isURL(accessSite[0].value)){
			parent.$.messager.alert("提示","接入网址格式不正确","warning");
			return false;
		}
	}
	
	var organizationCode = parent.$.modalDialog.handler.find('#organizationCode');
	if(isBlank(organizationCode[0].value)){
		parent.$.messager.alert("提示","组织机构代码不能够为空","warning");
		return false;
	}
	if(!engNum(organizationCode[0].value)){
		parent.$.messager.alert("提示","组织机构代码只能是字母和数字","warning");
		return false;
	}
	
	var cstResponsible = parent.$.modalDialog.handler.find('#cstResponsible');
	if(isBlank(cstResponsible[0].value)){
		parent.$.messager.alert("提示","商户负责人不能够为空","warning");
		return false;
	}
	
	if(!testFlexLengthText(cstResponsible[0].value,30)){
		parent.$.messager.alert("提示","商户负责人只能是0~30个字母,数字或者0~15个汉字组成","warning");
		return false;
	}
	
	var cstTelphone = parent.$.modalDialog.handler.find('#cstTelphone');
	if(isBlank(cstTelphone[0].value)){
		parent.$.messager.alert("提示","联系座机必填","warning");
		return false;
	}
	
	var comDetailAddr = parent.$.modalDialog.handler.find('#comDetailAddr');
	if(isBlank(comDetailAddr[0].value)){
		parent.$.messager.alert("提示","详细地址不能够为空","warning");
		return false;
	}
	
	if(!testFlexLengthText(comDetailAddr[0].value, 180)){
		parent.$.messager.alert("提示","详细地址只能是0~180个字母,数字或0~100个汉字组成","warning");
		return false;
	}
	
	
	
	//如果时添加用户需要校验登录名,编辑用户不需要校验 编辑用户的时候也不用校验管理员信息
	if(actionName != "edit"){
		
		var userName = parent.$.modalDialog.handler.find('#userName');
		if(isBlank(userName[0].value)){
			parent.$.messager.alert("提示","管理员姓名不能够为空","warning");
			return false;
		}
		
		if(!testFlexLengthText(userName[0].value, 30)){
			parent.$.messager.alert("提示","管理员姓名只能是0~30个字母,数字或0~15个汉字组成","warning");
			return false;
		}
		
		var email = parent.$.modalDialog.handler.find('#email');
		if(isBlank(email[0].value)){
			parent.$.messager.alert("提示","管理员邮箱不能够为空","warning");
			return false;
		}
		if(!isEmail(email[0].value)){
			parent.$.messager.alert("提示","管理员邮箱格式不正确","warning");
			return false;
		}
		
		
		var mobile = parent.$.modalDialog.handler.find('#mobile');
		if(isBlank(mobile[0].value)){
			parent.$.messager.alert("提示","手机号不能够为空","warning");
			return false;
		}
		if(!isMobile(mobile[0].value)){
			parent.$.messager.alert("提示","手机号码格式不正确","warning");
			return false;
		}
		
		
		var loginName = parent.$.modalDialog.handler.find('#loginName');
		if(isBlank(loginName[0].value)|| hasSpace(loginName[0].value)){
			parent.$.messager.alert("提示","登录名不能够为空且不能够包含空格","warning");
			return false;
		}
		
		
		if (!/^[0-9a-zA-Z]{1,}$/g.test(loginName[0].value) && !isEmail(loginName[0].value)) {
			parent.$.messager.alert("提示","登录名只能含字母、数字、@和.","warning");
			return false;
		}
		
		//验证登录名是否已经存在
		var existError = false;
		$.ajax({
			type  : "POST",
			async : false,
			url   : "checkOperatorLoginName.do",
			data  : "loginName=" + loginName[0].value,
			success : function(result){
				result = eval("("+result+")");
				if(result.success){
					existError = true;
				}
			}
		});
		
		//已经被占用，错误
		if(existError){
			parent.$.messager.alert("提示","登录名已经被使用！","warning");
			return false;
		}
	}
	
	
	return true;
}





//第二个表单的校验
function validateForm2(){

	
	var rcvAccountName = parent.$.modalDialog.handler.find('#rcvAccountName');
	if(isBlank(rcvAccountName[0].value)){
		parent.$.messager.alert("提示","对公账户名称不能为空","warning");
		return false;
	}
	
	if(!testFlexLengthText(rcvAccountName[0].value,100)){
		parent.$.messager.alert("提示","对公账户名只能是0~100个数字,字母或0~50个汉字组成","warning");
		return false;
	}
	
	var rcvBankAccount = parent.$.modalDialog.handler.find('#rcvBankAccount');
	if(!isBlank(rcvBankAccount[0].value)){
		if(!num(rcvBankAccount[0].value)){
			parent.$.messager.alert("提示","对公银行账号只能是数字","warning");
			return false;
		}
	}else{
		parent.$.messager.alert("提示","对公银行账号不能为空","warning");
		return false;
	}
	
	var rcvBankSub = parent.$.modalDialog.handler.find('#rcvBankSub');
	if(isBlank(rcvBankSub[0].value)){
		parent.$.messager.alert("提示","开户支行名称不能为空","warning");
		return false;
	}
	
	if(!testFlexLengthText(rcvBankSub[0].value,100)){
		parent.$.messager.alert("提示","开户支行名称只能是0~100个数字,字母或0~50个汉字组成","warning");
		return false;
	}
	
	
	var minSettleAmount = parent.$.modalDialog.handler.find('#minSettleAmount');
	if(isBlank(minSettleAmount[0].value)){
		parent.$.messager.alert("提示","最小结算金额不能够为空","warning");
		return;
	}
	if(!positiveNumber(minSettleAmount[0].value)){
		parent.$.messager.alert("提示","最小结算金额必须是非负数","warning");
		return;
	}
	
	var rcvBankLineNo = parent.$.modalDialog.handler.find('#rcvBankLineNo');
	if(!isBlank(rcvBankLineNo[0].value)){
		if(!num(rcvBankLineNo[0].value)){
			parent.$.messager.alert("提示","银行联行号只能是数字","warning");
			return false;
		}
	}else{
		parent.$.messager.alert("提示","银行联行号不能够为空","warning");
		return false;
	}
	
	var bankId = parent.$.modalDialog.handler.find('#bankId');
	if(!isBlank(bankId[0].value)){
		if(!num(bankId[0].value)){
			parent.$.messager.alert("提示","备付金账号只能是数字","warning");
			return false;
		}
	}
	
	return true;
}



//第三个表单的校验
function validateForm3(){
	
	
	var mallName = parent.$.modalDialog.handler.find('#mallName');
	if(isBlank(mallName[0].value)){
		parent.$.messager.alert("提示","电商名称不能够为空","warning");
		return false;
	}
	
	if(!testFlexLengthText(mallName[0].value,200)){
		parent.$.messager.alert("提示","电商名称称只能是0~200个数字,字母或0~100个汉字组成","warning");
		return false;
	}
	
	
	var mallWebsite = parent.$.modalDialog.handler.find('#mallWebsite');
	if(!isBlank(mallWebsite[0].value)){
		if(!isURL(mallWebsite[0].value)){
			parent.$.messager.alert("提示","电商官网网址格式不正确","warning");
			return false;
		}
	}

	return true;
}


//校验方法

//只能是汉字英文字母组成
function chEngNum(value){
	return /^[a-zA-Z\d\u4e00-\u9fa5]{1,}$/.test(value);
}
//只能是数字
function num(value){
	return /^\d{0,}$/.test(value);
}

//只能是英文和字母组成
function engNum(value){
	return /^[a-zA-Z0-9]*$/.test(value);
}

//为空校验
function isBlank(value){
	if(value == null || value == undefined || value == ''|| (value.replace(/\s*/g,"").length == 0)){
		return true;
	}
	return false;
}
//身份证号码校验
function isCard(value){
	return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
}

//图片的格式校验
function isPic(value){
	var suffix = value.substr(value.lastIndexOf(".") + 1);
	//转化为小写字母校验
	suffix = suffix.toLowerCase();
	if(suffix == "jpg" || suffix == "png" || suffix == "gif" || suffix == "jpeg" || suffix == "pcx" || suffix == "tiff"){
		return true;
	}
	return false;
}


//图片的大小暂定为10M
function picMaxValue(value){
	if(value <= 1048576){
		return true;
	}
	return false;
}

//校验checkbox 是否选中
function isChecked(value){
	if(value.checked == false){
		return false;
	}
	return true;
}
//网址校验
function isURL(value){
	 var regUrl = new RegExp();  
	 regUrl.compile("[(http)|(https)]:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]");
	return regUrl.test(value);
}

//固定电话校验
function isPhone(value){
	return /^([0-9]{3,4}-)?[0-9]{7,8}$/.test(value);
}
//手机号校验
function isMobile(value){
	return /^((\+?86)|(\(\+86\)))?(13[0123456789][0-9]{8}|14[012356789][0-9]{8}|15[012356789][0-9]{8}|17[012356789][0-9]{8}|18[02356789][0-9]{8})$/.test(value);
}

//邮箱校验
function isEmail(value){
	return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value);
}
//非负浮点数
function positiveNumber(value){
	return /^\d+(\.{0,1}\d+){0,1}$/.test(value);
}
//是否含有空额
function hasSpace(value){
	
	return /\s+/g.test(value);
}


//把支持的支付类型  支持的操作类型的值 由 四个1000 0100 0010 0001  转换成一个值xxxx,否则后台数据库会溢出,因为后台数据库的长度是5	
function getcheck(value){
	
	var paymentTypevalue = "";
	var paymentType  = parent.$.modalDialog.handler.find(value);
	for(var i = 0; i<paymentType.length; ++i){
		if(isChecked(paymentType[i])){
			paymentTypevalue += "1";
		}else{
			paymentTypevalue += "0";
		}
	}
	return paymentTypevalue;
}


//编辑商户基本信息
function editMerchant(){
	
	//确认是否有行被选中,如果有就得到得到商户的ID 无就提示先选中一行
	var row = $("#comdg").datagrid("getSelected");
	if(row != null){
		 parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
		});
		//获取商户的cstNo
		var cstNo = row.cstNo;
		
		if($.updateMerch){
			parent.$.modalDialog({
				title : '编辑商户信息',
				width : 820,
				height : 560,
				href : 'secondMerch/toMerchantEdit.do?cstNo=' + cstNo,
				buttons : [ {
					text : '保存',
					handler : editMerchantAction
				}]
			});
		}else{
			parent.$.modalDialog({
				title : '编辑商户信息',
				width : 820,
				height : 560,
				href : 'secondMerch/toMerchantEdit.do?cstNo=' + cstNo,
			});
		}
		
		
		parent.$.messager.progress("close");
	}else {
		parent.$.messager.alert("Tip","请首先选中一行信息!","info");
	}
	
}


function editMerchantAction(){
	parent.$.modalDialog.openner_dataGrid = dataGrid;
	//第一个录入表单的校验  商户基本信息的校验
	//自己写的校验规则校验
	var flag1 = validateForm1("edit");
	if(!flag1){
		return;
	}
	//easyui-form  规则的校验  整体的校验,排除几个无法获取的下拉框的元素信息
	var result = parent.$.modalDialog.handler.find('#merchantFormInfo').form("validate");
	if(!result){
		parent.$.messager.alert("提示","商户基本信息录入有误","warning");
		return;
	}
	
	
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	//提交表单数据  先得到表单数据
	var params1 = parent.$.modalDialog.handler.find('#merchantFormInfo').serialize();
	
	//这里需要一个特殊处理   商户的基本信息中paymentType字段是varchar2(5), 支持支付方式 (1000账户支付0100 储蓄卡支付 0010信用卡支付0001预付卡支付)
	//所以需要转化为一个值 1011 1001之类的数据去传递
	//商户支持的支持的支付方式
	var paymentTypevalue = getcheck("input[class='paymentTypecheck']");
	
	var params = params1 + "&paymentType="+ paymentTypevalue;
	
	$.ajax({
		type:"POST",
		url:"updateMerchant.do",
		data:params,
		success:function(result){
			var data = eval("("+result+")");
			if(!data.success){
				parent.$.messager.progress("close");
				parent.$.messager.alert("温馨提示",data.msg,"warning");
			}else{
				parent.$.messager.progress("close");
				parent.$.modalDialog.handler.dialog("close");
				//then refresh the datagrid
				parent.$.modalDialog.openner_dataGrid.datagrid("reload");
			}
		}
	
	});
}


function editSettleInfo(){

	//确认是否有行被选中,如果有就得到得到商户的ID 无就提示先选中一行
	var row = $("#comdg").datagrid("getSelected");
	if(row != null){
		 parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
		});
		//获取商户的cstNo
		var cstNo = row.cstNo;
		
		if($.updateSettleInfo){
			parent.$.modalDialog({
				title : '编辑结算信息',
				width : 820,
				height : 400,
				href : 'secondMerch/toSettleInfoEdit.do?cstNo=' + cstNo,
				buttons : [ {
					text : '保存',
					handler : editSettleInfoAction
				}]
			});
		}else{
			parent.$.modalDialog({
				title : '编辑结算信息',
				width : 820,
				height : 400,
				href : 'secondMerch/toSettleInfoEdit.do?cstNo=' + cstNo,
			});
		}
		
		
		parent.$.messager.progress("close");
	}else {
		parent.$.messager.alert("Tip","请首先选中一行信息!","info");
	}
}


function editSettleInfoAction(){
	//需要检验结算信息
	var settleValidateflag = validateForm2();
	if(!settleValidateflag){
		return;
	}
	
	var result = parent.$.modalDialog.handler.find('#settleFormInfo').form("validate");
	if(!result){
		parent.$.messager.alert("提示","商户结算信息录入有误","warning");
		return;
	}
	
	
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	
	var params = parent.$.modalDialog.handler.find('#settleFormInfo').serialize();
	
	
	$.ajax({
		type:"POST",
		url:"updateSettleInfo.do",
		data:params,
		success:function(result){
			var data = eval("("+result+")");
			if(!data.success){
				parent.$.messager.progress("close");
				parent.$.messager.alert("温馨提示",data.msg,"warning");
			}else{
				parent.$.messager.progress("close");
				parent.$.modalDialog.handler.dialog("close");
			}
		}
	
	});
	
}

//编辑签约信息
function editMallInfo(){
	//确认是否有行被选中,如果有就得到得到商户的ID 无就提示先选中一行
	var row = $("#comdg").datagrid("getSelected");
	if(row != null){
		 parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
		});
		//获取商户的cstNo
		var cstNo = row.cstNo;
		
		if($.updateMallInfo){
			parent.$.modalDialog({
				title : '编辑商户签约信息',
				width : 820,
				height : 400,
				href : 'secondMerch/toMallInfoEdit.do?cstNo=' + cstNo + "&parentCstNo=" + row.parentCstNo,
				buttons : [ {
					text : '保存',
					handler : editMallInfoAction
				}]
			});
		}else{
			parent.$.modalDialog({
				title : '编辑商户签约信息',
				width : 820,
				height : 400,
				href : 'secondMerch/toMallInfoEdit.do?cstNo=' + cstNo,
			});
		}
		
		
		parent.$.messager.progress("close");
	}else {
		parent.$.messager.alert("Tip","请首先选中一行信息!","info");
	}
}

function editMallInfoAction(){
	
	var flag3 = validateForm3();
	if(!flag3){
		return;
	}
	
	var result = parent.$.modalDialog.handler.find('#schemeFormInfo').form("validate");
	if(!result){
		parent.$.messager.alert("提示","商户签约信息录入有误","warning");
		return;
	}
	
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	
	var params3 = parent.$.modalDialog.handler.find('#schemeFormInfo').serialize();
	var operatTypevalue = getcheck("input[class='operatTypecheck']");
	var mallSignProduct = getMallSignProductcheck("input[class='mallSignProduct']");
	var params = params3 + "&operatType=" + operatTypevalue+ "&mallSignProduct=" + mallSignProduct;
	
	
	$.ajax({
		type:"POST",
		url:"updateMallInfo.do",
		data:params,
		success:function(result){
			var data = eval("("+result+")");
			if(!data.success){
				parent.$.messager.progress("close");
				parent.$.messager.alert("温馨提示",data.msg,"warning");
			}else{
				parent.$.messager.progress("close");
				parent.$.modalDialog.handler.dialog("close");
			}
		},
		error:function(){
			parent.$.messager.progress("close");
			parent.$.messager.alert("温馨提示","系统异常,请联系管理员","warning");
		}
	
	});
	
}





function editFeeComSchema(){
	//确认是否有行被选中,如果有就得到得到商户的ID 无就提示先选中一行
	var row = $("#comdg").datagrid("getSelected");
	if(row != null){
		 parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
		});
		//获取商户的cstNo
		var cstNo = row.cstNo;
		
		if($.updateFeeComSchema){
			parent.$.modalDialog({
				title : '编辑商户收费方案',
				width : 700,
				height : 400,
				href : 'secondMerch/toFeeComSchemaEdit.do?cstNo=' + cstNo,
				buttons : [ {
					text : '保存',
					handler : editFeeComSchemaAction
				}]
			});
		}else{
			parent.$.modalDialog({
				title : '编辑商户收费方案',
				width : 700,
				height : 400,
				href : 'secondMerch/toFeeComSchemaEdit.do?cstNo=' + cstNo,
			});
		}
		
		
		parent.$.messager.progress("close");
	}else {
		parent.$.messager.alert("Tip","请首先选中一行信息!","info");
	}
	
}


function editFeeComSchemaAction(){
	
	var params = parent.$.modalDialog.handler.find('#feeComSchemaForm').serialize();
	
	$.ajax({
		type:"POST",
		url:"updateFeeComSchema.do",
		data:params,
		success:function(result){
			var data = eval("("+result+")");
			if(!data.success){
				parent.$.messager.progress("close");
				parent.$.messager.alert("温馨提示",data.msg,"warning");
			}else{
				parent.$.messager.progress("close");
				parent.$.modalDialog.handler.dialog("close");
			}
		},
		error:function(){
			parent.$.messager.progress("close");
			parent.$.messager.alert("温馨提示","系统异常,请联系管理员","warning");
		}
	
	});
	
	
}


//注销商户
function cancelMerch(cstNo){
	
	parent.$.messager.confirm("提示","确认注销编号是"+cstNo+"的商户", function(r){
		if(r){
			$.ajax({
				type:"POST",
				url:"cancelMerch.do",
				data:"cstNo="+cstNo,
				success:function(result){
					var data = eval("("+result+")");
					if(!data.success){
						parent.$.messager.alert("温馨提示",data.msg,"warning");
					}else{
						//then refresh the datagrid
						$("#comdg").datagrid("reload");
					}
				}
			});
		}else{
			return;
		}
	});
}

//激活商户
function activateMerch(cstNo){
	$.ajax({
		type:"POST",
		url:"activateMerch.do",
		data:"cstNo="+cstNo,
		success:function(result){
			var data = eval("("+result+")");
			if(!data.success){
				parent.$.messager.alert("温馨提示",data.msg,"warning");
			}else{
				//then refresh the datagrid
				$("#comdg").datagrid("reload");
			}
		}
	});
}

//冻结商户
function freezeMerch(cstNo){
	$.ajax({
		type:"POST",
		url:"freezeMerch.do",
		data:"cstNo="+cstNo,
		success:function(result){
			var data = eval("("+result+")");
			if(!data.success){
				parent.$.messager.alert("温馨提示",data.msg,"warning");
			}else{
				//then refresh the datagrid
				$("#comdg").datagrid("reload");
			}
		}
	});
}

//解冻商户
function thawMerch(cstNo){
	$.ajax({
		type:"POST",
		url:"thawMerch.do",
		data:"cstNo="+cstNo,
		success:function(result){
			var data = eval("("+result+")");
			if(!data.success){
				parent.$.messager.alert("温馨提示",data.msg,"warning");
			}else{
				//then refresh the datagrid
				$("#comdg").datagrid("reload");
			}
		}
	});
}

//商户审核
function reviewMerch(cstNo, parentCstNo, comType){
	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
	});
	parent.$.modalDialog.openner_dataGrid = dataGrid;
	//如果属于平台二级商户则跳转到平台二级商户审核界面
	if(parentCstNo != 'null' && parentCstNo != '' && comType == '2'){
		parent.$.modalDialog({
			title:'审核',
			width:600,
			height:350,
			href:'merch/agentSecMerch/toReviewIndex.do?cstNo=' + cstNo
		});
		parent.$.messager.progress("close");
		return;
	}
	
	//非平台二级商户
	if($.reviewPass && $.reviewDeny){
		parent.$.modalDialog({
			title : '编辑商户信息',
			width : 820,
			height : 560,
			href : 'merch/reviewMerch.do?cstNo=' + cstNo,
			buttons : [ {
				text : '审核通过',
				iconCls:'icon-ok',
				handler : function reviewPass(){
							$.ajax({
								type:"POST",
								url:"reviewPass.do",
								data:"cstNo="+cstNo,
								success:function(result){
									var data = eval("("+result+")");
									if(!data.success){
										parent.$.messager.alert("温馨提示",data.msg,"warning");
									}else{
										parent.$.modalDialog.handler.dialog("close");
										//then refresh the datagrid
										$("#comdg").datagrid("reload");
									}
								}
							});
				}
			},{
				text : '审核拒绝',
				iconCls:'icon-cancel',
				handler : function reviewDeny(){
							parent.$.messager.confirm("提醒","确认审核拒绝",function(r){
								if(r){
									$.ajax({
										type:"POST",
										url:"reviewDeny.do",
										data:"cstNo="+cstNo,
										success:function(result){
											var data = eval("("+result+")");
											if(!data.success){
												parent.$.messager.alert("温馨提示",data.msg,"warning");
											}else{
												parent.$.modalDialog.handler.dialog("close");
												//then refresh the datagrid
												$("#comdg").datagrid("reload");
											}
										}
									});
								}
							});
				}
			}]
		});
	}else {
		parent.$.modalDialog({
			title : '编辑商户信息',
			width : 820,
			height : 560,
			href : 'merch/reviewMerch.do?cstNo=' + cstNo
		});
	}
	
	
	parent.$.messager.progress("close");
}


//商户复核
function reReviewMerch(cstNo, parentCstNo, comType){
	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
	});
	parent.$.modalDialog.openner_dataGrid = dataGrid;
	//如果属于平台二级商户则跳转到平台二级商户审核界面
	if(parentCstNo != 'null' && parentCstNo != '' && comType == '2'){
		parent.$.modalDialog({
			title:'审核',
			width:600,
			height:350,
			href:'merch/agentSecMerch/toReReviewIndex.do?cstNo=' + cstNo
		});
		parent.$.messager.progress("close");
		return;
	}
	
	//非平台二级商户
	if($.reviewPass && $.reviewDeny){
		parent.$.modalDialog({
			title : '编辑商户信息',
			width : 820,
			height : 560,
			href : 'merch/reReviewMerch.do?cstNo=' + cstNo,
			buttons : [ {
				text : '复核通过',
				iconCls:'icon-ok',
				handler : function reviewPass(){
							$.ajax({
								type:"POST",
								url:"reReviewPass.do",
								data:"cstNo="+cstNo,
								success:function(result){
									var data = eval("("+result+")");
									if(!data.success){
										parent.$.messager.alert("温馨提示",data.msg,"warning");
									}else{
										parent.$.modalDialog.handler.dialog("close");
										//then refresh the datagrid
										$("#comdg").datagrid("reload");
									}
								}
							});
				}
			},{
				text : '复核拒绝',
				iconCls:'icon-cancel',
				handler : function reviewDeny(){
							parent.$.messager.confirm("提醒","确认复核拒绝",function(r){
								if(r){
									$.ajax({
										type:"POST",
										url:"reReviewDeny.do",
										data:"cstNo="+cstNo,
										success:function(result){
											var data = eval("("+result+")");
											if(!data.success){
												parent.$.messager.alert("温馨提示",data.msg,"warning");
											}else{
												parent.$.modalDialog.handler.dialog("close");
												//then refresh the datagrid
												$("#comdg").datagrid("reload");
											}
										}
									});
								}
							});
				}
			}]
		});
	}else {
		parent.$.modalDialog({
			title : '编辑商户信息',
			width : 820,
			height : 560,
			href : 'merch/reReviewMerch.do?cstNo=' + cstNo
		});
	}
	
	
	parent.$.messager.progress("close");
}



function stylefunction(cdate){
	if(cdate == undefined || cdate == null){
		return true;
	}
	var tdate =  new Date();
	var nowDate = new Date();
	//得到不同月的秒数
	var month = cdate.substr(5,2);
	var reg = new RegExp(month);
	var monthseconds = 2678400000;	//默认是一个月31天
	if(month == "2"){
		monthseconds = 2419200000;
	}else if(month == "4" || month == "6" || month == "9" || month =="11"){
		monthseconds = 2592000000;
	}
	tdate.setFullYear(parseInt(cdate.substr(0,4)),parseInt(cdate.substr(5,2))-1,parseInt(cdate.substr(8,2)));
	if ((tdate.getTime()-monthseconds) <= nowDate.getTime()){
		return false;
	}else{
		return true;
	}
}
	


function testFlexLengthText(value,len){
	
	var c = '';
	var sn = 0;//数字、字母个数 ()
	var cn = 0;//汉字个数（）-
	
	if(!/^[(\u4E00-\u9FA5)|(|)|（|）|\-|(0-9)|(a-zA-Z)]+$/g.test(value)){
		return false;
	}
	
	for(var i=0;i<value.length;i++){ 
		c = value.charAt(i);
		if($.trim(c).length == 0){
			continue;
		}
		if(/^[\u4E00-\u9FA5|（|）]+$/g.test(c)){
			cn +=2;
		}else{
			if(/^[(0-9)|(a-zA-Z)|(|)|\-]+$/g.test(c)){
				sn +=1;
			}else{
				return false;
			}
		}
	}
	var total = sn+cn;
	if(total <= len ){
		return true;
	}
	return false;
}


function testFlexLengthTextSpical(value,len){
	
	var c = '';
	var sn = 0;//数字、字母个数
	var cn = 0;//汉字个数
	var sp = 0;//括号
	
	if(!/^[(\u4E00-\u9FA5)|(0-9)|(a-zA-Z)|()（）]+$/g.test(value)){
		return false;
	}
	
	for(var i=0;i<value.length;i++){ 
		c = value.charAt(i);
		if($.trim(c).length == 0){
			continue;
		}
		if(/^[\u4E00-\u9FA5]+$/g.test(c)){
			cn +=2;
		}else{
			if(/^[(0-9)|(a-zA-Z)]+$/g.test(c)){
				sn +=1;
			}else if(/^[()（）]+$/g.test(c)){
				sp += 1;
			}else{
				return false;
			}
		}
	}
	var total = sn+cn + sp;
	if(total <= len ){
		return true;
	}
	return false;
}

//商户规则参数
function riskReg(cstNo, cstName){
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	window.parent.$('#index_tabs').tabs('add',{    
	    title:cstName + '&nbsp;规则参数',    
	    content:'<iframe src="risk/index.do?userId='+cstNo+'" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>',    
	    closable:true, 
		border : false,
		fit : true,
	});  

	
	parent.$.messager.progress("close");
}



//商户操作员管理
function operatorManage(cstNo, loginName, cstName){
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	window.parent.$('#index_tabs').tabs('add',{    
	    title:cstName + '&nbsp;管理员',    
	    content:'<iframe src="merch/operator/toIndex.do?cstNo='+cstNo+'&loginName='+loginName+'" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>',    
	    closable:true, 
		border : false,
		fit : true,
	});  

	
	parent.$.messager.progress("close");
}


//获取签约产品的组合  这里模拟使用二进制字符串的或操作 1|0= 1 0|0= 0
function getMallSignProductcheck(value){
	var signProduct = "00000000000000000000";
	var signProducts  = parent.$.modalDialog.handler.find(value);
	for(var i = 0; i<signProducts.length; ++i){
		if(isChecked(signProducts[i])){
			signProduct = binaryOr(signProduct,signProducts[i].value);
		}
	}
	return signProduct;
}

//这里模拟使用二进制字符串的或操作 1|0= 1 0|0= 0  1|1=1  0|1=1
function binaryOr(str1, str2){
	var result = "";
	
	if($.trim(str2) == ''){
		return str1;
	}
	
	for(var i=0; i<str2.length; ++i){
		if((str1.charAt(i) == '1') && (str2.charAt(i) == '1')){
			result += "1";
		}else if((str1.charAt(i) == '0') && (str2.charAt(i) == '1')){
			result += "1";
		} else if((str1.charAt(i) == '1') && (str2.charAt(i) == '0')){
			result += "1";
		}else{
			result += "0";
		}
	}
	
	return result;
}

//根据商户号查询商户账户信息
function merchAccInfo(cstNo){
	
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	parent.$.modalDialog({
		title : '账户信息',
		width : 600,
		height : 100,
		href : 'merch/selectMerchAccInfo.do?cstNo=' + cstNo,
	});
	
	parent.$.messager.progress("close");
}


