// view/user/Grid.js
Ext.define('JXP.social.frontend.view.friend.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*'],

	alias : 'widget.socialfriendgrid',

	id : 'socialfriend-grid',
	config : {},

	//store : 'JXP.social.frontend.store.Friends',
	constructor : function(config) {
		var me = this;
		this.store = Ext.create('JXP.social.frontend.store.Friends');
		config = config || {};
		var idRender = function(val, meta, rec, row, col, store, view) {
			var src = rec.data.avatar=='null'?'/user/resources/images/l_.png':rec.data.avatar;
			return '<img src="'+ROOT_PATH+src+'" wdith="48" height="48" />';
		};
		var nameRender = function(val, meta, rec, row, col, store, view) {
			if (val=='null') {val = rec.data.username;}
			return '<b>'+val+'</b><br />'
				+ '<img src="'+ROOT_PATH+'/social/resources/images/boy_24.png" style="width:16px;height:16px;"/> 广东 惠州 <br />'
				+ '朋友 123 | 分享 123 <br />';
		};
		var introductionRender = function(val, meta, rec, row, col, store, view) {
			return '<i>'+val+'</i><br />'
				+ '<p>'+rec.data.updates+'</p>';
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
				text : "nickname",
				width : 150,
				dataIndex : 'nickname',
				renderer: nameRender
			}, {
				text : "introduction",
				width : 600,
				dataIndex : 'introduction',
				renderer: introductionRender
			}, {
				flex : 1,
				text : "createdTime",
				//renderer : Ext.util.Format.dateRenderer('m/d/Y'),
				dataIndex : 'createdTime'
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
				items : [ ]
			})
		});
		this.callParent(arguments);
		this.on('itemcontextmenu', function(view, rec, item, index, e, opts) {
			me.getSelectionModel().select(rec);
			var records = me.getSelectionModel().getSelection();
			if (!(me.contextMenu)) {
				me.contextMenu = Ext.create('Ext.menu.Menu', {
				    width: 100,
				    floating: true,  
				    renderTo: Ext.getBody(),  
				    items: [{
				        text: '编辑',
				        handler: function() {
				        	me.edit(rec);
				        }
				    }, {
				        text: '角色',
				        handler: function() {
				        	me.editRole(rec);
				        }
				    }, '-', {
				        text: '重置密码',
				        handler: function() {
				        	me.editRole(rec.data);
				        }
				    }, '-', {
				        text: '禁止登录'
				    }]
				});
			}
			if (me.contextMenu.isVisible()) {
				me.contextMenu.hide();
			}
			me.contextMenu.setPagePosition(e.getXY());
			me.contextMenu.show();
			e.stopEvent();
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