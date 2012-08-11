$require('/mypoem.js');
$require('/myclip.js');
$require('/following.js');
$require('/follower.js');

function calcLevel(user) {
	var result = '';
	var lvl = user.theNumberOfBookmarks + user.theNumberOfFollowings + user.theNumberOfFollowers + user.theNumberOfPoetries; 
	if (lvl < 100) {
		result = '초보시인';
	} else if (lvl < 300){
		result = '중수시인';
	} else {
		result = '시인';
	}	
	return result;
}

$class('iampoet.MyController').extend(tau.ui.SceneController).define({
	MyController: function (opts){
		this.curr_name = opts.name;
		var name = tau.util.getCookie('name');
		if (opts.name === name || opts.name == undefined) {
			this.setTitle('My Page');
		} else {
			this.setTitle(opts.penname);
		}
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
		
		var name = tau.util.getCookie('name');
		if (this.curr_name === name || this.curr_name == undefined) {
		} else {
			scene.getComponent('logout').setVisible(false);
		}
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
		seqNavi.pushController(new iampoet.WriteformController({mission :'My Page'}));
	},
	
	getMyInfo: function (){
		var scene = this.getScene();
		var config = tau.getConfig();
    var rootURL = config.rootURL;
    
		var name = tau.util.getCookie('name');
		if (this.curr_name) {
			name = this.curr_name;
		}
		tau.wreq({
			type: 'GET',
			url: '/poet/' + name,
			callbackFn : function (resp) {
				if (resp.status === 200) {
//					scene.getComponent('myName').setText(resp.data.username);
					var imageSrc = '/image/icon-person.png';
					if ((resp.data.icon != null) && (resp.data.icon !== 'null')) {
						imageSrc = rootURL + '/binary/' + resp.data.icon;
			        }
					var ui = scene.getComponent('userIcon');
					ui.setStyle('backgroundImage', 'url(' + imageSrc + ')');
					scene.getComponent('myPenName').setText(resp.data.penName);
					userLevel = calcLevel(resp.data);
					var ul = scene.getComponent('myLevel');
					ul.setText(userLevel);
					var mpc = scene.getComponent('myPoemCount').setText(resp.data.theNumberOfPoetries + '&nbsp;');
					var cpc = scene.getComponent('myClipPoemCount').setText(resp.data.theNumberOfBookmarks + '&nbsp;');
					var cpc = scene.getComponent('followingCount').setText(resp.data.theNumberOfFollowings + '&nbsp;');
					var cpc = scene.getComponent('followerCount').setText(resp.data.theNumberOfFollowers + '&nbsp;');
					
				} else { tau.alert("에러")};
			}
		});
	},
	
	gotoMyPoem:function() {
		var seqNavi = this.getParent();
		var cname = tau.util.getCookie('name');
		if (this.curr_name) {
			cname = this.curr_name;
		}	
		seqNavi.pushController(new iampoet.MyPoemController({name:cname}));		
	},
		
	gotoMyClipPoem:function() {
		var seqNavi = this.getParent();
		var cname = tau.util.getCookie('name');
		if (this.curr_name) {
			cname = this.curr_name;
		}			
		seqNavi.pushController(new iampoet.MyClipController({name:cname}));		
	},
		
	gotoFollowing:function() {
		var seqNavi = this.getParent();
		var cname = tau.util.getCookie('name');
		if (this.curr_name) {
			cname = this.curr_name;
		}	
		seqNavi.pushController(new iampoet.FollowingController({name:cname}));		
	},
		
	gotoFollower:function() {
		var seqNavi = this.getParent();
		var cname = tau.util.getCookie('name');
		if (this.curr_name) {
			cname = this.curr_name;
		}
		seqNavi.pushController(new iampoet.FollowerController({name:cname}));		
	},
		
	
});