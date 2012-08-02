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
					//height : '32px',
					width : '100%'
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
		src : '/image/star.jpeg',
	});
	authorPanel.add(starImage);
	var starLabel = new tau.ui.Label({
		id : 'starNum',
		text : '17',
	});
	authorPanel.add(starLabel);
	var commentImage = new tau.ui.ImageView({
		src : '/image/comment.jpeg',
	});
	authorPanel.add(commentImage);
	var commentLabel = new tau.ui.Label({
		id : 'commentNum',
		text : '2',
	});
	authorPanel.add(commentLabel);
	/*
	var backImage = new tau.ui.ImageView({
				id : 'backImage',
				src : '/image/mainimage.jpeg',
				styles : {
					//bacauthorPaneldImage : '-webkit-gradient(linear, left top, left bottom,from(#EEDCDC),to(#FFFFFF))',
					display : 'block',
					height : '100%',
					width : '320px',
					border : 'none'
				}
	});
	scrollPanel1.add(backImage);
	*/
	var textView1 = new tau.ui.TextView(
			{
				id : 'content',
				text : '\n  하늘은 늘 거기에 있네\n\n 소리 없이 열려 있네\n\n 구름떼에 뒤덮이고\n\n 눈비에 가리워도\n\n 늘 거기에 열려\n\n 마알갛게 웃고 있네\n\n 지금은 안개 자욱한\n\n 저 산봉우리 너머로\n\n ...\n\n ...999\n\n...999\n\n...999\n\n...999\n\n \n  하늘은 늘 거기에 있네\n\n 소리 없이 열려 있네\n\n 구름떼에 뒤덮이고\n\n 눈비에 가리워도\n\n 늘 거기에 열려\n\n 마알갛게 웃고 있네\n\n 지금은 안개 자욱한\n\n 저 산봉우리 너머로\n\n ...\n\n ...999\n\n...999\n\n...999\n\n...999\n\n \n  하늘은 늘 거기에 있네\n\n 소리 없이 열려 있네\n\n 구름떼에 뒤덮이고\n\n 눈비에 가리워도\n\n 늘 거기에 열려\n\n 마알갛게 웃고 있네\n\n 지금은 안개 자욱한\n\n 저 산봉우리 너머로\n\n ...\n\n ...999\n\n...999\n\n...999\n\n...999\n\n ',
				styles : {
					color : '#FFFFFF',
					display : 'block',
					height : 'auto',
					position : 'relative',
					width : '95%',
					'margin-right' : '10px',
					zIndex : '10000',
					'border-style' : 'none'
						
				}
			});
	poemPanel.add(textView1);
	scrollPanel1.add(poemPanel);
	var starBtn = new tau.ui.Button({id : 'star', label: '별'});
	starBtn.onEvent('tap',this.handleStar, this);
    var replyBtn = new tau.ui.Button({id : 'reply', label: '답글'});
    replyBtn.onEvent('tap',this.handleReply, this);
    var bookmarkBtn = new tau.ui.Button({id : 'bookmark', label: '북마크'});
    bookmarkBtn.onEvent('tap',this.handleBookmark, this);
    var followBtn = new tau.ui.Button({id : 'follow', label: '팔로잉'});
    followBtn.onEvent('tap',this.handleFollow, this);
	var toolbar = new tau.ui.ToolBar({
		dock : tau.ui.ToolBar.BOTTOM_DOCK,
		components: [starBtn, replyBtn, bookmarkBtn, followBtn]
	});
	scene.add(toolbar);
	
}