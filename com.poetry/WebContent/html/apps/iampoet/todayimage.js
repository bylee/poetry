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
	
	handleToday: function (){
	  
	}
});