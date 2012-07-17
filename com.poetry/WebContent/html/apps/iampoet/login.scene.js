function initScene() {
	var scene = this.getScene();
	var panel1 = new tau.ui.Panel({styles : {WebkitBoxAlign: 'center', WebkitBoxOrient: 'Vertical', WebkitBoxPack: 'center', display: '-webkit-box', height: '100%', position: '', width: '100%'}});
	scene.add(panel1);
	var panel2 = new tau.ui.Panel({styles : {WebkitBoxAlign: 'center', WebkitBoxOrient: 'Horizontal', WebkitBoxPack: 'center', display: '-webkit-box', height: '50px', position: '', width: '100%'}});
	panel1.add(panel2);
	var label1 = new tau.ui.Label({text : 'ID' , styles : {display: 'block', height: '34px', paddingTop: '16px', textAlign: 'center', width: '50px'}});
	panel2.add(label1);
	var textField1 = new tau.ui.TextField({id : 'id' , type : 'email' , styles : {display: '-webkit-box', height: '34px', width: '198px'}});
	panel2.add(textField1);
	var panel3 = new tau.ui.Panel({styles : {WebkitBoxAlign: 'center', WebkitBoxPack: 'center', display: '-webkit-box', height: '50px', position: '', width: '100%'}});
	panel1.add(panel3);
	var label2 = new tau.ui.Label({text : 'PW' , styles : {display: 'block', height: '34px', paddingTop: '16px', textAlign: 'center', width: '50px'}});
	panel3.add(label2);
	var textField2 = new tau.ui.TextField({id : 'pw' , type : 'password' , styles : {display: '-webkit-box', height: '34px', width: '198px'}});
	panel3.add(textField2);
	var panel4 = new tau.ui.Panel({styles : {WebkitBoxOrient: 'Horizontal', WebkitBoxPack: 'center', display: '-webkit-box', height: '40px', position: '', width: '100%'}});
	panel1.add(panel4);
	var checkbox1 = new tau.ui.Checkbox({id : 'remember' , value : 'true' , styles : {display: 'block', marginRight: '', paddingRight: ''}});
	panel4.add(checkbox1);
	var label3 = new tau.ui.Label({text : 'Remember me' , styles : {display: 'block', height: '24px', marginLeft: '', paddingLeft: '10px', paddingRight: '10px', width: '178px'}});
	panel4.add(label3);
	var panel5 = new tau.ui.Panel({styles : {WebkitBoxPack: 'center', display: '-webkit-box', height: '50px', position: '', width: '100%'}});
	panel1.add(panel5);
	var button1 = new tau.ui.Button({id : 'cancel' , styles : {display: 'block', marginRight: '10px'} , label : {normal: 'Cancel'}});
	panel5.add(button1);
	var button2 = new tau.ui.Button({id : 'login' , styles : {display: '-webkit-box', marginLeft: '10px'} , label : {normal: 'Login'}});
	panel5.add(button2);
}