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
	var titleLabel = new tau.ui.Label({
		id : 'title',
		styles : {
			backgroundColor : 'transparent',
			height : '30px',
			width : '100%',
			textAlign : 'center',
			display : 'block'
				
		}
	});
	poemPanel.add(titleLabel);
	
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
		  width : '15px'
		}
		  
	});
	authorPanel.add(starImage);
	var starLabel = new tau.ui.Label({
		id : 'starNum',
		text : '17',
		styles : {
		  color : 'white',
      fontSize : '10px',
      paddingTop  : '6px'
		}
	});
	authorPanel.add(starLabel);
	var commentImage = new tau.ui.ImageView({
		src : '/image/comment.png',
    styles: {
      width : '15px'
    }

	});
	authorPanel.add(commentImage);
	var commentLabel = new tau.ui.Label({
		id : 'commentNum',
		text : '2',
		styles : {
      color : 'white',
      fontSize : '10px',
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
	var toolPanel = new tau.ui.Panel({
		id :'toolPanel',
		styles : {
			width : '100%',
			height : '40px',
			display : '-webkit-box',
			'-webkit-box-orient':'horizontal',
			'-webkit-box-pack':'justify',
			'-webkit-box-align':'center',
			paddingLeft : '20px',
			paddingRight : '20px'
		}
	});
	poemPanel.add(toolPanel);
	
	var starBtn = new tau.ui.Button({
	  id : 'star',
	  styles: {
	    background: 'url(/image/icon-white_01.png) transparent  no-repeat',
	    backgroundSize: '30px',
	    border: 'none',
	    width: '30px',
	    height: '30px',
	    display : 'block'
	  }
	  
  });
	starBtn.onEvent('tap',this.handleStar, this);
	
  var replyBtn = new tau.ui.Button({
    id : 'reply',
    styles: {
      background: 'url(/image/icon-white_02.png) transparent  no-repeat',
      backgroundSize: '30px',
      border: 'none',
      width: '30px',
      height: '30px',
      display : 'block'
    }
  });
  replyBtn.onEvent('tap',this.handleReply, this);
  
  var bookmarkBtn = new tau.ui.Button({
    id : 'bookmark',
    styles: {
      background: 'url(/image/icon-white_03.png) transparent no-repeat',
      backgroundSize: '30px',
      border: 'none',
      width: '30px',
      height: '30px',
      display : 'block'
    }
  });
  bookmarkBtn.onEvent('tap',this.handleBookmark, this);
  
  var followBtn = new tau.ui.Button({
    id : 'following',
    styles: {
      background: 'url(/image/icon-white_04.png) transparent  no-repeat',
      backgroundSize: '30px',
      border: 'none',
      width: '30px',
      height: '30px',
      display : 'block',
      backgroundPosition: '0px'
    }
  });
  followBtn.onEvent('tap',this.handleFollow, this);
  
	toolPanel.add(starBtn);
	toolPanel.add(replyBtn);
	toolPanel.add(bookmarkBtn);
	toolPanel.add(followBtn);
	scrollPanel1.add(poemPanel);
	
	
}