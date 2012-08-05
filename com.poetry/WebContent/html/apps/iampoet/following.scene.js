function initScene() {
	var scene = this.getScene();
	
	var myFollowingScrollPanel = new tau.ui.ScrollPanel({
		id : 'myFollowingPanel',
	    styles : {
	        position : ''
	    },
	    hScroll: false
	});
	scene.add(myFollowingScrollPanel);

	var table = new tau.ui.Table({
		id : 'followingTable'
	});
	myFollowingScrollPanel.add(table);

}