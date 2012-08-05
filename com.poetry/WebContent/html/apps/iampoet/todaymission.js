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
            margin : '20px auto 0px auto',
            '-webkit-border-radius' : '7px'
	      }
	    });
	    var imageSrc = '/image/icon-person.png';
	    var writer = poet.writer;
      
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
          normal : writer.penName
        }
      });
      namePanel.add(penName);
      var levelName = new tau.ui.Label({
        text : writer.level,
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
        text : '17',
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
        text : '2',
        styles : {
          fontSize : '20px',
          paddingTop  : '6px',
          'text-align' : 'center',
          display : 'block'
           
            
        }
      });
      innerPanel2.add(commentLabel);
      var content = new tau.ui.TextView(
          {
            text : poet.contents,
            styles : {
              WebkitBorderRadius : '2px',
              //backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
              display : 'inline-block',
              fontSize : '13px',
              marginTop : '13px',
              borderTop : '1px solid black'
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