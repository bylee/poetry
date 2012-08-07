$class('iampoet.FollowingController').extend(tau.ui.SceneController).define({
	FollowingController: function (opts){
		this.setTitle('Following');
		this.curr_name = opts.name;
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
		var that = this;
		var scene = this.getScene();
		var followingsT = scene.getComponent('followingTable');

		var name = tau.util.getCookie('name');
		if (this.curr_name) {
			name = this.curr_name;
		}		
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name + '/following' ,
			callbackFn : function (resp) {	
				if (resp.status === 200) {
					var followings = resp.data;
					for ( var following in followings) {

						var imageSrc = '/image/icon-person.png';
						var user = followings[following];
						if ((user.icon != null) && (user.icon !== 'null')) {
							imageSrc = rootURL + '/binary/' + user.icon;
				        }						
						var Ttablecell = new tau.ui.TableCell({
							styles : {
								height : '60px',
					            margin : '5px 0px 5px 0px'
							}
						});
						
						var userPanel = new tau.ui.Panel({
							styles : {
//					            backgroundColor : '#FFFFFF',
//					            border : '1px solid rgb(102,102,102)',
//					            padding : '5px',
//					            'box-shadow': '2px 2px 5px #888888',
					            width : '100%',
					            height : '100%',
//					            margin : '20px auto 0px auto',
//					            '-webkit-border-radius' : '7px'
							}							
						});
						
					    var imagePanel = new tau.ui.Panel({
					        styles : {
						        width : '52px',
						        height : '52px',
						        overflow : 'hidden',
						        display : 'inline-box',
						        backgroundImage : 'url('+ imageSrc + ')',
						        backgroundPosition : 'center',
						        backgroundSize : '52px',
						        '-webkit-border-radius' : '10px',
						        border : '2px solid white',
						        overflow : 'hidden'					            
					        }
					    });
					    userPanel.add(imagePanel);

					    var namePanel = new tau.ui.Panel({
							styles: {
							    display : 'inline-block'
							}
					    });
					    userPanel.add(namePanel);
					    var penName = new tau.ui.Button({
					    	styles : {
					    		backgroundColor : 'transparent',
					    		backgroundImage : 'none',
					    		borderStyle : 'none',
					    		width: '180px',
					    		'font-family' : 'YDIYGO330',
					    		color : '#414143',
					    		'text-align': 'left',
					    		display : 'block',
					    		height : '30px',
					    		paddingLeft : '15px'
					    	},
					    	label : {
					    		normal : user.penName
					    	}
					    });
					    namePanel.add(penName);
					    penName.username = user.username;
					    penName.onEvent('tap', that.gotoMyPage, that);

					    var levelName = new tau.ui.Label({
					    	text : calcLevel(user),
					    	styles : {
					    		display : 'block',
					    		fontSize : '15px',
					    		'font-family' : 'YDIYGO310',
					    		color : '#acacac',
					    		paddingLeft : '15px'
					    	}
					    });
					    namePanel.add(levelName);	
					    
					    var addRemoveButton = new tau.ui.Button({
					    	styles : {
					    		backgroundColor : 'transparent',
					    		backgroundImage : 'url(/image/my-page-remove.png)',
					    		borderStyle : 'none',
					    		width: '78px',
					    		'text-align': 'left',
					    		display : 'block',
					    		height : '39px',
					    		paddingLeft : '5px',
					    		display : 'inline',
					    		position : 'absolute',
					    		right : '5px',
					    		'background-size' : '100%',
					    		margin : '5px 0px 0px 0px',
					    	},
					    	flag : 0,
					    	poetId : user.username
					    });
					    addRemoveButton.poetId=user.username;
					    userPanel.add(addRemoveButton);
					    addRemoveButton.onEvent('tap', that.followingAction, that);
					    
					    Ttablecell.setContentItem(userPanel);												
						followingsT.add(Ttablecell);
					}
					followingsT.render();
				}
			}
		});
	},
	
	gotoMyPage : function(event) {
		var comp = event.getSource();
//		tau.alert(comp + ' : move : ' + comp.username);
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.MyController({name:comp.username}));			
	},
	
	followingAction : function(event) {
		var comp = event.getSource();
		if (comp.flag == 0) {
			tau.wreq({
				type: 'DELETE',
				url: '/following/' + comp.poetId ,
				callbackFn : function (resp) {	
					if (resp.status === 200) {
						comp.flag = 1;
						comp.setStyle('backgroundImage', 'url(/image/my-page-add.png)');						
					}
				}
			});
		} else {
			tau.wreq({
				type: 'POST',
				url: '/following/' + comp.poetId ,
				callbackFn : function (resp) {	
					if (resp.status === 200) {
						comp.flag = 0;
						comp.setStyle('backgroundImage', 'url(/image/my-page-remove.png)');
					}
				}
			});
		}
	}
	
});