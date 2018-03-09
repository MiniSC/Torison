$(document).ready(function(){
	
	//银行省份下拉列表单 数据加载
	$("#bankProvince").combobox({
		onSelect:function(record){
			var proNo = record.no;
			//先清空城市列表的值
			$("#bankCity").combobox('clear');
			$("#bankCity").combobox('reload','selectCityCat.do?no=' + proNo);
		}
	});
	
	

		var settleTime = $("select[name='settleTime'] option:selected").val();
		if(settleTime == '1' || settleTime == '3'){
			$("#spanSettleMaxAmountT0").hide();
			$("input[name='settleMaxAmountT0']").hide();
		}
		
		$("#settleTime").combobox({
			onSelect:function(){
				var settleTime = $("#settleTime").combobox("getValue");
				if(settleTime == '0' || settleTime == '2'){
					$("#spanSettleMaxAmountT0").show();
					$("input[name='settleMaxAmountT0']").show();
					if(settleTime == '0'){
						$("#spanFeeCompanyTip").html("不要忘记给该商户配置D0清分服务费!");
					}else{
						$("#spanFeeCompanyTip").html("不要忘记给该商户配置T0清分服务费!");
					}
				}else{
					$("#spanSettleMaxAmountT0").hide();
					$("input[name='settleMaxAmountT0']").hide();
					$("#spanFeeCompanyTip").html("");
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

