function initScene() {
	var scene = this.getScene();
	var scrollPanel1 = new tau.ui.ScrollPanel({id : 'mainPanel'});
	scene.add(scrollPanel1);
	var imageView1 = new tau.ui.ImageView({id : 'today-pic' , styles : {height: '286px', margin: '', width: '100%'}});
	scrollPanel1.add(imageView1);
	var textView1 = new tau.ui.TextView({id : 'today-text' , styles : {WebkitBorderRadius: '14px', backgroundImage: '-webkit-gradient(linear, left top, left bottom,from(#EAF4D6),to(#FFFFFF))', borderStyle: 'solid', borderWidth: '1px', display: 'block', height: '124px'}});
	scrollPanel1.add(textView1);
	var panel1 = new tau.ui.Panel({align : 'center' , id : 'wBtnPanel' , styles : {WebkitBoxAlign: 'center', WebkitBoxOrient: 'Vertical', display: '-webkit-box', height: '65px', position: '', textAlign: '', width: '100%'}});
	scrollPanel1.add(panel1);
	var button1 = new tau.ui.Button({styles : {display: 'block', marginTop: '10px', width: '240px'} , label : {normal: '작성하기'}});
	panel1.add(button1);
	button1.onEvent('tap', this.handleWriteView, this);
	var textArea1 = new tau.ui.TextArea({id : 'writeTextarea' , placeholderLabel : '시를 작성해 주세요.' , vScroll : true , visible : false , styles : {WebkitBorderRadius: '12px', backgroundImage: '-webkit-gradient(linear, left top, left bottom,from(#DFEAF8),to(#FFFFFF))', display: 'block', height: '150px'}});
	scrollPanel1.add(textArea1);
	var panel2 = new tau.ui.Panel({id : 'btnPanel' , visible : false , styles : {WebkitBoxOrient: 'Horizontal', WebkitBoxPack: 'center', display: '-webkit-box', position: '', width: '100%'}});
	scrollPanel1.add(panel2);
	var button2 = new tau.ui.Button({id : 'cancelBtn' , styles : {display: 'block', marginRight: '20px', width: '100px'} , label : {normal: '취소'}});
	panel2.add(button2);
	button2.onEvent('tap', this.handleCancel, this);
	var button3 = new tau.ui.Button({styles : {display: 'block', marginLeft: '20px', width: '100px'} , label : {normal: '전송'}});
	panel2.add(button3);
	button3.onEvent('tap', this.handleSubmit, this);
}