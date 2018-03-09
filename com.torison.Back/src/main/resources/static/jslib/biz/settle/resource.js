function search_Data(){
     var v = $.trim($("#flowNo").val());
	 var reg = /^[a-z0-9A-Z]+$/;
	 if(v!='' && !reg.test(v)){
	    alert("支付流水号只能为数字或字母！");
	    return false;
	 }
     $("#formId").submit();
}
/**
 * 全选
 * @param selectAllBoxClass
 * @param childboxClass
 */
function all_checked(selectAllBoxClass,childboxClass){
    var $target = $("."+selectAllBoxClass+":first-child"); 
	 var $childbox = $("."+childboxClass);
	 if($target.attr("checked") == "checked"){
	 	$.each($childbox,function(index,item){
		   $(this).attr("checked","checked");
		});
	 }else{
	 	$.each($childbox,function(index,item){
		   $(this).attr("checked",false);
		});
	 }	
}

/**
 * 单选
 * @param selectAllBoxClass
 * @param childboxClass
*/
function check_each(selectAllBoxClass,childboxClass)
{
    var $target = $("."+selectAllBoxClass+":first-child"); 
	var $childbox = $("."+childboxClass);
	var rt = true;
	$.each($childbox,function(index,item)
	{
		if(!($(this).attr("checked") == "checked")){
			rt = false;
            return false;			
		}
	});
	
	if(rt)
	{
		$target.attr("checked",true);
	}else
	{
		$target.attr("checked",false);
	}
}
//初始化时间控件 
function initalQueryDateInput(){
	$("#startDate").bind("focus",function (){
		WdatePicker({
			maxDate: '#F{$dp.$D(\'endDate\')}'
		});
	});
	$("#endDate").bind("focus",function (){
		WdatePicker({
			minDate: '#F{$dp.$D(\'startDate\')}'
		});
	});
	
	
	
}
//初始化时间控件 
function initQueryDateInput(){
	$("#queryStartDate").bind("focus",function (){
		WdatePicker({
			maxDate: '#F{$dp.$D(\'queryEndDate\')}'
		});
	});
	$("#queryEndDate").bind("focus",function (){
		WdatePicker({
			minDate: '#F{$dp.$D(\'queryStartDate\')}'
		});
	});
	
	$("#settleTime").bind("focus",function (){
		WdatePicker();
	});
}
//初始化时间控件 
function initCheckDateInput(){
	$("#checkStart").bind("focus",function (){
		WdatePicker({
			maxDate: '#F{$dp.$D(\'checkEnd\')}'
		});
	});
	$("#checkEnd").bind("focus",function (){
		WdatePicker({
			minDate: '#F{$dp.$D(\'checkStart\')}'
		});
	});
}
//初始化时间控件 
function initSettleDateInput(){
	$("#settleStart").bind("focus",function (){
		WdatePicker({
			maxDate: '#F{$dp.$D(\'settleEnd\')}'
		});
	});
	$("#settleEnd").bind("focus",function (){
		WdatePicker({
			minDate: '#F{$dp.$D(\'settleStart\')}'
		});
	});
}

var settleCount = 0;
var tranAmount = 0;
var refundAmount = 0;
var feeAmount = 0;
var settleAmount = 0;
var batchHtml = "";
function initalStatistData(confirmType){
	settleCount = 0;
	tranAmount = 0;
	refundAmount = 0;
	feeAmount = 0;
	settleAmount = 0;
	batchHtml = "";
	
	//遍历被选中的结算单
	$.each($(".settleNoCheckBox"),function (){
		if($(this).attr("checked") == "checked"){
			var settleNo = $(this).val();
			
			var tmpTran = $("#trxAmount" + settleNo).text();
			var tmpRefund = $("#refundAmount" + settleNo).text();
			var tmpFeeAmount = $("#feeAmount" + settleNo).text();
			var tmpSettle = $("#settleAmount" + settleNo).text();
			settleCount ++;
			tranAmount = accAdd(tranAmount,tmpTran);
			refundAmount = accAdd(refundAmount,tmpRefund);
			feeAmount = accAdd(feeAmount,tmpFeeAmount);
			settleAmount = accAdd(settleAmount,tmpSettle);
		}
	});
	
	if(confirmType){
		batchHtml = "<p>审核通过：</p>";	
	}else{
		batchHtml = "<p>拒绝操作：</p>";
	}	
	
	batchHtml = batchHtml + "<p>结算单：" +  settleCount + "个</p>";
	
	batchHtml = batchHtml + "<p>总交易金额：" + transforVal(tranAmount) + "元</p>";
	batchHtml = batchHtml + "<p>总退款金额：" + transforVal(refundAmount) + "元</p>";
	batchHtml = batchHtml + "<p>总服务费金额：" + transforVal(feeAmount) + "元</p>";
	batchHtml = batchHtml + "<p>总结算金额：" + transforVal(settleAmount) + "元</p>";
	
}
//解决js相加精度丢失问题
function accAdd(arg1,arg2){
    var r1,r2,m; 
    try{
    	r1=arg1.toString().split(".")[1].length;
    } catch(e){
    	r1=0;
    } 
    try{
    	r2=arg2.toString().split(".")[1].length;
    } catch(e){
    	r2=0;
    } 
    m=Math.pow(10,Math.max(r1,r2));
    return (arg1*m+arg2*m)/m;
}
//减法函数，用来得到精确的减法结果
function accSub(arg1,arg2){
   var r1,r2,m,n;
   try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
   try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
   m=Math.pow(10,Math.max(r1,r2));
   //last modify by deeka
   //动态控制精度长度
   n=(r1>=r2)?r1:r2;
   return ((arg1*m-arg2*m)/m).toFixed(n);
}

