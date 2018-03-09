/**
 * wangyj 2014-12-04
 */
(function(jQuery) {
	jQuery.fn.flowImg = function(options) {
		var x = 10, y = 20;
		var init = {
			box : false
		};
		var opt = jQuery.extend(init, options);
		if (this.length == 0)
			return;
		var i_w=0,i_h;
		jQuery(this).bind("mousemove", function(ev) {
			i_w=jQuery(this).width(),i_h=jQuery(this).height();
			jQuery("img.img_flow_d").remove();
			var img = document.createElement('img');
			img.src = this.src;
			img.className = "img_flow_d";
			var jQueryimg = jQuery(img);
			jQuery("body").prepend(jQueryimg);
			jQueryimg.css({
				"position" : "absolute",
				"z-index" : "10001",
				"left" : ev.pageX + x + "px",
				//"top" : ev.pageY + y + "px",
				"float" : "left"
			});
			if (opt.box != false) {
				var dx = jQueryimg.width() - jQueryimg.height();
				var max = dx > 0 ? jQueryimg.width() : jQueryimg.height();
				if (max > opt.box) {
					if (dx >= 0)
						jQueryimg.css("width", opt.box + "px");
					else
						jQueryimg.css("height", opt.box + "px");
				}
				if(jQuery(window).width()-(ev.pageX+x)-i_w-jQueryimg.width()<0){
						jQueryimg.css({
						"left" : ev.pageX - x -jQueryimg.width()+ "px"
					});
				}
				$("#pagex").val(ev.pageX);
				$("#pagey").val(ev.pageY);
				$("#show").val($(window).height()-(ev.pageY-y)-jQueryimg.height()-i_h);
				if($(window).height()-(ev.pageY-y)-jQueryimg.height()<0){
					jQueryimg.css({
							"top" : ev.pageY-y -jQueryimg.height()+ "px"
					});
				}else{
					jQueryimg.css({
							"top" : ev.pageY + y + "px"
					});
				}
			}else{
				if(jQuery(window).width()-(ev.pageX+x)-i_w-jQueryimg.width()<0){
						jQueryimg.css({
						"left" : ev.pageX - x -jQueryimg.width()+ "px"
					});
				}
			}
		}).bind("mouseout", function(ev) {
			jQuery("img.img_flow_d").remove();
		});
	}
})(jQuery);