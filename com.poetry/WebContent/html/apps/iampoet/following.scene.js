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
		id : 'followingTable',
		styles : {
            backgroundColor : '#FFFFFF',
            border : '1px solid rgb(102,102,102)',
            padding : '5px',
            'box-shadow': '2px 2px 5px #888888',
            width : '97%',
            height : '100%',
            margin : '5px 5px 5px 5px',
            '-webkit-border-radius' : '7px',
		}		
	});
	myFollowingScrollPanel.add(table);

}