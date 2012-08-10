$require('/poem.js');
$require('/login.js');
$class('iampoet.TodayImageController').extend(tau.ui.SceneController).define({
	TodayImageController: function (opts){
		this.setTitle('Cover');
	},
 
	init: function (){
		
		this.onEvent('changeData',this.handleDataEvent, this);
		this.handleLogin();
		
	},
	
	handleLogin: function () {
		if(!tau.util.getCookie('name')) {
			this.modalCtrl = new iampoet.LoginController();
			this.modalCtrl.onEvent('dismiss', this.handleDismiss, this);
			this.modalCtrl.onEvent('todayData', this.handleToday, this);
			this.presentModal(this.modalCtrl, {
				layout: 'FULL',
				animate: 'vertical'
			});
		} else { 
		  var dateStr = poetutil.getPoetDate();
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
	  scene.removeAll(true);
	  var rootURL = tau.getCurrentContext().getConfig().rootURL;
	  this.mainpoem = data;
	  var scrollPanel1 = new tau.ui.ScrollPanel({
		  styles : {
			  height : 'auto'
		  }
	  });
	  var mainStyle = {
			  height : '240px',
			  color : 'white',
	  };
	  
	  var subStyle = {
			  color : 'white',
			  width : '50%',
			  height : '160px',
			  display : 'inline-block',
			  border : '2px solid white'
			  
	  };
	  scene.add(scrollPanel1);
	  for(var index in data) {
		  var poempanel = new tau.ui.Panel({
			  id : 'p'+index,
			  styles : index==0?mainStyle:subStyle
		  });
		  
		  if (index == 0) {
			  var todayImagePanel = new tau.ui.ImageView({
				  src : '/image/today-icon.png',
				  styles : {
					  position : 'absolute',
					  top : '8px',
					  left : '16px',
					  width : '59px',
						  
				  }
			  });
			  poempanel.add(todayImagePanel);
			  
			  var mainTitlePanel = new tau.ui.Panel({
				  styles : {
					  position : 'absolute',
					  bottom : '20px',
					  width : '100%',
					  height : '50px'
				  }
			  });
			  
			  var missionLabel = new tau.ui.Label({
				  text : 'TODAY MISSION',
				  styles : {
					  display : 'block',
					  color : 'white',
					  fontFamily : 'Charcoal CY',
					  fontSize : '25px'
						  
				  }
			  });
			  var titleLabel = new tau.ui.Label({
				  text : decodeURIComponent(data[index].title),
				  styles : {
					  display : 'block'
				  }
			  });
			  poetutil.settingPoetFontStyle(titleLabel,null,data[index]);
			  mainTitlePanel.add(missionLabel);
			  mainTitlePanel.add(titleLabel);
			  poempanel.add(mainTitlePanel);
		  } else {
			  if ((index % 2) == 0) {
				  poempanel.setStyle('width' , '40%');
			  } else {
				  poempanel.setStyle('width' , '60%');
			  }
			  var titleLabel = new tau.ui.Label({
				  text : decodeURIComponent(data[index].title),
				  styles : {
					  position : 'absolute',
					  top : '10px'
				  }
			  });
			  var authorLabel = new tau.ui.Label({
				  text : decodeURIComponent(data[index].author.penName),
				  styles : {
					  position : 'absolute',
					  bottom : '10px',
					  right : '10px'
				  }
			  });
			  poetutil.settingTodayPoetFontStyle(titleLabel,authorLabel,data[index]);
			  poempanel.add(titleLabel);
			  poempanel.add(authorLabel);
		  }
		  var that = this;
		  var url = rootURL + '/binary/' + data[index].image;
		  var time = '5s';
		  if (index == 0) {time = '10s';}
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
							width : this.width,
							time : time
						} );
					};
		  };
		  poetutil.preloadImage(
				  url, callbk(poempanel)
		  );
		  poempanel.onEvent('tap', this.handleImage, this);
		  scrollPanel1.add(poempanel);
	  }
	  scene.update();
	  scrollPanel1.refresh();
	  
	},
	
	
	handleImage: function (event){
		var comp = event.getSource();
		var compId = comp.getId();
		var targetpoem = this.mainpoem[compId.substring(1)];
		
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