function initScene() {
	var scene = this.getScene();
	var button_write = new tau.ui.Button({
		id : 'write' , 
//		label : {normal: 'write'}
		styles : {
    		backgroundColor : 'transparent',
    		backgroundImage : 'url(/image/write.png)',
            border : '1px solid rgb(102,102,102)',
	        backgroundPosition : 'center',
	        'background-size' : '100%',
            'box-shadow': '2px 2px 5px #888888',    		
		}
	});
	scene.add(button_write);
	button_write.onEvent('tap', this.handleWrite, this);

}