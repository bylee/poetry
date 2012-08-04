$require('/poem.js');
$require('/login.js');
$class('iampoet.TodayImageController').extend(tau.ui.SceneController).define({
	TodayImageController: function (opts){
		this.setTitle('Cover');
	},
 
	init: function (){
		

		if(!tau.util.getCookie('name')) {
			this.modalCtrl = new iampoet.LoginController();
			this.modalCtrl.onEvent('dismiss', this.handleDismiss, this);
			this.modalCtrl.onEvent('todayData', this.handleToday, this);
			this.presentModal(this.modalCtrl, {
				layout: 'FULL',
				animate: 'vertical'
			});
		} else { 
		  tau.wreq({
        type: 'GET',
        url : '/today/20120712',
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
	
	handleImage: function (event){
		var comp = event.getSource();
		var compId = comp.getId();
		var targetpoem ;
		switch (compId){
		case "mainImage":
			targetpoem  = this.mainpoem[0];
			break;
		case "sub1Image":
			targetpoem  = this.mainpoem[1];
			break;
		case "sub2Image":
			targetpoem  = this.mainpoem[2];
			break;
		case "sub3Image":
			targetpoem  = this.mainpoem[3];
			break;
		case "sub4Image":
			targetpoem  = this.mainpoem[4];
			break;
		default:
			return;
		}
		
		var seqCtrl = this.getParent();
		seqCtrl.pushController(
				new iampoet.PoemController(
						{
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
	
	handleToday: function (event, param){
	  var scene = this.getScene();
	  this.mainpoem = param;
	  var poetryIndex = [{
	    imageId : 'mainImage',
	    titleId : 'mainTitle',
	    authorId : 'mainAuthor'
	  },{
	    imageId : 'sub1Image',
	    titleId : 'sub1Title',
	    authorId : 'sub1Author'
	  },{
      imageId : 'sub2Image',
      titleId : 'sub2Title',
      authorId : 'sub2Author'
    },{
      imageId : 'sub3Image',
      titleId : 'sub3Title',
      authorId : 'sub3Author'
    },{
      imageId : 'sub4Image',
      titleId : 'sub4Title',
      authorId : 'sub4Author'
    }];
	  var index = 0;
	  var rootURL = tau.getCurrentContext().getConfig().rootURL;
	  for(var poetry in this.mainpoem) {
	    var imagePanel = scene.getComponent(poetryIndex[index].imageId);
	    var titleLabel = scene.getComponent(poetryIndex[index].titleId);
	    var authorLabel = scene.getComponent(poetryIndex[index].authorId);
	    imagePanel.setSrc(rootURL+"/binary/"+this.mainpoem[poetry].image);
	    titleLabel.setText(this.mainpoem[poetry].title);
	    authorLabel.setText(this.mainpoem[poetry].author.penName);
	    index++;
	  }
	  
	  
	}
});