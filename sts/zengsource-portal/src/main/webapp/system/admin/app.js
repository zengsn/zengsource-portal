/**
 * 
 */
Ext.application({
    name: 'JXP',
 
    //appFolder: 'app',

//	controllers : [ 
//	    'JXP.system.controller.Admin', 
//	    'JXP.system.controller.Invitation', 
//	    'JXP.system.controller.User', 
//	    'JXP.system.controller.Role', 
//	    'JXP.system.controller.Permission', 
//	    'JXP.system.controller.Module', 
//	    'JXP.system.controller.Page',
//	    'JXP.system.controller.Portlet',
//	    'JXP.system.controller.Config'
//	],
    controllers : _controllers,
 
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [{
                id: 'app-header',
                region: 'north',
                margins: '5 5 5 5',
                height: 40,
    		    frame: true,
    		    layout: 'fit',
    		    items: [{
                    xtype: 'adminheaderbar',
                    //frame: true,
                    defaults: {
                    	scale: 'small'
                    },
                    items: [' ', {
                    	text: 'Ext Portal'
                    }, '->', {
        	        	text: '登录',
        	        	target: '_self',
        	            href: 'signin.jxp'
        	        }, {
        	        	text: '注册',
        	        	target: '_self',
        	            href: 'signup.jxp'
        	        }, '-',  {
        	        	text: '申请邀请码 <font color="red">new</font>',
        	        	target: '_self',
        	            href: 'account/ask.jxp'
        	        }]
    		    }]
            }, {
                layout: 'border',
                id: 'westpanel',
                region:'west',
                border: false,
    		    frame: true,
                split:true,
                margins: '0 0 5 5',
                width: 275,
                minSize: 100,
                maxSize: 500,
                items: [{
                	xtype: 'admintreepanel'
                }, {
                	xtype: 'admindetailpanel'
                }]
            }, {
            	xtype: 'admincenterpanel'
            } ]
        });
    }
});