Ext.define('JXP.user.frontend.view.changepsw.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'Ext.form.field.Text'
    ],
    
    alias: 'widget.userchangepswportlet',

    //id:'useravatarportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '修改密码',
            items: [{
            	xtype: 'form',
            	border: false,
                bodyPadding : 15,
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
                }, /*
                reader : Ext.create('Ext.data.reader.Xml', {
                    model: 'JXP.user.frontend.model.User',
                    record : 'User',
                    successProperty: '@success'
                }),*/
                errorReader: Ext.create('Ext.data.reader.Xml', {
                    model: 'XP.FieldError',
                    record : 'field',
                    successProperty: '@success'
                }),
                items: [{
                    fieldLabel: '原密码',
                    name      : 'oldPassword',
                    inputType : 'password',
                    allowBlank: false,
                    enableKeyEvents: true,
                    listeners: {
    	            	'keypress' : function(field, event, obj) {
    		            	if (event.getKey() == Ext.EventObject.ENTER) {
    	            			var form = field.up('form');
    	            			var field = form.getForm().findField('newPassword');
    	            			field.focus();
    		            	}
    		            }
    	            }
                }, {
                    fieldLabel: '新密码',
                    name      : 'newPassword',
                    inputType : 'password',
                    allowBlank: false,
                    enableKeyEvents: true,
                    listeners: {
    	            	'keypress' : function(field, event, obj) {
    		            	if (event.getKey() == Ext.EventObject.ENTER) {
    	            			var form = field.up('form');
    	            			var field = form.getForm().findField('newPassword2');
    	            			field.focus();
    		            	}
    		            }
    	            }
                }, {
                    fieldLabel: '重复密码',
                    name      : 'newPassword2',
                    inputType : 'password',
                    allowBlank: false,
                    enableKeyEvents: true,
                    listeners: {
    	            	'keypress' : function(field, event, obj) {
    		            	if (event.getKey() == Ext.EventObject.ENTER) {
    		            		//Ext.getCmp('btn-signin').fireEvent('click');
    	            			var form = field.up('userchangepswportlet');
    	            			form.submitForm();
    		            	}
    		            }
    	            }
                }]
            }],
            buttonAlign: 'center',
            buttons: [{
            	text: '保存',
            	scale: 'medium',
            	handler: function(btn) {
            		var form = btn.up('userchangepswportlet');
            		form.submitForm();
            	}
            }]
    	});
        this.callParent(arguments);
        this.on('afterrender', function(panel) {
        	panel.focusOldPassword();
        });
    },
    
    focusOldPassword : function(file) {
    	var me = this;
    	var form = me.down('form');
    	var field = form.getForm().findField('oldPassword');
    	setTimeout(function() {
    		field.focus();
    	}, 500);
    },
    
    submitForm : function() {
    	var me = this;
    	var form = this.down('form');
		form.submit({
		    clientValidation: true,
		    url: ROOT_PATH+'/user/changepassword.jxp',
		    params: {
		        client: 'extajax',
		        action: 'change'
		    },
		    success: function(form, action) {
		    	//var msg = action.result.msg;
		    	var msg = action.response.responseXml;
		    	XP_Msg({
            		corner : 'bl',
            		manager: me,
            		title  : '操作成功',
            		html   : '您的密码已经成功修改！' 
            	});
		    	form.reset();
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