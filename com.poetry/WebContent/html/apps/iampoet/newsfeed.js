$class('iampoet.NewsFeedController').extend(tau.ui.SceneController).define({
	NewsFeedController: function (opts){
		this.setTitle("NewsFeed");
	},
 
	init: function (){
		
	},
	
	destroy: function (){
		
	},
	
	sceneLoaded: function (){
		var scene = this.getScene();
		var carousel = scene.getComponent('carousel');
		var panel1 = scene.getComponent('panel1');
		var panel2 = scene.getComponent('panel2');
		var panel3 = scene.getComponent('panel3');
		carousel.setComponents([panel1, panel2, panel3]);
		var writeBtn = scene.getComponent('write');
		this.getNavigationBar().setRightItem(writeBtn);
	},
	
	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.WriteformController({mission :'mypoetry'}));
	}
});