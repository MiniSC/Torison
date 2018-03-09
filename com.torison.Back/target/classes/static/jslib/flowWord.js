(function(jQuery) {
	$.fn.flowWord = function(options) {
		var x = 10, y = 20;
		var init = {
			box : false
		};
		var opt = jQuery.extend(init, options);
		$(this).bind("mousemove",function(e){
			$("._flow_word").remove();
			var $str=$('<div class="_flow_word" style="padding:1px 1px;border:1px solid #aaa;background:#efefef;"><div style="padding:5px 10px;background:#fff;border:1px solid #ddd;">'+this.innerHTML+'</div></div>');
			$("body").prepend($str);
			$str.css({
				"position" : "absolute",
				"z-index" : "10001",
				"left" : e.pageX + x + "px",
				"top" : e.pageY + y + "px",
				"float" : "left",
				"font-size":"12pt"
			});
		}).bind("mouseout", function(ev) {
			$("._flow_word").remove();
		});
	}
})(jQuery);