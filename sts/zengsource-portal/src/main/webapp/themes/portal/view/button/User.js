// portal/view/Button.js
Ext.define('JXP._core.view.button.User', {
	extend : 'Ext.button.Button',

	requires : [ ],

	alias : 'widget.userbutton',

	//id : 'zerolink-button',
	config : {},
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
			//text: '用户设置',
			//scale: 'medium',
			//iconAlign: 'top',
			//arrowAlign: 'right',
			//icon: ROOT_PATH+'/user/resources/images/1.jpg',
            menu: [{
	        	xtype: 'linkitem',
            	text: '帐号设置',
            	target: '_self',
            	href: ROOT_PATH+'/user/'
            }, '-', {
	        	xtype: 'linkitem',
            	text: '上传头像',
            	target: '_self',
            	href: ROOT_PATH+'/user/uploadavatar.jxp'
            }, {
	        	xtype: 'linkitem',
            	text: '修改密码',
            	target: '_self',
            	href: ROOT_PATH+'/user/changepassword.jxp'
            }, '-', {
            	text: '邀请码(1)',
            	href: ROOT_PATH+'/invitation.jxp',
            	target: '_self'
            }, '-', {
	        	text: '退出',
	        	target: '_self',
	            href: ROOT_PATH+'/signout.jxp'
	        }]
		});
		this.callParent(arguments);
	}
}, function() {
	// after create ...
});