//parseFloat精度转化，解决0.9999999995
function transforVal(v)
{
	var vals = (v + "").split(".");

	if(vals.length > 1)
	{
			var len = vals[1].length;

			var nv;

			if(len > 2)
			{	
				nv = parseInt(vals[0] + vals[1].substr(0,2)) + 1;

				v = nv/100;
			}
	}
	return v;
	
}

function Ajax(targetUrl,param,successMsg,exceptionMsg){
	$.ajax({
		type	: "POST",
		url 	: targetUrl,
		async 	: false,
		dataType: "json",
		data    : param,
		success	: function (data){
			var bool = data.success;
			if(bool==true){
				$('#myModal').modal('hide');
				parent.$.messager.alert("成功",successMsg,"info");
				dataGrid.datagrid('reload');
			}else{
				$.messager.alert(exceptionMsg, data.msg, 'error');
			}
			return false;
		}

	});
}
function initalBatchBtn(){
	//批量审核订单按钮
	$("#batchConfirmBtn").bind("click",function (){
		if(!hasSelectElements()){
			$.ligerDialog.warn("请选择要操作结算单");
			return false;		
		}
		initalStatistData(true);
		$("#Modal").text("确认审核");
		
		$("#ModelContent").html(batchHtml);
		$('#settleModal').modal('show');
		var settleId = '';
		$.each($(".settleNoCheckBox:checked"),function(index,item){
			settleId += '&settleId='+$(this).val();
		});
		var targetUrl = confirmActionUrl + "?single=false" + settleId;
		$("#modelConfirm").bind("click",process);
		$("#modelCancel").one("click",function (){
			$('#settleModal').modal('hide');
			$('#modelConfirm').unbind("click",process);
		});
		function process(){
			$('#modelConfirm').unbind("click",process);
			$('#settleModal').modal('hide');
			Ajax(targetUrl,null,'审核成功','审核失败');
			return false;
		}
		return false;
	});
	//批量拒绝订单按钮
	$("#batchRejectBtn").bind("click",function (){
		if(!hasSelectElements()){
			$.ligerDialog.warn("请选择要操作结算单");
			return false;
		}
		initalStatistData(false);
		$("#Modal").text("拒绝操作");
		$("#ModelContent").html(batchHtml);
		$('#settleModal').modal('show');
		var settleId = '';
		$.each($(".settleNoCheckBox:checked"),function(index,item){
			settleId += '&settleId='+$(this).val();
		});
		var targetUrl = rejectActionUrl + "?single=false" + settleId;
		$("#modelConfirm").bind("click",process);
		$("#modelCancel").one("click",function (){
			$('#settleModal').modal('hide');
			$('#modelConfirm').unbind("click",process);
		});
		function process(){
			$('#modelConfirm').unbind("click",process);
			Ajax(targetUrl,null,'操作成功','操作失败');
			return false;
		}
		return false;
	});
}
function hasSelectElements(){
	var selectEmpty = true;
	$.each($(".settleNoCheckBox"),function (){
		if($(this).attr("checked") == "checked"){
			selectEmpty = false;
		}
	});
	return !selectEmpty;
}

/**
 * 结算单审核确认弹出框
 */
