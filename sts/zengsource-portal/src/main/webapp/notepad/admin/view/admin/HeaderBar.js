/**
 * @class Ext.app.HeaderBar
 * @extends Ext.toolbar.Toolbar
 * @see {@link Ext.toolbar.Toolbar Toolbar} 
 */
Ext.define('JXP.system.view.admin.HeaderBar', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.adminheaderbar',
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	CURRENT_USER = CURRENT_USER || {};
    	this.config = Ext.apply(config, {
            border: false,
            defaults: {
            	scale: 'small'
            },
            items: [' ', {
            	text: 'Ext Portal (0.1beta)',
            	target: '_self',
                href: ROOT_PATH+'/system/'
            }, '-', {
            	text: '<b>系统状态</b>',
            	iconAlign: 'right',
            	icon: ROOT_PATH+'/system/resources/images/ok_16.png',
            	handler: function(btn, e) {
            		me.checkSystem(btn, e);
            	}
            }, '->', '欢迎您!', {
	        	text: CURRENT_USER.name,
	            menu: [{
	            	text: '帐号设置',
	            	href: ROOT_PATH+'/setting.jxp',
	            	target: '_self'
	            }, {
	            	text: '邀请码(1)',
	            	href: ROOT_PATH+'/invitation.jxp',
	            	target: '_self'
	            }, '-', {
		        	text: '网站首页',
		        	target: '_self',
		            href: ROOT_PATH+'/'
		        }, '-', {
		        	text: '退出',
		        	target: '_self',
		            href: ROOT_PATH+'/signout.jxp'
		        }]
	        }]
    	});
        this.callParent(arguments);
    },
    
    checkSystem : function(btn, e) {
    	Ext.Ajax.request({
    	    url: ROOT_PATH+'/system/status.jxp',
    	    params: {client: 'extajax', action: 'check'},
    	    success: function(response, opts) {
    	        var obj = Ext.decode(response.responseText);
    	        var result = obj;
    	        if (result.errors && result.errors.length>0 ) {
    	        	btn.setIcon(ROOT_PATH+'/system/resources/images/error_16.png');
    	        	btn.setTooltip({
    	        		title: '错误',
    	        		text : result.errors[0].msg
    	        	});
    	        } else {
    	        	btn.setIcon(ROOT_PATH+'/system/resources/images/ok_16.png');
    	        	btn.setTooltip('一切正常');
    	        }
    	    },
    	    failure: function(response, opts) {
    	    	JXP_console.log('server-side failure with status code ' + response.status);
    	    }
    	});
    }
});
