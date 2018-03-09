/*
	商户信息管理界面的javascript
*/
var dataGrid;
var cstNo = "";
$(document).ready(function(){
	
	$("#reset").click(function(){
		$("#countForm").form('clear');
	});
	
	//条件查询
	$("#select").click(function(){
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		//序列化表单中的参数,然后datagrid重新加载新的查询数据
		var userName = $("input[name='userName']").val();
		var email = $("input[name='email']").val();
		var mobile = $("input[name='mobile']").val();
		
		$("#opedg").datagrid("load", {
			userName:userName,
			email:email,
			mobile:mobile
		});
		parent.$.messager.progress('close');
	});
	
	
	
	
	//表格中的列定义
	var columns = [
	       [{
		    field:'id',title:'ID',checkbox:true
		},{
			field:'loginName',title:'登录名',width:150,align:'center'
	    },{
			field:'userState',title:'状态',width:100,align:'center',
			formatter:function(value,row,index){
				 if(value == "0"){
					 return "正常";
				 }else if(value == "1"){
					 return "冻结";
				 }else if(value == "2"){
					 return "注销";
				 }else if(value == "3"){
					 return "暂时冻结";
				 }else{
					 return "";
				 }
			}
	    },{
	    	field:'userName',title:'操作员姓名',width:150,align:'center'
	    },{
		    field:'mobile',title:'操作员手机号',width:150,align:'center',
		},{
			field:'email',title:'操作员邮箱',width:150,align:'center'
		},{
			field:'createTime',title:'创建时间',width:150,align:'center',
				formatter:function(value,row,index){
					  if(value != null && value != ""){
						  return value.substr(0,4) + "-" + value.substr(4,2) + "-" + value.substr(6,2);
					  }
					  return "";
				}
		},{
			field:'operate',title:'操作',width:120,align:'center',
			formatter:function(value,row,index){
				   var options = "";
				   if("${shiro:hasPermission('/merch/operator/toUpdateOperator.do')}" == "true"){
					   options += '<a  onclick="operatorView(\''+ row.id+'\')" href="javascript:void(0);">修改信息</a>&nbsp;&nbsp;';
				   }
				return options;
			}
	}]];
	
	
	cstNo = $("#cstNo").val();
	

	//数据表格datagrid
	dataGrid = $("#opedg").datagrid({
		url:'listPage.do?cstNo='+cstNo,
		columns:columns,
		pagination:true,
		striped:true,
		rownumbers:true,
		singleSelect:true,
		fit:true,
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
			$(this).datagrid('tooltip',['loginName','userName','email']);
			$("#opedg").parent().find("div .datagrid-header-check").children("input[type=\"checkbox\"]").eq(0).attr("style", "display:none;");
		}

	});
	

});





function operatorView(id){
	
	   parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
		});
		
		if("${shiro:hasPermission('/merch/operator/updateOperator.do')}" == "true"){
			parent.$.modalDialog({
				title : '修改操作员信息',
				width : 650,
				height : 250,
				href : 'merch/operator/toUpdateOperator.do?id=' + id,
				buttons : [ {
					text : '保存',
					handler : editOperatorAction
				}]
			});
		}else{
			parent.$.modalDialog({
				title : '修改操作员信息',
				width : 650,
				height : 250,
				href : 'merch/operator/toUpdateOperator.do?id=' + id,
			});
		}
		parent.$.messager.progress("close");
	
}

