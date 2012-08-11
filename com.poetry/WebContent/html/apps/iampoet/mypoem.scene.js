function initScene() {
	var scene = this.getScene();
	var button_write = new tau.ui.Button({
		id : 'write' , 
//		label : {normal: 'write'}
		styles : {
    		backgroundColor : 'transparent',
    		backgroundImage : 'url(/image/write.png)',
    		border : 'none',
	        backgroundPosition : 'center',
	        'background-size' : '100%',
            'box-shadow': '2px 2px 5px #888888',
            margin : '0px 10px 0px 0px'
		}

	});
	scene.add(button_write);
	button_write.onEvent('tap', this.handleWrite, this);

}