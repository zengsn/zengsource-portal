// system/portlets/SignUpPortlet.js
Ext.define('JXP.system.frontend.view.signup.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'Ext.form.field.Text',
        'XP.FieldError'
    ],
    
    alias: 'widget.signupportlet',

    id:'signupportlet',
    config: {
    },
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '注册新帐号',
            frame: true,
            //closable: false,
            items: [{
            	xtype: 'form',
            	border: false,
                bodyStyle: {
                    background : '#fff',
                    padding    : '10px'
                },
                fieldDefaults  : {
                    msgTarget  : 'side',
                    labelAlign : 'right',
                    labelWidth : 75
                },
                defaults: {
                    xtype  : 'textfield',
                    anchor : '95%',
                    height : 25,
                    style  : 'font-size:14px;',
                    labelStyle: 'font-size:13px;height:25px;vertical-align:middle;'
                }, 
                standardSubmit: false,
                errorReader: Ext.create('Ext.data.reader.Xml', {
                    model: 'XP.FieldError',
                    record : 'field',
                    successProperty: '@success'
                }),
                items: [{
                    fieldLabel: '邮箱',
                    name      : 'email',
                    allowBlank:false/*
                }, {
                    fieldLabel: '用户名',
                    name      : 'username',
                    allowBlank:false */
                }, {
                    fieldLabel: '密码',
                    name      : 'password',
                    inputType : 'password',
                    allowBlank:false,
                    enableKeyEvents: true,
                    listeners: {
    	            	'keypress' : function(field, event, obj) {
    		            	if (event.getKey() == Ext.EventObject.ENTER) {
    	            			var portlet = field.up('signupportlet');
    	            			portlet.submitForm();
    		            	}
    		            }
    	            }
                }, { /*
                    fieldLabel: '重复',
                    name      : 'password2',
                    inputType : 'password',
                    allowBlank: false,
                    validator : function(value) {
                    	var password = this.previousSibling('[name=password]');
                        return (value === password.getValue()) ? true : '密码不匹配!';
    	            	//var p1 = me.getForm().findField('password').getValue();
    	            	//return value == p1 ? true : '密码不匹配!';
    	            }
                }, { */
                    fieldLabel: '邀请码',
                    name      : 'invitation',
                    //style     : 'margin-top:15px',
                    allowBlank: false
                }, {
                    xtype: 'checkboxfield',
                    name: 'acceptTerms',
                    fieldLabel: '注册协议',
                    //hideLabel: true,
                    style: 'margin-top:15px',
                    boxLabel: '我已阅读并同意 <a href="privacy_policy.html" class="terms">服务协议</a>。',

                    listeners: {
                        click: {
                            element: 'boxLabelEl',
                            fn: function(e) {
                                var target = e.getTarget('.terms'),
                                    win;
                                if (target) {
                                    win = Ext.widget('window', {
                                        title: '服务协议',
                                        modal: true,
                                        html: '<iframe src="' + target.href + '" width="950" height="500" style="border:0"></iframe>',
                                        buttons: [{
                                            text: 'Decline',
                                            handler: function() {
                                                this.up('window').close();
                                                me.down('[name=acceptTerms]').setValue(false);
                                            }
                                        }, {
                                            text: 'Accept',
                                            handler: function() {
                                                this.up('window').close();
                                                me.down('[name=acceptTerms]').setValue(true);
                                            }
                                        }]
                                    });
                                    win.show();
                                    e.preventDefault();
                                }
                            }
                        }
                    },
                    getErrors: function() {
                        return this.getValue() ? [] : ['请先阅读注册协议！'];
                    }
                }]
            }],
            buttonAlign: 'center',
            buttons: [{
            	id: 'btn-submit',
            	text: '确定',
            	scale: 'medium',
            	handler: function(btn) {
            		me.submitForm();
            	}
            }, {
            	text: '取消',
            	scale: 'medium',
            	handler: function(btn) {
            		window.location.replace('./');
	            }
            }] ,
            listeners: {
            	'show' : function(panel) {
            		//panel.focusEmail();
            	}
            },
            tools:[{
                type:'help',
                tooltip: '注册帮助',
                handler: function(event, toolEl, panel){
                    // show help here
                }
            }]
    	});
        this.callParent(arguments);
        this.on('afterrender', this.focusEmail, this);
    	Ext.getBody().on('keydown', function(e, t, opt) {
    		if (e.getKey()==Ext.EventObject.ENTER) {
    			me.submitForm();
    		}
    	});
    },
    
    submitForm : function() {	
    	var me = this;
    	var form = this.down('form');
    	var email = form.getForm().findField('email').getValue();
    	//alert(email);return;
		form.submit({
		    clientValidation: true,
		    //url: Ext.get('signupUrl').dom.innerHTML,
		    url: 'signup.jxp',
		    params: {
		        client: 'extajax',
		        action: 'create'
		    },
		    success: function(form, action) {
		    	//JXP_console.dir(action);
		        Ext.Msg.show({
		    	    title:'成功',
		    	    msg: '新帐号创建成功',
		    	    buttons: Ext.Msg.OK,
		    	    animateTarget: 'btn-submit',
		    	    icon: Ext.window.MessageBox.INFO,
		    	    fn: function() {
			    		form.reset();
		        		window.location.replace('signupsuccess.jxp?email='+email);
			        }
		    	});
		    },
		    failure: function(form, action) {
		        switch (action.failureType) {
		            case Ext.form.action.Action.CLIENT_INVALID:
		                //Ext.Msg.alert('错误', '请检查并填写正确的信息！');
		            	XP_Msg({
		            		corner : 'bl',
		            		manager: me,
		            		title  : '错误',
		            		html   : '<font color="red">请检查并填写正确的信息！</font>' 
		            	});
		                break;
		            case Ext.form.action.Action.CONNECT_FAILURE:
		                Ext.Msg.alert('错误', '注册失败！');
		                break;
		            case Ext.form.action.Action.SERVER_INVALID:
		               //Ext.Msg.alert('错误', action.result.msg);
		       }
		    }
		});
    },
    
    setCode : function(code, email) {
    	var me = this;
    	var fieldCode  = me.getForm().findField('invitation');
    	var fieldEmail = me.getForm().findField('email');
    	fieldCode.setValue(code);
    	fieldEmail.setValue(email);
    },
    
    focusEmail: function() {
    	var me = this;
    	var fieldEmail = me.down('form').getForm().findField('email');
    	var fieldCode = me.down('form').getForm().findField('invitation');
    	var fieldPasswd = me.down('form').getForm().findField('password');
    	var url = window.location.toString();
    	var arr = url.split('?');
    	if (arr.length == 2) {
    		//JXP_console.dir(arr);
        	var qObj = Ext.Object.fromQueryString(arr[1]);
    		//JXP_console.dir(qObj);
        	if (qObj.email && fieldEmail) {
        		fieldEmail.setValue(qObj.email);
        		if (fieldPasswd) fieldPasswd.focus();
        	}
        	if (qObj.code && fieldCode) {
        		fieldCode.setValue(qObj.code);
        	}
    	} else {
        	if(fieldEmail) fieldEmail.focus();
    	}
    }
    
}, function() {
	// after create ...
});