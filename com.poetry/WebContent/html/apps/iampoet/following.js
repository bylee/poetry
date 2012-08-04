$class('iampoet.FollowingController').extend(tau.ui.SceneController).define({
	FollowingController: function (opts){
		this.setTitle('Following');
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
	

	
});