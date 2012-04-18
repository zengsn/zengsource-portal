// portal/view/Button.js
Ext.define('JXP._core.view.button.Social', {
	extend : 'Ext.button.Button',

	requires : [ ],

	alias : 'widget.socialbutton',

	//id : 'zerolink-button',
	config : {},
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
			//xtype: 'splitbutton',
			text: '关系(<b>3</b>)',
			//scale: 'medium',
			iconAlign: 'top',
			arrowAlign: 'right',
			icon: ROOT_PATH+'/social/resources/images/social_16.png',
			menu: [{
				xtype: 'linkitem',
				text: '我的好友',
				target: '_self',
				href: ROOT_PATH+'/social/friend.jxp'
			}, {
				xtype: 'linkitem',
				text: '好友请求',
				target: '_self',
				href: ROOT_PATH+'/social/request.jxp'
			}, {
				xtype: 'linkitem',
				text: '查找好友',
				target: '_self',
				href: ROOT_PATH+'/social/find.jxp'
			}, '-', {
				xtype: 'linkitem',
				text: '邀请好友',
				target: '_self',
				href: ROOT_PATH+'/social/invite.jxp'
			}, {
				xtype: 'linkitem',
				text: '第三方接入',
				target: '_self',
				href: ROOT_PATH+'/social/connect.jxp'
			}]
		});
		this.callParent(arguments);
	}
}, function() {
	// after create ...
});