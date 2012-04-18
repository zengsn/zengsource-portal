Ext.define('JXP.notepad.frontend.view.Form', {
    extend: 'Ext.form.Panel',

    requires: [
        'Ext.form.field.Text',
        'JXP.notepad.frontend.view.Month'
    ],
    
    alias: 'widget.notepadform',

    //id:'notepadform',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            //bodyPadding : 5,
            fieldDefaults : {
                msgTarget  : 'side',
                labelAlign : 'right',
                labelWidth : 75
            },
            standardSubmit: false,
            defaults: {
                anchor : '95%',
                xtype  : 'textarea'
            }, 
            layout: 'fit',
	        waitMsgTarget: true,
	        buttonAlign: 'center',
	        reader : Ext.create('Ext.data.reader.Xml', {
	        	model: 'JXP.notepad.frontend.model.Note',
	        	record : 'Note',
	        	successProperty: '@success'
	        }),
	        items: [{
	        	id: 'notepadformtext',
	        	name: 'text',
	        	//xtype: 'htmleditor',
	        	margins: '5 5 0 5',
	    		border: false,
	        	emptyText: '点击开始记录……',
	    		enableKeyEvents : true,
	    		listeners: {
	    			'focus': function(field) {
	    				me.updateStatus('正在编辑……');
	    			},
	    			'blur' : function(field) {
	    				me.updateStatus('点击开始编辑。');
	    			},
	    			'keypress' : function(field, e, opts) {
	    				if (e.isSpecialKey() && e.getKey()==115) {
	    					me.save();
		    				e.preventDefault();
	    				}
	    			}
	    		}
	        }, {
	        	name: 'id',
	        	xtype: 'hidden'
	        }],
	        tbar: [{
	        	text: '文件',
	        	menu: [{
	        		text: '打开',
	        		//handler: function(item, e) {me.open();},
	        		menu: [{
	        			width : 425,
	        			height: 405,
	        			//title : '查看记事',
	        			noteForm: me,
	        			xtype : 'notepadmonthgrid'
	        		}]
	        	}, {
	        		text: '保存',
	        		handler: function(item, e) {me.save();}
	        	}, {
	        		text: '另存为...',
	        		handler: function(item, e) {me.saveAs();}
	        	}, '-', {
	    			text: '本地上传'
	    		}, '-', {
	        		text: '最近记事',
	        		menu: []
	        	}]
	        }, ' ', {
	        	text: '编辑',
	        	menu: [{
	        		text: '复制'
	        	}]
	        }, ' ', {
	        	text: '帮助',
	        	menu: [{
	        		text: '关于'
	        	}]
	        }, '->', '点击开始记录。']
    	});
        this.callParent(arguments);
        this.on('afterrender', function(cmp) {
        	cmp.focusText();
        	cmp.loadRecent();
        });
//        var keyMap = new Ext.util.KeyMap(me.items.first().id, {
//            key : Ext.EventObject.S,
//            ctrl: true,
//            fn  : function() {me.save();},
//            scope: me
//        });
    },
    
    focusText: function() {
    	var me = this;
    	me.getForm().findField('text').focus();
    },
    
    loadRecent: function() {
    	var me = this;
        var tbar = me.getDockedItems()[0];
    	var store = Ext.create('JXP.notepad.frontend.store.Notes');
    	store.on('load', function(store, records, success) {
    		store.each(function(record) {
    			tbar.items.getAt(0).menu.items.getAt(6).menu.add({
    				text: record.data.title,
    				href: ROOT_PATH+'/notepad/edit.jxp?id='+record.data.id,
    				handler: function(btn, e) {
    					e.preventDefault();
    					me.loadNote(record.data.id);
    				}
    			});
    		});
    	});
    	store.load({params: {limit: 10}});
    },
    
    loadNote: function(id) {
    	var me = this;
    	me.getForm().load({
    		url: ROOT_PATH+'/notepad/edit.jxp',
    		params: {client: 'extajax', action: 'load', id: id},
    		success: function() {},
    		failure: function() {}
    	});
    },
    
    updateStatus: function(status) {
    	var me = this;
        var tbar = me.getDockedItems()[0];
        tbar.items.getAt(6).setText(status);
    },    
    
    save : function(btn, e) {
    	var me = this;
    	me.getForm().submit({
		    clientValidation: true,
		    url: ROOT_PATH+'/notepad/edit.jxp',
		    params: {
		        client: 'extajax',
		        action: 'save'
		    },
		    success: function(form, action) {
		    	var obj = Ext.decode(action.response.responseText);
    	    	var result = obj.result;
		    	me.updateStatus(result.msg);
		    	form.findField('id').setValue(result.id);
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
    },
    
    saveAs: function() {
    	
    },
    
    open: function() {
    	var me = this;
    	if (!(me.openWin)) {
			//JXP_console.log(me.execution);
			me.openWin = Ext.create('Ext.window.Window', {
				title: '打开记事',
				layout: 'fit',
				width: 435,
				height: 435,
				border: false,
				resizable: false,
				closeAction: 'hide',
				items: [{
					execution: me.execution,
					xtype: 'notepadmonthgrid'
				}]
			});
		}
		me.openWin.show(me);
    }
}, function() {
	// after create ...
});