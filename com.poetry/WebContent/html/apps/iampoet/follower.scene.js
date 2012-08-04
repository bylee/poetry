function initScene() {
	var scene = this.getScene();


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