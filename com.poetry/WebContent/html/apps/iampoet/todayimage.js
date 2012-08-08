$require('/poem.js');
$require('/login.js');
$class('iampoet.TodayImageController').extend(tau.ui.SceneController).define({
	TodayImageController: function (opts){
		this.setTitle('Cover');
	},
 
	init: function (){
		
		this.onEvent('changeData',this.handleDataEvent, this);
		if(!tau.util.getCookie('name')) {
			this.modalCtrl = new iampoet.LoginController();
			this.modalCtrl.onEvent('dismiss', this.handleDismiss, this);
			this.modalCtrl.onEvent('todayData', this.handleToday, this);
			this.presentModal(this.modalCtrl, {
				layout: 'FULL',
				animate: 'vertical'
			});
		} else { 
		  var dateObj = new Date();
		  var dateStr = dateObj.getFullYear() + '-'+ (dateObj.getMonth()+1) + '-' +dateObj.getDate();
		  tau.wreq({
        type: 'GET',
        url : '/today/' + dateStr,//2012-07-14
        callbackFn : function (resp) {
          if (resp.status === 200) {
            this.handleToday('todayData',resp.data);
          } else {
            tau.alert('초기 데이타 로딩 실패'); 
          }
        },
        callbackCtx : this
      });
		}
	},
	
	destroy: function (){
		
	},
	
	sceneLoaded: function (){
	  
	},
	
	handleToday: function (event,data){
	  var scene = this.getScene();
	  var rootURL = tau.getCurrentContext().getConfig().rootURL;
	  this.mainpoem = data;
	  var mainData = data[0];
	  var sub1Data = data[1];
	  var sub2Data = data[2];
    var sub3Data = data[3];
    var sub4Data = data[4];
	  var scrollPanel1 = new tau.ui.ScrollPanel({
	    styles : {
	      position : ''
	    }
	  });
	  scene.add(scrollPanel1);
	  var imageView1 = new tau.ui.ImageView({
	    id : 'mainImage',
	    src : rootURL + '/binary/' + mainData.image,
	    styles : {
	      WebkitBorderRadius : '7px',
	      borderStyle : 'solid',
	      borderWidth : '3px',
	      height : '258px',
	      padding : '4px',
	      width : '320px'
	    }
	  });
	  scrollPanel1.add(imageView1);
	  imageView1.onEvent('tap', this.handleImage, this);
	  var imageView2 = new tau.ui.ImageView({
	    id : 'sub1Image',
	    src : rootURL + '/binary/' + sub1Data.image,
	    styles : {
	      WebkitBorderRadius : '7px',
	      borderStyle : 'solid',
	      borderWidth : '2px',
	      height : '160px',
	      padding : '02px',
	      width : '160px'
	    }
	  });
	  scrollPanel1.add(imageView2);
	  imageView2.onEvent('tap', this.handleImage, this);
	  var imageView3 = new tau.ui.ImageView({
	    id : 'sub2Image',
	    src : rootURL + '/binary/' + sub2Data.image,
	    styles : {
	      WebkitBorderRadius : '7px',
	      borderStyle : 'solid',
	      borderWidth : '2px',
	      height : '160px',
	      padding : '02px',
	      width : '160px'
	    }
	  });
	  scrollPanel1.add(imageView3);
	  imageView3.onEvent('tap', this.handleImage, this);
	  var imageView4 = new tau.ui.ImageView({
	    id : 'sub3Image',
	    src : rootURL + '/binary/' + sub3Data.image,
	    styles : {
	      WebkitBorderRadius : '7px',
	      borderStyle : 'solid',
	      borderWidth : '2px',
	      height : '160px',
	      padding : '02px',
	      width : '160px'
	    }
	  });
	  scrollPanel1.add(imageView4);
	  imageView4.onEvent('tap', this.handleImage, this);
	  var imageView5 = new tau.ui.ImageView({
	    id : 'sub4Image',
	    src : rootURL + '/binary/' + sub4Data.image,
	    styles : {
	      WebkitBorderRadius : '7px',
	      borderStyle : 'solid',
	      borderWidth : '2px',
	      height : '160px',
	      padding : '02px',
	      width : '160px'
	    }
	  });
	  scrollPanel1.add(imageView5);
	  imageView5.onEvent('tap', this.handleImage, this);
	  var label1 = new tau.ui.Label({
	    id : 'sub1Title',
	    style : 'position: static',
	    text : 'Big Picture',
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '10px',
	      position : 'absolute',
	      right : '67%',
	      top : '266px',
	      width : '96px'
	    }
	  });
	  scrollPanel1.add(label1);
	  var label2 = new tau.ui.Label({
	    id : 'sub1Author',
	    style : 'position: static',
	    text : sub1Data.author.penName,
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '51px',
	      position : 'absolute',
	      right : '7%',
	      textAlign : 'right',
	      top : '390px',
	      width : '96px'
	    }
	  });
	  scrollPanel1.add(label2);
	  var label3 = new tau.ui.Label({
	    id : 'sub2Title',
	    style : 'position: static',
	    text : sub2Data.title,
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '169px',
	      position : 'absolute',
	      right : '67%',
	      top : '266px',
	      width : '111px'
	    }
	  });
	  scrollPanel1.add(label3);
	  var label4 = new tau.ui.Label({
	    id : 'sub2Author',
	    style : 'position: static',
	    text : sub2Data.author.penName,
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '202px',
	      position : 'absolute',
	      right : '7%',
	      textAlign : 'right',
	      top : '391px',
	      width : '108px'
	    }
	  });
	  scrollPanel1.add(label4);
	  var label5 = new tau.ui.Label({
	    id : 'sub3Title',
	    style : 'position: static',
	    text : sub3Data.title,
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '10px',
	      position : 'absolute',
	      right : '67%',
	      top : '429px',
	      width : '96px'
	    }
	  });
	  scrollPanel1.add(label5);
	  var label6 = new tau.ui.Label({
	    id : 'sub3Author',
	    style : 'position: static',
	    text : sub3Data.author.penName,
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '51px',
	      position : 'absolute',
	      right : '7%',
	      textAlign : 'right',
	      top : '547px',
	      width : '96px'
	    }
	  });
	  scrollPanel1.add(label6);
	  var label7 = new tau.ui.Label({
	    id : 'sub4Author',
	    style : 'position: static',
	    text : sub4Data.author.penName,
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '214px',
	      position : 'absolute',
	      right : '7%',
	      textAlign : 'right',
	      top : '546px',
	      width : '96px'
	    }
	  });
	  scrollPanel1.add(label7);
	  var label8 = new tau.ui.Label({
	    id : 'sub4Title',
	    style : 'position: static',
	    text : sub4Data.title,
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '169px',
	      position : 'absolute',
	      right : '67%',
	      top : '429px',
	      width : '111px'
	    }
	  });
	  scrollPanel1.add(label8);
	  var label9 = new tau.ui.Label({
	    id : 'mainTitle',
	    style : 'position: static',
	    text : mainData.title,
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '10px',
	      position : 'absolute',
	      right : '67%',
	      top : '10px',
	      width : '96px'
	    }
	  });
	  scrollPanel1.add(label9);
	  var label10 = new tau.ui.Label({
	    id : 'mainAuthor',
	    style : 'position: static',
	    text : mainData.author.penName,
	    styles : {
	      backgroundColor : 'transparent',
	      backgroundImage : 'none',
	      bottom : 'auto',
	      color : '#FFFFFF',
	      display : 'block',
	      height : '17px',
	      left : '202px',
	      position : 'absolute',
	      right : '7%',
	      textAlign : 'right',
	      top : '221px',
	      width : '96px'
	    }
	  });
	  scrollPanel1.add(label10);
	  scene.update();
	  scrollPanel1.refresh();
	  
	},
	
	
	handleImage: function (event){
		var comp = event.getSource();
		var compId = comp.getId();
		var targetpoem ;
		var index = 0;
		switch (compId){
		case "mainImage":
			targetpoem  = this.mainpoem[0];
			break;
		case "sub1Image":
			targetpoem  = this.mainpoem[1];
			index = 1;
			break;
		case "sub2Image":
			targetpoem  = this.mainpoem[2];
			index = 2;
			break;
		case "sub3Image":
			targetpoem  = this.mainpoem[3];
			index = 3;
			break;
		case "sub4Image":
			targetpoem  = this.mainpoem[4];
			index = 4;
			break;
		default:
			return;
		}
		
		var seqCtrl = this.getParent();
		seqCtrl.pushController(
				new iampoet.PoemController(
						{
						  dataref : this,
						  poem :targetpoem,
						  seqCtrl : seqCtrl 
						}
				) 
				,{ hideNavigationBar: false}
		);
	},
	
	handleDismiss: function (){
		this.dismissModal(true);
	},
	
	handleDataEvent: function (event, payload) {
		var targetPoem = null;
		for (var index in this.mainpoem) {
			if (payload.id == this.mainpoem[index].id) {
				targetPoem = this.mainpoem[index];
			}
		}
		targetPoem[payload.type] = payload.value;
	}
});