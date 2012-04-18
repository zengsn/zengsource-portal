/**
 * @class Ext.app.HeaderBar
 * @extends Ext.toolbar.Toolbar
 * @see {@link Ext.toolbar.Toolbar Toolbar} 
 */
Ext.define('Ext.app.HeaderBar', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.headerbar',
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	// Check user status
    	CURRENT_USER = CURRENT_USER || {};
    	var items = new Array();
    	// Home button
		items.push([' ', {
        	text: 'Ext Portal (1.0beta)',
        	scale: 'large',
        	target: '_self',
            href: ROOT_PATH
        }, '->']);
    	if (CURRENT_USER.name) { // Already login 
    		items.push(['欢迎您!']);
    		if (CURRENT_USER.isAdmin) { 
    			items.push([{
    	        	text: '<b>管理员</b>',
    	        	target: '_self',
    	            href: ROOT_PATH+'/system/'
    	        }]);
    		} 
    		items.push([{
    			//xtype: 'splitbutton',
	        	text: CURRENT_USER.name,
	        	//target: '_self',
	            //href: ROOT_PATH+'/user.jxp',
	            menu: [{
	            	text: '帐号设置',
	            	href: ROOT_PATH+'/setting.jxp',
	            	target: '_self'
	            }, {
	            	text: '邀请码(1)',
	            	href: ROOT_PATH+'/invitation.jxp',
	            	target: '_self'
	            }, '-', {
		        	text: '退出',
		        	target: '_self',
		            href: ROOT_PATH+'/signout.jxp'
		        }]
	        }]);
    	} else { // Not login
    		items.push([{
	        	text: '登录',
	        	target: '_self',
	            href: ROOT_PATH+'/signin.jxp'
	        }, {
	        	text: '注册',
	        	target: '_self',
	            href: ROOT_PATH+'/signup.jxp'
	        }, '-',  {
	        	text: '申请邀请码 <font color="red">new</font>',
	        	target: '_self',
	            href: ROOT_PATH+'/reqinvite.jxp'
	        }]);
    	}
    	this.config = Ext.apply(config, {
            border: false,
            items: items
    	});
        this.callParent(arguments);
    }
});
