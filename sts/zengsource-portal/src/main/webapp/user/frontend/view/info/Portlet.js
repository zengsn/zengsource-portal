Ext.define('JXP.user.frontend.view.info.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'Ext.form.field.Text'
    ],
    
    alias: 'widget.userinfoportlet',

    id:'userinfoportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '个人信息',
            margins: '5 5 5 5',
            items: [{
            	xtype: 'form',
            	border: false,
                bodyPadding : 5,
                autoScroll  : true,
                fieldDefaults : {
                    msgTarget  : 'side',
                    labelAlign : 'right',
                    labelWidth : 75
                },
                standardSubmit: false,
                defaults: {
                    anchor : '95%',
                    xtype  : 'textfield'
                }, 
                reader : Ext.create('Ext.data.reader.Xml', {
                    model: 'JXP.user.frontend.model.User',
                    record : 'User',
                    successProperty: '@success'
                }),
                errorReader: Ext.create('Ext.data.reader.Xml', {
                    model: 'XP.FieldError',
                    record : 'field',
                    successProperty: '@success'
                }),
                items: [{
                    fieldLabel: '登录帐号',
                    name      : 'username',
                    disabled  : true,
                    allowBlank: false
                }, {
                    fieldLabel: '昵称',
                    name      : 'nickname',
                    allowBlank: false,
                    style     : 'margin-top:15px'
                }, {
                	xtype     : 'textarea',
                	height    : 48,
                    fieldLabel: '介绍',
                    name      : 'introduction'
                }, {
                    fieldLabel: '标签',
                    name      : 'tags'
                }, {
                    xtype     : 'fieldcontainer',
                    fieldLabel: '博客',
                    style     : 'margin-top:15px',
                    combineErrors: true,
                    msgTarget : 'side',
                    layout: 'hbox',
                    defaults: {
                        flex: 1,
                        hideLabel: true
                    },
                    items: [{
                        flex: 4,
                    	xtype     : 'textfield',
                        fieldLabel: '博客',
                        name      : 'blog'
                    }, {
                        xtype: 'radiogroup',
                        fieldLabel: '是否公开?',
                        margin : '0 0 0 5',
                        //cls: 'x-check-group-alt',
                        items: [
                            {boxLabel: '保密', name: 'blogShare', inputValue: 0},
                            {boxLabel: '公开', name: 'blogShare', inputValue: 1, checked: true}
                        ]
                    }]
                }, {
                    xtype     : 'fieldcontainer',
                    fieldLabel: '微博',
                    combineErrors: true,
                    msgTarget : 'side',
                    layout: 'hbox',
                    defaults: {
                        flex: 1,
                        hideLabel: true
                    },
                    items: [{
                        flex: 3,
                    	xtype     : 'textfield',
                        fieldLabel: '标签',
                        name      : 'weibo'
                    }, {
                    	xtype: 'button',
                    	text : '授权',
                    	margin : '0 0 0 5',
                    	tooltip: '授权后可以使用本站微博连接和转发功能，也可以在需要时再授权。',
                    	handler: function() {}
                    }, {
                        xtype: 'radiogroup',
                        fieldLabel: '是否公开?',
                        margin : '0 0 0 5',
                        //cls: 'x-check-group-alt',
                        items: [
                            {boxLabel: '保密', name: 'weiboShare', inputValue: 0, checked: true},
                            {boxLabel: '公开', name: 'weiboShare', inputValue: 1}
                        ]
                    }]
                }, {
                    xtype     : 'fieldcontainer',
                    fieldLabel: 'QQ',
                    combineErrors: true,
                    msgTarget : 'side',
                    layout: 'hbox',
                    defaults: {
                        flex: 1,
                        hideLabel: true
                    },
                    items: [{
                        flex: 3,
                    	xtype     : 'textfield',
                        fieldLabel: 'QQ',
                        name      : 'qq'
                    }, {
                    	xtype: 'button',
                    	text : '授权',
                    	margin : '0 0 0 5',
                    	tooltip: '授权后可以使用本站微博连接和转发功能，也可以在需要时再授权。',
                    	handler: function() {}
                    }, {
                        xtype: 'radiogroup',
                        fieldLabel: '是否公开?',
                        margin : '0 0 0 5',
                        //cls: 'x-check-group-alt',
                        items: [
                            {boxLabel: '保密', name: 'qqShare', inputValue: 0, checked: true},
                            {boxLabel: '公开', name: 'qqShare', inputValue: 1}
                        ]
                    }]
                }, {
                    xtype     : 'fieldcontainer',
                    fieldLabel: 'MSN',
                    combineErrors: true,
                    msgTarget : 'side',
                    layout: 'hbox',
                    defaults: {
                        flex: 1,
                        hideLabel: true
                    },
                    items: [{
                        flex: 4,
                    	xtype     : 'textfield',
                        fieldLabel: 'MSN',
                        name      : 'msn'
                    }, {
                        xtype: 'radiogroup',
                        fieldLabel: '是否公开?',
                        margin : '0 0 0 5',
                        //cls: 'x-check-group-alt',
                        items: [
                            {boxLabel: '保密', name: 'msnShare', inputValue: 0, checked: true},
                            {boxLabel: '公开', name: 'msnShare', inputValue: 1}
                        ]
                    }]
                }, {
                    xtype     : 'fieldcontainer',
                    fieldLabel: 'Email',
                    combineErrors: true,
                    msgTarget : 'side',
                    layout: 'hbox',
                    defaults: {
                        flex: 1,
                        hideLabel: true
                    },
                    items: [{
                        flex: 4,
                    	xtype     : 'textfield',
                        fieldLabel: 'Email',
                        name      : 'email'
                    }, {
                        xtype: 'radiogroup',
                        fieldLabel: '是否公开?',
                        margin : '0 0 0 5',
                        //cls: 'x-check-group-alt',
                        items: [
                            {boxLabel: '保密', name: 'emailShare', inputValue: 0, checked: true},
                            {boxLabel: '公开', name: 'emailShare', inputValue: 1}
                        ]
                    }]
                }, {
                    xtype     : 'fieldcontainer',
                    fieldLabel: '手机',
                    combineErrors: true,
                    msgTarget : 'side',
                    layout: 'hbox',
                    defaults: {
                        flex: 1,
                        hideLabel: true
                    },
                    items: [{
                        flex: 4,
                    	xtype     : 'textfield',
                        fieldLabel: '手机',
                        name      : 'mobile'
                    }, {
                        xtype: 'radiogroup',
                        fieldLabel: '是否公开?',
                        margin : '0 0 0 5',
                        //cls: 'x-check-group-alt',
                        items: [
                            {boxLabel: '保密', name: 'mobileShare', inputValue: 0, checked: true},
                            {boxLabel: '公开', name: 'mobileShare', inputValue: 1}
                        ]
                    }]
                }]
            }],
            buttonAlign: 'center',
            buttons: [{
            	text: '保存',
            	scale: 'medium',
            	handler: function(btn) {
            		var form = btn.up('userinfoportlet');
            		form.submitForm();
            	}
            }, {
            	text : '重置',
            	scale: 'medium',
            	handler: function(btn) {
            		var form = btn.up('userinfoportlet');
            		form.loadForm();
            	}
            }]
    	});
        this.callParent(arguments);
        this.on('afterrender', function(panel) {
        	//panel.focusUsername();
        	panel.loadForm();
        });
    },
    
    focusUsername : function() {
    	var form = this.down('form').getForm();
    	var field = form.findField('username');
    	//JXP_console.log(field);
    	if (field) {
        	var lastUsername = Ext.util.Cookies.get('rememberonclient');
        	//JXP_console.log(lastUsername);
        	if (lastUsername) { 
        		field.setValue(lastUsername);
        		var remOnClient = form.findField('rememberonclient');
        		if (remOnClient) { remOnClient.setValue(true); }
        		var passwd = form.findField('password');
        		if (passwd) {passwd.focus();}
        	}
        	else { field.focus(); }
    	}
    },
    
    loadForm : function() {
    	var me = this;
    	var form = me.down('form');
    	form.load({
    		url: ROOT_PATH+'/user/info.jxp',
    		params: {action: 'load', client: 'extajax'},
    		failure: function(form, action) {}
    	});
    },
    
    submitForm : function(form) {
    	var me = this;
    	var form = this.down('form').getForm();
		form.submit({
		    clientValidation: true,
		    url: ROOT_PATH+'/user/info.jxp',
		    params: {
		        client: 'extajax',
		        action: 'edit'
		    },
		    success: function(form, action) {
		    	XP_Msg({
            		corner : 'bl',
            		manager: me,
            		title  : '操作成功',
            		html   : '<font color="green">用户信息已更新！</font>' 
            	});
		    },
		    failure: function(form, action) {
		        switch (action.failureType) {
		            case Ext.form.action.Action.CLIENT_INVALID:
		            	XP_Msg({
		            		corner : 'bl',
		            		manager: me,
		            		title  : '错误',
		            		html   : '<font color="red">请检查并填写正确的信息！</font>' 
		            	});
		                break;
		            case Ext.form.action.Action.CONNECT_FAILURE:
		                Ext.Msg.alert('错误', '登录错误！');
		                break;
		            case Ext.form.action.Action.SERVER_INVALID:
		               //Ext.Msg.alert('Failure', action.result.msg);
		       }
		    }
		});
    }
}, function() {
	// after create ...
});