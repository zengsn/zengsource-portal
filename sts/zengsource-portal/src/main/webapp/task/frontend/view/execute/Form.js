Ext.define('JXP.task.frontend.view.execute.Form', {
    extend: 'Ext.form.Panel',

    requires: [
        'Ext.form.*'
    ],
    
    alias: 'widget.taskexecuteform',

    //id:'taskexecuteform',
    
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
//            reader : Ext.create('Ext.data.reader.Xml', {
//                model: 'JXP.task.frontend.model.Task',
//                record : 'Task',
//                successProperty: '@success'
//            }),
            errorReader: Ext.create('Ext.data.reader.Xml', {
                model: 'XP.FieldError',
                record : 'field',
                successProperty: '@success'
            }),
            items: [{
            	xtype : 'textarea',
            	name  : 'text',
            	fieldLabel: '进度说明',
            	allowBlank: false
            }, {
                xtype: 'container',
                //fieldLabel: '起止时间',
                layout: 'hbox',
                defaults: {
                    //flex: 1,
                    hideLabel: false
                },
                items: [{
                	width: 393,
                	xtype: 'sliderfield',
                	name : 'progress',
                	fieldLabel: '当前进度',
                	value: config.execution.progress.replace('%','')-0,
                	tipText: function(thumb){
                        return String(thumb.value) + '%';
                    },
                    listeners: {
                    	'change':function(slider, newValue, thumb, opts) {
                    		var label = slider.up('container').items.getAt(1);
                    		label.setText(newValue+' %');
                    	}
                    }
                }, {
                	xtype: 'label',
                	margins: '0 0 0 10',
                	html: '0'
                }]
            }, {
            	xtype     : 'filefield',
                fieldLabel: '进度成果',
                name      : 'attachment',
                allowBlank: true,
                emptyText : '选取需要提交的进度成果文件'
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
    	    url: ROOT_PATH+'/task/execute.jxp',
    	    params: {
    	        client: 'extajax',
    	        execution: me.execution.id,
    	        action: 'add'
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
    	       me.up('window').close();
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