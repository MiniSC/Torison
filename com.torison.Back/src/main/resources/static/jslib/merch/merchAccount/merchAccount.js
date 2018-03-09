$(document).ready(function(){
	
	//查询表单重置按钮
	$("#reset").click(function(){
		$("#queryForm").form("clear");
	});
	
	
	//查询表单查询按钮
	$("#selectbtn").click(function(){
		var cstName = $("#cstName").val();
		var cstNo = $("#cstNo").val();
		var businessScope = $("#businessScope").combobox("getValue");
		
		$("#merchAccountDg").datagrid("load",{
			cstName:cstName,
			cstNo:cstNo,
			businessScope:businessScope
		});
		parent.$.messager.progress("close");
	});
	
	$(document).keydown(function(event){
		
		if(event.keyCode == 13){
			$("#selectbtn").click();
		}
		
	});
	
	
	//merchAccount 的datagrid  
	var columns=[[{
					field:'cstNo',title:'商户编号',width:150,align:'center'
				},{
					field:'cstName',title:'商户名称',width:150,align:'center'
				},{
					field:'businessScope',title:'商户行业',width:150,align:'center'
				},{
					field:'itemCategory',title:'商品类目',width:150,align:'center'
				},{
					field:'balance',title:'日常余额',width:150,align:'center'
				},{
					field:'do',title:'操作',width:215,align:'center',
					formatter:function(value,row,index){ 
						return '<a onclick=withdrawView("'+row.cstNo+'","'+row.cstName+'")>提现记录</a>';
					}
				}]];
	
	$("#merchAccountDg").datagrid({
		url:'selectMerchAccount.do',
		columns:columns,
		pagination:true,
		striped:true,
		fit:true,
		rownumbers:true,
		singleSelect:true,
		autoRowHeight:false,
		nowrap:true,
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


//提现记录展示页面
function withdrawView(cstNo, cstName){
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	parent.addTab({
		id : 'p_' + cstNo,
		url : 'merchAccount/toViewWithdraw.do?cstNo='+cstNo,
		title:cstName + '提现记录',
		iconCls : 'icon_script_edit'
	});
	
	parent.$.messager.progress("close");
}