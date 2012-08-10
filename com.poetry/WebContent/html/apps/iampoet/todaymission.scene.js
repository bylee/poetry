function initScene() {
  var scene = this.getScene();
  var mainScrollPanel = new tau.ui.ScrollPanel({
    id : 'mainPanel',
    pullDownLabel: ['업데이트하시려면 아래로 당기세요.', '업데이트하시려면 당겼다 놓으세요.', '업데이트중...'],
    pullToRefresh: 'both',
    pullUpLabel: ['추가로 보실려면 위로 당기세요.', '추가하시려면 당겼다 놓으세요.', '업데이트중...'],
    pullDownFn : tau.ctxAware(this.loadingRefresh, this),
    pullUpFn : tau.ctxAware(this.loadingAddData, this)
  });
  
  scene.add(mainScrollPanel);
  var missionInfoPanel = new tau.ui.Panel(
      {
        id : 'missionImageInfo',
        styles : {
          backgroundColor : '#FFFFFF',
          backgroundImage : '-webkit-gradient(linear, 0% 0%, 0% 100%,from(#000000),to(#FFFFFF))',
          height : '150px',
          width : '100%'
        }
      });
  mainScrollPanel.add(missionInfoPanel);
  
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
        id : 'pinInfoText',
        text : '  이 사진은 강원도 지역의 시골길을 찍은 것이며, 멀리 보이는 길을 가르쳐주는 가을 나무들이 있는 풍경입니다. 날짜는 2002년 10월경입니다.',
        styles : {
          WebkitBorderRadius : '2px',
          backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
          display : 'inline-block',
          fontSize : '15px',
          margin : '',
          width : '100%'
        }
      });
  missionPicInfo.handleTouchMove = function (e, payload) {
		
		var pageY = e.changedTouches[0].pageY;
		var topDelta = this.scrollY ? pageY - this.touchStartY : 0;
		if (Math.abs(topDelta) < 30 && Math.abs(topDelta) != 0)  {
			tau.ui.TextView.$super.handleTouchMove.apply(this, arguments);
			e.stopPropagation();
		} 
	};
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
      normal : '작성'
    }
  });
  scene.add(writeBtn);
  writeBtn.onEvent('tap', this.handleWrite, this);
}