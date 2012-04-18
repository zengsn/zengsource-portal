Ext.define('JXP.system.frontend.view.signin.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'Ext.form.field.Text', 'Ext.util.Cookies'
    ],
    
    alias: 'widget.signinportlet',

    //id:'signinportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	var rdUrl = ROOT_PATH+'/signin.jxp';
    	this.config = Ext.apply(config, {
            title: '登录',
            margins: '5 5 5 5',
            items: [{
            	xtype: 'form',
            	border: false,
                bodyPadding : 5,
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
                errorReader: Ext.create('Ext.data.reader.Xml', {
                    model: 'XP.FieldError',
                    record : 'field',
                    successProperty: '@success'
                }),
                items: [{
                    fieldLabel: '帐号',
                    name      : 'username',
                    style     : 'margin-top:15px',
                    allowBlank:false
                }, {
                    fieldLabel: '密码',
                    name      : 'password',
                    inputType : 'password',
                    allowBlank: false,
                    style     : 'margin-top:15px',
                    enableKeyEvents: true,
                    listeners: {
    	            	'keypress' : function(field, event, obj) {
    		            	if (event.getKey() == Ext.EventObject.ENTER) {
    		            		//Ext.getCmp('btn-signin').fireEvent('click');
    	            			var form = field.up('signinportlet');
    	            			form.submitForm();
    		            	}
    		            }
    	            }
                }, {
                	xtype: 'checkboxgroup',
                    fieldLabel: '选项',
                    //cls: 'x-check-group-alt',
                    style     : 'margin-top:15px',
                    columns: [100, 100],
                    vertical: true,
                    items: [
                        {boxLabel: '记住登录帐号', name: 'rememberonclient'},
                        {boxLabel: '自动登录系统', name: 'remember', inputValue: 1}
                    ]
                }]
            }],
            buttonAlign: 'center',
            buttons: [{
            	id: 'btn-signin',
            	text: '登录',
            	scale: 'medium',
            	handler: function(btn) {
            		var form = btn.up('signinportlet');
            		form.submitForm();
            	}
            }, {
            	text : '注册',
            	scale: 'medium',
            	href : ROOT_PATH+'/signup.jxp',
            	target: '_self'
            }, {
            	text : '微博登录',
            	scale: 'medium',
            	//href : 'https://api.weibo.com/oauth2/authorize?client_id=1558270472&response_type=code&display=default&redirect_uri=http://www.lobosi.com/weibo.jxp',
            	href: ROOT_PATH+'/weibo.jxp',
            	target: '_self'
//            	handler: function(btn) {
//            		me.loginWeibo();
//            	}
            }]
    	});
    	//document.write('<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=1558270472" type="text/javascript" charset="utf-8"></script>');
    	//Ext.Loader.setPath({'WB2':'http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=1558270472'});
    	//Ext.require('WB2', function() {});
        this.callParent(arguments);
        this.on('afterrender', function(panel) {
        	panel.focusUsername();
        });
    },
    
    focusUsername : function() {
    	var me = this;
    	var form = me.down('form').getForm();
    	var field = form.findField('username');
    	// 用户激活后
    	var url = window.location.toString();
    	var arr = url.split('?');
    	if (arr.length == 2) {
        	var qObj = Ext.Object.fromQueryString(arr[1]);
        	if (qObj.email && qObj.confirm !== undefined) {
        		field.setValue(qObj.email);
        		me.down('form').setTitle('<font color="gray">恭喜：您的邮件帐号已经激活，请输入密码后登录。</font>');
        	}
    	} 
    	if (!(field.getValue())) { // 如果没有值，尝试取 Cookie
        	var lastUsername = Ext.util.Cookies.get('rememberonclient');
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
    
    submitForm : function() {
    	var me = this;
    	var form = this.down('form').getForm();
    	var remOnClient = form.findField('rememberonclient');
    	if (remOnClient && remOnClient.getValue()) {
        	var username = form.findField('username');
        	// 保留一周
        	var week = Ext.Date.add(new Date(), Ext.Date.DAY, 7);
    		Ext.util.Cookies.set('rememberonclient', username.getValue(), week);
    	} else {
    		Ext.util.Cookies.clear('rememberonclient');
    	}
    	me.getEl().mask("正在登录……");
		form.submit({
		    clientValidation: true,
		    url: ROOT_PATH+'/signin.jxp?action=authentication',
		    params: {
		        client: 'extajax'
		    },
		    success: function(form, action) {
		       //Ext.Msg.alert('Success', action.result.msg);
		    	//JXP_console.log("登录成功");
		    	window.location.replace(ROOT_PATH+'/home/');
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
		            default:
		            	me.getEl().unmask();
		       }
		    }
		});
    },
    
    loginWeibo : function() {
    	JXP_console.log('weibo ...');
    	//document.write('<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=2853515410" type="text/javascript" charset="utf-8"></script>');
    	//Ext.Loader.setPath({'WB2':'http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=2853515410'});
    	//Ext.require('WB2', function() {
        //	WB2.login(function(){
        //    	JXP_console.log('weibo login ...');
    	//	});
    	//});
    	WB2.login(function(){
    		JXP_console.dir(arguments);
    	});
    }
}, function() {
	// after create ...
});