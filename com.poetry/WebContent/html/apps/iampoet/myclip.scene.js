function initScene() {
	var scene = this.getScene();
//	var button_write = new tau.ui.Button({id : 'write' , label : {normal: 'write'}});
//	scene.add(button_write);
//	button_write.onEvent('tap', this.handleWrite, this);


	var myClipPoemScrollPanel = new tau.ui.ScrollPanel({
		id : 'myClipPoemPanel',
	    styles : {
	        position : ''
	    }
	});
	scene.add(myClipPoemScrollPanel);

	var myInfoPanel = new tau.ui.Panel({
		id : 'myInfoPanel',
		styles : {
			backgroundColor : 'transparent',
			//height : '32px',
			width : '100%'
		}
	});
	myClipPoemScrollPanel.add(myInfoPanel);

	
	var table = new tau.ui.Table({
		id : 'clipTable'
	});
	myClipPoemScrollPanel.add(table);

	var iampoetImg = new tau.ui.ImageView({
		src : '/image/my-page-get_20.png',
	})
	myClipPoemScrollPanel.add(iampoetImg);
}