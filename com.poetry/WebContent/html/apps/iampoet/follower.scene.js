function initScene() {
	var scene = this.getScene();

	var myFollowerScrollPanel = new tau.ui.ScrollPanel({
		id : 'myFollowerPanel',
	    styles : {
	        position : ''
	    },
	    hScroll: false
	});
	scene.add(myFollowerScrollPanel);

	var table = new tau.ui.Table({
		id : 'followerTable',
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
	myFollowerScrollPanel.add(table);

}