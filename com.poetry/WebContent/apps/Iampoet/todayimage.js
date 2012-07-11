$require('/poem.js');
$class('iampoet.TodayImageController').extend(tau.ui.SceneController).define({
	TodayImageController: function (opts){
		this.setTitle('Cover');
	},
 
	init: function (){
		
	},
	
	destroy: function (){
		
	},
	
	handleMainImage: function (){
		var seqCtrl = this.getParent();
		seqCtrl.pushController(new iampoet.PoemController());
	},
	
	handleImage2_1: function (){
		console.log('bbb');
	}
});