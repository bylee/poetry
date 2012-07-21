$require('/poem.js');
$require('/login.js');
$class('iampoet.TodayImageController').extend(tau.ui.SceneController).define({
	TodayImageController: function (opts){
		this.setTitle('Cover');
	},
 
	init: function (){
		this.modalCtrl = new iampoet.LoginController();
		this.modalCtrl.onEvent('dismiss', this.handleDismiss, this);
		this.modalCtrl.onEvent('todayData', this.handleToday, this);
		this.presentModal(this.modalCtrl, {
			layout: 'FULL',
			animate: 'vertical'
		});
	},
	
	destroy: function (){
		
	},
	
	sceneLoaded: function (){
	  
	},
	
	handleMainImage: function (){
		var seqCtrl = this.getParent();
		seqCtrl.pushController(new iampoet.PoemController());
	},
	
	handleImage2_1: function (){
		console.log('bbb');
	},
	
	handleDismiss: function (){
		this.dismissModal(true);
	},
	
	handleToday: function (event, param){
	  var scene = this.getScene();
	  var resJson = param;
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
	  for(var poetry in resJson) {
	    var imagePanel = scene.getComponent(poetryIndex[index].imageId);
	    var titleLabel = scene.getComponent(poetryIndex[index].titleId);
	    var authorLabel = scene.getComponent(poetryIndex[index].authorId);
	    imagePanel.setSrc("/../../../binary/"+resJson[poetry].image);
	    titleLabel.setText(resJson[poetry].title);
	    authorLabel.setText(resJson[poetry].author.penName);
	    index++;
	  }
	  
	  
	}
});