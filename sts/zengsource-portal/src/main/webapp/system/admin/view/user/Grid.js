// view/user/Grid.js
Ext.define('JXP.system.view.user.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
			'Ext.selection.CheckboxModel' ],

	alias : 'widget.systemusergrid',

	id : 'systemuser-grid',
	config : {},

	store : 'JXP.system.store.Users',
	constructor : function(config) {
		var me = this;
		config = config || {};
		var sm = Ext.create('Ext.selection.CheckboxModel');
		this.config = Ext.apply(config, {
			title : '用户列表',
			iconCls : 'icon-grid',
			autoScroll: true,
			selModel: sm,
			columns : [ {
				text : "username",
				width : 200,
				dataIndex : 'username'
			}, {
				text : "status",
				width : 60,
				dataIndex : 'status'
			}, {
				text : "roles",
				width : 60,
				dataIndex : 'roles'
			}, {
				text : "nickname",
				width : 100,
				dataIndex : 'nickname'
			}, {
				text : "realname",
				width : 100,
				dataIndex : 'realname'
			}, {
				text : "sex",
				width : 50,
				dataIndex : 'sex'
			}, {
				text : "birthday",
				width : 100,
				dataIndex : 'birthday'
			}, {
				text : "location",
				width : 100,
				dataIndex : 'location'
			}, {
				flex : 1,
				text : "createdTime",
				//renderer : Ext.util.Format.dateRenderer('m/d/Y'),
				dataIndex : 'createdTime'
			} ],
			plugins : [ {
				ptype : 'rowexpander',
				rowBodyTpl : [ '<p style="padding-left:50px;font-size:12px;"><b>介绍:</b> {introduction}<br />', 
				               '<b>QQ:</b>{qq}({qqShare}), <b>微博:</b>{weibo}, <b>博客:</b>{blog}, <b>MSN:</b>{msn}<br />', 
				               '<b>Email:</b>{email}, <b>手机:</b>{mobile}<br />',
				               '</p>']
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
				items : [ '-' , {
					text: '编辑',
					scale: 'medium',
					handler: function(btn) {
						
					}
				}, {
			        text: '锁定',
					scale: 'medium',
					handler: function(btn) {
						
					}
			    }]
			})
		});
		this.callParent(arguments);
		this.on('itemcontextmenu', function(view, rec, item, index, e, opts) {
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