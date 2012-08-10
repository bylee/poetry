$class('iampoet.PoemStyleController').extend(tau.ui.SceneController).define({
	
	init: function () {
		
	},
	
	loadScene: function () {
		var scene = this.getScene();
		var fonts = poetutil.fonts;
		var carousel = new tau.ui.Carousel({
			styles : {
				backgroundColor : 'gray',
				height : '80%',
				textAlign : 'center'
			}
		});
		scene.add(carousel);
		for (var index in fonts) {
			var panel = new tau.ui.Panel({
				styles : {
					'font-family' : fonts[index].font,
				}
			});
			carousel.add(panel);
			var title = new tau.ui.Label({
				text : '제목입니다.',
				styles : {
					display : 'block',
					'font-size' : fonts[index].titlesize,
					'margin' : '10px'
				}
			});
			panel.add(title);
			var content = new tau.ui.Label({
				text  : '시 내용이 들어가는 본문입니다.',
				styles : {
					display : 'block',
					'font-size' : fonts[index].contentsize
				}
			});
			panel.onEvent('tap',function (e){
				var font = e.getSource().getStyle('font-family');
				this.fireEvent('changeFont',font);
			},this);
			panel.add(content);
			
		}
		
		var colorpanel = new tau.ui.Panel({
			styles : {
				width : '100%',
				marginTop : '10px',
				textAlign : 'center'
				
			}
		});
		scene.add(colorpanel);
		var colors = ['black','white','blue','orange','green','red'];
		for (var index in colors) {
			var btn = new tau.ui.Button({
				text : '',
				styles : {
					width : '20px',
					height : '20px',
					border : '2px solid gray',
					margin : '2px',
					backgroundColor : colors[index]
				}
			});
			btn.onEvent('tap',function (e){
				var color = e.getSource().getStyle('background-color');
				this.fireEvent('changeColor',color);
			},this);
			colorpanel.add(btn);
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