$require('/poemstyle.js');
$class('iampoet.WriteformController').extend(tau.ui.SceneController).define({
	WriteformController: function (opts){
		this.setTitle("Write");
		this.writeType = opts.mission.type;
		this.imageId = opts.mission.imageId;
		this.parentCtrl = opts.mission.parentCtrl;
	},
 
	init: function (){
		this.writefont = poetutil.fonts[4];
		this.writefontcolor = 'black';
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
		var config = tau.getConfig();
  	  	var rootURL = config.rootURL;
  	  	
		if (this.writeType == 'mission') {
		  var cameraBtn = scene.getComponent('pickImg');
		  cameraBtn.setVisible(false);
		  var imageView = scene.getComponent('mainImage');
		  imageView.setSrc(rootURL+'/binary/'+this.imageId);
		}
		
		var submitBtn = new tau.ui.Button({
			label : '전송'
		});
		submitBtn.onEvent('tap',this.handleSummit, this);
		var navBar = this.getNavigationBar();
		
		navBar.setRightItem(submitBtn);
		
		var title = scene.getComponent('title');
		title.setStyle('font-family',this.writefont.font);
		title.setStyle('font-size',this.writefont.titlesize);
		title.setStyle('color', this.writefontcolor);
		var contents = scene.getComponent('contents');
		contents.setStyle('font-family',this.writefont.font);
		contents.setStyle('font-size',this.writefont.contentsize);
		contents.setStyle('color',this.writefontcolor);
	},
	
	handleSummit: function () {
		var config = tau.getConfig();
  	  	var rootURL = config.rootURL;
  	  	var params = {'param1':'param1 value'};
  	    var files = {'uploadFile' : this.imageFile};
  	  	var that = this;
		//ax.ext.ui.showProgress('Loading...');
  	  if (this.writeType == 'mission') {
  		that.handleSubmitPoetry({id : this.imageId});
  	  } else {
  		if (this.imageFile != null) {
  			ax.ext.net.upload(
  	  	  			rootURL + '/binary',
  	  	  			params,
  	  	  			files,
  	  	  			function (resp) {
  				  	  	if (resp.status === 200) {
  							that.handleSubmitPoetry(tau.parse(resp.data));
  						} else {tau.alert("시의 사진이 등록 되지 못했습니다. 다시 시도해 주세요");}
  	  	  			},
  	  	  			function (e) {
  	  	  				tau.alert("시의 사진이 등록 되지 못했습니다. 다시 시도해 주세요.");
  	  	  			}
  			);
  		} else { 
  			that.handleSubmitPoetry();
  		}
  		
  	  }
  	  	
	},
	
	handleSubmitPoetry: function (image) {
		var that = this;
		var scene = this.getScene();
		var title = scene.getComponent('title');
		var contents = scene.getComponent('contents');
		var titlesize = 
			this.writefont.titlesize.substr(
					0,
					this.writefont.titlesize.length-2);
		var contentsize = 
			this.writefont.contentsize.substr(
					0,
					this.writefont.contentsize.length-2);
		var params = {
			title: encodeURIComponent(title.getText()),
			titleFont : this.writefont.font,
			titleSize : titlesize,
			titleColor: this.writefontcolor,
			contents: encodeURIComponent(contents.getText()),
			contentsFont : this.writefont.font,
			contentsSize : contentsize,
			contentsColor: this.writefontcolor,
			where: this.writeType	
		};
		
		/*
		var params = {
				title: title.getText(),
				contents: contents.getText(),
				where: this.writeType	
		};
		*/
		if (image != null) {
			params.image = image.id;
		}
		tau.wreq({
			type: 'POST',
			url: '/poetry',
			params: params,
			callbackFn: function (resp) {
					if (resp.status === 200) {
						//TODO : 성공 실패 테스트 
						if (this.parentCtrl != null) {
							this.parentCtrl.loadingRefresh();
						}
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
		this.popover = new tau.ui.PopoverController({width : '200px', height : '200px'});
		var ctrl = new iampoet.PoemStyleController();
		this.popover.presentCtrl(ctrl, event.getSource(), {
			masking : true
		});
		this.popover.onEvent('changeColor',this.handleChangeColor,this);
		this.popover.onEvent('changeFont',this.handleChangeFont,this);
		
	},
	
	handleChangeColor: function (event, color) {
		var scene = this.getScene();
		var title = scene.getComponent('title');
		title.setStyle('color', color);
		var contents = scene.getComponent('contents');
		contents.setStyle('color',color);
		this.writefontcolor = color;
	},
	
	handleChangeFont: function (event, font) {
		var scene = this.getScene();
		var fonts = poetutil.fonts;
		var targetFont = null;
		for (var index in fonts) {
			if (fonts[index].font == font) {
				targetFont = fonts[index];
			}
		}
		this.writefont = targetFont;
		
		var title = scene.getComponent('title');
		title.setStyle('font-family',targetFont.font);
		title.setStyle('font-size',targetFont.titlesize);
		var contents = scene.getComponent('contents');
		contents.setStyle('font-family',targetFont.font);
		contents.setStyle('font-size',targetFont.contentsize);

	},
	
	
});