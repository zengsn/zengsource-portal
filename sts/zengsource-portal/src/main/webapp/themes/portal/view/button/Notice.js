// portal/view/Button.js
Ext.define('JXP._core.view.button.Notice', {
	extend : 'Ext.button.Button',

	requires : [ ],

	alias : 'widget.noticebutton',

	//id : 'zerolink-button',
	config : {},
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
			//xtype: 'splitbutton',
			text: '通知(<b>6</b>)',
			//scale: 'medium',
			iconAlign: 'top',
			arrowAlign: 'right',
			icon: ROOT_PATH+'/notice/resources/images/notice_16.png',
			menu: [{
				text: '好友消息'
			}, {
				text: '系统消息'
			}, '-', {
				text: '通知设置'
			}]
		});
		this.callParent(arguments);
	}
}, function() {
	// after create ...
});