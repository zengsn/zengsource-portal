/**
 * @class Ext.app.HeaderBar
 * @extends Ext.toolbar.Toolbar
 * @see {@link Ext.toolbar.Toolbar Toolbar} 
 */
Ext.define('JXP._core.view.HeaderBar', {
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
			icon: ROOT_PATH+'/system/resources/images/lobosi_32.png',
        	text: Ext.get('app-logo').dom.innerHTML,
        	scale: 'large',
        	target: '_self',
        	tooltip: CURRENT_USER.name?'返回首页':'个人信息服务社区',
            href: ROOT_PATH
        }]);
    	if (CURRENT_USER.name) { // Already login 
    		// 菜单
    		items.push([' ', {
    			xtype: 'socialbutton'
    		}, ' ', {
    			xtype: 'appsbutton'
    		}, ' ', {
    			xtype: 'noticebutton'
    		}]);
    		items.push(['->', '欢迎您!']);
    		if (CURRENT_USER.isAdmin) { 
    			items.push([{
    	        	text: '<b>管理员</b>',
    	        	target: '_self',
    	            href: ROOT_PATH+'/system/'
    	        }]);
    		} 
    		//JXP_console.log(ROOT_PATH+CURRENT_USER.avatar);
    		items.push([{
    			xtype: 'userbutton',
    			icon : (ROOT_PATH+CURRENT_USER.avatar).replace('l_', 's_'),
	        	text : CURRENT_USER.nickname?CURRENT_USER.nickname:CURRENT_USER.name
	        }]);
    	} else { // Not login
    		items.push(['->', {
	        	xtype: 'linkbutton',
	        	text: '登录',
	        	target: '_self',
	            href: ROOT_PATH+'/signin.jxp'
	        }, {
	        	xtype: 'linkbutton',
	        	text: '注册',
	        	target: '_self',
	            href: ROOT_PATH+'/signup.jxp'
	        }, '-',  {
	        	xtype: 'linkbutton',
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
    },
    
    signin : function(btn, e) {
    	//JXP_console.log(btn);
    	var jxppanel = btn.up('jxppanel');
    	var portal = jxppanel.down('portal');
    	//JXP_console.log(portal);
    	// 加载配置
		Ext.Ajax.request({
		    url: 'system/portlets/signin.json',
		    success: function(response, opts) {
		        var obj = Ext.decode(response.responseText);
		        if (obj) {
		        	var items = portal.items;//return;
            		if (items.getCount()==1) {
            			var item = items.get('loader');
            			if (item) {
            				portal.remove(0);
            			}
            		}
		        	//Ext.Loader.setPath({'JXP.system.frontend':ROOT_PATH+'/system/frontend'});
            		Ext.Loader.setPath(obj.path);
		        	//Ext.require(['JXP.system.frontend.controller.SignIn'], function() {
            		Ext.require(obj.requires, function() {
            			for(var i=0;i<obj.portlets.length;i++) {
            				var p = portal.down(obj.portlets[i].xtype);
            				if (p) { 
            					continue; 
            				} else {
            					portal.insert(0, Ext.apply(obj.portlets[i]));
            				}
            			}			        	
		        	});
		        }
		    },
		    failure: function(response, opts) {
		    	JXP_console.log('server-side failure with status code ' + response.status);
		    }
		});
    }
});
