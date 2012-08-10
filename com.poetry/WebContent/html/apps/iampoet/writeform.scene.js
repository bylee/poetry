function initScene() {
	var scene = this.getScene();
	var image = new tau.ui.ImageView({
		src: '',
		styles: {
			top : '0px',
			left : '0px',
			position : 'absolute',
			'z-index' : '0',
			height : '100%'
		},
		id : 'mainImage'
	});
	scene.add(image);
	
	var scrollPanel1 = new tau.ui.ScrollPanel({
		id : 'mainPanel'
	});
	scene.add(scrollPanel1);
	var panel1 = new tau.ui.Panel({
		styles : {
			height : '11px',
			width : '100%'
		}
	});
	scrollPanel1.add(panel1);
	
	var textField1 = new tau.ui.TextField({
		id : 'title',
		styles : {
			height : '30px',
			width :'212px',
			'background-color' : 'transparent'
		},
		placeholderLabel : '시 제목을 입력해주세요~'
	});
	scrollPanel1.add(textField1);
	var button1 = new tau.ui.Button({
		label : {
		},
		id : 'pickImg',
		styles : {
			width : '50px',
			background : 'url(/image/camera.png) no-repeat center transparent',
			'background-size' : '32px',
			border : '0px'
		}
	});
	button1.onEvent('tap', this.handlePickImage, this);
	scrollPanel1.add(button1);
	var button2 = new tau.ui.Button({
		label : {
		},
		id : 'layout',
		styles : {
			width : '50px',
			background : 'url(/image/styling.png) no-repeat center transparent',
			'background-size' : '32px',
			border : '0px'
		}
	});
	button2.onEvent('tap', this.handleLayout, this);
	scrollPanel1.add(button2);
	var textArea1 = new tau.ui.TextArea({
		id : 'contents',
		styles : {
			height : '255px',
			marginBottom : '20px',
			paddingBottom : '7px',
			'background-color' : 'transparent',
			'margin-top' : '10px'
				
		},
		placeholderLabel : '시를 입력해주세요~'
	});
	scrollPanel1.add(textArea1);
	var panel2 = new tau.ui.Panel({
		styles : {
			WebkitBoxPack : 'center',
			display : '-webkit-box',
			height : '32px',
			marginBottom : '20px',
			position : '',
			width : '100%'
		}
	});
	scrollPanel1.add(panel2);
}