
$class('iampoet.DefaultController').extend(tau.ui.SceneController).define(
{
	DefaultController: function(opts) {
		this.setTitle('오늘의 시');
	},
	
	init: function (){
		
	},
	
	sceneDrawn: function () {
		console.log("here");
		var scene = this.getScene();
		var list = scene.getComponent("todaypoetlist");
		//list.add()
		console.log(list);
	},
  
	destroy: function (){
		
	}
});