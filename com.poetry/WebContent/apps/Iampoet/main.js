$require('/default.js');
$require('/writer.js');
$require('/todayimage.js');
$require('/todaymission.js');
$require('/newsfeed.js');
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
		                     new iampoet.NewsFeedNavigator()
		                     ]);
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