function editOperatorAction(){
	
	//表单的校验
	var userName = parent.$.modalDialog.handler.find('#userName').val();
	var email = parent.$.modalDialog.handler.find('#email').val();
	var loginPwd = parent.$.modalDialog.handler.find('#loginPwd').val();
	var operatePwd = parent.$.modalDialog.handler.find('#operatePwd').val();
	var mobile = parent.$.modalDialog.handler.find('#mobile').val();
	
	
	
	if ($.trim(userName).length == 0) {
		alert('管理员姓名不能为空！');
		return;
	}
	
	
	if(!testFlexLengthText(userName)){
		alert("管理员姓名只能为长度为30数字、字母或15位汉字");
		return;
	}
	
	if ($.trim(email).length == 0) {
		alert('邮箱不能为空！');
		return;
	}
	
	if(!testEmail(email)){
		alert('邮箱格式不正确空！');
		return;
	}
	
	if ($.trim(mobile).length == 0) {
		alert('手机号不能为空！');
		return;
	}
	
	if(!/^1[3|4|7|5|8|9]\d{9}$/g.test(mobile)){
		alert('手机号格式不正确！');
		return;
	}
	
	
	//登录密码和操作密码只能是6-12的数字和字母组成
	if(loginPwd.length > 0){
		if(!checkpassword(loginPwd)){
			alert('登录密码只能是6-12位的数字和字母组合');
			return false;
		}
	}
	
	if(operatePwd.length > 0){
		if(!checkpassword(operatePwd)){
			alert('操作密码只能是6-12位的数字和字母组合');
			return false;
		}
	}
	
	
	
	if($.trim(loginPwd) == $.trim(operatePwd)){
		
		if(loginPwd.length == 0 && operatePwd.length == 0){
			
		}else{
			alert('登录密码和操作密码不能够相同!');
			return false;
		}
		
    }
	
	//验证通过
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	
	
	var params = parent.$.modalDialog.handler.find('#operatorForm').serialize();
	
	$.ajax({
		type:"POST",
		url:"updateOperator.do",
		data:params,
		success:function(result){
			var data = eval("("+result+")");
			if(!data.success){
				parent.$.messager.progress("close");
				parent.$.messager.alert("温馨提示",data.msg,"warning");
			}else{
				$("#opedg").datagrid("reload");
				parent.$.messager.progress("close");
				parent.$.modalDialog.handler.dialog("close");
			}
		}
	});
	
	
}

// 验证参数：只能为长度为30数字、字母或15位汉字
function testFlexLengthText(userName){
	
	var c = '';
	var sn = 0;//数字、字母个数
	var cn = 0;//汉字个数
	
	if(!/^[(\u4E00-\u9FA5)|(0-9)|(a-zA-Z)]+$/g.test(userName)){
		return false;
	}
	
	for(var i=0;i<userName.length;i++){ 
		c = userName.charAt(i);
		if($.trim(c).length == 0){
			continue;
		}
		if(/^[\u4E00-\u9FA5]+$/g.test(c)){
			cn +=2;
		}else{
			if(/^[(0-9)|(a-zA-Z)]+$/g.test(c)){
				sn +=1;
			}else{
				return false;
			}
		}
	}
	var total = sn+cn;
	if(total<=30 ){
		return true;
	}
	return false;
}


function testEmail(email) {
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/g;
	
	return reg.test(email);
}


function isNumber(charCode){
    //48为字符'0'的unicode码,57为字符'9'的unicode码
    return charCode >= 48 && charCode <= 57
}

function isLetter(charCode){
//65为字符'A'的unicode码,90为字符'Z'的unicode码
//90为字符'a'的unicode码,122为字符'z'的unicode码
return (charCode >= 65 && charCode <= 90) 
            || (charCode >= 97 && charCode <= 122);
}

//密码只能是6-12位的数字和字母的组合
function checkpassword(password){
	
	   var numberCount,//数字字符数目
	       letterCount,//字母字符数目
	       noNumLetter,//其他字符
	       length = password.length;
	   if(length < 6 || length > 12){
	       return false;
	   }
	   numberCount = letterCount = noNumLetter = 0;
	   for(var i = 0, charCode; i < length; i++){
	       charCode= password.charCodeAt(i);//取得每个字符
	       if(isNumber(charCode)){
	           numberCount += 1;//数字字符数目加1 
	       }else if(isLetter(charCode)){
	           letterCount += 1;//字母字符数目加1 
	       }else{
	    	   noNumLetter += 1;//即不是字母又不数字
	       }       
	   }
	   
	   if(noNumLetter > 0 ){
		   return false;
	   }
	   
	   if(numberCount == length || letterCount == length){
		   return false;
	   }
	   
	   return true;
}

	
