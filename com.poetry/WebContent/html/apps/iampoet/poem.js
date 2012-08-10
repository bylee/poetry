$require("/comment.js");
$class('iampoet.PoemController').extend(tau.ui.SceneController).define({
	PoemController: function (opts){
		this.poem = opts.poem;
		this.seqCtrl = opts.seqCtrl;
		this.dataref = opts.dataref;
		this.setTitle("시 보기");
		this.status = {
		    star : this.poem.star,
		    bookmark : this.poem.bookmark,
		    following : this.poem.following
		}; 
	},
 
	init: function (){
		
	},
	
	destroy: function (){
	},
	
	sceneLoaded: function (){
	  //loading Comment
    tau.wreq({
      type: 'GET',
      url: '/reply/' + this.poem.id,
      callbackFn : this.handleLoadComment,
      callbackCtx : this
    });
	  var scene = this.getScene();
		
		var rootURL = tau.getCurrentContext().getConfig().rootURL;
		var starNum = scene.getComponent('starNum');
		var commentNum = scene.getComponent('commentNum');
		var authorName = scene.getComponent('author');
		var poemPanel = scene.getComponent('poemPanel');
		var poemContent = scene.getComponent('content');
		var starBtn = scene.getComponent('star');
		var bookmarkBtn = scene.getComponent('bookmark');
		var followingBtn = scene.getComponent('following');
		
		if (this.status.star) {
			starBtn.setBackgroundImage('/image/star-red.png');
		}
		if (this.status.bookmark) {
			bookmarkBtn.setBackgroundImage('/image/bookmark-red.png');
		}
		if (this.status.following) {
			followingBtn.setBackgroundImage('/image/following-red.png');
		}
		
		
		starNum.setText(this.poem.stars);
		commentNum.setText(this.poem.replys);
		authorName.setLabel(decodeURIComponent(this.poem.author.penName));
		/*
		poemPanel.setStyles({
			'background-image': 'url('+rootURL + '/binary/' + this.poem.image+')'
		});
		*/
		var that = this;
		var url = rootURL + '/binary/' + this.poem.image;
		var callbk = function (poempanel){
			    return function () {
						poempanel.setStyles({
							'background-image':'url('+url+')',
							'background-size' : 'cover',
							'background-repeat' : 'no-repeat'
						});
						poetutil.handleImageAni({
							comp : poempanel,
							ratio : poempanel.getDOM().clientHeight/this.height,
							width : this.width
						} );
					};
		};
		poetutil.preloadImage(
				url, callbk(poemPanel)
		);
		var title = scene.getComponent('title');
		title.setText(decodeURIComponent(this.poem.title));
		poetutil.settingPoetFontStyle(title,poemContent,this.poem);
		
		poemContent.setText(decodeURIComponent(this.poem.contents));
		
		poemContent.handleTouchMove = function (e, payload) {
			
			var pageY = e.changedTouches[0].pageY;
			var topDelta = this.scrollY ? pageY - this.touchStartY : 0;
			if (Math.abs(topDelta) < 30 && Math.abs(topDelta) != 0)  {
				tau.ui.TextView.$super.handleTouchMove.apply(this, arguments);
				e.stopPropagation();
			} 
		};
		
		if (this.poem.author.username == tau.util.getCookie('name')) {
			scene.getComponent('toolPanel').setStyle('display',"none");
		}
	},
	
	handleLoadComment: function (resp) {
		if (resp.status === 200) {
			//TODO resp.data.status 체크 필요.
			var scene = this.getScene();
			var scrollPanel = scene.getComponent('mainSpanel');
			var rootURL = tau.getCurrentContext().getConfig().rootURL;
			var comments = resp.data;
			for ( var index in comments) {
				var commentPanel = new tau.ui.Panel(
				{
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
				//TODO : IMAGE 없음.. 
				var imageSrc = '/image/icon-person.png';
				var writer = comments[index].writer;
				
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
				commentPanel.add(poetryImage);
				var penName = new tau.ui.Button({
					styles : {
						backgroundColor : 'transparent',
						backgroundImage : 'none',
						borderStyle : 'none'
					},
					label : {
						normal : decodeURIComponent(writer.penName)
					}
				});
				commentPanel.add(penName);
				var timeLabel = new tau.ui.Label({
					text : comments[index].createDate,
					styles : {
						right : '5px',
						'text-align' : 'right',
						'display' : 'inline',
						'font-size' : '10px'
					}
				});
				commentPanel.add(timeLabel);
				var comment = new tau.ui.TextView(
					      {
					        text : decodeURIComponent(comments[index].contents),
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
				commentPanel.add(comment);
				scrollPanel.add(commentPanel);
				scrollPanel.render();
			}
		} else {tau.alert('커맨트가 로딩되지 못했습니다. error code ' + resp.status);}
	},
	
	handleStar : function () {
	  var scene = this.getScene();
	  var starBtn = scene.getComponent('star');
    
    if (this.status.star) {
      tau.wreq({
        type : 'DELETE',
        url : '/star/' + this.poem.id,
        callbackFn : function (resp) {
          if (resp.status === 200) {
              starBtn.setBackgroundImage('/image/icon-white_01.png');
              this.status.star = false;
              this.handleDataChange('star', false);
          } else {tau.alert('별등록이 해제 되지 못했습니다. 다시 시도해 주세요.');}
        },
        callbackCtx : this
      });
      
    } else {
      tau.wreq({
        type : 'POST',
        url : '/star/' + this.poem.id,
        callbackFn : function (resp) {
          if (resp.status === 200) {
              starBtn.setBackgroundImage('/image/star-red.png');
              this.status.star = true;
              this.handleDataChange('star', true);
          } else {tau.alert('별이 등록 되지 못했습니다. 다시 시도해 주세요.');}
        },
        callbackCtx : this
      });
      
    }
		
	},
	
	handleReply : function () {
		this.seqCtrl.pushController(
			new iampoet.CommentController({targetId : this.poem.id})
		,{ hideNavigationBar: false});
	},
	
	handleBookmark : function () {
	  
	  var scene = this.getScene();
    var bookmarkBtn = scene.getComponent('bookmark');
    
    if (this.status.bookmark) {
      tau.wreq({
        type : 'DELETE',
        url : '/bookmark/' + this.poem.id,
        callbackFn : function (resp) {
          //TODO 이미 등록 되었을 경우 해제하는 것을 변경.~
          if (resp.status === 200) {
            bookmarkBtn.setBackgroundImage('/image/icon-white_03.png');
            this.status.bookmark = false;
            this.handleDataChange('bookmark', false);
          } else {tau.alert('북마크가 해제 되지 못했습니다. 다시 시도해 주세요.');}
        },
        callbackCtx : this
      });
    } else {
      tau.wreq({
        type : 'POST',
        url : '/bookmark/' + this.poem.id,
        callbackFn : function (resp) {
          //TODO 이미 등록 되었을 경우 해제하는 것을 변경.~
          if (resp.status === 200) {
            bookmarkBtn.setBackgroundImage('/image/bookmark-red.png');
            this.status.bookmark = true;
            this.handleDataChange('bookmark', true);
          } else {tau.alert('북마크가 등록 되지 못했습니다. 다시 시도해 주세요.');}
        },
        callbackCtx : this
      });
    }
		
	},
	
	handleFollow : function () {
	  var scene = this.getScene();
    var followingBtn = scene.getComponent('following');
    if (this.status.following) {
      tau.wreq({
        type : 'DELETE',
        url : '/following/' + this.poem.author.username,
        callbackFn : function (resp) {
          if (resp.status === 200) {
            followingBtn.setBackgroundImage('/image/icon-white_04.png');
            this.status.following = false;
            this.handleDataChange('following', false);
          } else {tau.alert('팔로워가 해제 되지 못했습니다. 다시 시도해 주세요.');}
        },
        callbackCtx : this
      });
    } else {
      tau.wreq({
        type : 'POST',
        url : '/following/' + this.poem.author.username,
        callbackFn : function (resp) {
          if (resp.status === 200) {
            followingBtn.setBackgroundImage('/image/following-red.png');
            this.status.following = true;
            this.handleDataChange('following', true);
          } else {tau.alert('팔로워가 등록 되지 못했습니다. 다시 시도해 주세요.');}
        },
        callbackCtx : this
      });
    }
	},
	
	handleDataChange: function (type, status) {
		this.dataref.fireEvent('changeData',
				{ 
					id : this.poem.id,
					type : type,
					value : status
				}
		);
	}
	
});