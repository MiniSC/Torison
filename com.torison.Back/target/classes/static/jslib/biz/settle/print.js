
$(window).ready(function (){    
	//MyPreview();
	doPrint();	
});
var LODOP;
function doPrint(){
	LODOP=getLodop();  
	LODOP.PRINT_INIT("付款通知书");
	//LODOP.SET_PRINT_PAGESIZE(2,3000,2200,"A4");
	LODOP.SET_PRINT_PAGESIZE(2,0,0,"A4");
	CreateAllPages();
	LODOP.PREVIEW();
	//LODOP.PRINT();
	/*LODOP=getLodop();  
	LODOP.PRINT_INIT("付款通知书");
	LODOP.SET_PRINT_PAGESIZE(1,500,508,"");	
	CreateAllPages();
	LODOP.PREVIEW();*/
}

function CreateAllPages(){
	var styleHtml="<style>"+$("#printStyle").html()+"</style>";	
	$.each($(".printArea"),function (){
		LODOP.NewPage();		
		var bodyHtml = $(this).html();
		var htmlContent= "<body>" + styleHtml + bodyHtml + "</body>";
		LODOP.ADD_PRINT_HTM("0%","0%","100%","100%",htmlContent);		
	});
};