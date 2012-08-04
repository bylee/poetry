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
		
		for (var i=1; i<4 ; i++) {
			var pPoem = scene.getComponent('pPoem'+i);
			pPoem.onEvent(
					tau.rt.Event.TOUCHMOVE,
					this.handleScrollStopPropagation,
					this);
		}
		
	},
	
	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.WriteformController({mission : 'mission'}));
	},
	
	handleScrollStopPropagation: function (event){
		event.stopPropagation();
	}
});