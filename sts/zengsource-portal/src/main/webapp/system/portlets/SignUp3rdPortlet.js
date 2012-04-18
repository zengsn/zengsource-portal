// system/portlets/SignUp3rdPortlet.js
Ext.define('JXP.system.SignUp3rdPortlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
    ],
    
    alias: 'widget.signup3rdportlet',

    id:'signup3rdportlet',
    config: {
    },
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	// Prepare markup
    	Ext.getBody().insertHtml('beforeEnd', '<div id="thirdparty"></div>');
    	Ext.get('thirdparty').insertHtml('beforeEnd', '<div id="qqbtn"></div>');
    	this.config = Ext.apply(config, {
            title: '第三方合作帐号登录',
            margins: '5 5 5 5',
            //border : false,
            frame: true,
            closable: false,
            bodyStyle: {
                background : '#fff',
                padding    : '0px'
            },
            //contentEl: 'thirdparty',
            layout: {
                type: 'hbox',
                padding: '1',
                align: 'stretch'
            },
            defaults:{ 
            	flex: 1,
            	margins: '0 0 0 0',
            	border: false,
            	xtype: 'panel',
                layout: {
                    type:'vbox',
                    padding:'1',
                    align:'stretch'
                },            	
                defaults:{
                	//flex: 1,
                	xtype: 'button',
                	margins:'0 0 1 0'
                }
            },
            items: [{
            	items: [{
                	text: 'QQ 登录',
                	icon: ROOT_PATH+'/system/resources/images/qq16.png',
            		href: 'https://graph.qq.com/oauth2.0/authorize?response_type=token&client_id=100242143&redirect_uri=zsn.cc&scope=get_user_info',
            		target: '_blank'
            	}, {
                	text: 'QQ 登录',
                	icon: ROOT_PATH+'/system/resources/images/qq16.png'
            	}, {
                	text: 'QQ 登录',
                	icon: ROOT_PATH+'/system/resources/images/qq16.png'
            	}, {
            		margins: 0,
                	text: 'QQ 登录',
                	icon: ROOT_PATH+'/system/resources/images/qq16.png'
            	}]
            }, {
            	items: [{
                	text: '微博登录',
                	icon: ROOT_PATH+'/system/resources/images/weibo16.png',
            		href: 'https://graph.qq.com/oauth2.0/authorize?response_type=token&client_id=100242143&redirect_uri=zsn.cc&scope=get_user_info',
            		target: '_blank'
            	}, {
                	text: '微博登录',
                	icon: ROOT_PATH+'/system/resources/images/weibo16.png'
            	}, {
                	text: '微博登录',
                	icon: ROOT_PATH+'/system/resources/images/weibo16.png'
            	}, {
            		margins: 0,
                	text: '微博登录',
                	icon: ROOT_PATH+'/system/resources/images/weibo16.png'
            	}]
            }],
            buttonAlign: 'center',
            buttons: [{
            	id: 'btn-apply',
            	text: '请先申请邀请码',
            	scale: 'medium',
            	target: '_self',
            	href: ROOT_PATH+'/reqinvite.jxp'
            }]
    	});
        this.callParent(arguments);
        
        Ext.create('Ext.button.Button', {
        	text: 'QQ 登录',
        	icon: ROOT_PATH+'/system/resources/images/qq16.png',
    		href: 'https://graph.qq.com/oauth2.0/authorize?response_type=token&client_id=100242143&redirect_uri=zsn.cc&scope=get_user_info',
    		target: '_blank'
        });//.render('qqbtn');
    }
    
}, function() {
	// after create ...
});