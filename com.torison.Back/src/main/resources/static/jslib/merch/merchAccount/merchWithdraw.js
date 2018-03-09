$(document).ready(function(){
	var cstNo = $("#cstNo").val();
	
	$("#reset").click(function(){
		$("#searchForm").form("clear");
	});
	
	$("#selectbtn").click(function(){
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var status = $("#status").combobox("getValue");
		
		$("#withdrawDg").datagrid("load",{
			orderStartDate : startDate,
			orderEndDate : endDate,
			status : status
		});
	});
	
	
	var columns=[[{
		field:'oboid',title:'提现/充值单号',width:120,align:'center'
	},{
		field:'oboamount',title:'提现金额',width:120,align:'center'
	},{
		field:'bankcode',title:'银行卡号',width:150,align:'center'
	},{
		field:'obostatus',title:'状态',width:100,align:'center',
		formatter:function(value,row,index){
			if(value == "00"){
				return "成功";
			}else if(value == "01"){
				return "待处理";
			}else if(value == "03"){
				return "失败";
			}else{
				return "";
			}
		}
	},{
		field:'obotype',title:'类型',width:100,align:'center',
		formatter:function(value,row,index){
			if(value == "00"){
				return "提现 ";
			}else if(value == "01"){
				return " 充值";
			}else if(value == "02"){
				return "退款";
			}else if(value == "03"){
				return "代扣";
			}else{
				return "";
			}
		}
	},{
		field:'payno',title:'支付流水号',width:150,align:'center'
	},{
		field:'feeamount',title:'手续费',width:100,align:'center'
	},{
		field:'createtime',title:'创建时间',width:160,align:'center',
		formatter:function(value, row, index){
			if(value != undefined && value != '' && value != '0'){
				return value.substring(0,4)+"-"+value.substring(4,6)+"-"+value.substring(6,8)+"&nbsp;&nbsp;"+value.substring(8,10)+":"+value.substring(10,12)+":"+value.substring(12,14);
			}else{
				return "";
			}
		}
	},{
		field:'successtime',title:'成功时间',width:160,align:'center',
		formatter:function(value, row, index){
			if(value != undefined && value != '' && value != '0'){
				return value.substring(0,4)+"-"+value.substring(4,6)+"-"+value.substring(6,8)+"&nbsp;&nbsp;"+value.substring(8,10)+":"+value.substring(10,12)+":"+value.substring(12,14);
			}else{
				return "";
			}
		}
	}]];
	
	
	$("#withdrawDg").datagrid({
		url:'viewWithdraw.do?cstNo=' + cstNo,
		columns:columns,
		pagination:true,
		striped:true,
		fit:true,
		rownumbers:true,
		singleSelect:true,
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
		}

	});
	
	
});