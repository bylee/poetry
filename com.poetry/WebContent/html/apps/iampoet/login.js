$class('iampoet.LoginController').extend(tau.ui.SceneController).define({
	LoginController: function (opts){
		
	},
 
	init: function () {
		
	},
	
	destroy: function () {
		
	},
	
	sceneLoaded: function () {
		var scene = this.getScene();
		//scene.getComponent('cancel').onEvent(tau.rt.Event.TAP, this.fireDismiss, this);
		scene.getComponent('login').onEvent(tau.rt.Event.TAP, this.handleLogin, this);
	},
	
	fireDismiss: function () {
		this.fireEvent('dismiss');
	},
	
	handleLogin: function () {
		var scene = this.getScene();
		var id = scene.getComponent('id').getText();
		var pw = scene.getComponent('pw').getText();
		//var remember = scene.getComponent('remember').getValue();
		
		/*
		var req = tau.req({
			type: 'POST',
			//url: tau.resolveURL('../login'),
			//url: '../login',
			url: '../service/signin',
			params: {
			  j_username: id,
			  j_password: pw
			},
			callbackFn: tau.ctxAware(this.handleLoginCallBack, this)
		});
		
		req.send();
		*/
		var config = tau.getConfig();
   	  	var reqType = config.reqType;
   	  	var rootURL = config.rootURL;
   	  	var that = this;
		switch (reqType) {
		case "appspresso" :
			
			var req = tau.req(
					{
						type: 'JSONP',
						url: rootURL+'/service/signin?j_username='+id+'&j_password='+pw,
						jsonpCallback: 'callbackFn',
						timeout: 500000,
						callbackFn: function (resp) {
							var response = {
									status : resp.status,
									data : resp.responseJSON
							};
							that.handleLoginCallBack(response);
						}
					}
			);
			req.send();
			
			/*
			tau.wreq({
				type: 'POST',
				url: '/service/signin',
				params: { 
			    	j_username: id,
			    	j_password: pw 
		    	},
		    	callbackFn: this.handleTest,
		    	callbackCtx : this
				
			});
			*/
			break;
		default :
			tau.wreq({
				type: 'POST',
				url: '/service/signin',
				params: { 
			    	j_username: id,
			    	j_password: pw 
		    	},
		    	callbackFn: this.handleLoginCallBack,
		    	callbackCtx : this
				
			});
		}
	},
	
	handleTest: function () {
		
	},
	
	handleLoginCallBack: function (resp) {
		if (resp.status === 200) {
			if (resp.data.status === 'success'){
				//tau.util.setCookie('jsessionid', resp.data);
				tau.util.setCookie('name', resp.data.username);
				tau.util.setCookie('session', resp.data.jsessionid);
				tau.wreq({
					type: 'GET',
					url : '/today/' + poetutil.getPoetDate(),
					callbackFn : this.handleMain,
					callbackCtx : this
				});  
			} else { tau.alert('로그인 실패');}
		}else {tau.alert('로그인 실패 \n [Error] error code' + resp.status);}
	},
	
	handleMain: function (resp) {
	  if (resp.status === 200) {
		  //TOOD 서버쪽 수정요청 했음!
		  //if (data.status === 'success') {
			  this.fireEvent('dismiss');
			  this.fireEvent('todayData', resp.data);
		  //} else {tau.alert('초기 데이타 로딩 실패');}
		  
	  } else {
		  tau.alert('초기 데이타 로딩 실패 : error code' + resp.status);
	  }
	}
});