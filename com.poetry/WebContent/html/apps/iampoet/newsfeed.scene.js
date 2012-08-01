function initScene() {
	var scene = this.getScene();
scene.setStyles({backgroundColor:'transparent',backgroundImage:'none'});
	var scrollPanel1 = new tau.ui.ScrollPanel({id : 'panel1'});
	scene.add(scrollPanel1);
	var imageView1 = new tau.ui.ImageView({id : 'imagePanel' , src : '/image/image2-1.jpeg' , styles : {height: '403px', width: '320px'}});
	scrollPanel1.add(imageView1);
	var textView1 = new tau.ui.TextView({text : '\n\n\n\n\n 달 하나와 나' , styles : {backgroundColor: 'transparent', backgroundImage: 'none', color: '#FFFFFF', display: 'block', fontSize: '17px', left: '0px', position: 'absolute', top: '0px'}});
	scrollPanel1.add(textView1);
	var panel1 = new tau.ui.Panel({styles : {WebkitBoxOrient: 'Horizontal', WebkitBoxPack: 'center', display: '-webkit-box', height: '32px', position: '', width: '100%'}});
	scrollPanel1.add(panel1);
	var button1 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', borderStyle: 'none', display: '-webkit-box'} , label : {normal: 'Good'}});
	panel1.add(button1);
	var button2 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', backgroundColor: '#FFFFFF', backgroundImage: "url('/image/addcomment.gif')", backgroundRepeat: 'no-repeat', borderStyle: 'none', color: 'transparent', display: '-webkit-box', width: '48px'}});
	panel1.add(button2);
	var panel2 = new tau.ui.Panel({styles : {WebkitBoxFlex: 4, backgroundColor: '#FFFFFF', width: '180px'}});
	panel1.add(panel2);
	var button3 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', borderStyle: 'none', display: '-webkit-box'} , label : {normal: 'G 27'}});
	panel1.add(button3);
	var button4 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', backgroundColor: '#FFFFFF', backgroundImage: "url('/image/comments.gif')", backgroundPosition: 'left center', backgroundSize: '50%', borderColor: 'transparent', borderStyle: 'none', color: '#516691', display: '-webkit-box', textAlign: 'right', width: '78px'} , label : {normal: '19'}});
	panel1.add(button4);
	var scrollPanel2 = new tau.ui.ScrollPanel({id : 'panel2' , styles : {WebkitBoxOrient: 'Horizontal'}});
	scene.add(scrollPanel2);
	var imageView2 = new tau.ui.ImageView({id : 'imagePanel' , src : '/image/image2-2.jpeg' , styles : {height: '500px', width: '320px'}});
	scrollPanel2.add(imageView2);
	var textView2 = new tau.ui.TextView({text : '\n\n 옹기 종기 모여 있는 \n 우리는 \n 사무실에서도 옹기종기 \n\n 산과 들엔 누가?' , styles : {backgroundColor: 'transparent', backgroundImage: 'none', color: '#FFFFFF', display: 'block', fontSize: '18px', left: '0px', position: 'absolute', top: '0px'}});
	scrollPanel2.add(textView2);
	var panel3 = new tau.ui.Panel({styles : {WebkitBoxOrient: 'Horizontal', WebkitBoxPack: 'center', display: '-webkit-box', height: '32px', position: '', width: '100%'}});
	scrollPanel2.add(panel3);
	var button5 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', borderStyle: 'none', display: '-webkit-box'} , label : {normal: 'Good'}});
	panel3.add(button5);
	var button6 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', backgroundColor: '#FFFFFF', backgroundImage: "url('/image/addcomment.gif')", backgroundRepeat: 'no-repeat', borderStyle: 'none', color: 'transparent', display: '-webkit-box', width: '48px'}});
	panel3.add(button6);
	var panel4 = new tau.ui.Panel({styles : {WebkitBoxFlex: 4, backgroundColor: '#FFFFFF', width: '180px'}});
	panel3.add(panel4);
	var button7 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', borderStyle: 'none', display: '-webkit-box'} , label : {normal: 'G 27'}});
	panel3.add(button7);
	var button8 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', backgroundColor: '#FFFFFF', backgroundImage: "url('/image/comments.gif')", backgroundPosition: 'left center', backgroundSize: '50%', borderColor: 'transparent', borderStyle: 'none', color: '#516691', display: '-webkit-box', textAlign: 'right', width: '78px'} , label : {normal: '19'}});
	panel3.add(button8);
	var scrollPanel3 = new tau.ui.ScrollPanel({id : 'panel3' , styles : {height: 'auto'}});
	scene.add(scrollPanel3);
	var imageView3 = new tau.ui.ImageView({id : 'imagePanel' , src : '/image/image3-1.jpeg' , styles : {width: '320px'}});
	scrollPanel3.add(imageView3);
	var textView3 = new tau.ui.TextView({text : '\n\n 여름엔 겨울이 그립고 \n 겨울엔 여름이 그립고 \n 외로운 남자는 여자가 그립고 \n 여자는 핸드백이 그립고 (농담임) ' , styles : {backgroundColor: 'transparent', backgroundImage: 'none', color: '#FFFFFF', display: 'block', fontFamily: '"Tahoma","Verdana","Arial","Sans-Serif"', fontSize: '17px', left: '0px', position: 'absolute', top: '0px'}});
	scrollPanel3.add(textView3);
	var panel5 = new tau.ui.Panel({styles : {WebkitBoxOrient: 'Horizontal', WebkitBoxPack: 'center', display: '-webkit-box', height: '32px', position: '', width: '100%'}});
	scrollPanel3.add(panel5);
	var button9 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', borderStyle: 'none', display: '-webkit-box'} , label : {normal: 'Good'}});
	panel5.add(button9);
	var button10 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', backgroundColor: '#FFFFFF', backgroundImage: "url('/image/addcomment.gif')", backgroundRepeat: 'no-repeat', borderStyle: 'none', color: 'transparent', display: '-webkit-box', width: '48px'}});
	panel5.add(button10);
	var panel6 = new tau.ui.Panel({styles : {WebkitBoxFlex: 4, backgroundColor: '#FFFFFF', width: '180px'}});
	panel5.add(panel6);
	var button11 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', borderStyle: 'none', display: '-webkit-box'} , label : {normal: 'G 27'}});
	panel5.add(button11);
	var button12 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', backgroundColor: '#FFFFFF', backgroundImage: "url('/image/comments.gif')", backgroundPosition: 'left center', backgroundSize: '50%', borderColor: 'transparent', borderStyle: 'none', color: '#516691', display: '-webkit-box', textAlign: 'right', width: '78px'} , label : {normal: '19'}});
	panel5.add(button12);
	var carousel1 = new tau.ui.Carousel({id : 'carousel'});
	scene.add(carousel1);
	var button13 = new tau.ui.Button({id : 'write' , label : {normal: 'write'}});
	scene.add(button13);
	button13.onEvent('tap', this.handleWrite, this);
}