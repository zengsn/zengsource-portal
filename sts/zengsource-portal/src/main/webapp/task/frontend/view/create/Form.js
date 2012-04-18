Ext.define('JXP.task.frontend.view.create.Form', {
    extend: 'Ext.form.Panel',

    requires: [
        'Ext.form.*','Ext.ux.form.field.BoxSelect',
        'JXP.social.frontend.model.Friend',
        'JXP.social.frontend.store.Friends'
    ],
    
    alias: 'widget.taskcreateform',

    //id:'taskfileform',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
    		//height: 150,
    		bodyStyle:'padding:5px 5px 5px 5px',
            fieldDefaults: {
            	msgTarget : 'side',
                labelAlign: 'right',
                labelWidth: 75
            },
            autoScroll: true,
            defaultType: 'textfield',
            defaults: {
                anchor: '97%'
            },
            reader : Ext.create('Ext.data.reader.Xml', {
                model: 'JXP.task.frontend.model.Task',
                record : 'Task',
                successProperty: '@success'
            }),
            errorReader: Ext.create('Ext.data.reader.Xml', {
                model: 'XP.FieldError',
                record : 'field',
                successProperty: '@success'
            }),
            items: [{
            	name: 'name',
            	fieldLabel: '任务名称',
            	allowBlank: false
            }, {
            	//height: 64,
            	xtype : 'textarea',
            	name  : 'introduction',
            	fieldLabel: '任务说明'
            }, {
            	name: 'tags',
            	fieldLabel: '添加标签'
            }, {
                xtype: 'container',
                //fieldLabel: '起止时间',
                layout: 'hbox',
                defaults: {
                    flex: 1,
                    hideLabel: false
                },
                items: [{
                    layout: 'hbox',
                    xtype : 'fieldcontainer',
                    combineErrors: true,
                    fieldLabel: '起始时间',
                    msgTarget : 'side',
                    labelWidth: 75,
                    margins : '0 8 0 0',
                    defaults: {
                        flex: 1,
                        hideLabel: true
                    },
                    items: [{
                        xtype     : 'datefield',
                        name      : 'startDate',
                        fieldLabel: '开始日期',
                        margin    : '0 5 0 0',
                        format    : 'Y-m-d',
                        allowBlank: false
                    }, {
                        xtype: 'timefield',
                        name : 'startTime',
                        fieldLabel: '开始时间',
                        format: 'H:i:s',
                        //minValue  : '6:00 AM',
                        //maxValue  : '8:00 PM',
                        increment : 30,
                        allowBlank: false
                    }]
                }, {
                    xtype  : 'fieldcontainer',
                    layout : 'hbox',
                    fieldLabel: '结束时间',
                    msgTarget : 'side',
                    labelWidth: 75,
                    combineErrors: true,
                    defaults: {
                        flex: 1,
                        hideLabel: true
                    },
                    items: [{
                        xtype     : 'datefield',
                        name      : 'endDate',
                        fieldLabel: '结束日期',
                        margin    : '0 5 0 0',
                        format    : 'Y-m-d',
                        allowBlank: false
                    }, {
                        xtype: 'timefield',
                        name : 'endTime',
                        fieldLabel: '结束时间',
                        format: 'H:i:s',
                        //minValue  : '6:00 AM',
                        //maxValue  : '8:00 PM',
                        increment : 30,
                        allowBlank: false
                    }]
                }]
            }, {
            	xtype     : 'filefield',
                fieldLabel: '任务附件',
                name      : 'attachment',
                allowBlank: true,
                emptyText : '选取本任务的相关文件'
            }, {
            	xtype: 'boxselect',
            	name : 'repondors',
                fieldLabel: '选择执行人',
                store: Ext.create('JXP.social.frontend.store.Friends'),
                pageSize: 25,
        		emptyText: '选择一个或多个执行人',
                queryMode: 'remote',
        		valueField: 'id',
                displayField: 'username',
                triggerOnClick: false,
                labelTpl: '{username} ({id})',
                tpl: Ext.create('Ext.XTemplate',
                    '<ul><tpl for=".">',
                        '<li role="option" class="' + Ext.baseCSSPrefix + 'boundlist-item' + '">{username}: {id}</li>',
                    '</tpl></ul>'
                ),
                delimiter: '|'//,
        		//value: 'NC|VA|ZZ'
            }],
            buttonAlign: 'center',
            buttons: [{
            	text: '发送',
            	scale: 'medium',
            	handler: function(btn, e) {
            		var form = btn.up('taskcreateform');
            		form.submitForm({type: 'send'});
            	}
            }, {
            	text: '保存',
            	scale: 'medium',
            	handler: function(btn, e) {
            		var form = btn.up('taskcreateform');
            		form.submitForm({type: 'draft'});
            	}
            }, {
            	text: '取消',
            	scale: 'medium',
            	handler: function(btn, e) {
            		var form = btn.up('taskcreateform');
            		form.reset();
            	}
            }]
    	});
        this.callParent(arguments);
    },
    
    focusQuery : function() {
    	var me = this;
    	me.down('textfield').focus();
    },
    
    submitForm : function(opt) {
    	var me = this;
    	me.getForm().submit({
    		//clientValidation: true,
    	    url: ROOT_PATH+'/task/create.jxp',
    	    params: {
    	        client: 'extajax',
    	        type  : opt.type,
    	        action: 'save'
    	    },
    	    success: function(form, action) {
    	    	var obj = Ext.decode(action.response.responseText);
    	    	var msg = obj.result.msg;
     	       form.reset();
    	       XP_Msg({
	           		corner : 'bl',
	           		manager: me,
	           		title  : '提交成功',
	           		html   : msg 
	           	});
    	    },
    	    failure: function(form, action) {
    	        switch (action.failureType) {
    	            case Ext.form.action.Action.CLIENT_INVALID:
    	                Ext.Msg.alert('Failure', '请正确填写表单信息');
    	                break;
    	            case Ext.form.action.Action.CONNECT_FAILURE:
    	                Ext.Msg.alert('Failure', '通信错误');
    	                break;
    	            case Ext.form.action.Action.SERVER_INVALID:
    	               Ext.Msg.alert('Failure', "服务器拒绝");
    	       }
    	    }
    	});
    },
    
    doSearch : function(field) {
    	var me = this;
    	if (!field) {
    		field = me.down('textfield');
    	}
		var portlet = field.up('socialfindportlet');
		var grid = portlet.down('socialfindgrid');
		var store = grid.getStore();
		store.getProxy().extraParams.q = field.getValue();
		store.load({start:0, limit:25, q: field.getValue()});
    }
}, function() {
	// after create ...
});