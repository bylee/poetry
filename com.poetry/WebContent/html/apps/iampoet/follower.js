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
		
		this.getFollowers();
	},
	

	getFollowers : function() {
		
		var scene = this.getScene();
		var followersT = scene.getComponent('followerTable');

		var name = tau.util.getCookie('name');
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name + '/follower' ,
			callbackFn : function (resp) {	
				if (resp.status === 200) {
					var followers = resp.data;
					for ( var follower in followers) {
						
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
						
						var lvl = followers[follower].theNumberOfBookmarks + followers[follower].theNumberOfFollowings + followers[follower].theNumberOfFollowers + followers[follower].theNumberOfPoetries; 
						if (lvl < 100) {
							lvl = '초보시인';
						} else if (lvl < 300){
							lvl = '중수시인';
						} else {
							lvl = '시인';
						}
						tmpUserLabel.setText('>>' + followers[follower].penName +', ' + followers[follower].icon +', ' + lvl );
						tmpUserInfoPanel.add(tmpUserLabel);
						
						Ttablecell.setContentItem(tmpUserInfoPanel);
						followersT.add(Ttablecell);
					}
					followersT.render();
				}
			}
		});
	}
});