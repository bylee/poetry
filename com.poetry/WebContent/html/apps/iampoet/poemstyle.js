$class('iampoet.PoemStyleController').extend(tau.ui.SceneController).define({
	
	init: function () {
		
	},
	
	loadScene: function () {
		var scene = this.getScene();
		
		scene.add(new tau.ui.ToolBar({
			dock: tau.ui.ToolBar.TOP_DOCK,
			components: [
			   new tau.ui.Button({label: '취소', tap : tau.ctxAware(this.handleCancel, this)}),
			   new tau.ui.Label({text: 'Styling'}),
			   new tau.ui.Button({label: '적용', tap : tau.ctxAware(this.handleApply, this)}),
			 ]
		}));
		var titlePanel = new tau.ui.Panel({});
		var titleSizeSpin = new tau.ui.Spinner({
			step : 1,
			min : 5,
			max : 30,
			readonly : false
		});
		titleSizeSpin.onEvent(tau.rt.Event.VALUECHANGE,this.handleTitleFontSize, this);
		var titleFontFamilySelect = new tau.ui.Select({
			components: [
			             {label : 'Font A'},
			             new tau.ui.Button({label: 'Font B'}),
			             {label : 'Font C'}
			           ],
           placeHolder: 'Font Type',
           popupTitle: 'Font Type',
           fullscreen: false,
           modal: false,
           selectedIndexes: [0],
           maxSelectableCnt: 1
		});
		var titleFontColor = new tau.ui.Select({
		   components: [
			             new tau.ui.Button({label: 'White'}),
			             new tau.ui.Button({label: 'Black'}),
			             new tau.ui.Button({label: 'Blue'})
			           ],
           placeHolder: 'Font Color',
           popupTitle: 'Font Color',
           fullscreen: false,
           modal: false,
           selectedIndexes: [0],
           maxSelectableCnt: 1
		});
		/*
		var titleAlignBtn = new tau.ui.Select({
			   components: [
				              new tau.ui.Button({label: 'Left'}),
				              new tau.ui.Button({label: 'Center'}),
				              new tau.ui.Button({label: 'Right'})
				           ],
	           placeHolder: 'Align',
	           popupTitle: 'Font Align',
	           fullscreen: false,
	           modal: false,
	           selectedIndexes: [0],
	           maxSelectableCnt: 1
		});
		*/
		var titleAlignBtn = new tau.ui.SegmentedButton({
		   components: [
			          new tau.ui.Button({label: 'Left'}),
			          new tau.ui.Button({label: 'Center'}),
			          new tau.ui.Button({label: 'Right'})
			        ],
	       maxSelectableCnt: 1,
	       selectedIndexes: [0]
	    });
	    
		titleAlignBtn.onEvent(tau.rt.Event.VALUECHANGE, this.handleTitleAlign, this);
		
		titlePanel.add(new tau.ui.Label({text: '제목'}));
		titlePanel.add(titleSizeSpin);
		titlePanel.add(titleFontFamilySelect);
		titlePanel.add(titleFontColor);
		titlePanel.add(titleAlignBtn);
		
		var contentPanel = new tau.ui.Panel({});
		var contentSizeSpin = new tau.ui.Spinner({
			step : 1,
			min : 5,
			max : 30,
			readonly : false
		});
		contentSizeSpin.onEvent(tau.rt.Event.VALUECHANGE,this.handleContentFontSize, this);
		var contentFontFamilySelect = new tau.ui.Select({
			components: [
			             {label : 'Font A'},
			             new tau.ui.Button({label: 'Font B'}),
			             {label : 'Font C'}
			           ],
           placeHolder: 'Font Type',
           popupTitle: 'Font Type',
           fullscreen: false,
           modal: false,
           selectedIndexes: [0],
           maxSelectableCnt: 1
		});
		contentFontFamilySelect.onEvent(tau.rt.Event.VALUECHANGE, this.handleContentFontFamily, this);
		
		var contentFontColor = new tau.ui.Select({
		   components: [
			             new tau.ui.Button({label: 'White'}),
			             new tau.ui.Button({label: 'Black'}),
			             new tau.ui.Button({label: 'Blue'})
			           ],
           placeHolder: 'Font Color',
           popupTitle: 'Font Color',
           fullscreen: false,
           modal: false,
           selectedIndexes: [0],
           maxSelectableCnt: 1
		});
		contentFontColor.onEvent(tau.rt.Event.VALUECHANGE, this.handleContentFontColor, this);
		/*
		var contentAlignBtn = new tau.ui.Select({
			   components: [
				              new tau.ui.Button({label: 'Left'}),
				              new tau.ui.Button({label: 'Center'}),
				              new tau.ui.Button({label: 'Right'})
				           ],
	           placeHolder: 'Align',
	           popupTitle: 'Font Align',
	           fullscreen: false,
	           modal: false,
	           selectedIndexes: [0],
	           maxSelectableCnt: 1
		});
		*/
		var contentAlignBtn = new tau.ui.SegmentedButton({
			components: [
				          new tau.ui.Button({label: 'Left'}),
				          new tau.ui.Button({label: 'Center'}),
				          new tau.ui.Button({label: 'Right'})
				        ],
	       maxSelectableCnt: 1,
	       selectedIndexes: [0]
		});
		
		contentAlignBtn.onEvent(tau.rt.Event.VALUECHANGE, this.handleContentAlign, this);
		
		contentPanel.add(new tau.ui.Label({text: '내용'}));
		contentPanel.add(contentSizeSpin);
		contentPanel.add(contentFontFamilySelect);
		contentPanel.add(contentFontColor);
		contentPanel.add(contentAlignBtn);
		
		var layoutCarousel = new tau.ui.Carousel({
			
		});
		
		scene.add(titlePanel);
		scene.add(contentPanel);
		scene.add(layoutCarousel);
		
		for ( var int = 0; int < 5; int++) {
			var panel = new tau.ui.Panel();
			panel.add(new tau.ui.ImageView({
				src: '/img/application.png',
				styles: {
					    maxWidth: '100px',
					    maxHeight: '100px'
				}
			}));
			layoutCarousel.add(panel);
		}
		
		
	},
	
	sceneLoaded: function () {
		
	},
	
	handleTitleFontSize: function (event) {
		
	},
	
	handleTitleFontFamily: function (event) {
		
	},
	
	handleTitleFontColor: function (event) {
		
	},
	
	handleTitleAlign: function (event) {
		
	},
	
	handleContentFontSize: function (event) {
		
	},
	
	handleContentFontFamily: function (event) {
		
	},
	
	handleContentFontColor: function (event) {
		
	},
	

	handleContentAlign: function (event) {
		
	},
	
	handleCancel: function (event) {
		this.fireEvent('cancelStyling');
	},
	
	handleApply: function (event) {
		this.fireEvent('applyStyling',{});
	}
	
});