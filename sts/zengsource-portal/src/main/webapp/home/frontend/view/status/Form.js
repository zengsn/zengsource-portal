/**
 * 
 */
Ext.define('JXP.home.frontend.view.status.Form', {
	extend : 'Ext.form.Panel',
	alias : 'widget.homestatusform',
	
	//id: 'homestatusform',
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
			bodyStyle : 'padding:5px 5px 5px 5px',
			defaultType : 'textfield',
			fieldDefaults : {
				//labelAlign: 'top',
				msgTarget : 'side'
			},
//            reader : Ext.create('Ext.data.reader.Xml', {
//                model: 'Question.model.Question',
//                record : 'Question',
//                successProperty: '@success'
//            }),
            errorReader: Ext.create('Ext.data.reader.Xml', {
                model: 'XP.FieldError',
                record : 'field',
                successProperty: '@success'
            }),
			defaults : {
	            //frame: true,
	            border: false,
	            xtype: 'panel',
	            margins:'0 5 0 0',
                layout: {
                    type:'vbox',
                    //padding:'5',
                    align:'stretch'
                },
                defaults:{margins:'0 0 0 0'}
			},
            layout: {
                type: 'hbox',
                //padding:'5',
                align:'stretch'
            },
			items : [ {
				flex: 4,
	            items: [{
    				xtype : 'textarea',
    				name  : 'text',
    				height: 55,
    				emptyText: '最近在做什么……'
                }, {
                	xtype: 'hidden',
                	name : 'filename'
                }, {
                	xtype: 'toolbar',
    				margins: '0',
    				items: [{
    	            	xtype     : 'filefield',
    	                name      : 'picture',
    	                buttonText: '+ 图片',
    	                buttonOnly: true,
    	                //hideLabel : true,
    	                listeners : {
    	                	'change' : function(file, value, opts) {
    	                		me.submitForm(file);
    	                	}
    	                }
    				}, '->', {
    					xtype: 'linkbutton',
    					text: '> 转到记事本',
    					target: '_self',
    					href: ROOT_PATH+'/notepad/'
    				}]
	            }]
	        }, {
	            flex: 1,
	            margins: '0',
	            items: [{
                	xtype: 'button',
    				text : '提交',
                    anchor:'100%',
                    height: 55,
                    margins: '0 0 3 0',
                	handler: function(btn) {
                		me.submitForm();
                	}
				}, {
                	xtype: 'button',
                    height: 30,
                    margins: '0 0 0 0',
					text: '取消',
                	handler: function(btn) {
                		me.getForm().reset();
                	}
				}]
	        }]
		});
		this.callParent(arguments);
	},
    
    submitForm : function(file) {	
    	var me = this;
		this.submit({
		    //clientValidation: true,
		    url: ROOT_PATH+'/social/update.jxp',
		    params: {
		        client: 'extajax',
		        temp  : !!(file),
		        action: 'save'
		    },
		    success: function(form, action) {
    	    	var obj = Ext.decode(action.response.responseText);
    	    	var result = obj.result;
    	    	me.afterUpdate(result, file);
		    	//form.reset();
//		    	var grid = me.up('askcenterpanel').down('askgrid');
//		    	grid.getStore().load({params:{start: 0, limit: 25}});
		    },
		    failure: function(form, action) {
		        switch (action.failureType) {
		            case Ext.form.action.Action.CLIENT_INVALID:
		                Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
		                break;
		            case Ext.form.action.Action.CONNECT_FAILURE:
		                Ext.Msg.alert('Failure', 'Ajax communication failed');
		                break;
		            case Ext.form.action.Action.SERVER_INVALID:
		               Ext.Msg.alert('Failure', action.result.msg);
		       }
		    }
		});
    },
    
    afterUpdate : function(obj, file) {
    	var me = this;
    	//JXP_console.dir(obj);
    	if ('done'==obj.operation) {
    		if (me.previewWin) {
        		me.previewWin.close();
    		}
    		obj.text = me.getForm().findField('text').getValue();
    		me.getForm().reset();
    		// 插入更新GRID
    		var grid = me.up('homestatusportlet').down('homestatusgrid');
    		var record = Ext.create('JXP.home.frontend.model.Update', obj);
    		grid.getStore().insert(0, record);
    	} else { // 第一次上传图片
    		me.getForm().findField('filename').setValue(obj.picture);
    		var imgMarkup = '<img src="'+ROOT_PATH+'/upload/social/'+obj.ownerId+"/"+obj.picture+'" width="300" height="300"/>';
    		if (!(me.previewWin)) {
        		me.previewWin = Ext.create('Ext.window.Window', {
        			title : '图片预览',
        			width : 300,
        			height: 300,
        			layout: 'fit',
        			items : [{
        				html: imgMarkup
        			}],
        			closeAction: 'hide',
        			headerPosition: left,
        			buttonAlign: 'center',
        			buttons: [{
        				text: '删除',
        				handler: function() {me.getForm().findField('filename').setValue('');me.previewWin.close();}
        			}]
        		});
        		var xy = file.getEl().getXY();
        		me.previewWin.setPosition(xy[0], xy[1]+20);
    		} else {
    			me.previewWin.items.getAt(0).update(imgMarkup);
    		}
    		me.previewWin.show();
    	}
    }
});