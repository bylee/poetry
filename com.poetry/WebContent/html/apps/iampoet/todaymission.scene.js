function initScene() {
  var scene = this.getScene();
  var mainScrollPanel = new tau.ui.ScrollPanel({
    id : 'mainPanel',
    pullToRefresh : 'up'
  });
  scene.add(mainScrollPanel);
  var missionInfoPanel = new tau.ui.Panel(
      {
        id : 'missionImageInfo',
        styles : {
          backgroundColor : '#FFFFFF',
          backgroundImage : '-webkit-gradient(linear, left top, right top,from(#000000),to(#FFFFFF))',
          height : '150px',
          width : '100%'
        }
      });
  mainScrollPanel.add(missionInfoPanel);
  var missionpicAuthorImg = new tau.ui.ImageView({
    src : '/image/person.jpeg',
    styles : {
      borderColor : '#CCCCCC',
      borderStyle : 'solid',
      borderWidth : '2px',
      display : 'inline',
      height : '32px',
      width : '32px'
    }
  });
  missionInfoPanel.add(missionpicAuthorImg);
  var missionAuthorPenname = new tau.ui.Button({
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      borderStyle : 'none'
    },
    label : {
      normal : 'Big Picture James'
    }
  });
  missionInfoPanel.add(missionAuthorPenname);
  var missionDate = new tau.ui.Label({
    text : 'updated 2012.07',
    styles : {
      bottom : '2px',
      fontSize : '11px',
      position : 'absolute',
      right : '5px'
    }
  });
  missionInfoPanel.add(missionDate);
  var missionInfoTextPanel = new tau.ui.Panel({
    align : 'center',
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      height : '110px',
      margin : '',
      padding : '10px',
      position : '',
      textAlign : '',
      width : '100%'
    }
  });
  missionInfoPanel.add(missionInfoTextPanel);
  var missionPicInfo = new tau.ui.TextView(
      {
        text : '  이 사진은 강원도 지역의 시골길을 찍은 것이며, 멀리 보이는 길을 가르쳐주는 가을 나무들이 있는 풍경입니다. 날짜는 2002년 10월경입니다.',
        styles : {
          WebkitBorderRadius : '2px',
          backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
          display : 'inline-block',
          fontSize : '15px',
          margin : '',
          width : ''
        }
      });
  missionInfoTextPanel.add(missionPicInfo);
  var missionPic = new tau.ui.ImageView({
    id : 'missionImage',
    src : '/image/fallroad.jpeg',
    styles : {
      height : '150px',
      width : '100%'
    }
  });
  mainScrollPanel.add(missionPic);
  var missionCarousel = new tau.ui.Carousel({
    id : 'missionPanel',
    styles : {
      height : '150px'
    }
  });
  mainScrollPanel.add(missionCarousel);
  
  var writeBtn = new tau.ui.Button({
    id : 'write',
    styles : {
      fontSize : '10px'
    },
    label : {
      normal : 'Write Mission'
    }
  });
  scene.add(writeBtn);
  writeBtn.onEvent('tap', this.handleWrite, this);
}