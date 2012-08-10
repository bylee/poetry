$require('/writeform.js');
$require('/my.js');

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
		this.missionPanel = scene.getComponent('missionPanel');
		var missionImage = scene.getComponent('missionImage');
		var missionImageInfo = scene.getComponent('missionImageInfo');
		var missionInfoText = scene.getComponent('pinInfoText');
		this.missionPanel.setComponents([missionImage,missionImageInfo]);
		var writeBtn = scene.getComponent('write');
		this.getNavigationBar().setRightItem(writeBtn);
		var rootURL = tau.getCurrentContext().getConfig().rootURL;
		
		tau.wreq({
	      type: 'GET',
	      url : '/mission/' + poetutil.getPoetDate(),
	      callbackFn : function (resp) {
	        if (resp.status === 200) {
	          if (resp.data != null) {
	            this.missionData = resp.data;
	            missionInfoText.setText(decodeURIComponent(this.missionData.description));
	            missionImage.setSrc(rootURL+"/binary/"+this.missionData.imageId);
	          } else {
	        	  tau.alert('미션 데이터 로딩 실패');
	          }
	        } else {
	          tau.alert('미션 데이타 로딩 실패'); 
	        }
	      },
	      callbackCtx : this
		}); 
    
		tau.wreq({
	      type: 'GET',
	      url : '/missionpoetry/' + poetutil.getPoetDate(),
	      callbackFn : function (resp) {
	        if (resp.status === 200) {
	          if (this.lastId == null && resp.data.length == 0) {
	            tau.alert('현재 미션시가 없습니다. 첫 도전을 해주세요~');
	          } else {
	        	this.poemData = resp.data;  
	            this.loadingMissionPoet(resp.data);
	          }
	        } else {
	          tau.alert('초기 데이타 로딩 실패'); 
	        }
	      },
	      callbackCtx : this
		});
		
	},
	
	loadingRefresh: function () {
	  var scene = this.getScene();
    var scrollPanel = scene.getComponent('mainPanel');
    scrollPanel.removeAll();
    tau.wreq({
      type: 'GET',
      url : '/missionpoetry/'+ poetutil.getPoetDate(),
      callbackFn : function (resp) {
        if (resp.status === 200) {
          scrollPanel.add(this.missionPanel);
          this.poemData = resp.data;
          this.loadingMissionPoet(resp.data);
        } else {
          tau.alert('추가 데이타 로딩 실패'); 
        }
      },
      callbackCtx : this
    });
  },
  
  loadingAddData: function () {
    tau.wreq({
      type: 'GET',
      url : '/missionpoetry/'+ poetutil.getPoetDate(),
      params : {
        start : this.lastId
      },
      callbackFn : function (resp) {
        if (resp.status === 200) {
        	this.poemData.push(resp.data);
            this.loadingMissionPoet(resp.data);
        } else {
          tau.alert('추가 데이타 로딩 실패'); 
        }
      },
      callbackCtx : this
    });
  },
	
	loadingMissionPoet: function (data) {
	  var missionPoets = data;
	  var rootURL = tau.getCurrentContext().getConfig().rootURL;
	  var scene = this.getScene();
	  var scrollPanel = scene.getComponent('mainPanel');
	  
	  for (var index in missionPoets) {
	    var poet = missionPoets[index];
	    var poetPanel = new tau.ui.Panel({
	      styles : {
            backgroundColor : '#FFFFFF',
            //backgroundImage : '-webkit-gradient(linear, left top, right top,from(#FF0A05),to(#FFFFFF))',
            border : '1px solid rgb(102,102,102)',
            padding : '5px',
            'box-shadow': '2px 2px 5px #888888',
            width : '95%',
            margin : '20px auto 0px auto',
            '-webkit-border-radius' : '7px'
	      }
	    });
	    var imageSrc = '/image/icon-person.png';
	    var writer = poet.author;
      
      if ((writer.icon != null)) {
        imageSrc = rootURL + '/binary/' + writer.icon;
      }
      
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
          normal : decodeURIComponent(writer.penName)
        }
      });
      namePanel.add(penName);
      penName.username = writer.username;
      penName.onEvent('tap', this.gotoMyPage, this);
      var levelName = new tau.ui.Label({
        text : poetutil.calcLevel(writer),
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
          color : 'black',
          width : '70px'
          
        }
      });
      poetPanel.add(rightPanel);
      var innerPanel1 = new tau.ui.Panel({
        styles : {
          display : 'inline-block',
          width : '30px'
        }
      });
      rightPanel.add(innerPanel1);
      var innerPanel2 = new tau.ui.Panel({
        styles : {
          display : 'inline-block',
          width : '30px'
        }
      });
      rightPanel.add(innerPanel2);
      
      var starImage = new tau.ui.ImageView({
        src : '/image/star.png',
        styles: {
          width : '25px',
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
          fontSize : '15px',
          paddingTop  : '6px',
          'text-align' : 'center',
          display : 'block'
        }
      });
      innerPanel1.add(starLabel);
      var commentImage = new tau.ui.ImageView({
        src : '/image/comment.png',
        styles: {
          width : '25px',
          marginLeft : 'auto',
          marginRight : 'auto',
          display : 'block',
          height : '24px',
          paddingTop : '3px'
        }

      });
      innerPanel2.add(commentImage);
      var commentLabel = new tau.ui.Label({
        id : 'commentNum',
        text : poet.replys,
        styles : {
          fontSize : '15px',
          paddingTop  : '5.5px',
          'text-align' : 'center',
          display : 'block'
           
            
        }
      });
      innerPanel2.add(commentLabel);
      var contentPanel = new tau.ui.Panel({
    	  id : poet.id,
    	  styles : {
    		  color : 'black',
    		  marginTop : '10px',
    		  height : '400px',
    		  'background-image' : 'url('+rootURL + '/binary/' + this.missionData.imageId+')',
    		  'background-size' : 'cover'
    			  
    	  }
      });
      var that = this;
      var callBack = function (poet) {
	  		return function () {
	  			that.handlePoem(poet);
	  		};
	  }(poet);
      contentPanel.onEvent('tap',callBack);
      var title = new tau.ui.Label({
    	  id : 'titlePanel',
    	  text : decodeURIComponent(poet.title),
    	  styles : {
    		  display: 'block',
    		  textAlign : 'center'
    	  }
      });
      contentPanel.add(title);
      var content = new tau.ui.TextView(
          {
            text : decodeURIComponent(poet.contents),
            styles : {
              WebkitBorderRadius : '2px',
              //backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
              display : 'block',
              fontSize : '13px',
              marginTop : '13px',
              maxHeight : '300px',
            }
          }
      );
      poetutil.settingPoetFontStyle(title,content, poet);
      
      content.handleTouchMove = function (e, payload) {
			
			var pageY = e.changedTouches[0].pageY;
			var topDelta = this.scrollY ? pageY - this.touchStartY : 0;
			if (Math.abs(topDelta) < 30 && Math.abs(topDelta) != 0) {
				tau.ui.TextView.$super.handleTouchMove.apply(this, arguments);
				e.stopPropagation();
			} 
		};
	      contentPanel.add(content);
	      poetPanel.add(contentPanel);
	      scrollPanel.add(poetPanel);
	      this.lastId = poet.id;
	  }
	  scene.update();
	  scrollPanel.refresh();
	},
	
	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(
				new iampoet.WriteformController({
					mission : {
						type : 'mission',
						imageId : this.missionData.imageId,
						parentCtrl : this
					}
				})
		);
	},
	
	handleScrollStopPropagation: function (event){
		event.stopPropagation();
	},
	
	handlePoem: function (poem){
		var seqNavi = this.getParent();
		seqNavi.pushController(
				new iampoet.PoemController(
						{
						  dataref : this,
						  poem : poem,
						  seqCtrl : seqNavi 
						}
				) 
				,{ hideNavigationBar: false}
		);
	},
	
	gotoMyPage: function (event) {
		var comp = event.getSource();
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.MyController({name:comp.username}));
	}
});