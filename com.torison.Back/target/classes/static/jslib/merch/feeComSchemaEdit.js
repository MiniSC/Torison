$(document).ready(function(){

	//为计费项目添加改变事件  返回关联的计费方案
	$("select[id^='feeBelong']").combobox({
		onSelect:function(record){
			var index = $(this).attr("id").substr(9);
			$("#freeCode" + index).combobox("clear");
			$("#freeCode" + index).combobox({
				value:'',
				valueField: 'freeCode',    
		        textField: 'freeName',    
		        url: 'merch/fee/selectFeeComByFeeBelong.do?feeBelong=' + record.value,
			});
		}
	});
	
	$("a[id^='delbtn']").click(function(){
		var delbtnid = $(this).attr("id");
		$("#tr"+delbtnid.substr(6)).remove();
	});
	
	//记录增加的收费信息行数
	var tlen = 0;
	//得到现有商户的收费方案的条数,方便后来增加一行收费方案id的计算
	tlen += $("select[id^='feeBelong']").length;
	
	//增加收费信息的表格行数
	$("#addBtn").click(function(){
		//表格
		var feeTab = $("#feeComSchTable");
		//添加新的一行
		var trNew = $("<tr id='tr"+tlen+"'>");
		
		
		//第1个单元格  计费项目
		var td02Text = $("<td align='left'><span class='blank'>*</span>计费项目：</td>");
		var td02 = $("<td>");
		var td02Sel = $("<select id='feeBelong"+tlen+"' name='feeCompanys["+tlen+"].feeBelong' class='easyui-combobox'  style='width:150px' onchange='feeChange(this)' data-options='editable:false,panelHeight:200'><option value=''>---请选择---</option><option value='JS'>T1结算</option>"+
				"<option value='ZF'>支付</option><option value='TK'>退款</option><option value='QF'>清分</option><option value='TX'>提现</option><option value='FR'>分润手续费</option><option value='T0'>T0结算</option><option value='D0'>D0结算</option><option value='D1'>D1结算</option>" +"</select>");
		
		//中间空的单元格
		var tdkong = $("<td>&nbsp;&nbsp;&nbsp;</td>");
		
		//第2个单元格    计费方案
		var td03Text = $("<td align='left'><span class='blank'>*</span>计费方案：</td>");
		var td03 = $("<td>");
		var td03Sel = $("<select id='freeCode"+tlen+"' class='easyui-combobox'  name='feeCompanys["+ tlen +"].freeCode' class='easyui-combobox' data-options='delay:0' style='width:150px'>");
		
		//<td><a id="delbtn${vs.index }" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a></td>
		var td04 = $("<td><a id='delbtn"+tlen+"' href='javascript:void(0)' class='easyui-linkbutton' data-options='iconCls:\"icon-cancel\"'>删除</a></td>");
		
		
		td02.append(td02Sel);
		td03.append(td03Sel);
		trNew.append(td02Text);
		trNew.append(td02);
		trNew.append(tdkong);
		trNew.append(td03Text);
		trNew.append(td03);
		trNew.append(td04);
		feeTab.append(trNew);
		
		
		$.parser.parse("#feeBelong"+tlen);
		$("#freeCode" + tlen).combobox();
		$("#delbtn"+tlen).linkbutton();
		
		
		$("#feeBelong"+tlen).combobox({
			onSelect:function(record){
				var index = $(this).attr("id").substr(9);
				$("#freeCode" + index).combobox({
					valueField: 'freeCode',    
			        textField: 'freeName',    
			        url: 'merch/fee/selectFeeComByFeeBelong.do?feeBelong=' + record.value,
				});
			}
		});
		
		$("#delbtn"+tlen).click(function(){
			var delbtnid = $(this).attr("id");
			$("#tr"+delbtnid.substr(6)).remove();
		});
		
		tlen++;
	});
	
	
});


//根据计费类型查询计费方案 级联查询
function feeChange(record){
	adtlen = $(record).attr("id").substr(9);
	//发送ajax 请求获得企业收费方案
	$.ajax({
		type:'POST',
		async:false,
		url:'merch/fee/selectFeeComByFeeBelong.do',
		data:'feeBelong=' + record.value,
		dataType:"json",
		success:function(data){
			//先清空之前的下拉数据然后再添加
			$("#freeCode"+ adtlen).empty();
			//遍历所有的对象添加到级联的下拉计费方案中
			var optFeeCode = $("#freeCode"+ adtlen);
			for(var i=0; i<data.length; ++i){
				optFeeCode.append("<option value='"+data[i].freeCode+"'>"+data[i].freeName+"</option>");
			}
		}
	});
}

