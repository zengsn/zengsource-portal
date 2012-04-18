// system/portlets/ReqInvitePortlet.js
Ext.define('JXP.system.frontend.view.reqinvite.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'Ext.form.field.Text',
        'XP.FieldError'
    ],
    
    alias: 'widget.reqinviteportlet',

    //id:'reqinviteportlet',
    config: {
    },
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '申请邀请码',
            margins: '5 5 5 5',
            items: [{
            	xtype: 'form',
                border : false,
                bodyPadding : 5,
                fieldDefaults : {
                    msgTarget  : 'side',
                    labelAlign : 'right',
                    labelWidth : 100
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
                	id: 'email',
                    fieldLabel: '电子邮件',
                    name      : 'email',
                    vtype     : 'email',
                    vtypeText : '请填写正确的电子邮件',
                    emptyText : '例如：12345@qq.com',
                    allowBlank: false,
                    blankText : '必填项目'
                }, {
                	xtype     : 'textarea',    
                    fieldLabel: '自我介绍',
                    name      : 'introduction',
                    emptyText : '请简单描述您希望申请本网站注册帐号的原因？',
                    allowBlank: false,
                    blankText : '必填项目',
                    enableKeyEvents: true,
                    listeners: {
    	            	'keypress' : function(field, event, obj) {
    		            	if (event.getKey() == Ext.EventObject.ENTER) {
    		            		//Ext.getCmp('btn-signin').fireEvent('click');
    	            			var form = field.up('reqinviteportlet');
    	            			form.submitForm();
    		            	}
    		            }
    	            }
                }, {
                	 xtype: 'checkboxgroup',
                    fieldLabel: '常用工具',
                    name      : 'socialType',
                    items: [{
                    	boxLabel: 'QQ', name: 'qq'
                    }, {
                    	boxLabel: '微博', name: 'weibo'
                    }, {
                    	boxLabel: '百度', name: 'baidu'
                    }, {
                    	boxLabel: '支付宝', name: 'alipay'
                    }, {
                    	boxLabel: '人人网', name: 'renren'
                    }, {
                    	boxLabel: '开心网', name: 'kaixin001'
                    }]
                }]
            }],
            buttonAlign: 'center',
            buttons: [{
            	id: 'btn-submit',
            	text: '确定',
            	scale: 'medium',
            	handler: function(btn) {
            		var form = btn.up('reqinviteportlet');
            		form.submitForm();
            	}
            }, {
            	text: '取消',
            	scale: 'medium',
            	target: '_self',
            	href: '../'
            }],
            tools:[{
                type:'help',
                tooltip: '关于邀请码？',
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
    
    submitForm : function(form) {	
    	var me = this;
		this.down('form').submit({
		    clientValidation: true,
		    url: 'reqinvite.jxp',
		    params: {
		        client: 'extajax',
		        action: 'apply'
		    },
		    success: function(form, action) {
		       //Ext.Msg.alert('Success', action.result.msg);
		       Ext.Msg.show({
		    	    title:'成功',
		    	    msg: '我们会根据您的申请在适当的时候向您发送邀请码，谢谢关注！',
		    	    buttons: Ext.Msg.OK,
		    	    animateTarget: 'btn-submit',
		    	    icon: Ext.window.MessageBox.INFO,
		    	    fn: function() {
		    	    	form.reset();
						window.location.replace(ROOT_PATH);
			        }
		    	});
		    },
		    failure: function(form, action) {
		        switch (action.failureType) {
		            case Ext.form.action.Action.CLIENT_INVALID:
		                //Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
		            	XP_Msg({
		            		corner : 'bl',
		            		manager: me,
		            		title  : '错误',
		            		html   : '<font color="red">请检查并填写正确的信息！</font>' 
		            	});
		                break;
		            case Ext.form.action.Action.CONNECT_FAILURE:
		                Ext.Msg.alert('Failure', 'Ajax communication failed');
		                break;
		            case Ext.form.action.Action.SERVER_INVALID:
		               //Ext.Msg.alert('Failure', action.result.msg);
		            	JXP_console.dir(action.result);
		       }
		    }
		});
    },
    
    focusEmail : function() {
    	//JXP_console.dir(this.id);
    	var field = this.down('form').getForm().findField('email');
    	//JXP_console.dir(field);
    	//if (field) setTimeout(function() {field.focus();}, 100);
    	if (field) field.focus();
    }
}, function() {
	// after create ...
});