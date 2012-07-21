$require('/default.js');
$require('/writer.js');
$require('/todayimage.js');
$require('/todaymission.js');
$require('/newsfeed.js');
$require('/setting.js');
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
		                     new iampoet.SettingNavigator()
		                     ]);
		
		this.appCtx = tau.getCurrentContext();
    var tabs = this.appCtx.getConfig().tabs;
		var tabBar = this.getTabBar();
    var tabcomps = tabBar.getComponents();
    for (i in tabs) {
      var tabcomp = tabcomps[i];
      var backImage = {
        normal: tabs[i].icon,
        //selected: tabs[i].selectedIcon,
        selected: tabs[i].icon,
        disabled: tabs[i].icon,
        highlighted: tabs[i].icon,
      };
      tabcomp.setBackgroundImage(backImage);
      tabcomp.setStyles({
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'top center'
      });
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
		this.setRootController(new iampoet.TodayImageController());
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
