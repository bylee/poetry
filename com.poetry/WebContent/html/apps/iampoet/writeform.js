$require('/poemstyle.js');
$class('iampoet.WriteformController').extend(tau.ui.SceneController).define({
	WriteformController: function (opts){
		this.setTitle("Write");
	},
 
	init: function (){
		var that = this;
		
		
		this.stylingCtrl = new iampoet.PoemStyleController();
		this.stylingCtrl.onEvent('cancelStyling', function () {this.dismissModal();});
		this.stylingCtrl.onEvent('applyStyling', this.handleApplyStyling, this);
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
	
	sceneLoaded: function () {
		var scene = this.getScene();
		var submitBtn = new tau.ui.Button({
			label : '전송'
		});
		submitBtn.onEvent('tap',this.handleSummit, this);
		var navBar = this.getNavigationBar();
		
		navBar.setRightItem(submitBtn);
		
	},
	
	handleSummit: function () {
		/*
		tau.wreq({
			type: 'POST',
			url: '/poetry',
			contentType: 'multipart/form-data',
			params: {
				file: this.imageFile,
				title: title.getText(),
				contents: contents.getText(),
				callbackFn: function (resp) {
					if (resp.status === 200) {
						//TODO : 성공 실패 테스트 
						tau.alert("시가 등록 되었습니다.");
					} else {tau.alert("시가 등록 되지 못했습니다. 다시 시도해 주세요");}
				},
				callbackCtx: this
			}
		});
		*/
		/*
		tau.wreq({
			type: 'POST',
			url: '/binary',
			contentType: 'multipart/form-data',
			params: {
				file: this.imageFile,
				//title: title.getText(),
				//contents: contents.getText(),
				callbackFn: function (resp) {
					if (resp.status === 200) {
						//TODO : 성공 실패 테스트 
						//tau.alert("시가 등록 되었습니다.");
						this.handlePoetry(resp.data);
					} else {tau.alert("시가 등록 되지 못했습니다. 다시 시도해 주세요");}
				},
				callbackCtx: this
			}
		});
		*/
		var config = tau.getConfig();
  	  	var rootURL = config.rootURL;
  	  	var params = {'param1':'param1 value'};
  	  	var that = this;
		//ax.ext.ui.showProgress('Loading...');
		ax.ext.net.upload(
				rootURL + '/binary',
				params,
				this.imageFile.fullPath,
				function (resp) {
					if (resp.status === 200) {
						console.log('successCB:', o.status, o.data);
						//TODO : 성공 실패 테스트 
						tau.alert("여기..");
						that.handlePoetry(resp.data);
					} else {tau.alert("시가 등록 되지 못했습니다. 다시 시도해 주세요");}
				},
				function (e) {
					tau.alert('errorCB:', e.message, e.code);
				},
				function (progress) {
					tau.alert('progressCB:', progress[0], progress[1]);
				}
		);
		
		
	},
	
	handlePoetry: function (image) {
		var scene = this.getScene();
		var title = scene.getComponent('title');
		var contents = scene.getComponent('contents');
		tau.wreq({
			type: 'POST',
			url: '/poetry',
			//contentType: 'multipart/form-data',
			params: {
				//file: this.imageFile,
				title: title.getText(),
				contents: contents.getText(),
				imageId: image.id,
				callbackFn: function (resp) {
					if (resp.status === 200) {
						//TODO : 성공 실패 테스트 
						tau.alert("시가 등록 되었습니다.");
					} else {tau.alert("시가 등록 되지 못했습니다. 다시 시도해 주세요");}
					ax.ext.ui.hideProgress();
				},
				callbackCtx: this
			}
		});
	},
	
	
	handlePickImage: function (){
		var that = this;
		var scb = function(file){
		   
		    window.deviceapis.filesystem.resolve( 
		        function(file){
		        	that.imageFile = file;
		        	tau.alert(that.imageFile.fullPath);
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
		var opts = {'out': 'images/pickedImage'+ tau.genId('') +'.jpg'};
		ax.ext.media.pickImage(scb, ecb, opts);
		//ax.ext.media.pickImage(scb, ecb);
	},
	
	handleLayout: function (event){
		//TODO 컴포넌트 이슈로 모달 다이얼로그로 변경
		/*
		var popover = new tau.ui.PopoverController({width : '200px', height : '300px'});
		var ctrl = new iampoet.PoemStyleController();
		popover.presentCtrl(ctrl, event.getSource(), {
			masking : true
		});
		*/
		
		this.presentModal(this.stylingCtrl,{layout: 'FULL', 'animate': 'vertical'});
		
		
	},
	
	handleModalDismiss: function (event, payload) {
		this.dismissModal();
	},
	
	handleApplyStyling: function (evnet, payload) {
		this.dismissModal(true);
		event.stopPropogate();
	}
	
});