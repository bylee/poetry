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
			url: tau.resolveURL('../login'),
			params: {
				id: id,
				pw: pw
			},
			callbackFn: function (resp) {
				tau.log(resp);
			}
		});
		
		req.send();
	}
});