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
	        WebkitBorderRadius : '7px',
			backgroundColor : 'transparent',
            border : '1px solid rgb(102,102,102)',
            'box-shadow': '2px 2px 5px #888888',
            padding : '5px',
			height : '70px',
			width : '96%',
            margin : '5px 5px 5px 5px'
		}
	});
	myScrollPanel.add(myInfoPanel);

	var imageSrc = '/image/icon-person.png';

    var imagePanel = new tau.ui.Panel({
    	id : 'userIcon',
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
    myInfoPanel.add(imagePanel);
	
    var namePanel = new tau.ui.Panel({
		styles: {
		    display : 'inline-block'
		}
    });
    myInfoPanel.add(namePanel);
	var myPenNameLabel = new tau.ui.Label({
		id : 'myPenName',
		styles :{
			fontSize : '25px',
			'font-family' : 'YDIYGO330',
		    margin : '5px 0px 0px 10px'
		}
	});
	namePanel.add(myPenNameLabel);
	
    var levelName = new tau.ui.Label({
    	id :'myLevel',
    	text : '',
    	styles : {
    		display : 'block',
    		fontSize : '15px',
    		color : 'black',
    		paddingLeft : '10px',
    		paddingTop : '5px',
    	}
    });
    namePanel.add(levelName);	
	
	var myInfoPanel2 = new tau.ui.Panel({
		id : 'myInfoPanel2',
		styles : {
			backgroundColor : 'transparent',
			height : '95px',
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
			width : '45%',
		    border : '1px solid rgb(102,102,102)',
            'box-shadow': '2px 2px 5px #888888',
	        position : 'absolute',
	        left : '5px',
		    top : '20px',
		    padding : '5px'
		}
	});
	
	myInfoPanel2.add(myPoemInfo);
	
	var myPoemCount = new tau.ui.Label({
		id: 'myPoemCount',
		styles : {
			margin : '3px 5px 0px 0px',
			display : 'block',
			'font-size' : '30px',
			'font-weight' : 'bold', 
//			'font-style' : 'italic',
			'font-family' : 'Monotype Corsiva',
			'text-align' : 'right'
		}
	});
	myPoemInfo.add(myPoemCount);

	var myPoemLabel = new tau.ui.Label({
		id: 'myPoemLabel',
		text : '내가 쓴 시보기 ', 
		styles : {
			margin : '5px 0px 0px 0px',
			display : 'block',
			'font-size' : '20px',
//			'font-weight' : 'bold', 
			'font-family' : 'Monotype Corsiva',
			'text-align' : 'right'
		}
	});
	myPoemInfo.add(myPoemLabel);
		
	var clipPoemInfo = new tau.ui.Panel({
		id : 'clipPoemInfo',
		styles : {
	        WebkitBorderRadius : '7px',
			backgroundColor : 'transparent',
			height : '70px',
			width : '45%',
		    border : '1px solid rgb(102,102,102)',
            'box-shadow': '2px 2px 5px #888888',
	        position : 'absolute',
	        right : '10px',
		    top : '20px',
			padding : '5px'
		}
	});
	myInfoPanel2.add(clipPoemInfo);
	
	var myClipPoemCount = new tau.ui.Label({
		id: 'myClipPoemCount',
		styles : {
			margin : '3px 5px 0px 0px',
			display : 'block',
			'font-size' : '30px',
			'font-weight' : 'bold', 
//			'font-style' : 'italic',
			'font-family' : 'Monotype Corsiva',
			'text-align' : 'right'
		}
	});
	clipPoemInfo.add(myClipPoemCount);

	var myClipPoemLabel = new tau.ui.Label({
		id: 'myClipPoemLabel',
		text : '가져온 시보기 ', 
		styles : {
			margin : '5px 0px 0px 0px',
			display : 'block',
			'font-size' : '20px',
//			'font-weight' : 'bold', 
			'font-family' : 'Monotype Corsiva',
			'text-align' : 'right'
		}
	});
	clipPoemInfo.add(myClipPoemLabel);
	
	var myInfoPanel3 = new tau.ui.Panel({
		id : 'myInfoPanel3',
		styles : {
			backgroundColor : 'transparent',
			height : '100px',
			width : '100%',
			margin : '0px 0px 30px 0px'
		}
	});	
	myScrollPanel.add(myInfoPanel3);

	var followingInfo = new tau.ui.Panel({
		id : 'followingInfo',
		styles : {
	        WebkitBorderRadius : '7px',
			backgroundColor : 'transparent',
			height : '70px',
			width : '45%',
		    border : '1px solid rgb(102,102,102)',
            'box-shadow': '2px 2px 5px #888888',
	        position : 'absolute',
	        left : '5px',
		    top : '20px',
		    padding : '5px'
		}
	});
	myInfoPanel3.add(followingInfo);

	var followingCount = new tau.ui.Label({
		id: 'followingCount',
		styles : {
			margin : '3px 5px 0px 0px',
			display : 'block',
			'font-size' : '30px',
			'font-weight' : 'bold', 
//			'font-style' : 'italic',
			'font-family' : 'Monotype Corsiva',
			'text-align' : 'right'			
		}
	});
	followingInfo.add(followingCount);

	var followingLabel = new tau.ui.Label({
		id: 'followingLabel',
		text : 'Following&nbsp;', 
		styles : {
			margin : '5px 0px 0px 0px',
			display : 'block',
			'font-size' : '25px',
//			'font-weight' : 'bold', 
			'font-family' : 'Monotype Corsiva',
			'height' : '30px',
			'text-align' : 'right'			
		}
	});
	followingInfo.add(followingLabel);	
	
	var followerInfo = new tau.ui.Panel({
		id : 'followerInfo',
		styles : {
	        WebkitBorderRadius : '7px',
			backgroundColor : 'transparent',
			height : '70px',
			width : '45%',
		    border : '1px solid rgb(102,102,102)',
            'box-shadow': '2px 2px 5px #888888',
	        position : 'absolute',
	        right : '10px',
		    top : '20px',
		    padding : '5px'
		}
	});
	myInfoPanel3.add(followerInfo);


	var followerCount = new tau.ui.Label({
		id: 'followerCount',
		styles : {
			margin : '3px 5px 0px 0px',
			display : 'block',
			'font-size' : '30px',
			'font-weight' : 'bold', 
//			'font-style' : 'italic',
			'font-family' : 'Monotype Corsiva',
			'text-align' : 'right'			
		}
	});
	followerInfo.add(followerCount);

	var followerLabel = new tau.ui.Label({
		id: 'followerLabel',
		text : 'Follower&nbsp;', 
		styles : {
			margin : '5px 0px 0px 0px',
			display : 'block',
			'font-size' : '25px',
//			'font-weight' : 'bold', 
			'font-family' : 'Monotype Corsiva',
			'text-align' : 'right'			
		}
	});
	followerInfo.add(followerLabel);		
	
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
			width : '160px',
			height : '40px',
			marginLeft : '80px',
			marginRight : '80px',
			background : 'url(/image/my-page_49.png) no-repeat center transparent',
			'background-size' : '100%',
			border : 'none'
		}
	});	

	
	myInfoPanel5.add(logoutImage);
			
}