<#macro header title="默认文字">
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../static/jslib/extBrowser.js" charset="utf-8"></script>

<!-- 引入my97日期时间控件 -->
<script type="text/javascript"
		src="../static/jslib/My97DatePicker4.8b3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- 引入jQuery -->
<script src="../static/jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>


<!-- 引入EasyUI -->
<link id="easyuiTheme" rel="stylesheet"
	  href="../static/jslib/jquery-easyui-1.3.3/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="../static/jslib/jquery-easyui-1.3.3/themes/icon.css"
	  type="text/css">
<script type="text/javascript" src="../static/jslib/jquery-easyui-1.3.3/jquery.easyui.min.js"
		charset="utf-8"></script>
<script type="text/javascript"
		src="../static/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- 修复EasyUI1.3.3中layout组件的BUG
<script type="text/javascript" src="../static/jslib/jquery-easyui-1.3.3/plugins/jquery.layout.js" charset="utf-8"></script>
-->
<!-- 扩展EasyUI -->
<script type="text/javascript" src="../static/jslib/extEasyUI.js"
		charset="utf-8"></script>

<!-- 扩展EasyUI Icon -->
<link rel="stylesheet" href="../static/style/extIcon.css" type="text/css">

<!-- 扩展jQuery -->
<script type="text/javascript" src="../static/jslib/extJquery.js" charset="utf-8"></script>
<style>
	.ftb {
		width: 98%;
		font-size: 18px
	}

	.ftbs {
		width: 98%;
		font-size: 12px
	}

	.ftbs tr th,.ftb tr th {
		width: 18%;
		background: #f6f6f6;
		border-bottom: #efefef 1px solid;
		border-right: #efefef 1px solid;
		color: #0466b2;
		height: 36px;
		line-height: 36px;
		text-align: right;
		padding-left: 15px;
	}

	.ftbs tr td,.ftb tr td {
		width: 32%;
		border-bottom: #efefef 1px solid;
		border-right: #efefef 1px solid;
		color: #666;
		text-align: left;
		padding-left: 10px
	}

	.datagrid-body a {
		color: #379BE9;
		outline: medium none;
		text-decoration: none;
	}

	.ftbfix {
		width: 98%;
		font-size: 18px
	}

	.ftbfix tr th {
		width: 100px;
		background: #f6f6f6;
		border-bottom: #efefef 1px solid;
		border-right: #efefef 1px solid;
		color: #0466b2;
		height: 36px;
		line-height: 36px;
		text-align: right
	}

	.ftbfix tr td {
		width: 150px;
		border-bottom: #efefef 1px solid;
		border-right: #efefef 1px solid;
		color: #666;
		text-align: left;
		padding-left: 10px
	}

	.table-condensed th,.table-condensed td {
		padding: 1px 3px;
	}

	.requireNote {
		color: red;
		padding-right: 3px;
	}

	#searchForm {
		padding-top: 8px;
		border: none;
	}

	#searchForm table tr td {
		border: none;
	}

	#searchForm table td {
		padding-left: 5px;
	}

	#searchForm table td input {
		margin-bottom: 0px;
	}

	#searchForm table td select {
		margin-bottom: 0px;
	}

	#searchForm .searchText {
		height: 32px;
	}

	.smallPicList {
		float: left;
		margin: 5px 10px;
		heigth: auto;
		width: 100px;
		overflow: hidden;
	}

	.smallPicList div {
		margin-bottom: 2px;
		overflow: hidden;
		heigth: 100px;
		text-align: center
	}

	pre {
		white-space: pre-wrap;
		white-space: -moz-pre-wrap;
		white-space: -pre-wrap;
		white-space: -o-pre-wrap;
		word-wrap: break-word;
	}
	.datagrid-view1-2{
		position: relative;
		float: right;
		right: auto;
		left:auto;
	}
	.datagrid-view2-2{
		right:auto;
		left: 0px;
	}
	.datagrid-view1-2 .datagrid-header td, .datagrid-view1-2 .datagrid-body td, .datagrid-view1-2 .datagrid-footer td {
	    border-width: 0 1px 1px 1px;
	    border-style: dotted;
	    margin: 0;
	    padding: 0;
	}
</style>


	

 <#nested>  
</head>
<body>
</#macro>  

<#macro footer>  

<#nested>  
</body>
</html>
</#macro>  