function confirmDialog(settleId){
	var targetUrl = confirmActionUrl + "?single=true&settleId=" + settleId;
	$("#Modal").text("确认审核");
	var html = "<p>确认审核：</p>";
	html = html + settleOrderDetail(settleId);
	$("#ModelContent").html(html);
	$('#settleModal').modal('show');
	$("#modelConfirm").bind("click",process);
	$("#modelCancel").one("click",function (){
		$('#settleModal').modal('hide');
		$('#modelConfirm').unbind("click",process);
	});
	function process(){
		$('#modelConfirm').unbind("click",process);
        $('#settleModal').modal('hide');
		Ajax(targetUrl,null,'审核成功','审核失败');
		return false;
	}
	return false;
}

/**
 * 结算单复核确认弹出框
 */
function recheckDialog(settleId){
	var targetUrl = recheckActionUrl + "?single=true&settleId=" + settleId;
	$("#Modal").text("确认复核");
	var html = "<p>确认复核：</p>";
	html = html + settleOrderDetail(settleId);
	$("#ModelContent").html(html);
	$('#settleModal').modal('show');
	$("#modelConfirm").bind("click",process);
	$("#modelCancel").one("click",function (){
		$('#settleModal').modal('hide');
		$('#modelConfirm').unbind("click",process);
	});
	function process(){
		$('#modelConfirm').unbind("click",process);
        $('#settleModal').modal('hide');
		Ajax(targetUrl,null,'复核成功','复核失败');
		return false;
	}
	return false;
}

function settleOrderDetail(settleId){
	var html = "<p>结算单号：" +  settleId + "</p>";
	html = html + "<p>商户名称：" + $("#cst" + settleId).text() + "</p>";
	html = html + "<p>交易金额：" + $("#trxAmount" + settleId).text() + "元</p>";
	html = html + "<p>退款金额：" + $("#refundAmount" + settleId).text() + "元</p>";
	html = html + "<p>结算金额：" + $("#settleAmount" + settleId).text() + "元</p>";
	return html;
}
/**
 * 单个结算单拒绝弹出框
 * @param settleId
 * @returns {Boolean}
 */
function rejectDialog(settleId){
	var targetUrl = rejectActionUrl + "?single=true&settleId=" + settleId;
	$("#Modal").text("结算单拒绝");
	var html = "<p>拒绝操作：</p>";
	html = html + settleOrderDetail(settleId);
	$("#ModelContent").html(html);
	$('#settleModal').modal('show');
	$("#modelConfirm").bind("click",process);
	$("#modelCancel").one("click",function (){
		$('#settleModal').modal('hide');
		$('#modelConfirm').unbind("click",process);
	});
	function process(){
		$('#modelConfirm').unbind("click",process);
		$('#settleModal').modal('hide');
		Ajax(targetUrl,null,'拒绝操作成功','拒绝操作失败');
		return false;
	}
	return false;
}
/**
 * 单条结算单取消弹出框
 * @param settleId
 * @returns {Boolean}
 */
/*function cancleDialog(settleId){
	var targetUrl = cancelActionUrl + "?single=true&settleId=" + settleId;
	$("#Modal").text("结算单审核撤消");
	var html = "<p>撤消审核：</p>";
	html = html + settleOrderDetail(settleId);
	$("#ModelContent").html(html);
	$("#ModelBtnBox").show();
	$('#settleModal').modal('show');
	$("#modelConfirm").bind("click",process);
	$("#modelCancel").one("click",function (){
		$('#settleModal').modal('hide');
		$('#modelConfirm').unbind("click",process);
	});
	function process(){
		$('#modelConfirm').unbind("click",process);
		Ajax(targetUrl,null,'撤消成功','撤消失败');
        $('#settleModal').modal('hide');
		return false;
	}
	return false;
}*/

function closeRunTask(settleId){
	var targetUrl = rerunActionUrl + "?settleId=" + settleId + "&rerurn=false";
	$("#reModalLabel").text("结算单关闭重跑");
	var html = "<p>结算单" + settleId + "关闭重跑</p>";	
	$("#ModelContent").html(html);
	$("#ModelBtnBox").show();
	$('#reModal').modal('show');
	$("#modelConfirm").bind("click",process);
	$("#modelCancel").one("click",function (){
		$('#reModal').modal('hide');
		$('#modelConfirm').unbind("click",process);
	});
	function process(){
		$('#modelConfirm').unbind("click",process);
		$('#reModal').modal('hide');
		Ajax(targetUrl,null,'已关闭重跑','关闭失败');
		return false;
	}
	return false;
}
function alertAndFocus(id, msg) {
	alert(msg);
	$('#' + id).focus();
	return false;
}

