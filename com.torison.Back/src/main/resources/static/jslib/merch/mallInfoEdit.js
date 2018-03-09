$(document).ready(function(){
	//普通商户只有非支付宝,微信签约产品,  平台一级商户具有所有产品, 平台二级商户具有除代付代扣外的所有产品
	var comType = $("input[name='comType']").val();
	var mallSignProduct = $("input[name='originMallSignProduct']").val();
	
	if(comType == '0'){
		//普通商户 一级, 二级
	    checkProduct(comType, mallSignProduct);
	}else if(comType == '1'){
		//一级平台商户  没有企业网银产品
		$("#span6").hide();
	    checkProduct(comType, mallSignProduct);
	}
		
});


function checkProduct(comType, mallSignProduct){
	//普通商户和平台商户共同的签约产品
	
	//认证支付
	if(mallSignProduct.substr(0,1) == '1'){
		$("input[value='100000000000000000000000000000']").attr("checked","checked");
	}
	//快捷支付
	if(mallSignProduct.substr(1,2) != '00'){
		$(".mallSignProductKuai").attr("checked","checked");
		if(mallSignProduct.substr(1,1) == '1'){
			$("input[value='010000000000000000000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(2,1) == '1'){
			$("input[value='001000000000000000000000000000']").attr("checked","checked");
		}
	}
	//个人网银
	if(mallSignProduct.substr(3,2) != '00'){
		$(".mallSignProductPer").attr("checked","checked");
		if(mallSignProduct.substr(3,1) == '1'){
			$("input[value='000100000000000000000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(4,1) == '1'){
			$("input[value='000010000000000000000000000000']").attr("checked","checked");
		}
	}
	//企业网银  普通商户才有企业网银产品
	if(comType == '0'){
		if(mallSignProduct.substr(5,1) == '1'){
			$("input[value='000001000000000000000000000000']").attr("checked","checked");
		}
	}
	//代付
	if(mallSignProduct.substr(6,1) == '1'){
		$("input[value='000000100000000000000000000000']").attr("checked","checked");
	}
	//代扣
	if(mallSignProduct.substr(7,1) == '1'){
		$("input[value='000000010000000000000000000000']").attr("checked","checked");
	}
	//预付费卡
	if(mallSignProduct.substr(8,1) == '1'){
		$("input[value='000000001000000000000000000000']").attr("checked","checked");
	}
	
	//H5支付
	if(mallSignProduct.substr(19,1) == '1'){
		$("input[value='000000000000000000010000000000']").attr("checked","checked");
	}
		
	
	//微信产品
	if(mallSignProduct == null || mallSignProduct == undefined || $.trim(mallSignProduct) == '' || mallSignProduct.length < 10){
		//不做处理
	}else if(mallSignProduct.substr(9,3) != '000' || mallSignProduct.substr(15,1) != '0' || mallSignProduct.substr(17,1) != '0'){
		$(".mallSignProductWeChat").attr("checked","checked");
		if(mallSignProduct.substr(9,1) == '1'){
			$("input[value='000000000100000000000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(10,1) == '1'){
			$("input[value='000000000010000000000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(11,1) == '1'){
			$("input[value='000000000001000000000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(15,1) == '1'){
			$("input[value='000000000000000100000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(17,1) == '1'){
			$("input[value='000000000000000001000000000000']").attr("checked","checked");
		}
	}
	
	//支付宝产品
	if(mallSignProduct == null || mallSignProduct == undefined || $.trim(mallSignProduct) == '' || mallSignProduct.length < 10){
		//不做处理
	}else if(mallSignProduct.substr(12,3) != '000' || mallSignProduct.substr(16,1) != '0' || mallSignProduct.substr(18,1) != '0'){
		$(".mallSignProductAlipay").attr("checked","checked");
		if(mallSignProduct.substr(12,1) == '1'){
			$("input[value='000000000000100000000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(13,1) == '1'){
			$("input[value='000000000000010000000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(14,1) == '1'){
			$("input[value='000000000000001000000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(16,1) == '1'){
			$("input[value='000000000000000010000000000000']").attr("checked","checked");
		}
		if(mallSignProduct.substr(18,1) == '1'){
			$("input[value='000000000000000000100000000000']").attr("checked","checked");
		}
	}
	
	
		
	
	//普通扫码支付
	if(mallSignProduct.substr(20,1) == '1'){
		$("input[value='000000000000000000001000000000']").attr("checked","checked");
	}
	//普通被扫支付
	if(mallSignProduct.substr(21,1) == '1'){
		$("input[value='000000000000000000000100000000']").attr("checked","checked");
	}
	//QQ扫码支付
	if(mallSignProduct.substr(22,1) == '1'){
		$("input[value='000000000000000000000010000000']").attr("checked","checked");
	}
	//QQ被扫支付
	if(mallSignProduct.substr(23,1) == '1'){
		$("input[value='000000000000000000000001000000']").attr("checked","checked");
	}
	
}






