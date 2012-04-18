// view/user/Grid.js
Ext.define('JXP.task.frontend.view.execute.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*'],

	alias : 'widget.taskexecutegrid',

	//id : 'taskmy-grid',
	config : {},

	//store : 'JXP.social.frontend.store.Friends',
	constructor : function(config) {
		var me = this;
		this.store = Ext.create('JXP.task.frontend.store.Notes');
		config = config || {};
		var idRender = function(val, meta, rec, row, col, store, view) {
			var indicator = 'middle';
			if (rec.data.progress<0.3) indicator = 'low';
			if (rec.data.progress>0.7) indicator = 'full';
			var src = '/task/resources/images/'+indicator+'_32.png';
			return '<img src="'+ROOT_PATH+src+'" />';
		};
		var textRender = function(val, meta, rec, row, col, store, view) {
			return '<b>'+val+'</b><br />'
				+ '增加进度：' + (rec.data.progress-0)*100 + ' %';
		};
		var attachRender = function(val, meta, rec, row, col, store, view) {
			return '<p>'+(val?'<a href="'+rec.data.attachment+'" style="color:gray">下载附件</a>':'无附件')+'</p>';
		};
		this.config = Ext.apply(config, {
			autoScroll: true,
			hideHeaders: true,
			columns : [ {
				text : "ID",
				width : 64,
				dataIndex : 'id',
				renderer: idRender
			}, {
				text : "text",
				width : 650,
				dataIndex : 'text',
				renderer: textRender
			}, {
				text : "attachment",
				width : 100,
				dataIndex : 'attachment',
				renderer: attachRender
			}, {
				flex : 1,
				text : "createdTime",
				//renderer : Ext.util.Format.dateRenderer('m/d/Y'),
				dataIndex : 'createdTime'
			}],
			tbar: ['发起人:', {
				scale: 'medium',
				text: '……'
			}, '-', '任务名:', {
				scale: 'medium',
				text: '……'
			}, '-', '当前进度:', {
				scale: 'medium',
				text: '……'
			}, '->', {
				scale: 'medium',
				text: '+ 增加',
				handler: function(btn, e) {
					me.addNote(btn, e);
				}
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
				items : [ ]
			})
		});
		this.callParent(arguments);
        this.on('afterrender', function(grid) {
        	//JXP_console.dir(grid.getStore());
        	grid.getStore().on('beforeload', function(store, oper, opt) {
        		store.proxy.extraParams.id = grid.pageData.id;
        	});
        	grid.getStore().on('load', function(store, oper, opt) {
            	// 更新工具条信息
        		Ext.Ajax.request({
        		    url: ROOT_PATH+'/task/execute.jxp',
        		    params : {action: 'execution', client: 'extajax', id:grid.pageData.id},
        		    success: function(response, opts) {
        		        var obj = Ext.decode(response.responseText);
        		       // JXP_console.dir(obj);
        		        var tbar = grid.getDockedItems()[0];
        		        tbar.items.getAt(1).setText( '<b>'+obj.requestor+'</b>');
        		        tbar.items.getAt(4).setText( '<b>'+obj.task+'</b>('+(obj.isMain?'主':'')+')');
        		        //tbar.items.getAt(7).setText( '<b>'+obj.taskProgress+'</b>');
        		        tbar.items.getAt(7).setText('<b>'+obj.progress+'</b>');
        		        grid.execution = obj;
        		    },
        		    failure: function(response, opts) {
        		    	JXP_console.log('server-side failure with status code ' + response.status);
        		    }
        		});
        	});
        	grid.getStore().load({start:0,limit:25});
        });
        this.on('itemdblclick',function(view, rec, item, index, e, opts) {
        	me.showContextMenu(view, rec, item, index, e, opts);
        });
		this.on('itemcontextmenu', function(view, rec, item, index, e, opts) {
        	me.showContextMenu(view, rec, item, index, e, opts);
		});
	},
	
	showContextMenu : function(view, rec, item, index, e, opts) {
		var me = this;
		me.getSelectionModel().select(rec);
		var records = me.getSelectionModel().getSelection();
		if (!(me.contextMenu)) {
			me.contextMenu = Ext.create('Ext.menu.Menu', {
			    width: 100,
			    floating: true,  
			    renderTo: Ext.getBody(),  
			    items: [{
			        text: '加为好友',
			        handler: function(btn) {
			        	me.addFriend(me.getSelectionModel().getSelection());
			        }
			    }]
			});
		}
		if (me.contextMenu.isVisible()) {
			me.contextMenu.hide();
		}
		me.contextMenu.setPagePosition(e.getXY());
		me.contextMenu.show();
		e.stopEvent();
	},
	
	addNote : function(btn, e) {
		var me = this;
		var animateBtn = btn;
		if (!(me.addWin)) {
			//JXP_console.log(me.execution);
			me.addWin = Ext.create('Ext.window.Window', {
				title: '增加进度',
				layout: 'fit',
				width: 500,
				height: 250,
				border: false,
				closeAction: 'hide',
				items: [{
					execution: me.execution,
					xtype: 'taskexecuteform'
				}],
	            buttonAlign: 'center',
	            buttons: [{
	            	text: '确定',
	            	scale: 'medium',
	            	handler: function(btn, e) {
	            		var form = me.addWin.down('taskexecuteform');
	            		form.submitForm({type: 'send'});
	            	}
	            }, {
	            	text: '取消',
	            	scale: 'medium',
	            	handler: function(btn, e) {
	            		me.addWin.close(animateBtn);
	            	}
	            }]
			});
		}
		me.addWin.show(btn);
	}
}, function() {
	// after create ...
});