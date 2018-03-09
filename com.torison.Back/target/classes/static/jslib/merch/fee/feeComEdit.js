//添加表单中的按钮功能的实现

//增加的行数
var tlen = 0;
var len = 0;

function appendSection(index){
	if(index == undefined || index == null){
		index = 0;
	}
	tlen = index + len;
	
	//新的一行
	var trNew = $("<tr class='trnew' id='itemtr"+tlen+"'>");
	//新行的第一个单元格
	var td01 = $("<td><input id='list"+tlen+"startNum'  type='text' name='list["+tlen+"].startNum' class='easyui-validatebox' data-options='required:true,validType:[\"length[1,10]\",\"positiveInt\"]' style='width:100px' autocomplete='off'> ~ <input id='list"+tlen+"endNum' type='text' name='list["+tlen+"].endNum' class='easyui-validatebox' data-options='required:true,validType:[\"length[1,10]\",\"positiveInt\"]' validType='numbercompare[\"#list"+tlen+"startNum\"]' style='width:100px' autocomplete='off'>元"
				+"</td>");
	//第二个单元格
	var td02 = $("<td>"+
					"<select id='list"+tlen+"feeForm' name='list["+tlen+"].feeForm' class='easyui-combobox'data-options='editable:false,panelHeight:60' style='width:80px'>"
						+"<option id='list"+tlen+"feeForm1' value='1'>百分比</option>"+
							"<option id='list"+tlen+"feeForm0' value='0'>固定</option>"+
				     "</select>"+
				"</td>");
	//第三个单元格
	var td03 = $("<td>"+
						"<input id='list"+tlen+"feeNum' name='list["+tlen+"].feeNum' type='text' class='easyui-validatebox' data-options='required:true,validType:[\"length[1,11]\",\"feenumber\"]'  style='width:80px' autocomplete='off'><span class='spanflag"+tlen+"' >%</span>"+
                "</td>");
	var td04 = $("<td>"+
			"<input id='list"+tlen+"minFee' name='list["+tlen+"].minFee' type='text' class='easyui-validatebox' data-options='required:true,validType:[\"length[1,7]\",\"minFeeNumber\"]'  style='width:80px' maxlength='7' autocomplete='off' value='0.00'>元"+
		"</td>");
	var td05 = $("<td>"+
			"<input id='list"+tlen+"agencyCommissionRate' name='list["+tlen+"].agencyCommissionRate' type='text' class='easyui-validatebox' data-options='validType:[\"length[0,7]\",\"agencyCommissionRate\"]'  style='width:80px' autocomplete='off'><span class='spanflag"+tlen+"' >%</span>"+
    "</td>");
	
	//第四个单元格
	var td06 = $("<td><input id='list"+tlen+"startTime'  name='list["+tlen+"].startTime'  type='text' class='easyui-validatebox' onClick='WdatePicker()' onfocus='WdatePicker({readOnly:true})' data-options='required:true' style='width:80px'></td>");
	//第五个单元格
	var td07 = $("<td><input id='list"+tlen+"endTime' name='list["+tlen+"].endTime'  type='text' class='easyui-validatebox' onClick='WdatePicker()' onfocus='WdatePicker({readOnly:true})' validType='after[\"#list"+tlen+"startTime\"]' data-options='required:true' style='width:80px'></td>");
	//第六个单元格
	var td08 = $("<td><a href='#' class='easyui-linkbutton' id='delSection"+tlen+"' data-options='iconCls:\"icon-cancel\"'>删除</a></td>");
	
	//追加
	trNew.append(td01);
	trNew.append(td02);
	trNew.append(td03);
	trNew.append(td04);
	trNew.append(td05);
	trNew.append(td06);
	trNew.append(td07);
	trNew.append(td08);
	
	var table = $("#itemtab");
	table.append(trNew);
	
	$.parser.parse(trNew);
	
	//为新加的删除按钮添加功能
	$("#delSection" + tlen).click(function(){
		var btnid = $(this).attr("id");
		$("tr[id^='itemtr']").each(function(i){
			//通过判断添加的尾号是否相同来判断是不是需要删除的同一行
			if(btnid.substr(10) == $(this).attr("id").substr(6)){
				if(i == 0){
					$.messager.alert("Tip","第一行收费方案明细不可以删除!","warnning");
				}else{
					document.getElementById("itemtab").deleteRow(i+1);
				}
			}
			
		});
		
		
	});
	
	
	$('#list'+tlen+'feeForm').combobox({
		onSelect:function(){
			var value = $(this).combobox("getValue");
			if(value == 0){
				$(".spanflag"+tlen).html("元");
			}else if(value == 1){
				$(".spanflag"+tlen).html("%");
			}
		}
	});
	
	len ++;
}

$(document).ready(function(){
	
	
	/*
		方案明细中删除按钮功能的实现  第一个单元的不可以删除,其他单元的可以删除
	*/
	$("a[id^='delSection']").click(function(){
		//获取点击按钮的id
		var btnid = $(this).attr("id");
		if(btnid == "delSection0"){
			parent.$.messager.alert("Tip","第一行收费方案明细不可以删除!","warnning");
		}else{
			var len = btnid.substr(10);
			$("#itemtr"+len).remove();
		}
		
	});
	
	
	//为每一个计费额度的下拉按钮添加事件
	$("select[id$='feeForm']").combobox({
		onSelect:function(){
			var value = $(this).combobox("getValue");
			var len = parseInt($(this).attr("id").substr(4));
			if(value == "0"){
				$("#spanflag"+len).html("元");
			}else if(value == "1"){
				$("#spanflag"+len).html("%");
			}
		}
	});
	
});


$.extend($.fn.validatebox.defaults.rules, {
	numbercompare : {
		validator : function(value, param) {
			return compareNumber(value,$(String(param[0])).val()) ;
		},
		message : '结束金额必须大于起始金额'
	}
});

$.extend($.fn.validatebox.defaults.rules, {
	minFeeNumber : {
		validator : function(value, param) {
			return /^\d{1,4}(\.\d{1,2})?$/g.test(value);
		},
		message : '最低手续费限制在0.00-9999.99以内!'
	}
});

//比较两个数字的大小
function compareNumber(num1, num2){
	
	if(Number(num1) - Number(num2) > 0){
		return true;
	}else{
		return false;
	}
}

