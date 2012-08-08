$class('iampoet.MyPoemController').extend(tau.ui.SceneController).define({
	MyPoemController: function (opts){
		this.setTitle('내가 쓴 시');
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
		
		var myPoemScrollPanel = new tau.ui.ScrollPanel({
			id : 'myPoemPanel',
		    pullDownLabel: ['업데이트하시려면 아래로 당기세요.', '업데이트하시려면 당겼다 놓으세요.', '업데이트중...'],
		    pullToRefresh: 'both',
		    pullUpLabel: ['추가로 보실려면 위로 당기세요.', '추가하시려면 당겼다 놓으세요.', '업데이트중...'],
		    pullDownFn : tau.ctxAware(this.refreshPoems, this),
		    pullUpFn : tau.ctxAware(this.addPoems, this),
		    styles : {
		        position : ''
		    },
		    hScroll: false
		});
		scene.add(myPoemScrollPanel);

		var table = new tau.ui.Table({
			id : 'poemTable'
		});
		myPoemScrollPanel.add(table);

		var iampoetImg = new tau.ui.ImageView({
			src : '/image/my-page-logo.png',
			styles : {
				width : '108px',
				height : '91px',
				marginLeft : '110px',
				marginRight : '120px',
				border : 'none'
			}
		})
		myPoemScrollPanel.add(iampoetImg);	
		
		this.getPoems();
		
	},
	

	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.WriteformController({mission :'내가 쓴 시'}));
	},

	getPoems : function() {
		var that = this;
		var name = tau.util.getCookie('name');
		if (this.curr_name) {
			name = this.curr_name;
		}		
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name + '/poetry' ,
			callbackFn : function (resp) {
				if (resp.status === 200) {
					that.loadPoems(resp.data);					
				} else { 
					tau.alert("에러");
				};
			}
		});		
	},
	
	refreshPoems : function() {
		var that = this;
		var scene = this.getScene();
		var poemsT = scene.getComponent('poemTable');
		poemsT.removeAll();
		var name = tau.util.getCookie('name');
		if (this.curr_name) {
			name = this.curr_name;
		}
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name + '/poetry' ,
			callbackFn : function (resp) {
				if (resp.status === 200) {
					that.loadPoems(resp.data);					
				} else { 
					tau.alert("에러");
				};
			}
		});		
		
	},
	
	addPoems : function() {
		var that = this;
		var scene = this.getScene();
		var poemsT = scene.getComponent('poemTable');
		var name = tau.util.getCookie('name');
		if (this.curr_name) {
			name = this.curr_name;
		}
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name + '/poetry' ,
			params : {
				start : this.lastId
			},
			callbackFn : function (resp) {
				if (resp.status === 200) {
					that.loadPoems(resp.data);					
				} else { 
					tau.alert("에러");
				};
			}
		});		
	},
	
	loadPoems : function(poems) {
		var scene = this.getScene();
		var poemsT = scene.getComponent('poemTable');
		
//		var poems = resp.data;
		for (var poem in poems) {
			var imageSrc = '/image/icon-person.png';
			var author = poems[poem].author;
			var poet = poems[poem];
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
//		            margin : '20px auto 0px auto',
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
		                borderTop : '1px solid black',
		                height : '30px'
		    	},
			    vScroll: false,
			    hScroll: false
		    });
		    content.poem = poet;
		    content.onEvent('tap', this.detailPoetry, this);
		    this.lastId = poet.id
		    poetPanel.add(content);

		    Ttablecell.setContentItem(poetPanel);
			poemsT.add(Ttablecell);
		}
		poemsT.render();		
	},
	
	detailPoetry : function(event) {
		var comp = event.getSource();
		var seqNavi = this.getParent();
		seqNavi.pushController(
			new iampoet.PoemController({
				poem :comp.poem,
				seqCtrl : seqNavi 
			}),{hideNavigationBar: false}
		);
	}	
});