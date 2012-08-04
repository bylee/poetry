$class('iampoet.MyPoemController').extend(tau.ui.SceneController).define({
	MyPoemController: function (opts){
		this.setTitle('내가 쓴 시');
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