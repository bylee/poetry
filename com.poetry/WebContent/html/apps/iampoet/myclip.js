$class('iampoet.MyClipController').extend(tau.ui.SceneController).define({
	MyClipController: function (opts){
		this.setTitle('내가 가져온 시');
	},
 
	init: function () {
		
	},
	
	destroy: function () {
		
	},
	
	sceneLoaded: function () {
		var scene = this.getScene();
		var writeBtn = scene.getComponent('write');
		this.getNavigationBar().setRightItem(writeBtn);
		
	},
	
	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.WriteformController());
	},	
});