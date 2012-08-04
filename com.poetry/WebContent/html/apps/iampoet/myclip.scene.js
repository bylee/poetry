function initScene() {
	var scene = this.getScene();
	var button_write = new tau.ui.Button({id : 'write' , label : {normal: 'write'}});
	scene.add(button_write);
	button_write.onEvent('tap', this.handleWrite, this);


	var myPoemScrollPanel = new tau.ui.ScrollPanel({
		id : 'myPoemPanel',
	    styles : {
	        position : ''
	    }
	});
	scene.add(myPoemScrollPanel);

	var myInfoPanel = new tau.ui.Panel({
		id : 'myInfoPanel',
		styles : {
			backgroundColor : 'transparent',
			//height : '32px',
			width : '100%'
		}
	});
	myPoemScrollPanel.add(myInfoPanel);
			
}