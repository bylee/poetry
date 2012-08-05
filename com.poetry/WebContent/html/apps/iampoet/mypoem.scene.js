function initScene() {
	var scene = this.getScene();
	var button_write = new tau.ui.Button({id : 'write' , label : {normal: 'write'}});
	scene.add(button_write);
	button_write.onEvent('tap', this.handleWrite, this);


	var myPoemScrollPanel = new tau.ui.ScrollPanel({
		id : 'myPoemPanel',
	    styles : {
	        position : ''
	    },
	    hScroll: false
	});
	scene.add(myPoemScrollPanel);

	var table = new tau.ui.Table({
		id : 'poemTable'
	});
	myPoemScrollPanel.add(table);

	var iampoetImg = new tau.ui.ImageView({
		src : '/image/my-page-logo.png',
		styles : {
			width : '108px',
			height : '91px',
			marginLeft : '120px',
			marginRight : '120px',
			border : 'none'
		}
	})
	myPoemScrollPanel.add(iampoetImg);	
}