/*
	商户信息管理界面的javascript
*/
var dataGrid;
$(document).ready(function(){
	
	$("#reset").click(function(){
		$("#countForm").form('reset');
	});
	
	//条件查询
	$("#select").click(function(){
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		
		var name = $("input[name='name']").val();
		var createTimeStart = $("#createTimeStart").val();
		var createTimeEnd = $("#createTimeEnd").val();
		var data = $("input[name='data']").val();
		var certificateType = $("select[name='certificateType'] option:selected").val();
		
		//证件号码输入时  证件类型为必选项
		if($.trim(data) != '' && certificateType == ''){
			parent.$.messager.alert("Tip","请选择证件类型","warning");
			parent.$.messager.progress('close');
			return;
		}
		
		$("#comdg").datagrid("load", {
			name:name,
			createTimeStart:createTimeStart,
			createTimeEnd:createTimeEnd,
			data:data,
			certificateType:certificateType
		});
		parent.$.messager.progress('close');
	});
	
	$(document).keydown(function(event){
		
		if(event.keyCode == 13){
			$("#select").click();
		}
		
	});
	
	
	//刷新黑名单缓存功能
	$("#refresh").click(function(){
		$.ajax({
			url:'refreshBlackMerchRedis.do',
			dataType:'json',
			type:'GET',
			success:function(result){
				if(result.success){
					parent.$.messager.alert("Tip",result.msg,"info");
				}else{
					parent.$.messager.alert("Tip","刷新缓存失败","warning");
				}
			}
		});
		
	});
	
	
	//表格中的列定义
	var columns = [
	       [{field:'name',title:'名称',width:150,align:'center'
		},{
			field:'data',title:'证件号',width:150,align:'center',
			formatter:function(value,row,index){
				if(value != null && value != ''){
					return value.split("_")[1];
				}else{
					return value;
				}
			}
		},{
			field:'source',title:'信息来源',width:120,align:'center',
			formatter:function(value,row,index){
				if(value == '1'){
					return "内部录入";
				}else{
					return "外部收集";
				}
			}
		},{
			field:'remark',title:'违法信息',width:150,align:'center'
		},{
			field:'status',title:'是否有效',width:150,align:'center',
			formatter:function(value,row,index){
				if(value == '0'){
					return "失效";
				}else if(value == '1'){
					return "有效";
				}else{
					return value;
				}
			}
		},{
			field:'expireTime',title:'有效期',width:180,align:'center',
			formatter:function(value,row,index){
				return value;
			}
		},{
			field:'createTime',title:'创建时间',width:150,align:'center'
		},{
			field:'id',title:'操作',width:150,align:'center',
			formatter:function(value, row, index){
				if("${shiro:hasPermission('/merch/black/delBlackMerch.do')}" == "true" && row.source == '1'){
					return "<a href='#' onclick='delBlackMerch("+'"'+value+'"'+")'>删除<a>";
				}
			}
		}]];
	
	
	

	//数据表格datagrid
	dataGrid = $("#comdg").datagrid({
		url:'listPage.do',
		columns:columns,
		pagination:true,
		striped:true,
		rownumbers:true,
		fit:true,
		toolbar:'#tb',
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
			$(this).datagrid('tooltip',['remark']);
		}
	});
	
	
	//商户黑名单添加
	$("#addbtn").click(function(){
		
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			parent.$.modalDialog({
				title : '添加黑名单',
				width : 550,
				height : 250,
				href : 'merch/black/addBlackMerchIndex.do',
				buttons:[{
					text:'保存',
					iconCls:'icon-ok',
					handler:saveBlackMerch
				}]
			});
			parent.$.messager.progress("close");
		
	});

});


function saveBlackMerch(){
	
	var name = parent.$.modalDialog.handler.find("input[name='name']").val();
	var certificateType =  parent.$.modalDialog.handler.find("select[name='certificateType'] option:selected").val();
	var data = parent.$.modalDialog.handler.find("input[name='data']").val();
	var remark = parent.$.modalDialog.handler.find("textarea[name='remark']").val();
	
	//三个字段均不可以为空  且remark字段最大长度限制在100字以内
	if($.trim(name).length == 0){
		parent.$.messager.alert("提示","名称不可以为空",'warnning');
		return;
	}
	if($.trim(data).length == 0){
		parent.$.messager.alert("提示","证件号不可以为空",'warnning');
		return;
	}
	if($.trim(remark).length == 0){
		parent.$.messager.alert("提示","违法信息不可以为空",'warnning');
		return;
	}
	if(remark.length > 100){
		parent.$.messager.alert("提示","违法信息长度限制在100字以内",'warnning');
		return;
	}
	
	
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	$.ajax({
		url:'addBlackMerch.do',
		type:'POST',
		data:'name='+ name +"&data=" + data + "&remark=" + remark + "&certificateType=" + certificateType,
		dataType:'json',
		success:function(data){
			if(data.success){
				$("#comdg").datagrid("reload");
				parent.$.modalDialog.handler.dialog("close");
				parent.$.messager.progress('close');
			}else{
				parent.$.messager.alert("提示","黑名单添加失败",'error');
				parent.$.messager.progress('close');
			}
		}
	});
	
	
}

function delBlackMerch(id){
	
	parent.$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){  
	    	
	    	parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
	    	
	    	$.ajax({
	    		url:'delBlackMerch.do',
	    		type:'POST',
	    		data:'id='+id,
	    		dataType:'json',
	    		success:function(data){
	    			if(data.success){
	    				$("#comdg").datagrid("reload");
	    				parent.$.messager.progress('close');
	    			}else{
	    				parent.$.messager.alert("提示","黑名单删除失败",'error');
	    				parent.$.messager.progress('close');
	    			}
	    		}
	    	});
	    	
	    }    
	}); 
	
	
	
	
}
	
