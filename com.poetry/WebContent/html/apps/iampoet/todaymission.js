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
		 
		tau.wreq({
      type: 'GET',
      url : '/missionpoetry/' + poetutil.getPoetDate(),
      callbackFn : function (resp) {
        if (resp.status === 200) {
          console.log(resp.data);
          this.loadingMissionPoet(resp.data);
        } else {
          tau.alert('초기 데이타 로딩 실패'); 
        }
      },
      callbackCtx : this
    });
		
	},
	
	loadingRefresh: function () {
    console.log('loadingRefresh');
  },
  
  loadingAddData: function () {
    console.log('loadingRefresh');
  },
	
	loadingMissionPoet: function (data) {
	  missionPoets = data;
	
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
              fontSize : '13px',
              marginTop : '13px',
              borderTop : '1px solid black'
            }
          }
     );
      poetPanel.add(content);
      scrollPanel.add(poetPanel);
	  }
	  scrollPanel.render();
	},
	
	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.WriteformController({mission : 'mission'}));
	},
	
	handleScrollStopPropagation: function (event){
		event.stopPropagation();
	}
});