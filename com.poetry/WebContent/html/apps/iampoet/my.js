$require('/mypoem.js');
$require('/myclip.js');
$require('/following.js');
$require('/follower.js');

$class('iampoet.MyController').extend(tau.ui.SceneController).define({
	MyController: function (opts){
		this.setTitle('my');
	},
 
	init: function () {
		
	},
	
	destroy: function () {
		
	},
	
	sceneLoaded: function () {
		var scene = this.getScene();
		var writeBtn = scene.getComponent('write');
		this.getNavigationBar().setRightItem(writeBtn);
		
		var logout = scene.getComponent('logout');
		logout.onEvent('tap', this.handleLogout, this);
		
		this.getMyInfo();
		
		var myPoemInfo = scene.getComponent('myPoemInfo');
		myPoemInfo.onEvent('tap', this.gotoMyPoem, this);
		
		var myClipPoemInfo = scene.getComponent('clipPoemInfo');
		myClipPoemInfo.onEvent('tap', this.gotoMyClipPoem, this);
		
		var myFollowingInfo = scene.getComponent('followingInfo');
		myFollowingInfo.onEvent('tap', this.gotoFollowing, this);
		
		var myFollowerInfo = scene.getComponent('followerInfo');
		myFollowerInfo.onEvent('tap', this.gotoFollower, this);
		
	},
	
	handleLogout:function() {
		tau.wreq({
			type: 'GET',
			url: '/service/signout',
	    	callbackFn: this.handleLogout2,
	    	callbackCtx : this
		});
	},
	
	handleLogout2 : function() {
		tau.util.setCookie('name', null, -1);
		this.getParent().getParent().setIndex(0);
	},
	
	handleWrite: function (){
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.WriteformController());
	},
	
	getMyInfo: function (){
		var scene = this.getScene();
		var name = tau.util.getCookie('name');
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name,
			callbackFn : function (resp) {
				if (resp.status === 200) {
					scene.getComponent('myName').setText(resp.data.username);
					scene.getComponent('myPenName').setText(resp.data.penName);
					var mpc = scene.getComponent('myPoemCount').setText(resp.data.theNumberOfPoetries);
					var cpc = scene.getComponent('myClipPoemCount').setText(resp.data.theNumberOfBookmarks);
					var cpc = scene.getComponent('followingCount').setText(resp.data.theNumberOfFollowings);
					var cpc = scene.getComponent('followerCount').setText(resp.data.theNumberOfFollowers);
					
				} else { tau.alert("에러")};
			}
		});
	},
	
	gotoMyPoem:function() {
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.MyPoemController());		
	},
		
	gotoMyClipPoem:function() {
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.MyClipController());		
	},
		
	gotoFollowing:function() {
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.FollowingController());		
	},
		
	gotoFollower:function() {
		var seqNavi = this.getParent();
		seqNavi.pushController(new iampoet.FollowerController());		
	},
		
	
});