$class('iampoet.WriterController').extend(tau.ui.SceneController).define({
	WriterController: function (opts){
		this.setTitle("작성하기");
	},
 
	init: function (){
		//이미지랑 텍스트가 서버에서 넘어와야 나머지를 처리 할 수 있다.
		this.todaytext = "오늘의 주제는 어쩌구 저쩌구 ~~" +
				"오늘의 주제는 어쩌구 저쩌구 ~~" +
				"오늘의 주제는 어쩌구 저쩌구 ~~" +
				"오늘의 주제는 어쩌구 저쩌구 ~~" +
				"오늘의 주제는 어쩌구 저쩌구 ~~" +
				"오늘의 주제는 어쩌구 저쩌구 ~~" +
				"오늘의 주제는 어쩌구 저쩌구 ~~" +
				"오늘의 주제는 어쩌구 저쩌구 \n" +
				"오늘의 주제는 어쩌구 저쩌구 \n" +
				"오늘의 주제는 어쩌구 저쩌구 ";
		this.todaypicURL = "http://pds.egloos.com/pds/1/200501/08/50/b0050950_21324574.jpg";
	},
	
	sceneLoaded: function () {
		var scene = this.getScene();
		var todayPic = scene.getComponent('today-pic');
		var todayText = scene.getComponent('today-text');
		todayText.setText(this.todaytext);
		todayText.onEvent(
				tau.rt.Event.TOUCHMOVE,
				this.handleScrollStopPropagation,
				this);
		todayPic.setSrc(this.todaypicURL);
		var btnPanel = scene.getComponent('btnPanel');
		var wTextArea = scene.getComponent('writeTextarea');
		wTextArea.setVisible(false);
		btnPanel.setVisible(false);
		
	},
	
	destroy: function (){
		
	},
	
	handleScrollStopPropagation: function (event) {
		event.stopPropagation();
	},
	
	handleWriteView: function () {
		var scene = this.getScene();
		var wBtnPanel = scene.getComponent('wBtnPanel');
		wBtnPanel.setVisible(false);
		var btnPanel = scene.getComponent('btnPanel');
		var wTextArea = scene.getComponent('writeTextarea');
		wTextArea.setVisible(true);
		btnPanel.setVisible(true);
		var scrollpanel = scene.getComponent('mainPanel');
		scrollpanel.refresh();
		
	},
	
	handleCancel: function () {
		var scene = this.getScene();
		var wBtnPanel = scene.getComponent('wBtnPanel');
		wBtnPanel.setVisible(true);
		var btnPanel = scene.getComponent('btnPanel');
		var wTextArea = scene.getComponent('writeTextarea');
		wTextArea.setVisible(false);
		btnPanel.setVisible(false);
		var scrollpanel = scene.getComponent('mainPanel');
		scrollpanel.refresh();
	},
	
	handleSubmit: function () {
		
	}
});