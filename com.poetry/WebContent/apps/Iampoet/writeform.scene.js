function initScene() {
	var scene = this.getScene();
	var scrollPanel1 = new tau.ui.ScrollPanel();
	scene.add(scrollPanel1);
	var panel1 = new tau.ui.Panel({styles : {height: '11px', width: '100%'}});
	scrollPanel1.add(panel1);
	var label1 = new tau.ui.Label({text : 'Title' , styles : {height: '33px', paddingTop: '10px', textAlign: 'center', width: '51px'}});
	scrollPanel1.add(label1);
	var textField1 = new tau.ui.TextField({styles : {height: '30px', width: '212px'}});
	scrollPanel1.add(textField1);
	var button1 = new tau.ui.Button({label : {normal: 'Img'} , styles : {width: '56px'}});
	scrollPanel1.add(button1);
	var label2 = new tau.ui.Label({text : 'Contents' , styles : {display: 'block', height: '30px', paddingLeft: '10px', paddingTop: '10px'}});
	scrollPanel1.add(label2);
	var textArea1 = new tau.ui.TextArea({styles : {height: '255px', marginBottom: '20px', paddingBottom: '7px'}});
	scrollPanel1.add(textArea1);
	var panel2 = new tau.ui.Panel({styles : {WebkitBoxPack: 'center', display: '-webkit-box', height: '32px', marginBottom: '20px', position: '', width: '100%'}});
	scrollPanel1.add(panel2);
	var button2 = new tau.ui.Button({styles : {display: '-webkit-box'} , label : {normal: 'Cancel'}});
	panel2.add(button2);
	var button3 = new tau.ui.Button({styles : {display: '-webkit-box'} , label : {normal: 'Temp Save'}});
	panel2.add(button3);
	var button4 = new tau.ui.Button({styles : {display: '-webkit-box'} , label : {normal: 'Share'}});
	panel2.add(button4);
}