// view/user/Grid.js
Ext.define('JXP.task.frontend.view.dashboard.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*'],

	alias : 'widget.taskdashboardgrid',

	//id : 'taskdashboard-grid',
	config : {},

	//store : 'JXP.social.frontend.store.Friends',
	constructor : function(config) {
		var me = this;
		this.store = Ext.create('JXP.task.frontend.store.Executions');
		config = config || {};
		var idRender = function(val, meta, rec, row, col, store, view) {
			var indicator = 'middle';
			if (rec.data.progress<0.3) indicator = 'low';
			if (rec.data.progress>0.7) indicator = 'full';
			var src = '/task/resources/images/'+indicator+'_32.png';
			return '<img src="'+ROOT_PATH+src+'" />';
		};
		var nameRender = function(val, meta, rec, row, col, store, view) {
			return '<b>'+val+'</b><br />'
				+ '发起人：' + rec.data.requestor;
		};
		var introductionRender = function(val, meta, rec, row, col, store, view) {
			return '<i>'+val+'</i>，<i>总进度：'+rec.data.progress+'</i><br />'
				+ '<p>'+rec.data.lastUpdate+'</p>';
		};
		var timeRender = function(val, meta, rec, row, col, store, view) {
			return '<p>开始时间：'+val+'</p>'
				+ '<p>结束时间：'+rec.data.endTime+'</p>';
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
				text : "name",
				width : 150,
				dataIndex : 'name',
				renderer: nameRender
			}, {
				text : "introduction",
				width : 400,
				dataIndex : 'introduction',
				renderer: introductionRender
			}, {
				text : "startTime",
				width : 200,
				dataIndex : 'startTime',
				renderer: timeRender
			}, {
				flex : 1,
				text : "createdTime",
				//renderer : Ext.util.Format.dateRenderer('m/d/Y'),
				dataIndex : 'createdTime'
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
				items : [ ]
			})
		});
		this.callParent(arguments);
        this.on('afterrender', function(grid) {
        	grid.getStore().load({start:0,limit:25});
        });
		this.on('itemcontextmenu', function(view, rec, item, index, e, opts) {
			//JXP_console.log(rec.data.id);
			me.getSelectionModel().select(rec);
			var records = me.getSelectionModel().getSelection();
			if (me.contextMenu && me.contextMenu.isVisible()) {
				me.contextMenu.hide();
				me.contextMenu.destroy();
				me.contextMenu = undefined;
			}
			if (true) { //!(me.contextMenu)) {
				me.contextMenu = Ext.create('Ext.menu.Menu', {
				    floating: true,  
				    renderTo: Ext.getBody(),  
				    items: [{
				    	xtype : 'linkitem',
				        text  : '更新状态',
				        target: '_self',
				        href  : ROOT_PATH+'/task/execute.jxp?id='+rec.data.id+'&_revert'
				    }, '-', {
				    	xtype : 'linkitem',
				        text  : '查看任务',
				        target: '_self',
				        href  : ROOT_PATH+'/task/view.jxp?id='+rec.data.id+'&_revert=id'
				    }]
				});
			}
			me.contextMenu.setPagePosition(e.getXY());
			me.contextMenu.show();
			e.stopEvent();
		});
	},
	
	updateExecution : function(recArr, btn) {
		var me = this;
		var rec = recArr[0];
		Ext.MessageBox.show({
           title: '添加好友：'+rec.data.nickname,
           msg: '建议发送前填写一些自我介绍：',
           width:300,
           buttons: Ext.MessageBox.OKCANCEL,
           multiline: true,
           animateTarget: me,
           fn: function(btn, text) {
        	   if (btn != 'ok') return;
        	   Ext.Ajax.request({
        		   url: ROOT_PATH+'/social/find.jxp',
        		   params : {action: 'invite', friend: rec.data.id, message: text},
        		   success: function(response, opts) {
        			   var obj = Ext.decode(response.responseText);
        			   var msg = obj.result.msg;
        			   XP_Msg({
		            		corner : 'bl',
		            		manager: me,
		            		title  : '操作结果',
		            		html   : '<font color="green">'+msg+'</font>' 
		            	});
        		   },
        		   failure: function(response, opts) {
        			   JXP_console.log('server-side failure with status code ' + response.status);
        		   }
        	   });
           }
       });
	},
	
	edit : function(rec) {
		var me = this;
		var win = me.userFormWin;
		if (!win) {
			win = Ext.create('Ext.window.Window', {
			    title: '编辑用户',
			    height: 400,
			    width: 400,
			    layout: 'fit',
			    border: false,
			    autoScroll: true,
			    closeAction: 'hide',
			    items: {  
			        xtype: 'systemuserform'
			    }
			});
			win.on('show', function() {
				var form = win.down('form');
				form.loadRecord(rec);
				//JXP_console.log(rec);
			});
			me.userFormWin = win;
		}
		win.show();
	},
	
	editRole : function(rec) {
		
	}
}, function() {
	// after create ...
});