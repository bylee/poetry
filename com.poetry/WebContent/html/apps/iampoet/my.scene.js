function initScene() {
	var scene = this.getScene();
//	var panel1 = new tau.ui.Panel({styles : {WebkitBoxAlign: 'center', WebkitBoxOrient: 'Vertical', WebkitBoxPack: 'center', display: '-webkit-box', height: '100%', position: '', width: '100%'}});
//	dom1 = panel1.getDOM();
//	dom1.innerHTML = "<img src='/html/apps/iampoet/image/splash.png' width='180' height='240' />";
//	scene.add(panel1);
	
	var button_write = new tau.ui.Button({id : 'write' , label : {normal: 'write'}});
	scene.add(button_write);
	button_write.onEvent('tap', this.handleWrite, this);


	var myScrollPanel = new tau.ui.ScrollPanel({
		id : 'myPanel',
	    styles : {
	        position : ''
	    }
	});
	scene.add(myScrollPanel);

	var myInfoPanel = new tau.ui.Panel({
		id : 'myInfoPanel',
		styles : {
			backgroundColor : 'transparent',
			//height : '32px',
			width : '100%'
		}
	});
	myScrollPanel.add(myInfoPanel);
	
	var myInfoImage = new tau.ui.ImageView({
		src : '/image/my-page_09.png',
	});	
	
	myInfoPanel.add(myInfoImage);

	var myInfoPanel2 = new tau.ui.Panel({
		id : 'myInfoPanel2',
		styles : {
			backgroundColor : 'transparent',
			height : '150px',
			width : '100%'
		}
	});	
	myScrollPanel.add(myInfoPanel2);

	var myPoemInfo = new tau.ui.Panel({
		id : 'myPoemInfo',
		styles : {
	        WebkitBorderRadius : '7px',
			backgroundColor : 'transparent',
			height : '70px',
			width : '50%',
			borderStyle : 'solid',
	        borderWidth : '1px',
		}
	});
	myInfoPanel2.add(myPoemInfo);
	
	var myPoemCount = new tau.ui.Label({
		id: 'myPoemCount',
		styles : {
			
		}
	});
	myPoemInfo.add(myPoemCount);

	var myPoemLabel = new tau.ui.Label({
		id: 'myPoemLabel',
		text : '내가 쓴 시보기', 
		styles : {
			
		}
	});
	myPoemInfo.add(myPoemLabel);
		
	var clipPoemInfo = new tau.ui.Panel({
		id : 'clipPoemInfo',
		styles : {
	        WebkitBorderRadius : '7px',
			backgroundColor : 'transparent',
			height : '70px',
			width : '50%',
			borderStyle : 'solid',
	        borderWidth : '1px',
		}
	});
	myInfoPanel2.add(clipPoemInfo);
	
	var myClipPoemCount = new tau.ui.Label({
		id: 'myClipPoemCount',
		styles : {
			
		}
	});
	clipPoemInfo.add(myClipPoemCount);

	var myClipPoemLabel = new tau.ui.Label({
		id: 'myClipPoemLabel',
		text : '가져온 시보기', 
		styles : {
			
		}
	});
	clipPoemInfo.add(myClipPoemLabel);
	
	var myInfoPanel3 = new tau.ui.Panel({
		id : 'myInfoPanel3',
		styles : {
			backgroundColor : 'transparent',
			height : '150px',
			width : '100%'
		}
	});	
	myScrollPanel.add(myInfoPanel3);

	var followingInfo = new tau.ui.Panel({
		id : 'followingInfo',
		styles : {
	        WebkitBorderRadius : '7px',
			backgroundColor : 'transparent',
			height : '70px',
			width : '50%',
			borderStyle : 'solid',
	        borderWidth : '1px',
		}
	});
	myInfoPanel3.add(followingInfo);

	var followingCount = new tau.ui.Label({
		id: 'followingCount',
		styles : {
			
		}
	});
	followingInfo.add(followingCount);

	var followingLabel = new tau.ui.Label({
		id: 'followingLabel',
		text : 'Following', 
		styles : {
			
		}
	});
	followingInfo.add(followingLabel);	
	
	var followerInfo = new tau.ui.Panel({
		id : 'followerInfo',
		styles : {
	        WebkitBorderRadius : '7px',
			backgroundColor : 'transparent',
			height : '70px',
			width : '50%',
			borderStyle : 'solid',
	        borderWidth : '1px',
		}
	});
	myInfoPanel3.add(followerInfo);


	var followerCount = new tau.ui.Label({
		id: 'followerCount',
		styles : {
			
		}
	});
	followerInfo.add(followerCount);

	var followerLabel = new tau.ui.Label({
		id: 'followerLabel',
		text : 'Follower', 
		styles : {
			
		}
	});
	followerInfo.add(followerLabel);		
	
//	var myInfoPanel4 = new tau.ui.Panel({
//		id : 'myInfoPanel4',
//		styles : {
//			backgroundColor : 'transparent',
//			//height : '32px',
//			width : '100%'
//		}
//	});	
//	myScrollPanel.add(myInfoPanel4);
//
//	var mySettingImage = new tau.ui.ImageView({
//		src : '/image/my-page_43.png',
//	});	
//	myInfoPanel4.add(mySettingImage);		
	
	var myInfoPanel5 = new tau.ui.Panel({
		id : 'myInfoPanel5',
		styles : {
			backgroundColor : 'transparent',
			height : '320px',
			width : '100%'
		}
	});	
	myScrollPanel.add(myInfoPanel5);

	var logoutImage = new tau.ui.Button({
		label : {
		},
		id : 'logout',
		styles : {
			width : '331px',
			height : '80px',
			marginLeft : '80px',
			marginRight : '80px',
			background : 'url(/image/my-page_49.png) no-repeat center transparent',
			border : '0px'
		}
	});	

	
	myInfoPanel5.add(logoutImage);
			
}