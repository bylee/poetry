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
		
		this.getFollowings();
	},
	
	getFollowings : function() {
		
		var scene = this.getScene();
		var followingsT = scene.getComponent('followingTable');

		var name = tau.util.getCookie('name');
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name + '/following' ,
			callbackFn : function (resp) {	
				if (resp.status === 200) {
					var followings = resp.data;
					for ( var following in followings) {
						
						var Ttablecell = new tau.ui.TableCell({
							styles : {
						        WebkitBorderRadius : '7px',
								backgroundColor : 'transparent',
								height : '80px',
								width : '100%',
								borderStyle : 'solid',
						        borderWidth : '1px',
							}
						});
						var tmpUserInfoPanel = new tau.ui.Panel({
							styles : {
								height : '60px',
								width : '100%'
							}
						});						
						
						var tmpUserLabel = new tau.ui.Label();
						
						var lvl = followings[following].theNumberOfBookmarks + followings[following].theNumberOfFollowings + followings[following].theNumberOfFollowers + followings[following].theNumberOfPoetries; 
						if (lvl < 100) {
							lvl = '초보시인';
						} else if (lvl < 300){
							lvl = '중수시인';
						} else {
							lvl = '시인';
						}
						tmpUserLabel.setText('>>' + followings[following].penName +', ' + followings[following].icon +', ' + lvl );
						tmpUserInfoPanel.add(tmpUserLabel);
						
						Ttablecell.setContentItem(tmpUserInfoPanel);
						followingsT.add(Ttablecell);
					}
					followingsT.render();
				}
			}
		});
	}
	
});