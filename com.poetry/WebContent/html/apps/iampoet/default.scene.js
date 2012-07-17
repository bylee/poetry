function initScene() {
	var scene = this.getScene();
	var carousel1 = new tau.ui.Carousel({id : 'todaypoetlist'});
	scene.add(carousel1);
	var textView1 = new tau.ui.TextView({styles : {display: 'inline'}});
	carousel1.add(textView1);
	var textView2 = new tau.ui.TextView({styles : {display: 'inline'}});
	carousel1.add(textView2);
	var textView3 = new tau.ui.TextView({styles : {display: 'inline'}});
	carousel1.add(textView3);
}