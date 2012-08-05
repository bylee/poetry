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
//		var writeBtn = scene.getComponent('write');
//		this.getNavigationBar().setRightItem(writeBtn);

		this.getClips();
		
	},
	
	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.WriteformController());
	},	
	
	getClips : function() {
		
		var scene = this.getScene();
		var clipsT = scene.getComponent('clipTable');

		var name = tau.util.getCookie('name');
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name + '/bookmark' ,
			callbackFn : function (resp) {
				if (resp.status === 200) {
					var clips = resp.data;
					for ( var clip in clips) {
						var Ttablecell = new tau.ui.TableCell({
							styles : {
						        WebkitBorderRadius : '7px',
								backgroundColor : 'transparent',
								height : '160px',
								width : '100%',
								borderStyle : 'solid',
						        borderWidth : '1px',
							}
						});
						
						var tmpPanel = new tau.ui.Panel({});
						
						var tmpUserInfoPanel = new tau.ui.Panel({
							styles : {
								height : '60px',
								width : '100%'
							}
						});
						var tmpUserIcon = new tau.ui.ImageView({
							
						});
						var tmpUserLabel = new tau.ui.Label();
						tmpUserLabel.setText('>>' + clips[clip].author.penName);
						tmpUserInfoPanel.add(tmpUserLabel);
						
						var tmpPoemInfoPanel = new tau.ui.Panel({
							styles : {
								height : '60px',
								width : '100%'
							}		
						});
						var tmpContent = new tau.ui.TextView({
							styles : {
								marginLeft : '20px', 
								fontSize : '30px'
							}
						});
						tmpContent.setText(clips[clip].contents);
						tmpPoemInfoPanel.add(tmpContent);
						tmpPanel.add(tmpUserInfoPanel);
						tmpPanel.add(tmpPoemInfoPanel);
						
						Ttablecell.setContentItem(tmpPanel);
						clipsT.add(Ttablecell);
					}
					clipsT.render();
				} else { 
					tau.alert("에러");
				};
			}
		});		
	}
});