function initScene() {
	var scene = this.getScene();
	var scrollPanel1 = new tau.ui.ScrollPanel(
			{
				id : 'mainSpanel',
				styles : {
					backgroundColor : '#FFFFFF',
					backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#907373),color-stop(17%,#907373),to(#FFFFFF))'
				}
			});
	scene.add(scrollPanel1);
	
	var poemPanel = new tau.ui.Panel(
			{
				id : 'poemPanel',
				styles : {
					backgroundColor : 'transparent',
					height : '400px',
					width : 'auto',
					'background-size' : 'cover',
					'background-repeat' : 'no-repeat'
				}
	});
	
	var authorPanel = new tau.ui.Panel(
			{
				styles : {
					backgroundColor : 'transparent',
					height : '32px',
					width : '100%',
					'text-align': 'right'
				}
			});
	poemPanel.add(authorPanel);
	var button1 = new tau.ui.Button({
		id : 'author',
		styles : {
			backgroundColor : 'transparent',
			backgroundImage : 'none',
			borderStyle : 'none'
		},
		label : {
			normal : 'by AJ'
		}
	});
	authorPanel.add(button1);
	var starImage = new tau.ui.ImageView({
		src : '/image/star.png',
		styles: {
		  width : '35px'
		}
		  
	});
	authorPanel.add(starImage);
	var starLabel = new tau.ui.Label({
		id : 'starNum',
		text : '17',
		styles : {
		  color : 'white',
      fontSize : '20px',
      paddingTop  : '6px'
		}
	});
	authorPanel.add(starLabel);
	var commentImage = new tau.ui.ImageView({
		src : '/image/comment.png',
    styles: {
      width : '35px'
    }

	});
	authorPanel.add(commentImage);
	var commentLabel = new tau.ui.Label({
		id : 'commentNum',
		text : '2',
		styles : {
      color : 'white',
      fontSize : '20px',
      paddingTop  : '6px'
    }
	});
	authorPanel.add(commentLabel);
	var textView1 = new tau.ui.TextView(
			{
				id : 'content',
				text : '',
				styles : {
					color : '#FFFFFF',
					display : 'block',
					height : '300px',
					position : 'relative',
					width : '95%',
					'margin-right' : '10px',
					'border-style' : 'none'
						
				},
				
			});
	poemPanel.add(textView1);
	scrollPanel1.add(poemPanel);
	var starBtn = new tau.ui.Button({
	  id : 'star',
	  styles: {
	    background: 'url(/image/star.png) transparent  no-repeat',
	    backgroundSize: '30px',
	    border: 'none',
	    width: '50px',
	    height: '50px'
	  },
	  backgroundImage: {
      highlighted: '/image/star-sel.png'
    }
  });
	starBtn.onEvent('tap',this.handleStar, this);
	
  var replyBtn = new tau.ui.Button({
    id : 'reply',
    styles: {
      background: 'url(/image/comment.png) transparent  no-repeat',
      backgroundSize: '30px',
      border: 'none',
      width: '50px',
      height: '50px'
    },
    backgroundImage: {
      highlighted: '/image/comment-sel.png'
    }
  });
  replyBtn.onEvent('tap',this.handleReply, this);
  
  var bookmarkBtn = new tau.ui.Button({
    id : 'bookmark',
    styles: {
      background: 'url(/image/bookmark.png) transparent no-repeat',
      backgroundSize: '30px',
      border: 'none',
      width: '50px',
      height: '50px'
    },
    backgroundImage: {
      highlighted: '/image/bookmark-sel.png'
    }
  });
  bookmarkBtn.onEvent('tap',this.handleBookmark, this);
  
  var followBtn = new tau.ui.Button({
    id : 'following',
    styles: {
      background: 'url(/image/following.png) transparent  no-repeat',
      backgroundSize: '40px',
      border: 'none',
      width: '50px',
      height: '50px'
    },
    backgroundImage: {
      highlighted: '/image/following-sel.png'
    }
  });
  followBtn.onEvent('tap',this.handleFollow, this);
  
	var toolbar = new tau.ui.ToolBar({
		dock : tau.ui.ToolBar.BOTTOM_DOCK,
		components: [starBtn, replyBtn, bookmarkBtn, followBtn]
	});
	scene.add(toolbar);
	
}