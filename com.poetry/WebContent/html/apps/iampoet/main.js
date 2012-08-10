$require('/default.js');
$require('/writer.js');
$require('/todayimage.js');
$require('/todaymission.js');
$require('/newsfeed.js');
$require('/setting.js');
$require('/my.js');

(function (global) {
  if (typeof tau.util != 'undefined') {
    poetutil = {
        getPoetDate : function() {
        	var dateObj = new Date();
            var month = dateObj.getMonth(), date = dateObj.getDate();
            if (month < 9) {
              month = '0' + (month + 1);
            } 
            if (date < 10) {
              date = '0' + date;
            }
            var dateStr = dateObj.getFullYear() + '-' + month + '-' + date;
            return dateStr;
        },
        preloadImage : function (url, onloadFn) {
        	var img = new Image ();
			img.onload = onloadFn;
            img.src = url;
        },
        handleImageAni: function (data) {
    		var imgComp = data.comp;
    		var dom = imgComp.getDOM();
    		var time = (data.time == null? '20s':data.time);
    		
    		var movingDistance = data.ratio*data.width - dom.clientWidth;
    		if (movingDistance > 0) {
    			
    			var endlistener = function( event ) {
    				dom.removeEventListener('webkitAnimationEnd',endlistener, false);
    				dom.addEventListener( 
    						'webkitAnimationEnd',
    						startlistener,
    						false 
    				);
    				var ani2 = new tau.fx.Animation({
    					   from : {'background-position': -movingDistance +'px 0px'},
    					   to : {'background-position': '0px 0px'}
    					   }, {
    					     timingFunction : 'linear',
    					     duration : time, //시간 경과 설정
    					     override : true, //애니메이션한 후 속성 유지
    					     iterationCount : 1 //반복횟수
    			     });
    				ani2.animate(dom);
    			};
    			dom.addEventListener( 
    					'webkitAnimationEnd',
    					endlistener,
    					false 
    			);
    			
    			var startlistener = function () {
    				dom.removeEventListener('webkitAnimationEnd',startlistener, false);
    				dom.addEventListener( 
    						'webkitAnimationEnd',
    						endlistener,
    						false 
    				);
    				var ani = new tau.fx.Animation({
    					   from : {'background-position': '0px 0px'},
    					   to : {'background-position': -movingDistance +'px 0px'}
    					   }, {
    					     timingFunction : 'linear',
    					     duration : time, //시간 경과 설정
    					     override : true, //애니메이션한 후 속성 유지
    					     iterationCount : 1 //반복횟수
    					     });
    				ani.animate(dom);
    			};
    			startlistener();
    		}
    		
    	},
    	
    	fonts : [ 
           {font : "NanumGothicBold", titlesize : '18px', contentsize : '15px'}, 
    	   {font :"NanumGothic", titlesize : '18px', contentsize : '15px'},
    	   {font : "NanumMyeongjo", titlesize : '18px', contentsize : '15px'},
    	   {font : "NanumMyeongjoBold", titlesize : '18px', contentsize : '15px'},
    	   {font : "NanumPen", titlesize : '30px', contentsize : '23px'},
           {font : "NanumBrush", titlesize : '30px', contentsize : '23px'}
        ],
        
        todayfonts : [ 
                 {font : "NanumGothicBold", titlesize : '13px', contentsize : '10px'}, 
          	   {font :"NanumGothic", titlesize : '13px', contentsize : '10px'},
          	   {font : "NanumMyeongjo", titlesize : '13px', contentsize : '10px'},
          	   {font : "NanumMyeongjoBold", titlesize : '13px', contentsize : '10px'},
          	   {font : "NanumPen", titlesize : '20px', contentsize : '15px'},
                 {font : "NanumBrush", titlesize : '20px', contentsize : '15px'}
        ],
        
        settingPoetFontStyle: function (titlecmp, contentcmp, poet) {
        	var fontSetting = poetutil.fonts[4];
    		fontSetting.color = 'white';
    		if (poet.titleFont != null) {
    			fontSetting.color = poet.titleColor;
    			fontSetting.font = poet.titleFont;
    			fontSetting.titlesize = poet.titleSize + 'px';
    			fontSetting.contentsize = poet.contentsSize + 'px';
    		}
    		if (titlecmp != null) {
    			titlecmp.setStyle('font-family', fontSetting.font);
        		titlecmp.setStyle('font-size', fontSetting.titlesize);
        		titlecmp.setStyle('color',fontSetting.color);
    		}
    		
    		if (contentcmp != null) {
    			contentcmp.setStyle('font-family', fontSetting.font);
        		contentcmp.setStyle('font-size', fontSetting.contentsize);
        		contentcmp.setStyle('color',fontSetting.color);
    		}
    		
        },
        
        settingTodayPoetFontStyle: function (titlecmp, contentcmp, poet) {
        	var fontSetting = poetutil.todayfonts[5];
    		fontSetting.color = 'white';
    		if (poet.titleFont != null) {
    			fontSetting.color = poet.titleColor;
    			fontSetting.font = poet.titleFont;
    			fontSetting.titlesize = poet.titleSize + 'px';
    			fontSetting.contentsize = poet.contentSize + 'px';
    		}
    		if (titlecmp != null) {
    			titlecmp.setStyle('font-family', fontSetting.font);
        		titlecmp.setStyle('font-size', fontSetting.titlesize);
        		titlecmp.setStyle('color',fontSetting.color);
    		}
    		
    		if (contentcmp != null) {
    			contentcmp.setStyle('font-family', fontSetting.font);
        		contentcmp.setStyle('font-size', fontSetting.contentsize);
        		contentcmp.setStyle('color',fontSetting.color);
    		}
    		
        },
        calcLevel: function (user) {
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
    };
  }
}) (window);

/**
 * tab bar based application main controller
 */
$class('iampoet.MainController').extend(tau.ui.ParallelNavigator).define({
	MainController: function(opts) {
		
	},
	
	init: function (){
		this.setControllers([
		                     new iampoet.TodayNavigator(),
		                     new iampoet.TodayMissionNavigator(),
		                     new iampoet.NewsFeedNavigator(),
		                     new iampoet.MyNavigator()
		                     ]);
		
		this.appCtx = tau.getCurrentContext();
    var tabs = this.appCtx.getConfig().tabs;
		var tabBar = this.getTabBar();
    var tabcomps = tabBar.getComponents();
    for (i in tabs) {
      var tabcomp = tabcomps[i];
      var backImage = {
        normal: tabs[i].icon,
        selected: tabs[i].selectedIcon,
        //selected: tabs[i].icon,
        disabled: tabs[i].icon,
        highlighted: tabs[i].icon
      };
      tabcomp.setBackgroundImage(backImage);
      tabcomp.setStyles({
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'top center',
        backgroundSize: '30px'
      });
      tabcomp.setLabel(tabs[i].title);
    }
		
		
		
	},
	
	destroy: function (){
		
	}
});

$class('iampoet.TodayNavigator').extend(tau.ui.SequenceNavigator).define({
	TodayNavigator: function (opt) {
		this.setTitle('Today');
	},
	
	init: function () {
		this.setRootController(new iampoet.TodayImageController(),{hideNavigationBar : true});
		
	},
	
	destroy: function () {
		
	}
});

$class('iampoet.TodayMissionNavigator').extend(tau.ui.SequenceNavigator).define({
	TodayMissionNavigator: function (opt) {
		this.setTitle('Mission');
	},
	
	init: function () {
		this.setRootController(new iampoet.TodayMissionController());
	},
	
	destory: function () {
		
	}
});

$class('iampoet.NewsFeedNavigator').extend(tau.ui.SequenceNavigator).define({
	NewsFeedNavigator: function (opt) {
		this.setTitle('NewsFeed');
	},
	
	init: function () {
		this.setRootController(new iampoet.NewsFeedController());
	},
	
	destory: function () {
		
	}
});

$class('iampoet.SettingNavigator').extend(tau.ui.SequenceNavigator).define({
	SettingNavigator: function (opt) {
		this.setTitle('Setting');
	},
	
	init: function () {
		this.setRootController(new iampoet.SettingController());
	},
	
	destory: function () {
		
	}
});

$class('iampoet.MyNavigator').extend(tau.ui.SequenceNavigator).define({
	SettingNavigator: function (opt) {
		this.setTitle('My');
	},
	
	init: function () {
		this.setRootController(new iampoet.MyController({}));
	},
	
	destory: function () {
		
	}
});
