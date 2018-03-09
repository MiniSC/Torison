/**
 * 
 */
function getFilePath(obj){ 
	if (obj) {
		var filepath = "";
	    if (browser.isIe ) {
	    	obj.select();
	    	filepath = document.selection.createRange().text;
	    } else if (browser.isFirefox) {
	      if (obj.files) {
	
	    	  filepath = obj.files.item(0).getAsDataURL();
	      }
	    }
	    if(!filepath){
	    	filepath = obj.value;
		}
		return filepath;
	 }
}

//判断按键操作是否为可打印字符操作
var isPrintableKey = function (e) {
	debugger
	if(e === undefined){
		return false;
	} 
	if(e.ctrlKey || e.altKey){
		return false
	}
    return ((e.keyCode >= 48 && e.keyCode <= 57) || 
    		(e.keyCode >= 65 && e.keyCode <= 90) || 
    		(e.keyCode >= 96 && e.keyCode <= 99) || 
    		(e.keyCode >= 101 && e.keyCode <= 111) ||
    		(e.keyCode >= 186 && e.keyCode <= 192) ||
    		(e.keyCode >= 219 && e.keyCode <= 222)
    		)
}
var regexp_obj = { 
	telphone : /^(010|02\d|0[3-9]\d{2})?-*\d{6,20}[-\d]*$/,
	mobile :/^(13[0-9]|14[579]|15[0-3,5-9]|17[0135678]|18[0-9])\d{8}$/,
	telphone_match :/^(010|02\d|0[3-9]\d{2})-*(\d{6,20}[-\d]*)$/ 

}
		
$.extend($.fn.validatebox.defaults.rules, {
	isCard:{
		validator : function(value, param) {
			return /(^\d{15}$)|(^\d{17}([0-9]|X|x)$)/.test(value);
		},
		message : '身份证格式不正确'
	},
	isMobile :{
		validator : function(value, param) {
			return regexp_obj.mobile.test(value);
		},
		message : '手机号码格式不正确'
	},
	chEngNumSpical : {
		validator : function(value, param) {
			return /^[a-zA-Z\d\u4e00-\u9fa5()（）]{1,}$/.test(value);
		},
		message : '只能是汉字或数字或字母和()'
	},
	engnumber : {
		validator : function(value, param) {
			return /^[a-zA-Z\d]{1,500}$/.test(value);
		},
		message : '只能是数字或字母'
	},
	positiveNumber : {
		validator : function(value, param) {
			return /^\d+(\.{0,1}\d+){0,1}$/.test(value);
		},
		message : '只能是大于等于0的非负数'
	},
	onlyNumber : {
		validator : function(value, param) {
			return /^\d*$/.test(value);
		},
		message : '只能由数字组成'
	},
	isAmount:{
		validator : function(value, param) {
			var len = 2;
			if(!!param){
				len = param[0];
			}
			var re = new RegExp('^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,'+len+'})?$','i')
			return re.test(value);
		},
		message : '金额格式不正确'
	},
	isImgPhoto:{
		validator : function(value, param) {
			var re = new RegExp('('+param[0].replace(/、/g,'\|')+')$','i')
			return re.test(value)
		},
		message : '仅支持[{0}]格式'
	}
});