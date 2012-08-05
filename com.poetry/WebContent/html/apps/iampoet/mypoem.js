$class('iampoet.MyPoemController').extend(tau.ui.SceneController).define({
	MyPoemController: function (opts){
		this.setTitle('내가 쓴 시');
	},
 
	init: function () {
		
	},
	
	destroy: function () {
		
	},
	
	sceneLoaded: function () {
		var scene = this.getScene();
		var writeBtn = scene.getComponent('write');
		this.getNavigationBar().setRightItem(writeBtn);
		
		this.getPoems();
		
	},
	

	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.WriteformController());
	},

	getPoems : function() {
		
		var scene = this.getScene();
		var poemsT = scene.getComponent('poemTable');

		var name = tau.util.getCookie('name');
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name + '/poetry' ,
			callbackFn : function (resp) {
				if (resp.status === 200) {
					var poems = resp.data;
					for ( var poem in poems) {
						var imageSrc = '/image/icon-person.png';
						var author = poems[poem].author;
						if ((author.icon != null) && (author.icon !== 'null')) {
							imageSrc = rootURL + '/binary/' + author.icon;
				        }
					      
						var Ttablecell = new tau.ui.TableCell({
							styles : {
						        WebkitBorderRadius : '7px',
								backgroundColor : 'transparent',
								height : '160px',
					            width : '98%',
					            border : '1px solid rgb(102,102,102)',
					            padding : '5px',
					            'box-shadow': '2px 2px 5px #888888',
					            margin : '20px auto 0px auto'
							}
						});
						
						var tmpPanel = new tau.ui.Panel({
							styles : {
								width : '100%'
							}
						});
						
						var tmpUserInfoPanel = new tau.ui.Panel({
							styles : {
								height : '60px',
								width : '100%'
							}
						});
						var tmpUserIcon = new tau.ui.ImageView({
					        src :  imageSrc,
					        styles : {
						        WebkitBorderRadius : '7px',
						        display : 'inline',
						    	height : '64px',
						    	width : '64px'
					        }							
						});
						tmpUserInfoPanel.add(tmpUserIcon);
						var penNameBtn = new tau.ui.Button({
							styles : {
								backgroundColor : 'transparent',
								backgroundImage : 'none',
								borderStyle : 'none',
								width: '150px',
								'text-align': 'left'
							},
							label : {
								normal : author.penName
							}
				        });
						tmpUserInfoPanel.add(penNameBtn);
						
						var createLabel = new tau.ui.Label({
							text : poems[poem].createDate,
							styles : {
								right : '5px',
								'text-align' : 'right',
								'display' : 'inline',
								'font-size' : '10px'
							}								
						})

						tmpUserInfoPanel.add(createLabel);
						
						var lvl = poems[poem].author.theNumberOfBookmarks + poems[poem].author.theNumberOfFollowings + poems[poem].author.theNumberOfFollowers + poems[poem].author.theNumberOfPoetries; 
						if (lvl < 100) {
							lvl = '초보시인';
						} else if (lvl < 300){
							lvl = '중수시인';
						} else {
							lvl = '시인';
						}					      
						var lvlLabel = new tau.ui.Label({
							text : lvl,
						});

						tmpUserInfoPanel.add(lvlLabel);
						
						var poemStatus = new tau.ui.Panel({
							styles : {
								display  : 'inline',
								position : 'absolute',
								right : '5px',
								color : 'black'
							}
						});
						tmpUserInfoPanel.add(poemStatus);
						var starImg = new tau.ui.ImageView({
							src : '/image/star.png',
							styles : {
								width : '30px',
								'text-align' : 'right',
							}
						});
						poemStatus.add(starImg);
						
						var starLbl = new tau.ui.Label({
							id : 'starLbl',
							text : poems[poem].stars,
							styles : {
								fontSize : '20px',
								paddingTop : '6px',
								'text-align' : 'right'
							}
						});
						poemStatus.add(starLbl);
						var commentImg = new tau.ui.ImageView({
							src : '/image/comment.png',
							styles: {
								width : '30px',
								'text-align' : 'right'
							}
						});
						poemStatus.add(commentImg);
						var commentLbl = new tau.ui.Label({
							id : 'commentLbl',
							text : poems[poem].replys,
							styles : {
								fontSize : '20px',
								paddingTop : '6px',
								'text-align' : 'right'
							}
						});
						poemStatus.add(commentLbl);
					        
						// 이하 시 내용
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
						tmpContent.setText(poems[poem].contents);
						tmpPoemInfoPanel.add(tmpContent);
						tmpPanel.add(tmpUserInfoPanel);
						tmpPanel.add(tmpPoemInfoPanel);
						
						Ttablecell.setContentItem(tmpPanel);
						poemsT.add(Ttablecell);
					}
					poemsT.render();
				} else { 
					tau.alert("에러");
				};
			}
		});		
	}	
});