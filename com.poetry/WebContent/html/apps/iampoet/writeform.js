$require('/poemstyle.js');
$class('iampoet.WriteformController').extend(tau.ui.SceneController).define({
	WriteformController: function (opts){
		this.setTitle("Write");
		this.writeType = opts.mission.type;
		this.imageId = opts.mission.imageId;
	},
 
	init: function (){
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
		
		if (this.writeType == 'mission') {
		  var cameraBtn = scene.getComponent('pickImg');
		  cameraBtn.setVisible(false);
		}
		
		var submitBtn = new tau.ui.Button({
			label : '전송'
		});
		submitBtn.onEvent('tap',this.handleSummit, this);
		var navBar = this.getNavigationBar();
		
		navBar.setRightItem(submitBtn);
		
	},
	
	handleSummit: function () {
		var config = tau.getConfig();
  	  	var rootURL = config.rootURL;
  	  	var params = {'param1':'param1 value'};
  	    var files = {'uploadFile' : this.imageFile};
  	  	var that = this;
		//ax.ext.ui.showProgress('Loading...');
  	  if (this.writeType == 'mission') {
  		that.handlePoetry({id : this.imageId});
  	  } else {
  		if (this.imageFile != null) {
  			ax.ext.net.upload(
  	  	  			rootURL + '/binary',
  	  	  			params,
  	  	  			files,
  	  	  			function (resp) {
  				  	  	if (resp.status === 200) {
  							that.handlePoetry(tau.parse(resp.data));
  						} else {tau.alert("시의 사진이 등록 되지 못했습니다. 다시 시도해 주세요");}
  	  	  			},
  	  	  			function (e) {
  	  	  				tau.alert("시의 사진이 등록 되지 못했습니다. 다시 시도해 주세요.");
  	  	  			}
  			);
  		} else { tau.alert('사진을 업로드 해주세요~! ');}
  		
  	  }
  	  	
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
				title: encodeURIComponent(title.getText()),
				contents: encodeURIComponent(contents.getText()),
				image: image.id,
				where: this.writeType
			},
			callbackFn: function (resp) {
					if (resp.status === 200) {
						//TODO : 성공 실패 테스트 
						tau.alert("시가 등록 되었습니다.");
					} else {tau.alert("시가 등록 되지 못했습니다. 다시 시도해 주세요");}
					//ax.ext.ui.hideProgress();
			},
			callbackCtx: this
			
		});
	},
	
	
	handlePickImage: function (){
		var that = this;
		var successFn = function (file) {
			that.imageFile = file;
			window.deviceapis.filesystem.resolve( 
			        function(file){
			        	that.getScene().getComponent('mainImage')
			        	.getDOM().setAttribute('src', file.toURI());
			        }, function(e){ 
			            console.log('resolve err:' + e.message);
			        },
			        file,
			        "r"
			    );
		};
		var errorFn = function(e){
		    tau.alert("사진이 선택되지 못했습니다. 다시 선택해주세요. [" + e.message + "]");
		};
		ax.ext.media.pickImage(successFn, errorFn);
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