function isBlank(inputId) {
	var val = $("#" + inputId).val();
	// 将输入控件前后空格去除
	$("#" + inputId).val($.trim(val));
	if ($.trim(val) == "") {
		return true;
	}
	return false;
}
function testNumEn(val) {
	var regExp = new RegExp("^[(0-9)|(a-zA-Z)]+$","i");//第二个参数,表示匹配时不分大小写
	if (regExp.test(val)) {
		return true;
	}
	return false;
}

function submitValiate(){
	
	// 商户号
	if (isBlank("cstNoInput")) {
		return alertAndFocus('cstNoInput', '商户号不能为空！');
	} else if (!testNumEn($("#cstNoInput").val())) {
		return alertAndFocus('cstNoInput', '商户号只能包含字母和数字！');
	}
	
	//结算区间开始
	if (isBlank("startInput")) {
		return alertAndFocus('startInput', '结算区间开始不能为空！');
	}
	
	//结算区间结束
	if (isBlank("endInput")) {
		return alertAndFocus('endInput', '结算区间结束不能为空！');
	}
	return true;
}

function initalPaddingDateInput(){
	 $("#startInput").bind("focus", function () {
         WdatePicker({
             readOnly:true,maxDate: '#F{$dp.$D(\'endInput\',{d:-1});}'
         });
	 });
	 $("#endInput").bind("focus", function () {
	      WdatePicker({
	         readOnly:true,minDate: '#F{$dp.$D(\'startInput\',{d:1});}'
	      });
	 });
}

function startRunTask(settleId){
	var targetUrl = rerunActionUrl + "?settleId=" + settleId + "&rerurn=true";
	$("#reModalLabel").text("结算单重跑");
	var html = "<p>结算单" + settleId + "重跑</p>";	
	$("#ModelContent").html(html);
	$("#ModelBtnBox").show();
	$('#reModal').modal('show');
	$("#modelConfirm").bind("click",process);
	$("#modelCancel").one("click",function (){
		$('#reModal').modal('hide');
		$('#modelConfirm').unbind("click",process);
	});
	function process(){
		$('#modelConfirm').unbind("click",process);
		$('#reModal').modal('hide');
		Ajax(targetUrl,null,'已经触发重跑','触发重跑失败');
		return false;
	}
	return false;
}



function changeBank(obj,targetId){
	var v = $(obj).val();
    if(v!=''){
		v = v.split('_')[1];
	}
	$('#'+targetId).val(v);
}
/**
 * 打款确认弹出框
 * @param settleId
 * @returns {Boolean}
 */
function payDialog(settleId){
	var html = "<p>结算单打款拆分：</p>";
	html = html + settleOrderDetail(settleId);
	html = html + "<input type='hidden' name='id' value='" + settleId + "'>";
	html = html + "<input type='hidden' name='payStatus' value='1'>";
//	html = html + "<p>打款银行："+rcvSettleBankText+"</p>";
//	html = html + "<p>打款账户："+rcvBankAccount+"</p>";
//	html = html + "<p>打款日期："+formatterDate(new Date())+"</p>";
	$("#Modal").text("结算单打款拆分");
	$("#ModelContent").html(html);
	$("#modelForm").attr("action",payActionUrl);
	$('#settleModal').modal('show');
	$("#modelConfirm").bind("click",process);
	$("#modelCancel").one("click",function (){
		$('#settleModal').modal('hide');
		$('#modelConfirm').unbind("click",process);
	});
	function process(){
		$('#modelConfirm').unbind("click",process);
        $('#settleModal').modal('hide');
		Ajax(payActionUrl,{settleId:settleId},'打款拆分成功','打款拆分失败');
		return false;
	}
	return false;
}

function formatterDate(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	var hor = date.getHours();
	var min = date.getMinutes();
	var sec = date.getSeconds();
	return date.getFullYear() + '-' + month + '-' + day+" "+hor+":"+min+":"+sec;
};

function paySubmit(){
	var bankName = $.trim($("#bankNameInput").val());
	var bankAccount = $.trim($("#bankAccountInput").val());
	var val = true;
	if(bankName.length == 0){
		$("#bankNameError").text('打款银行不能为空');
		val = false;
		return val;
	}else{
		$("#bankNameError").text('');
	}
	if(bankAccount.length == 0){
		$("#bankAccountError").text('打款账户不能为空');
		val = false;
		return val;
	}else{
		$("#bankAccountError").text('');
	}
	document.getElementById("modelForm").submit();
}