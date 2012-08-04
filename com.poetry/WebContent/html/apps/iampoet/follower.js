$class('iampoet.FollowerController').extend(tau.ui.SceneController).define({
	FollowerController: function (opts){
		this.setTitle('Follower');
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