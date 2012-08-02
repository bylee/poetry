$class('iampoet.WriteformController').extend(tau.ui.SceneController).define({
	WriteformController: function (opts){
		this.setTitle("Write");
	},
 
	init: function (){
		var that = this;
		/*
		window.deviceapis.camera.getCameras(function(cameraArray) {
			try {
				console.debug(cameraArray);
				that.deviceCameras = cameraArray;
				
				if (cameraArray.length < 1) {
					alert('No Camera exists.');
					that.deviceCameras = null;
					goBack();
					return;
				}

				if( typeof (previewTarget) != 'undefined') {
					startPreview(0, previewTarget);
				}
			} catch(e) {
				console.log('getCameras Error: ' + e.message);
			}
		}, function () { tau.log("camera error");});
		*/
	},
	
	destroy: function (){
		
	},
	
	handlePickImage: function (){
		var that = this;
		var scb = function(file){
		   
		    window.deviceapis.filesystem.resolve( 
		        function(file){ 
		        	tau.alert(file.toURI());
		        	//tau.alert(that.getScene().getComponent('mainPanel').getId());
		        	that.getScene().getComponent('mainImage')
		        	.getDOM().setAttribute('src', file.toURI());
		            //document.body.innerHTML = '<img src="' + file.toURI() + '">';  
		        }, function(e){ 
		            console.log('resolve err:' + e.message);
		        },
		        file,
		        "r"
		    );
		};
		var ecb = function(e){
		    tau.log(e.message);
		};
		// for android - 안드로이드는 removable만 지원합니다.
		// var opts = {'crop':true, 'out': 'removable/pickedImage.jpg'};
		//var opts = {'crop':true, 'out': 'images/pickedImage'+tau.genId('')+'.jpg'};
		//ax.ext.media.pickImage(scb, ecb, opts);
		ax.ext.media.pickImage(scb, ecb);
	},
	
	handleLayout: function (){
		tau.alert("hello");
	}
});