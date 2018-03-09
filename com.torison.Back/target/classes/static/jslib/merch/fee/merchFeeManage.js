/*
查询表单功能的实现  queryForm
*/
$(document).ready(function(){
		//为重置按钮添加功能
		$("#reset").click(function(){
			$("#queryForm").form("clear");
		});
		//查询按钮功能的实现
		$("#select").click(function(){
			var data  = $("#queryForm").serializeArray();
			//发起请求获得数据
			$("#comfeedg").datagrid("load",{
				   'feeQuery.freeName':data[0].value,
				   'feeQuery.feeType':data[1].value,
				   'feeQuery.feeBelong':data[2].value,
				   'feeQuery.startTime':data[3].value,
				   'feeQuery.startTimeEnd':data[4].value,
				   'feeQuery.endTime':data[5].value,
				   'feeQuery.endTimeEnd':data[6].value,
			});
			
		});
		
		
		$(document).keydown(function(event){
			
			if(event.keyCode == 13){
				$("#select").click();
			}
			
		});
		
		
		
});
	

	/*
		加载商户 收费方案的信息表格
	*/
var dataGrid;
	$(document).ready(function(){
		
		var columns = [[
		       {
		    	  field:'id',title:'方案ID',checkbox:'true'    	
		       },{
		    	   field:'freeCode',title:'方案编号',width:'120'
		       },{
		    	   field:'freeName',title:'方案名称',width:'120'
		       },{
		    	   field:'status',title:'状态',width:'80',
		    	   formatter:function(value,row,index){
		    		   if(value == "00"){
		    			   return "有效";
		    		   }else if(value == "01"){
		    			   return "无效";
		    		   }else {
		    			   return "";
		    		   }
		    	   }
		       },{
		    	   field:'createTime',title:'创建时间',width:'120',
		    	   formatter:function(value,row,index){
		    		   if(value == undefined || value == null){
		    			   return "";
		    		   }else{
			    		   var year = value.substr(0,4);
							var month = value.substr(4,2);
							var day = value.substr(6,2);
							return year + "-" + month + "-" + day;
		    		   }
		    	   }
		       },{
		    	   field:'feeType',title:'计费类型',width:'120',
		    	   formatter:function(value,row,index){
		    		   if(value == "01" || value == "1"){
		    			   return "按金额区间";
		    		   }else if(value == "02" || value == "2"){
		    			   return "按固定金额";
		    		   }else if(value == "03" || value == "3"){
		    			   return "按金额比率";
		    		   }else {
		    			   return "";
		    		   }
		    	   }
		       },{
		    	   field:'feeBelong',title:'计费项目',width:'100',
		    	   formatter:function(value,row,index){
		    		   if(value == "JS"){
		    			   return "T1结算";
		    		   }else if(value == "ZF"){
		    			   return "支付";
		    		   }else if(value == "TK"){
		    			   return "退款";
		    		   }else if(value == "QF"){
		    			   return "清分";
		    		   }else if(value == "TX"){
		    			   return "提现";
		    		   }else if(value == "FR"){
		    			   return "分润手续费";
		    		   }else if(value == "T0"){
		    			   return "T0结算";
		    		   }else if(value == "D0"){
		    			   return "D0结算";
		    		   }else if(value == "D1"){
		    			   return "D1结算";
		    		   }else{
		    			   return "";
		    		   }
		    	   }
		       },{
		    	   field:'startTime',title:'生效时间',width:'120',
		    	   formatter:function(value,row,index){
		    		   if(value == undefined || value == null){
		    			   return "";
		    		   }else{
			    		   var year = value.substr(0,4);
							var month = value.substr(4,2);
							var day = value.substr(6,2);
							return year + "-" + month + "-" + day;
		    		   }
		    	   }
		       },{
		    	   field:'endTime',title:'失效时间',width:'120',
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
		    	   field:'payType',title:'支付方式',width:'80',
		    	   formatter:function(value,row,index){
		    		   if(value == "ALL"){
		    			   return "全部";
		    		   }else if(value == "01"){
		    			   return "企业网银";
		    		   }else if(value == "02"){
		    			   return "个人网银";
		    		   }else if(value == "03"){
		    			   return "快捷"; 
		    		   }else if(value == "04"){
		    			   return "预付卡"; 
		    		   }else if(value == "05"){
		    			   return "银企直联"; 
		    		   }else if(value == "06"){
		    			   return "代扣"; 
		    		   }else if(value == "07"){
		    			   return "代付"; 
		    		   }else if(value == "08"){
		    			   return "认证支付"; 
		    		   }else if(value == "09"){
		    			   return "wxsm"; 
		    		   }else if(value == "10"){
		    			   return "zfbsm"; 
		    		   }else if(value == "11"){
		    			   return "wxgz"; 
		    		   }else if(value == "12"){
		    			   return "zfbgzh"; 
		    		   }else if(value == "13"){
		    			   return "wxtm/wxsb"; 
		    		   }else if(value == "14"){
		    			   return "zfbtm/zfbsb"; 
		    		   }else if(value == "15"){
		    			   return "wxh5"; 
		    		   }else if(value == "16"){
		    			   return "zfbh5"; 
		    		   }else if(value == "17"){
		    			   return "wxsdk"; 
		    		   }else if(value == "18"){
		    			   return "zfbsdk"; 
		    		   }else if(value == "19"){
		    			   return "H5支付"; 
		    		   }else if(value == "20"){
		    			   return "扫码"; 
		    		   }else if(value == "21"){
		    			   return "条码"; 
		    		   }else if(value == "22"){
		    			   return "QQsm"; 
		    		   }else if(value == "23"){
		    			   return "QQbs"; 
		    		   }else {
		    			   return value;
		    		   }
		    	   }
		       },{
		    	   field:'payCard',title:'卡类型',width:'80',
		    	   formatter:function(value,row,index){
		    		   if(value == "ALL" || value == "all"){
		    			   return "全部";
		    		   }else if(value == "1"){
		    			   return "信用卡";
		    		   }else if(value == "0"){
		    			   return "储蓄卡"; 
		    		   }else if(value == "3"){
		    			   return "混合通道"; 
		    		   }else if(value == "2"){
		    			   return "预付卡"; 
		    		   }else{
		    			   return "";
		    		   }
		    	   }
		       },{
		    	   field:'payChannel',title:'支付渠道',width:'80',
		    	   formatter:function(value,row,index){
		    		   if(value == "all" || value == "ALL"){
		    			   return "全部";
		    		   }else if(value == "web"){
		    			   return "PC";
		    		   }else if(value == "mobile"){
		    			   return "手机"; 
		    		   }else if(value == "wap"){
		    			   return "WAP";
		    		   }else{
		    			   return "";
		    		   }
		    	   }
		       }]];
		
		dataGrid = $("#comfeedg").datagrid({
			url:'selectMerchFeeWithCondition.do',
			columns:columns,
			pagination:true,
			striped:true,
			rownumbers:true,
			fit:true,
			autoRowHeight:false,
			nowrap:true,
			onDblClickRow:dbClick,
			toolbar:'#tb',
			onBeforeLoad:function(){
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
			},
			onLoadSuccess:function(){
				parent.$.messager.progress("close");
				$("#comfeedg").datagrid('tooltip');
			}
		});
		
		
	});
	

	
	
	
	/*
		datagrid toolbar功能区中的按钮功能
		
	*/
	
	//增加一个计费方案
	function append(){
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		
		$.modalDialog.openner_dataGrid = dataGrid;
		if("${shiro:hasPermission('/merch/fee/addFeeCom.do')}" == "true"){
			$.modalDialog({
				title : '添加收费方案',
				width : 980,
				height : 400,
				href : 'merch/fee/toAddFeeCom.do',
				buttons : [ {
					text : '保存',
					handler : saveFeeCom
				}]
			});
		}else{
			$.modalDialog({
				title : '添加收费方案',
				width : 980,
				height : 400,
				href : 'merch/fee/toAddFeeCom.do',
			});
		}
		
		parent.$.messager.progress("close");
	}
	
	//添加收费方案  提交表单
	function saveFeeCom(){
		parent.$.modalDialog.openner_dataGrid = dataGrid;
		//提交表单之前需要校验收费方案条目中的数据是否符合条件  如果不符合就提醒一下
		var flag = validateFeecom();
		if(!flag){
			return false;
		}
		var flagItem = validateFeeItem();
		
		if(!flagItem){
			return false;
		}
		
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		var feeComdata =  parent.$.modalDialog.handler.find('#feeSchForm').serialize();
		var feeComItemdata =  parent.$.modalDialog.handler.find('#feeItemForm').serialize();
		
		if(flag && flagItem){
			
			$.ajax({
				url:"addFeeCom.do",
				type:"POST",
				data:feeComdata+"&"+feeComItemdata,
				success:function(result){
					result = eval("("+result+")");
					if(!result.success){
						parent.$.messager.progress("close");
						parent.$.messager.alert("温馨提示",result.msg,"warning");
					}else{
						parent.$.messager.progress("close");
						parent.$.modalDialog.handler.dialog("close");
						//then refresh the datagrid
						parent.$.modalDialog.openner_dataGrid.datagrid("reload");
					}
				}
			});
			
		}
		
	}
	
	
	function validateFeecom(){
		
		//方案名称
		var freeName = parent.$.modalDialog.handler.find('#freeName');
		if(isBlank(freeName[0].value)){
			parent.$.messager.alert("提示","方案名称不能够为空","warning");
			return false;
		}
		
		var startTime = parent.$.modalDialog.handler.find('#startTime');
		if(isBlank(startTime[0].value)){
			parent.$.messager.alert("提示","方案生效时间不能够为空","warning");
			return false;
		}
		var endTime = parent.$.modalDialog.handler.find('#endTime');
		if(isBlank(endTime[0].value)){
			parent.$.messager.alert("提示","方案失效时间不能够为空","warning");
			return false;
		}
		if(endTime[0].value.localeCompare(startTime[0].value) < 0){
			parent.$.messager.alert("提示","方案失效时间必须大于生效时间","warning");
			return false;
		}
		
		return true;
	}
	
	//校验收费条目表单
	function validateFeeItem(){
		var feeItemForm = parent.$.modalDialog.handler.find('#feeItemForm').serializeArray();
		var len = feeItemForm.length/8;
		for(var i=0; i<len; ++i){
			var index = i*8;
			if(isBlank(feeItemForm[index].value) || isBlank(feeItemForm[index + 1].value)){
				parent.$.messager.alert("提示","金额区间不能够为空","warning");
				return false;
			}
			
			if(!positiveNumber(feeItemForm[index].value) || !positiveNumber(feeItemForm[index+1].value)){
				parent.$.messager.alert("提示","金额区间只能是非负数","warning");
				return false;
			}
			
			if(Number(feeItemForm[index+1].value) <= Number(feeItemForm[index].value)){
				parent.$.messager.alert("提示","金额区间截止数必须大于起始数","warning");
				return false;
			}
			
			//校验费率
			if(isBlank(feeItemForm[index+3].value)){
				parent.$.messager.alert("提示","费率不能够为空","warning");
				return false;
			}
			if(!positiveNumber(feeItemForm[index+3].value)){
				parent.$.messager.alert("提示","费率只能是非负数","warning");
				return false;
			}
			
			if(!/^\d{1,4}(\.\d{1,2})?$/g.test(feeItemForm[index+4].value)){
				parent.$.messager.alert("提示","最低手续费限制在0.00-9999.99","warning");
				return false;
			}
			
			if(! isBlank(feeItemForm[index+5].value)){
				
				if(!/^\d{1,3}(\.\d{1,3})?$/g.test(feeItemForm[index+5].value)){
					parent.$.messager.alert("提示","代理返佣比例限制在0.000-999.999","warning");
					return false;
				}
				
				//并且代理返佣比例小于等于 商户的费率
				if(parseFloat(feeItemForm[index+5].value) > parseFloat(feeItemForm[index+3].value)){
					parent.$.messager.alert("提示","代理返佣比例 应当小于等于商户费率","warning");
					return false;
				}
			}
			
			//生效时间 结束时间
			if(isBlank(feeItemForm[index+6].value) || isBlank(feeItemForm[index+7].value)){
				parent.$.messager.alert("提示","生效时间和结束时间不能够为空","warning");
				return false;
			}
			if(feeItemForm[index+7].value.localeCompare(feeItemForm[index+6].value) < 0 ){
				parent.$.messager.alert("提示","结束时间必须大于生效时间","warning");
				return false;
			}
		}
		
		return true;
	}
	
	
	
	//编辑按钮功能的实现   先选中一行,然后根据id查询数据回显
	function editFeeCom(){
		//先找到选中行
		var rows =  $("#comfeedg").datagrid("getSelections");
		if(rows.length != 1){
			alert("修改是只能选中一行!");
			return;
		}
		var row = $("#comfeedg").datagrid("getSelected");
		if(row == null){
			alert("请先选中一行!");
			return;
		}
		
		var code = row.freeCode;
		
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		
		if("${shiro:hasPermission('/merch/fee/updateFeeCom.do')}" == "true"){
			parent.$.modalDialog({
				title : '编辑收费方案',
				width : 980,
				height : 400,
				href : 'merch/fee/toEditFeeCom.do?code=' + code,
				buttons : [ {
					text : '保存',
					handler : saveEditFeeCom
				}]
			});
		}else{
			parent.$.modalDialog({
				title : '编辑收费方案',
				width : 980,
				height : 400,
				href : 'merch/fee/toEditFeeCom.do?code=' + code,
			});
		}
		
		
		parent.$.messager.progress("close");
		
		
	}
	
	function saveEditFeeCom(){
		parent.$.modalDialog.openner_dataGrid = dataGrid;
		//提交表单之前需要校验收费方案条目中的数据是否符合条件  如果不符合就提醒一下
		var flag = validateFeecom();
		if(!flag){
			return false;
		}
		var flagItem = validateFeeItem();
		
		if(!flagItem){
			return false;
		}
		
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		var feeComdata =  parent.$.modalDialog.handler.find('#feeSchForm').serialize();
		var feeComItemdata =  parent.$.modalDialog.handler.find('#feeItemForm').serialize();
		
		if(flag && flagItem){
			
			$.ajax({
				url:"updateFeeCom.do",
				type:"POST",
				data:feeComdata+"&"+feeComItemdata,
				success:function(result){
					result = eval("("+result+")");
					if(!result.success){
						parent.$.messager.progress("close");
						parent.$.messager.alert("温馨提示",result.msg,"warning");
					}else{
						parent.$.messager.progress("close");
						parent.$.modalDialog.handler.dialog("close");
						//then refresh the datagrid
						parent.$.modalDialog.openner_dataGrid.datagrid("reload");
					}
				}
			});
			
		}
	}
	
	//删除按钮功能    选中一行后点击删除按钮删除这条方案  然后刷新datagrid
	function deleteFeeCom(){
		
		var rows =  $("#comfeedg").datagrid("getSelections");
		
		if(rows.length < 1){
			alert("至少选择一行数据");
			return;
		}
		
		
		$.messager.confirm('确认对话框', '您确认要删除么?', function(flag){
			if (flag){
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				//得到要删除的方案的id 
				var params = "";
				for(var i=0; i<rows.length; ++i){
					
					if(i == 0){
						params = "id=" + rows[i].id;
					}else {
						params = params + "&id=" + rows[i].id;
					}
					
				}
				//发送ajax请求删除收费方案
				$.ajax({
				   type: "POST",
				   url: "delFeeCom.do",
				   data: params,
				   dataType:'json',
				   success: function(data){
					 //如果返回失败信息
				       if(!data.success){
				    	   parent.$.messager.progress('close');
				    	   parent.$.messager.alert("提示",data.msg,"warning");
				       }else{
				    	   parent.$.messager.progress('close');
				    	   //刷新datagrid数据
				    	   $("#comfeedg").datagrid('load');
				       } 
				   }
					
				});
			}
		});

		
	}
	
	//datagrid 一行记录的双击事件
	function dbClick(index,field,value){
		var row = $("#comfeedg").datagrid("getSelected");
		if(row == null){
			return;
		}
		var freeCode = row.freeCode;
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		parent.$.modalDialog({
			title : '查看收费方案',
			width : 980,
			height : 400,
			href : 'merch/fee/selectFeeComWithItemByCode.do?code=' + freeCode,
		});
		
		parent.$.messager.progress("close");
		
		
	}

	
	
	//处理日期函数  处理一下日期格式  20160708000000 变成  2016-07-08
	function changeDate(arg){
		
		var year = arg.substr(0,4);
		var month = arg.substr(4,2);
		var day = arg.substr(6,2);
		return year+"-"+month+"-"+day;
	}
	

	//为空校验
	function isBlank(value){
		if(value == null || value == undefined || value == ''|| (value.replace(/\s*/g,"").length == 0)){
			return true;
		}
		return false;
	}	
	
	//非负浮点数
	function positiveNumber(value){
		return /^\d+(\.{0,1}\d+){0,1}$/.test(value);
	}
