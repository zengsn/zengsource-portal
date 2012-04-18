// Panel.js
Ext.define('JXP.system.view.user.Form', {
    extend: 'Ext.form.Panel',
    alias : 'widget.systemuserform',
    
    //id: 'systemuser-form',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
	        //title: '编辑用户',
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
	        reader : new Ext.data.XmlReader({
	            record : 'User',
	            success: '@success'
	        }, [
	            'id', 'username', 'status', 'email'
	        ]),
            errorReader: Ext.create('Ext.data.reader.Xml', {
                model: 'XP.FieldError',
                record : 'field',
                successProperty: '@success'
            }),
	        waitMsgTarget: true,
	        buttonAlign: 'center',
	        
	        listeners: {
	        	'actioncomplete': function(form, action){
	        		//alert(action.type);
		            if(action.type == 'load'){
		            	// Set enabled
		            	var radioGrpField = form.findField('u-enabled');
		            	var enabled = action.result.data.enabled;
		            	var trueId = 'enabled-rd-' + enabled;
		            	radioGrpField.setValue([false, false]);
		            	radioGrpField.setValue(trueId, true);

	        			centerFormPanel.fireEvent('beforeediting', centerFormPanel);
		            }
		        }
	        },

	        items: [{
	        	id: 'u-id',
	        	name: 'id',
	        	xtype: 'hidden'
	        }, {
	        	id: 'u-username',
                name: 'username',
                fieldLabel: '用户名',
                allowBlank: false,
	        	listeners: {
		        	'blur': function(field) {
			        	if (centerFormPanel.getForm().isDirty()) {Ext.getCmp('btn-u-save').enable();}
			        	else {Ext.getCmp('btn-u-save').disnable();}
			        }
		        }
            }, {
                name: 'nickname',
                fieldLabel: '昵称'
            }, {
                name: 'realname',
                fieldLabel: '真实姓名'
            }, {
                name: 'location',
                fieldLabel: '所在地'
            }, {
                name: 'sex',
                fieldLabel: '性别'
            }, {
            	id: 'u-email',
                name: 'email',
                fieldLabel: 'Email',
                vtype:'email',
	        	listeners: {
		        	'blur': function(field) {
			        	if (centerFormPanel.getForm().isDirty()) {Ext.getCmp('btn-u-save').enable();}
			        	else {Ext.getCmp('btn-u-save').disnable();}
			        }
		        }
            }, {
            	id: 'u-enabled',
                xtype: 'radiogroup',
                fieldLabel: '用户状态',
                itemCls: 'x-check-group-alt',
                //columns: 1,
                items: [
                    {id: 'enabled-rd-1', boxLabel: '启用', name: 'enabled', inputValue: 1, checked: true},
                    {id: 'enabled-rd-2', boxLabel: '关闭', name: 'enabled', inputValue: 2}
                ],
	        	listeners: {
		        	'blur': function(field) {
			        	if (centerFormPanel.getForm().isDirty()) {Ext.getCmp('btn-u-save').enable();}
			        	else {Ext.getCmp('btn-u-save').disnable();}
			        }
		        }
            }],
	        buttons: [{
	            text: '创建',
	            id: 'btn-u-add',
	            disabled: true,
	            handler: function(btn) {
        			Ext.getCmp('btn-u-add').disable();
	        		Ext.getCmp('btn-u-save').enable();
	        		thisUserMgmtPanel.clearForm('创建新用户');
	        	}	            
	        }, {
	            text: '保存',
	            id: 'btn-u-save',
	            disabled: true,
	            handler: function(btn) {
	        		centerFormPanel.getForm().submit({
	        			url:'./system/user.jxp',
	        			params: {action: 'save'},
	        			waitMsg:'请稍候...',
	        			success: function(form, action) {
        			       Ext.Msg.alert('操作成功', '用户信息已经保存!');
        			       Ext.getCmp('btn-u-save').disable();
        			       //Ext.getCmp('btn-u-delete').enable();
        			       thisUserMgmtPanel.userListStore.load({params:{start:0, limit:25}});
        			    },
        			    failure: function(form, action) {
        			        switch (action.failureType) {
        			            case Ext.form.Action.CLIENT_INVALID:
        			                Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
        			                break;
        			            case Ext.form.Action.CONNECT_FAILURE:
        			                Ext.Msg.alert('Failure', 'Ajax communication failed');
        			                break;
        			            case Ext.form.Action.SERVER_INVALID:
        			               Ext.Msg.alert('Failure', action.result.msg);
        			       }
        			    }
	        		});
	        	}
	        }, {
	        	text: '清空',
	            id: 'btn-u-clear',
	            disabled: true,
	            handler: function(btn) {
	        		thisUserMgmtPanel.clearForm();
	        	}
	        }]
		});
        this.callParent(arguments);
	}
});