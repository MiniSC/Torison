$(document).ready(function(){
	//普通商户只有非支付宝,微信签约产品,  平台一级商户具有所有产品, 平台二级商户具有除代付代扣外的所有产品
	var mallSignProduct = $("input[name='originMallSignProduct']").val();
	var pMallSignProduct = $("input[name='pMallSignProduct']").val();
	//二级商户的签约产品需要限制在一级商户签约产品内
	secMerchCheckProduct(mallSignProduct, pMallSignProduct);
	
});


function secMerchCheckProduct(mallSignProduct, pMallSignProduct){
	
	//认证支付
	if(pMallSignProduct.substr(0,1) == '0'){
		$("#span1").remove();
	}else{
		if(mallSignProduct.substr(0,1) == '1'){
			$("input[value='100000000000000000000000000000']").attr("checked","checked");
		}
	}
	//快捷支付
	if(pMallSignProduct.substr(1,2) == '00'){
		$("#kuaiSpan").remove();
	}else{
		if(mallSignProduct.substr(1,2) != '00'){
			$(".mallSignProductKuai").attr("checked","checked");
		}
		if(pMallSignProduct.substr(1,1) == '0'){
			$("#span2").remove();
		}else{
			if(mallSignProduct.substr(1,1) == '1'){
				$("input[value='010000000000000000000000000000']").attr("checked","checked");
			}
		}
		if(pMallSignProduct.substr(2,1) == '0'){
			$("#span3").remove();
		}else{
			if(mallSignProduct.substr(2,1) == '1'){
				$("input[value='001000000000000000000000000000']").attr("checked","checked");
			}
		}
	}
	//个人网银
	if(pMallSignProduct.substr(3,2) == '00'){
		$("#perSpan").remove();
	}else{
		if(mallSignProduct.substr(3,2) != '00'){
			$(".mallSignProductPer").attr("checked","checked");
		}
		if(pMallSignProduct.substr(3,1) == '0'){
			$("#span4").remove();
		}else{
			if(mallSignProduct.substr(3,1) == '1'){
				$("input[value='000100000000000000000000000000']").attr("checked","checked");
			}
		}
		
		if(pMallSignProduct.substr(4,1) == '0'){
			$("#span5").remove();
		}else{
			if(mallSignProduct.substr(4,1) == '1'){
				$("input[value='000010000000000000000000000000']").attr("checked","checked");
			}
		}
			
	}
	//企业网银
	if(pMallSignProduct.substr(5,1) == '0'){
		$("#span6").remove();
	}else{
		if(mallSignProduct.substr(5,1) == '1'){
			$("input[value='000001000000000000000000000000']").attr("checked","checked");
		}
	}
	//代付
	if(pMallSignProduct.substr(6,1) == '0'){
		$("#span7").remove();
	}else{
		if(mallSignProduct.substr(6,1) == '1'){
			$("input[value='000000100000000000000000000000']").attr("checked","checked");
		}
	}
	//代扣
	if(pMallSignProduct.substr(7,1) == '0'){
		$("#span8").remove();
	}else{
		if(mallSignProduct.substr(7,1) == '1'){
			$("input[value='000000010000000000000000000000']").attr("checked","checked");
		}
	}
	//预付费卡
	if(pMallSignProduct.substr(8,1) == '0'){
		$("#span9").remove();
	}else{
		if(mallSignProduct.substr(8,1) == '1'){
			$("input[value='000000001000000000000000000000']").attr("checked","checked");
		}
	}
	
	//H5支付
	if(pMallSignProduct.substr(19,1) == '0'){
		$("#span20").remove();
	}else{
		if(mallSignProduct.substr(19,1) == '1'){
			$("input[value='000000000000000000010000000000']").attr("checked","checked");
		}
	}
	
	//微信
	if(pMallSignProduct == null || pMallSignProduct == undefined || $.trim(pMallSignProduct) == ''){
		$(".mallSignPro_2").remove();
	}else if(pMallSignProduct.substr(9,3) == '000' && pMallSignProduct.substr(15,1) == '0' && pMallSignProduct.substr(17,1) == '0'){
		$(".mallSignPro_2").remove();
	}else if(mallSignProduct == null || mallSignProduct == undefined || $.trim(mallSignProduct) == ''){
		$(".mallSignPro_2").remove();
	}else{
		if(mallSignProduct.substr(9,3) != '000' || mallSignProduct.substr(15,1) != '0' || mallSignProduct.substr(17,1) != '0'){
			$(".mallSignProductWeChat").attr("checked","checked");
		}
		//扫码支付
		if(pMallSignProduct.substr(9,1) == '0'){
			$("#span10").remove();
		}else{
			if(mallSignProduct.substr(9,1) == '1'){
				$("input[value='000000000100000000000000000000']").attr("checked","checked");
			}
		}
		
		//公众号支付
		if(pMallSignProduct.substr(10,1) == '0'){
			$("#span11").remove();
		}else{
			if(mallSignProduct.substr(10,1) == '1'){
				$("input[value='000000000010000000000000000000']").attr("checked","checked");
			}
		}
		
		//条码声波支付
		if(pMallSignProduct.substr(11,1) == '0'){
			$("#span12").remove();
		}else{
			if(mallSignProduct.substr(11,1) == '1'){
				$("input[value='000000000001000000000000000000']").attr("checked","checked");
			}
		}
		
		//H5支付
		if(pMallSignProduct.substr(15,1) == '0'){
			$("#span16").remove();
		}else{
			if(mallSignProduct.substr(15,1) == '1'){
				$("input[value='000000000000000100000000000000']").attr("checked","checked");
			}
		}
		
		//SDK支付
		if(pMallSignProduct.substr(17,1) == '0'){
			$("#span18").remove();
		}else{
			if(mallSignProduct.substr(17,1) == '1'){
				$("input[value='000000000000000001000000000000']").attr("checked","checked");
			}
		}
	}
	
	
	//支付宝
	if(pMallSignProduct == null || pMallSignProduct == undefined || $.trim(pMallSignProduct) == ''){
		$(".mallSignPro_3").remove();
	}else if(pMallSignProduct.substr(12,3) == '000' && pMallSignProduct.substr(16,1) == '0' && pMallSignProduct.substr(18,1) == '0'){
		$(".mallSignPro_3").remove();
	}else if(mallSignProduct == null || mallSignProduct == undefined || $.trim(mallSignProduct) == ''){
		$(".mallSignPro_3").remove();
	}else{
		if(mallSignProduct.substr(12,3) != '000' || mallSignProduct.substr(16,1) != '0' || mallSignProduct.substr(18,1) != '0'){
			$(".mallSignProductAlipay").attr("checked","checked");
		}
		//扫码支付
		if(pMallSignProduct.substr(12,1) == '0'){
			$("#span13").remove();
		}else{
			if(mallSignProduct.substr(12,1) == '1'){
				$("input[value='000000000000100000000000000000']").attr("checked","checked");
			}
		}
		
		//公众号支付
		if(pMallSignProduct.substr(13,1) == '0'){
			$("#span14").remove();
		}else{
			if(mallSignProduct.substr(13,1) == '1'){
				$("input[value='000000000000010000000000000000']").attr("checked","checked");
			}
		}
		
		//条码声波支付
		if(pMallSignProduct.substr(14,1) == '0'){
			$("#span15").remove();
		}else{
			if(mallSignProduct.substr(14,1) == '1'){
				$("input[value='000000000000001000000000000000']").attr("checked","checked");
			}
		}
		
		//H5支付
		if(pMallSignProduct.substr(16,1) == '0'){
			$("#span17").remove();
		}else{
			if(mallSignProduct.substr(16,1) == '1'){
				$("input[value='000000000000000010000000000000']").attr("checked","checked");
			}
		}
		
		//SDK支付
		if(pMallSignProduct.substr(18,1) == '0'){
			$("#span19").remove();
		}else{
			if(mallSignProduct.substr(18,1) == '1'){
				$("input[value='000000000000000000100000000000']").attr("checked","checked");
			}
		}
	}
	
	
	
	//普通扫码支付:
	if(pMallSignProduct.substr(20,1) == '0'){
		$("#span21").remove();
	}else{
		if(mallSignProduct.substr(20,1) == '1'){
			$("input[value='000000000000000000001000000000']").attr("checked","checked");
		}
	}
	//普通被扫支付:
	if(pMallSignProduct.substr(21,1) == '0'){
		$("#span21").remove();
	}else{
		if(mallSignProduct.substr(21,1) == '1'){
			$("input[value='000000000000000000000100000000']").attr("checked","checked");
		}
	}
	//QQ扫码支付
	if(pMallSignProduct.substr(22,1) == '0'){
		$("#span22").remove();
	}else{
		if(mallSignProduct.substr(22,1) == '1'){
			$("input[value='000000000000000000000010000000']").attr("checked","checked");
		}
	}
	//QQ被扫支付
	if(pMallSignProduct.substr(23,1) == '0'){
		$("#span23").remove();
	}else{
		if(mallSignProduct.substr(23,1) == '1'){
			$("input[value='000000000000000000000001000000']").attr("checked","checked");
		}
	}
	
}






