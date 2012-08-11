function initScene() {
	var scene = this.getScene();

	var panel1 = new tau.ui.Panel({
		styles : {
			WebkitBoxAlign : 'center',
			WebkitBoxOrient : 'Vertical',
			WebkitBoxPack : 'center',
			display : '-webkit-box',
			height : '100%', 
			position : '',
			width : '100%'
		} 
	});
	scene.add(panel1);
	
	var iampoetImg = new tau.ui.ImageView({
		src : '/image/my-page-logo.png',
		styles : {
			width : '108px',
			height : '91px',
			marginLeft : '110px',
			marginRight : '120px',
			border : 'none'
		}
	})
	panel1.add(iampoetImg);

	
	var panel2 = new tau.ui.Panel({
		styles : {
			WebkitBoxAlign : 'center',
			WebkitBoxOrient : 'Horizontal',
			WebkitBoxPack : 'center',
			display : '-webkit-box',
			height : '50px',
			position : '',
            border : '1px solid rgb(102,102,102)',
            padding : '5px',
            'box-shadow': '2px 2px 5px #888888',
            width : '80%',
            margin : '10px auto 0px auto',
            '-webkit-border-radius' : '10px'
		}
	});
	panel1.add(panel2);
	var label1 = new tau.ui.Label({
		text : '아이디',
		styles : {
			display : 'block',
			height : '100%',
			paddingTop : '10px',
			paddingRight : '10px',
			textAlign : 'right',
			width : '80px',
			'font-size' : '20px',
			'font-style' : 'bold',
		}
	});
	panel2.add(label1);
	
	var textField1 = new tau.ui.TextField({
		id : 'id',
		type : tau.ui.TextField.TEXT,
		styles : {
//			display : '-webkit-box',
			height : '34px',
			width : '150px',
			paddingTop : '3px',
			border: '0 none' ,
			'-webkit-border-radius': '0px',
			'border-radius': '0px',
			'-webkit-box-shadow': 'none',
			'box-shadow': 'none',
//			paddingLeft : '10px',
		}
	});
	textField1.setPlaceholderLabel('아이디');
	
	panel2.add(textField1);
	var panel3 = new tau.ui.Panel({
		styles : {
			WebkitBoxAlign : 'center',
			WebkitBoxOrient : 'Horizontal',
			WebkitBoxPack : 'center',
			display : '-webkit-box',
			height : '50px',
			position : '',
            border : '1px solid rgb(102,102,102)',
            padding : '5px',
            'box-shadow': '2px 2px 5px #888888',
            width : '80%',
            margin : '10px auto 0px auto',
            '-webkit-border-radius' : '10px'
		}
	});
	panel1.add(panel3);
	var label2 = new tau.ui.Label({
		text : '비밀번호',
		styles : {
			display : 'block',
			height : '34px',
			paddingTop : '10px',
			paddingRight : '10px',
			textAlign : 'right',
			width : '80px',
			'font-size' : '20px'
		}
	});
	panel3.add(label2);
	var textField2 = new tau.ui.TextField({
		id : 'pw',
		type : 'password',
		styles : {
//			display : '-webkit-box',
			height : '34px',
			width : '150px',
//			paddingLeft : '10px',
			paddingTop : '3px',
			border: '0 none' ,
			'-webkit-border-radius': '0px',
			'border-radius': '0px',
			'-webkit-box-shadow': 'none',
			'box-shadow': 'none',
		}
	});
	textField2.setPlaceholderLabel('비밀번호');
	panel3.add(textField2);
	var panel4 = new tau.ui.Panel({
		styles : {
			WebkitBoxOrient : 'Horizontal',
			WebkitBoxPack : 'center',
			display : '-webkit-box',
			height : '40px',
			position : '',
			width : '100%'
		}
	});
	panel1.add(panel4);
//	var checkbox1 = new tau.ui.Checkbox({
//		id : 'remember',
//		styles : {
//			display : 'block',
//			marginRight : '',
//			paddingRight : ''
//		}
//	});
//	panel4.add(checkbox1);
//	var label3 = new tau.ui.Label({
//		text : 'Remember me',
//		styles : {
//			display : 'block',
//			height : '24px',
//			marginLeft : '',
//			paddingLeft : '10px',
//			paddingRight : '10px',
//			width : '178px'
//		}
//	});
//	panel4.add(label3);
	var panel5 = new tau.ui.Panel({
		styles : {
			WebkitBoxPack : 'center',
			display : '-webkit-box',
			height : '60px',
			position : '',
			width : '100%'
		}
	});
	panel1.add(panel5);
//	var button1 = new tau.ui.Button({
//		id : 'cancel',
//		styles : {
//			display : 'block',
//			marginRight : '10px'
//		},
//		label : {
//			normal : 'Cancel'
//		}
//	});
//	panel5.add(button1);
//	var button2 = new tau.ui.Button({
//		id : 'login',
//		styles : {
//			display : '-webkit-box',
//			WebkitBoxAlign : 'center',
//			WebkitBoxOrient : 'Horizontal',
//			WebkitBoxPack : 'center',
//			marginLeft : '10px'
//		},
//		label : {
//			normal : 'Login'
//		}
//	});
	
	var button2 = new tau.ui.Button({
		label : {
			normal : 'Log In',
		},
		id : 'login',
		styles : {
			width : '160px',
			height : '55px',
			marginLeft : '80px',
			marginRight : '80px',
//			border : 'none'
		    border : '1px solid rgb(102,102,102)',
            'box-shadow': '2px 2px 5px #888888',
			'font-size' : '40px',
			'font-family' : 'Monotype Corsiva',
			color : 'black',
		}
	});		
	panel5.add(button2);
}