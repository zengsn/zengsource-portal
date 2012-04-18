// Utils.js
function XP_Msg(config) {
	config = config || {};
	Ext.require('Ext.ux.window.Notification', function() {
    	Ext.create('Ext.ux.window.Notification', {
			corner  : config.corner   || 'bl',
			manager : config.manager  || Ext.getBody(),
			cls     : config.cls      || 'ux-notification-light',//window',
			iconCls : config.iconCls  || 'ux-notification-icon-information',
			closable: config.closable || true,
			title   : config.title    || '无 Title',
			html    : config.html     || '无 HTML',
			slideInDelay      : config.slideInDelay     || 800,
			slideDownDelay    : config.slideDownDelay   || 1500,
			autoDestroyDelay  : config.autoDestroyDelay || 2000,
			slideInAnimation  : config.slideInAnimation || 'elasticIn',
			slideDownAnimation: config.slideInAnimation || 'elasticIn'
		}).show();
	}); 
}

(function() {
	JXP_console = {
		log : function(obj) {
			try {
				console.log(obj);
			} catch (e) {
				// 异常处理
			}
		},
		dir : function(obj) {
			try {
				console.dir(obj);
			} catch (e) {
				// 异常处理
			}
		}
	};
})();