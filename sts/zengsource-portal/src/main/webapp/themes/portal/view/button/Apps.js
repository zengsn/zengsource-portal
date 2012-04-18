// portal/view/Button.js
Ext.define('JXP._core.view.button.Apps', {
	extend : 'Ext.button.Button',

	requires : [ ],

	alias : 'widget.appsbutton',

	//id : 'zerolink-button',
	config : {},
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
			//xtype: 'splitbutton',
			text: '应用(<b>8</b>)',
			//scale: 'medium',
			iconAlign: 'top',
			arrowAlign: 'right',
			icon: ROOT_PATH+'/apps/resources/images/apps_16.png',
			menu: [{
				text: '记事本',
				xtype: 'linkitem',
				target: '_self',
				href: ROOT_PATH+'/notepad/'
			}, {
				text: '提问题'
			}, {
				text: '发起任务',
				xtype: 'linkitem',
				target: '_self',
				href: ROOT_PATH+'/task/'
			}, '-', {
				text: '应用管理',
				xtype: 'linkitem',
				target: '_self',
				href: ROOT_PATH+'/apps/'
			}, {
				text: '应用请求'
			}]
		});
		this.callParent(arguments);
	}
}, function() {
	// after create ...
});