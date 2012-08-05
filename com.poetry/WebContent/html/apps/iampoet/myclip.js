$class('iampoet.MyClipController').extend(tau.ui.SceneController).define({
	MyClipController: function (opts){
		this.setTitle('내가 가져온 시');
		this.curr_name = opts.name;
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
		var that = this;
		
		var scene = this.getScene();
		var clipsT = scene.getComponent('clipTable');

		var name = tau.util.getCookie('name');
		if (this.curr_name) {
			name = this.curr_name;
		}		
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name + '/bookmark' ,
			callbackFn : function (resp) {
				if (resp.status === 200) {
					var clips = resp.data;
					for ( var poem in clips) {
						var imageSrc = '/image/icon-person.png';
						var author = clips[poem].author;
						var poet = clips[poem];
						if ((author.icon != null) && (author.icon !== 'null')) {
							imageSrc = rootURL + '/binary/' + author.icon;
				        }
					      
						var Ttablecell = new tau.ui.TableCell({
							styles : {
								height : '160px',
					            margin : '5px auto 0px auto'
							}
						});
						
						var poetPanel = new tau.ui.Panel({
							styles : {
					            backgroundColor : '#FFFFFF',
					            //backgroundImage : '-webkit-gradient(linear, left top, right top,from(#FF0A05),to(#FFFFFF))',
					            border : '1px solid rgb(102,102,102)',
					            padding : '5px',
					            'box-shadow': '2px 2px 5px #888888',
					            width : '100%',
					            height : '100%',
//					            margin : '20px auto 0px auto',
					            '-webkit-border-radius' : '7px'
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
					    poetPanel.add(imagePanel);

					    var namePanel = new tau.ui.Panel({
							styles: {
							    display : 'inline-block'
							}
					    });
					    poetPanel.add(namePanel);
					    var penName = new tau.ui.Button({
					    	styles : {
					    		backgroundColor : 'transparent',
					    		backgroundImage : 'none',
					    		borderStyle : 'none',
					    		width: '150px',
					    		'text-align': 'left',
					    		display : 'block',
					    		height : '30px',
					    		paddingLeft : '15px'
					    	},
					    	label : {
					    		normal : author.penName
					    	}
					    });
					    namePanel.add(penName);
					    penName.username = author.username;
					    penName.onEvent('tap', that.gotoMyPage, that);
					    
				      
					    
					    var levelName = new tau.ui.Label({
					    	text : calcLevel(poet.author),
					    	styles : {
					    		display : 'block',
					    		fontSize : '15px',
					    		color : 'black',
					    		paddingLeft : '15px'
					    	}
					    });
					    namePanel.add(levelName);					    
					    
					    var timeLabel = new tau.ui.Label({
					    	text : poet.createDate,
					    	styles : {
					    		right : '5px',
					    		'text-align' : 'right',
					    		'display' : 'inline',
					    		'font-size' : '10px'
					    	}
					    });
					    //poetPanel.add(timeLabel);
						
					    
					    var rightPanel = new tau.ui.Panel({
					    	styles : {
					    		display : 'inline',
					    		position : 'absolute',
					    		right : '5px',
					    		color : 'black'
					    	}
					    });
					    poetPanel.add(rightPanel);
					    var innerPanel1 = new tau.ui.Panel({
					    	styles : {
					    		display : 'inline-block',
					    		width : '50px'
					    	}
					    });
					    rightPanel.add(innerPanel1);
					    var innerPanel2 = new tau.ui.Panel({
					    	styles : {
					    		display : 'inline-block',
					    		width : '50px'
					    	}
					    });
					    rightPanel.add(innerPanel2);
					        
					    var starImage = new tau.ui.ImageView({
					    	src : '/image/star.png',
					    	styles: {
					            width : '35px',
					            marginLeft : 'auto',
					            marginRight : 'auto',
					            display : 'block'
					          }
					    });
					    innerPanel1.add(starImage);
					    var starLabel = new tau.ui.Label({
					          id : 'starNum',
					          text : poet.stars,
					          styles : {
					            fontSize : '20px',
					            paddingTop  : '6px',
					            'text-align' : 'center',
					            display : 'block'
					          }
					    });
					    innerPanel1.add(starLabel);
					    var commentImage = new tau.ui.ImageView({
					          src : '/image/comment.png',
					          styles: {
					            width : '35px',
					            marginLeft : 'auto',
					            marginRight : 'auto',
					            display : 'block',
					            paddingTop : '4px'
					          }

					    });
					    innerPanel2.add(commentImage);
					    var commentLabel = new tau.ui.Label({
					          id : 'commentNum',
					          text : poet.replys,
					          styles : {
					            fontSize : '20px',
					            paddingTop  : '6px',
					            'text-align' : 'center',
					            display : 'block'
					          }
					    });
					    innerPanel2.add(commentLabel);
					    
					    var content = new tau.ui.TextView({
					    	text : poet.contents,
					    	styles : {
					                display : 'block',
					                fontSize : '13px',
					                marginTop : '13px',
					                borderTop : '1px solid black'
					    	}
					    });
					    poetPanel.add(content);

					    Ttablecell.setContentItem(poetPanel);
					    clipsT.add(Ttablecell);
					}
					clipsT.render();
				} else { 
					tau.alert("에러");
				};
			}
		});		
	},
	
	gotoMyPage : function(event) {
		var comp = event.getSource();
//		tau.alert(comp + ' : move : ' + comp.username);
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.MyController({name:comp.username}));			
	}
});