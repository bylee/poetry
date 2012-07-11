function initScene() {
	var scene = this.getScene();
	var scrollPanel1 = new tau.ui.ScrollPanel({styles : {backgroundColor: '#FFFFFF', backgroundImage: '-webkit-gradient(linear, left top, left bottom,from(#907373),color-stop(17%,#907373),to(#FFFFFF))'}});
	scene.add(scrollPanel1);
	var panel1 = new tau.ui.Panel({styles : {backgroundColor: '#FFFFFF', backgroundImage: '-webkit-gradient(linear, left top, right top,from(#000000),to(#FFFFFF))', height: '32px', width: '100%'}});
	scrollPanel1.add(panel1);
	var imageView1 = new tau.ui.ImageView({src : '/image/person.jpeg' , styles : {borderColor: '#CCCCCC', borderStyle: 'solid', borderWidth: '2px', display: 'inline', height: '32px', width: '32px'}});
	panel1.add(imageView1);
	var button1 = new tau.ui.Button({styles : {backgroundColor: 'transparent', backgroundImage: 'none', borderStyle: 'none'} , label : {normal: 'AJ'}});
	panel1.add(button1);
	var label1 = new tau.ui.Label({text : '2011.07' , styles : {bottom: '5px', fontSize: '11px', position: 'absolute', right: '5px'}});
	panel1.add(label1);
	var imageView2 = new tau.ui.ImageView({src : '/image/mainimage.jpeg' , styles : {backgroundImage: '-webkit-gradient(linear, left top, left bottom,from(#EEDCDC),to(#FFFFFF))', display: 'block', height: '100%', width: '320px'}});
	scrollPanel1.add(imageView2);
	var textView1 = new tau.ui.TextView({text : '\n  하늘은 늘 거기에 있네\n\n 소리 없이 열려 있네\n\n 구름떼에 뒤덮이고\n\n 눈비에 가리워도\n\n 늘 거기에 열려\n\n 마알갛게 웃고 있네\n\n 지금은 안개 자욱한\n\n 저 산봉우리 너머로\n\n ...\n\n ...999\n\n...999\n\n...999\n\n...999\n\n ' , styles : {color: '#FFFFFF', display: 'block', height: 'auto', position: 'absolute', top: '32px', zIndex: '10000'}});
	scrollPanel1.add(textView1);
	var panel2 = new tau.ui.Panel({styles : {WebkitBoxOrient: 'Horizontal', WebkitBoxPack: 'center', display: '-webkit-box', position: '', width: '100%'}});
	scrollPanel1.add(panel2);
	var button2 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', borderStyle: 'none', display: '-webkit-box'} , label : {normal: 'Good'}});
	panel2.add(button2);
	var button3 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', backgroundColor: '#FFFFFF', backgroundImage: "url('/image/addcomment.gif')", backgroundRepeat: 'no-repeat', borderStyle: 'none', color: 'transparent', display: '-webkit-box', width: '48px'}});
	panel2.add(button3);
	var panel3 = new tau.ui.Panel({styles : {WebkitBoxFlex: 4, backgroundColor: '#FFFFFF', width: '180px'}});
	panel2.add(panel3);
	var button4 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', borderStyle: 'none', display: '-webkit-box'} , label : {normal: 'G 27'}});
	panel2.add(button4);
	var button5 = new tau.ui.Button({styles : {WebkitBorderRadius: '0px', backgroundColor: '#FFFFFF', backgroundImage: "url('/image/comments.gif')", backgroundPosition: 'left center', backgroundSize: '50%', borderColor: 'transparent', borderStyle: 'none', color: '#516691', display: '-webkit-box', textAlign: 'right', width: '78px'} , label : {normal: '19'}});
	panel2.add(button5);
}