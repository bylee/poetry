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
		
		this.loadingMissionPoet();
		
	},
	
	loadingMissionPoet: function () {
	  var missionPoets = [{
	    writer : {
	      penName : '땡중땡중',
	      userName : '박중훈',
	      level : '하수'
	    },
	    stars : 15,
	    comments : 22,
	    createDate : 2012-11-22,
	    contents : '루루루루루룰룰 \n 루룰루루루 \n 하하 룰루루루 \n 랄라랄라라'
	  },{
	    writer : {
        penName : '룰루룰루',
        userName : '할리갈리',
        level : '하수'
      },
      stars : 15,
      comments : 22,
      createDate : 2012-11-22,
      contents : '루1루2루3루4루5룰룰 \n 루룰루루루 \n 하하 룰루루루 \n 랄라랄라라'
	  },{
	    writer : {  
	      penName : '땡땡중',
	      userName : '이본용',
	      level : '하수'
	    },
	    stars : 151,
	    comments : 2,
	    createDate : 2012-11-22,
	    contents : '루루루루루룰룰 \n 루룰루루루 \n 하하 룰루루루 \n 랄라랄라라'
	  }];
	
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
            margin : '20px auto 0px auto'
	      }
	    });
	    var imageSrc = '/image/icon-person.png';
	    var writer = poet.writer;
      
      if ((writer.icon != null)) {
        imageSrc = rootURL + '/binary/' + writer.icon;
      }
      
      var poetryImage = new tau.ui.ImageView({
        src :  imageSrc,
        styles : {
          display : 'inline',
          height : '32px',
          width : '32px'
        }
      });
      poetPanel.add(poetryImage);
      var penName = new tau.ui.Button({
        styles : {
          backgroundColor : 'transparent',
          backgroundImage : 'none',
          borderStyle : 'none',
          width: '150px',
          'text-align': 'left'
        },
        label : {
          normal : writer.penName
        }
      });
      poetPanel.add(penName);
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
      })
      poetPanel.add(rightPanel);
      
      var starImage = new tau.ui.ImageView({
        src : '/image/star.png',
        styles: {
          width : '35px',
          'text-align' : 'right',
        }
          
      });
      rightPanel.add(starImage);
      var starLabel = new tau.ui.Label({
        id : 'starNum',
        text : '17',
        styles : {
          fontSize : '20px',
          paddingTop  : '6px',
          'text-align' : 'right',
        }
      });
      rightPanel.add(starLabel);
      var commentImage = new tau.ui.ImageView({
        src : '/image/comment.png',
        styles: {
          width : '35px',
          'text-align' : 'right'
        }

      });
      rightPanel.add(commentImage);
      var commentLabel = new tau.ui.Label({
        id : 'commentNum',
        text : '2',
        styles : {
          fontSize : '20px',
          paddingTop  : '6px',
          'text-align' : 'right'
            
        }
      });
      rightPanel.add(commentLabel);
      var content = new tau.ui.TextView(
          {
            text : poet.contents,
            styles : {
              WebkitBorderRadius : '2px',
              //backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
              display : 'inline-block',
              fontSize : '13px',
              margin : '',
              width : ''
            }
          }
     );
      poetPanel.add(content);
      scrollPanel.add(poetPanel);
	    
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