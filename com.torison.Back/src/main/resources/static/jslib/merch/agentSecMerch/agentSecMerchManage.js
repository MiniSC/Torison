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
				   if("${shiro:hasPermission('/secondMerch/cancelMerch.do')}" == "true"){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>&nbsp;&nbsp;';
				   }
				   if("${shiro:hasPermission('/secondMerch/freezeMerch.do')}" == "true"){
					   options += '<a  onclick="freezeMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">冻结</a>';
				   }
				   if("${shiro:hasPermission('/risk/index.do')}" == "true"){
					   options += '&nbsp;&nbsp;<a  onclick="riskReg('+"'"+row.cstNo+"','" + row.cstName+"'"+')" href="javascript:void(0);">规则参数</a>';
				   }
				   if("${shiro:hasPermission('/merch/operator/toIndex.do')}" == "true"){
						options += '&nbsp;&nbsp;<a  onclick="operatorManage('+"'"+ row.cstNo+"','" + row.loginName+"','" + row.cstName + "'"+')" href="javascript:void(0);">操作员管理</a>';
					}
				   if("${shiro:hasPermission('/merch/selectMerchAccInfo.do')}" == "true"){
					   options += '&nbsp;&nbsp;<a  onclick="merchAccInfo('+"'"+ row.cstNo+"'" + ')" href="javascript:void(0);">账户信息</a>';
				   }
		       }else if(status=='1'){//1：未激活；
		    	   if("${shiro:hasPermission('/secondMerch/cancelMerch.do')}" == "true"){
		    		   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>&nbsp;&nbsp;';
				   }
		    	   if("${shiro:hasPermission('/secondMerch/activateMerch.do')}" == "true"){
		    		   options += '<a  onclick="activateMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">激活</a>';
		    	   }
			   }else if(status=='2'){//2：冻结
				   if("${shiro:hasPermission('/secondMerch/cancelMerch.do')}" == "true"){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>&nbsp;&nbsp;';
				   }
				   if("${shiro:hasPermission('/secondMerch/thawMerch.do')}" == "true"){
					   options += '<a  onclick="thawMerch('+"'"+row.cstNo+"'"+')"  href="javascript:void(0);">解冻</a>'; 
				   }
			   }else if(status=='3'){//3：注销
				// options += '<a  onclick="" href="javascript:void(0);">激活</a>';
			   }else if(status=='4'){//4:待审核
				   if("${shiro:hasPermission('/merch/agentSecMerch/toReviewIndex.do')}" == "true"){
					   options += '<a  onclick="reviewMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">审核</a>&nbsp;&nbsp;';
				   }
				   if("${shiro:hasPermission('/secondMerch/cancelMerch.do')}" == "true"){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>';
				   }
			   }else if(status=='5'){//5：暂时冻结
				   if("${shiro:hasPermission('/secondMerch/cancelMerch.do')}" == "true"){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>&nbsp;&nbsp;';
				   }
				   if("${shiro:hasPermission('/secondMerch/thawMerch.do')}" == "true"){
					   options += '<a  onclick="thawMerch('+"'"+row.cstNo+"'"+')"  href="javascript:void(0);">解冻</a>';
				   }
			   }else if(status=='6'){//6：审核不通过；
				   if("${shiro:hasPermission('/secondMerch/cancelMerch.do')}" == "true"){
					   options += '<a  onclick="cancelMerch('+"'"+row.cstNo+"'"+')" href="javascript:void(0);">注销</a>';
				   }
			   }
			
			return options;
		}
	}]];
	
	
	

	//数据表格datagrid
	dataGrid = $("#comdg").datagrid({
		url:'listPage.do?parentCstNo=' + pCstNo,
		columns:columns,
		pagination:true,
		striped:true,
		rownumbers:true,
		singleSelect:true,
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
			width : 800,
			height : 400,
			href : 'merch/agentSecMerch/viewAgentMerch.do?cstNo=' + cstNo,
		});
		
		parent.$.messager.progress("close");
	}
}








//注销商户
function cancelMerch(cstNo){
	
	parent.$.messager.confirm("提示","确认注销编号是"+cstNo+"的商户", function(r){
		if(r){
			$.ajax({
				type:"POST",
				url:baseURL+"/secondMerch/cancelMerch.do",
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
		url:baseURL + "/secondMerch/activateMerch.do",
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
		url:baseURL + "/secondMerch/freezeMerch.do",
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
		url:baseURL + "/secondMerch/thawMerch.do",
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
function reviewMerch(cstNo){
	parent.$.messager.progress({
		title:'提示',
		text:'请求处理中,请稍后......'
	});
	
	parent.$.modalDialog.openner_dataGrid = dataGrid;
	
	parent.$.modalDialog({
		title:'审核',
		width:800,
		height:400,
		href:'merch/agentSecMerch/toReviewIndex.do?cstNo=' + cstNo
	});
	
	
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

//编辑商户的签约产品
function editMallSignProductEdit(parentCstNo){
	
	var rows = $("#comdg").datagrid("getSelections");
	if(rows.length != 1){
		parent.$.messager.alert("Info","请先选择一个商户","warning");
		return false;
	}
	var row = rows[0];
	var cstNo = row.cstNo;
	parent.$.messager.progress({
		title:'提示',
		text:'请求处理中,请稍后......'
	});
	parent.$.modalDialog.openner_dataGrid = dataGrid;
	parent.$.modalDialog({
		title:'编辑签约产品',
		width:600,
		height:300,
		href:'merch/agentSecMerch/toMallSignProductEdit.do?cstNo=' + cstNo + "&parentCstNo=" + parentCstNo,
		buttons : [ {
			text : '保存',
			iconCls:'icon-ok',
			handler : saveMallSignProductEdit
		}]
	});
	parent.$.messager.progress("close");
	
}

//保存签约信息
function saveMallSignProductEdit(){
	
	//校验必填字段,以及二级代理商信息是否大于等于代理商收费费率和最低手续费
	
	parent.$.messager.progress({
		title:'提示',
		text:'请求处理中,请稍后......'
	});
	
	var cstNo = $("#comdg").datagrid("getSelections")[0].cstNo;
	var mallNo = parent.$.modalDialog.handler.find('input[name="mallNo"]').val();
	var signProduct = getMallSignProductcheck("input[class='mallSignProduct']");
	var params = parent.$.modalDialog.handler.find('#mallForm').serialize();
	
	
	$.ajax({
		url:'editMallSignProduct.do',
		data:params+'&signProduct='+signProduct + "&cstNo=" + cstNo + "&mallNo=" + mallNo,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success){
				parent.$.modalDialog.handler.dialog("close");
			}else{
				parent.$.messager.progress("close");
				parent.$.messager.alert("Info",result.msg,"warning");
			}
		}
	});
	
	
	parent.$.messager.progress("close");
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

//校验checkbox 是否选中
function isChecked(value){
	if(value.checked == false){
		return false;
	}
	return true;
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

