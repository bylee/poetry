$class('iampoet.LoginController').extend(tau.ui.SceneController).define({
	LoginController: function (opts){
		
	},
 
	init: function () {
		
	},
	
	destroy: function () {
		
	},
	
	sceneLoaded: function () {
		var scene = this.getScene();
		scene.getComponent('cancel').onEvent(tau.rt.Event.TAP, this.fireDismiss, this);
		scene.getComponent('login').onEvent(tau.rt.Event.TAP, this.handleLogin, this);
	},
	
	fireDismiss: function () {
		this.fireEvent('dismiss');
	},
	
	handleLogin: function () {
		var scene = this.getScene();
		var id = scene.getComponent('id').getText();
		var pw = scene.getComponent('pw').getText();
		var remember = scene.getComponent('remember').getValue();
		
		
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
	},
	
	handleLoginCallBack: function (resp) {
	  var resJson = resp.responseJSON;
	  if (resJson.status === 'success') {
	    var req = tau.req({
	      type: 'GET',
	      url: '../today/20120712',
	      callbackFn: tau.ctxAware(this.handleMain, this)
	    });
	    
	    req.send();
	    
	  } else {
	    tau.alert("Login fail");
	  }
	},
	
	handleMain: function (resp) {
	  this.fireEvent('dismiss');
	  var resJson = resp.responseJSON;
	  this.fireEvent('todayData', resJson);
	  tau.alert('login success and today data!!');
	  tau.log(resJson);
	}
});