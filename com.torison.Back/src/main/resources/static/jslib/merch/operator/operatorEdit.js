$(document).ready(function () {
	    createPersonalGrid();
        
		$("#searchId").bind("click",searchData);

});  
	
	
	//Grid 操作处理函数
	function p_optor(rowdata, rowindex, value){
	   var h = '<a class="left_margin" onclick="openOperatorDialog(\''+ rowdata.cstNo + "','" + rowdata.loginName + '\');" href="javascript:void(0);">修改操作员信息</a>';
	   return  h;
	}
	
	function createPersonalGrid(){
		 var titleArray= ['id','cstNo','管理员姓名','管理员邮箱','手机号码','登录名','操作'];
		 var colNameArray = ['id','cstNo','userName','email','mobile','loginName',''];
		 var colWidthArray = [1,1,'15%','15%','15%','15%','25%'];
		 var alignArray = ['left','left','center','center','center','center','center'];
		 var hideArray = [true,true,false,false,false,false,false];
		 var processFunctionArray = [
		            {'render':null},{'render':null},{'render':null},
					{'render':null},{'render':null},{'render':null},
					{'render':p_optor}
			 ];
		 var columnsArray = createColomns(titleArray,colNameArray,colWidthArray,alignArray,hideArray,processFunctionArray);
		 
		 var gridParmSetting = {
			 pageSize: 20,
        	 width:'100%',
        	 height:'98%',
        	 delayLoad:true,
        	 url:basePath+'/merch/operatorManageJson.do'
		 };
		 base_grid = createGrid(gridParmSetting,columnsArray,'maingrid4');
		
	}
	//搜索
	function searchData(){
	  base_grid.setParm('cstNo',$("#cstNo").val());
	  base_grid.setParm('loginName',$("#loginName").val());
	  base_grid.reload();
	}

   
    
    //操作员信息管理
    
    function openOperatorDialog(cstNo, loginName){
    	
    	$.ajax({
    		type:"POST",
    		url:"getOperatorBean.do",
    		data:"cstNo="+cstNo+"&loginName="+loginName,
    		dataType:"json",
    		success:function(data){
    			$("#newId").val(data.id);
    			$("#newLoginName").val(data.loginName);
    			$("#newUserName").val(data.userName);
    			$("#newEmail").val(data.email);
    			$("#oldLoginPwd").val(data.loginPwd);
    			$("#oldOperatPwd").val(data.operatPwd);
    			$("#newMobile").val(data.mobile);
    			$("#oldMobile").val(data.mobile);
    			$("#operatorModal").modal('show');
    		}
    	});
    }
    
    function submitOperator(){
    	
    	if (isBlank("newUserName")) {
    		alert('管理员姓名不能为空！');
    		return alertAndFocus('newUserName');
    	}
    	
    	if (isBlank("newEmail")) {
    		alert('邮箱不能为空！');
    		return alertAndFocus('newEmail');
    	}
    	if(!testFlexLengthText('newUserName',true,'管理员姓名',true,null)){
    		return alertAndFocus('newUserName');
    	}
    	if(!testEmail('newEmail',true,'管理员邮箱',true,null)){
    		return alertAndFocus('newEmail');
    	} 
    	
    	if(!testMobile('newMobile',true,'管理员手机',true,null)){
    		return alertAndFocus('newMobile');
    	}
    	var loginPwd = $("#newLoginPwd").val();
    	var operatPwd = $("#newOperatPwd").val();
    	
    	//登录密码和操作密码只能是6-12的数字和字母组成
    	if(loginPwd.length > 0){
    		if(!checkpassword(loginPwd)){
    			alert('登录密码只能是6-12位的数字和字母组合');
    			return false;
    		}
    	}
    	
    	if(operatPwd.length > 0){
    		if(!checkpassword(operatPwd)){
    			alert('操作密码只能是6-12位的数字和字母组合');
    			return false;
    		}
    	}
    	
    	if($.trim(loginPwd) == $.trim(operatPwd)){
    		alert('登录密码和操作密码不能够相同!');
    		return false;
	    }
    	
    	
    	var id = $("#newId").val();
    	var cstNo = $("#cstNo").val();
    	var loginName = $("#newLoginName").val();
    	var username = $("#newUserName").val();
    	var email = $("#newEmail").val();
    	var oldLoginPwd = $("#oldLoginPwd").val();
		var oldOperatPwd = $("#oldOperatPwd").val();
    	var mobile = $("#newMobile").val();
    	var oldMobile = $("#oldMobile").val();
    	
    	
    	var param = {
    			id:id,
    			cstNo:cstNo,
    			loginName:loginName,
    			userName:username,
    			email:email,
    			loginPwd:loginPwd,
    			oldLoginPwd:oldLoginPwd,
    			operatPwd:operatPwd,
    			oldOperatPwd:oldOperatPwd,
    			mobile:mobile,
    			oldMobile:oldMobile
            };
        $.ajax({
            type	: "POST",
            url 	: "UpdateOperator.do",
            dataType: "json",
            data    : param,
            success	: function (data){
                if (data.resultCode == 'true') {
                    $.ligerDialog.alert('保存成功');
                    $('#operatorModal').modal('hide');
                } else {
                    $.ligerDialog.alert(data.resultMsg);
                }
            }
        });
    	
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
   
   