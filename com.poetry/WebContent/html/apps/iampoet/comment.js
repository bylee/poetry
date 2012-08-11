
$class('iampoet.CommentController').extend(tau.ui.SceneController).define(
{
  CommentController: function(opts) {
		this.setTitle('Comment');
		this.targetId = opts.targetId;
		this.poemCtrl = opts.poemCtrl;
	},
	
	init: function (){
		
	},
	
	loadScene: function (){
		var scene = this.getScene();
		var editor = new tau.ui.TextArea({
			id : 'editor',
			styles: {
				width: '100%',
				height: '300px'
			},
			placeholderLabel: '커맨트를 입력해 주세요.'
		});
		scene.add(editor);
	},
	
	sceneLoaded : function () {
		var summitBtn = new tau.ui.Button({
			label : '전송'
		});
		summitBtn.onEvent('tap',this.handleSummit, this);
		var navBar = this.getNavigationBar();
		
		navBar.setRightItem(summitBtn);
	},
	
	handleSummit: function (){
		var that = this;
		var seqNavi = this.getParent();
		var scene = this.getScene();
		var editor = scene.getComponent('editor');
		tau.wreq({
			type: 'POST',
			url: '/reply',
			params: {
				targetId: this.targetId,
				contents: encodeURIComponent(editor.getText())
			},
			callbackFn : function (resp) {
				if (resp.status === 200) {
					that.poemCtrl.handleReloadComment(); 
					seqNavi.popController();
					tau.alert("커맨트가 등록 되었습니다.");
				} else {tau.alert("커맨트가 등록 되지 못했습니다. 다시 시도해 주세요");}
			}
		});
	}
	
});