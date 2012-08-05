function initScene() {
	var scene = this.getScene();

	var myFollowerScrollPanel = new tau.ui.ScrollPanel({
		id : 'myFollowerPanel',
	    styles : {
	        position : ''
	    }
	});
	scene.add(myFollowerScrollPanel);

	var table = new tau.ui.Table({
		id : 'followerTable'
	});
	myFollowerScrollPanel.add(table);

}