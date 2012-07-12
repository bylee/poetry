$require('/writeform.js');
$class('iampoet.TodayMissionController').extend(tau.ui.SceneController).define({
	TodayMissionController: function (opts){
		this.setTitle('TodayMission');
	},
 
	init: function (){
		
	},
	
	destroy: function (){
		
	},
	
	sceneLoaded: function () {
		var scene = this.getScene();
		var missionPanel = scene.getComponent('missionPanel');
		var missionImage = scene.getComponent('missionImage');
		var missionImageInfo = scene.getComponent('missionImageInfo');
		missionPanel.setComponents([missionImage,missionImageInfo]);
		var writeBtn = scene.getComponent('write');
		this.getNavigationBar().setRightItem(writeBtn);
	},
	
	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.WriteformController());
	}
});