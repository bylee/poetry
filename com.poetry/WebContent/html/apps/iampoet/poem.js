$require("/comment.js");
$class('iampoet.PoemController').extend(tau.ui.SceneController).define({
	PoemController: function (opts){
		this.poem = opts.poem;
		this.seqCtrl = opts.seqCtrl;
		this.setTitle(this.poem.title);
	},
 
	init: function (){
		
	},
	
	destroy: function (){
		
	},
	
	sceneLoaded: function (){
		var scene = this.getScene();
		var rootURL = tau.getCurrentContext().getConfig().rootURL;
		
		var starNum = scene.getComponent('starNum');
		var commentNum = scene.getComponent('commentNum');
		var authorName = scene.getComponent('author');
		var poemPanel = scene.getComponent('poemPanel');
		
		starNum.setText(this.poem.star);
		commentNum.setText(this.poem.reply);
		authorName.setLabel('by '+this.poem.author.penName);
		poemPanel.setStyle('background','url('+rootURL + '/binary/' + this.poem.image+') no-repeat');
		//insert Comment
		tau.wreq({
			type: 'GET',
			url: '/reply/' + this.poem.id,
			callbackFn : this.handleLoadComment,
			callbackCtx : this
		});
		
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
				var poetryImage = new tau.ui.ImageView({
					src : rootURL + comments[index].writer.image?comments[index].writer.image:'' ,
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
						normal : comments[index].writer.penName
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
					        text : comments[index].contents,
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
		tau.wreq({
			type : 'POST',
			url : '/star/' + this.poem.id,
			callbackFn : function (resp) {
				if (resp.status === 200) {
					//TODO 이미 등록 되었을 경우 해제하는 것을 변경.~
					if (resp.data.status === 'success') {
						tau.alert("별이 등록 되었습니다.");
						//TODO 별 모양 바꾸기.
					}
				}
			},
			callbackCtx : this
		});
	},
	
	handleReply : function () {
		this.seqCtrl.pushController(
			new iampoet.CommentController({targetId : this.poem.id})
		,{ hideNavigationBar: false});
	},
	
	handleBookmark : function () {
		tau.wreq({
			type : 'POST',
			url : '/bookmark/' + this.poem.id,
			callbackFn : function (resp) {
				//TODO 이미 등록 되었을 경우 해제하는 것을 변경.~
				if (resp.status === 200) {
					tau.alert('북마크가 등록 되었습니다.');
					//TODO 북마크 표시.
				} else {tau.alert('북마크가 등록 되지 못했습니다. 다시 시도해 주세요.');}
			},
			callbackCtx : this
		});
	},
	
	handleFollow : function () {
		tau.wreq({
			type : 'POST',
			url : '/follow/' + this.poem.author.username,
			callbackFn : function (resp) {
				if (resp.status === 200) {
					//TODO 이미 등록 되었을 경우 해제하는 것을 변경.~
					tau.alert('팔로워가 등록 되었습니다.');
					//TODO 팔로워 표시.
				} else {tau.alert('팔로워가 등록 되지 못했습니다. 다시 시도해 주세요.');}
			},
			callbackCtx : this
		});
	}
	
});