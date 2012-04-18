// admin/ask/Grid.js
Ext.define('JXP.system.view.invitation.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
			'Ext.selection.CheckboxModel' ],

	alias : 'widget.invitationgrid',

	//id : 'invitation-panel',
	config : {},

	store : 'JXP.system.store.Invitations',
	constructor : function(config) {
		var me = this;
		config = config || {};
		var sm = Ext.create('Ext.selection.CheckboxModel');
		this.config = Ext.apply(config, {
			selModel: sm,
			columns : [ {
				text : "email",
				width : 160,
				dataIndex : 'email'
			}, {/*
				text : "introduction",
				width : 300,
				dataIndex : 'introduction'
			}, {*/
				text : "status",
				width : 60,
				dataIndex : 'status'
			}, {
				text : "code",
				width : 120,
				dataIndex : 'code'
			}, {
				text : "inviter",
				width : 150,
				dataIndex : 'inviter'
			}, {
				text : "invitee",
				width : 150,
				dataIndex : 'invitee'
			}, {
				text : "updatedTime",
				width : 150,
				dataIndex : 'updatedTime'
			}, {
				flex : 1,
				text : "createdTime",
				//renderer : Ext.util.Format.dateRenderer('m/d/Y'),
				dataIndex : 'createdTime'
			} ],
			plugins : [ {
				ptype : 'rowexpander',
				rowBodyTpl : [ '<p style="padding-left:50px;"><b>介绍:</b> {introduction}</p>']
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
				items : [ '-' , {
					text: '同意申请',
					scale: 'medium',
					handler: function(btn) {
						
					}
				}, {
			        text: '删除申请',
					scale: 'medium',
					handler: function(btn) {
						
					}
			    }]
			}),
			// collapsible : true,
			// animCollapse : false,
			title : '邀请码申请列表',
			iconCls : 'icon-grid'
		});
		this.callParent(arguments);
		this.on('itemcontextmenu', function(view, rec, item, index, e, opts) {
			if (!(me.contextMenu)) {
				me.contextMenu = Ext.create('Ext.menu.Menu', {
				    width: 100,
				    floating: true,  
				    renderTo: Ext.getBody(),  
				    items: [{
				        text: '同意申请',
				        handler: function() {
				        	me.accept(rec.data.id);
				        }
				    }, {
				        text: '再次发送',
				        handler: function() {
				        	me.resend(rec.data.id);
				        }
				    }, '-', {
				        text: '删除申请'
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
	
	accept : function(ivid) {
		var grid = this;
		var loadMask = grid.setLoading(true);
		Ext.Ajax.request({
			method: 'POST',
		    url: 'invite.jxp',
		    params: {
		    	client: 'extajax',
		    	action: 'accept',
		    	id: ivid
		    },
		    success: function(response, opts) {
		        var obj = Ext.decode(response.responseText);
		        //JXP_console.dir(obj);
		    },
		    failure: function(response, opts) {
		    	JXP_console.log('server-side failure with status code ' + response.status);
		    }
		});
        loadMask.hide();
	}, 
	
	resend : function(ivid) {
		var grid = this;
		var loadMask = grid.setLoading(true);
		Ext.Ajax.request({
			method: 'POST',
		    url: 'invite.jxp',
		    params: {
		    	client: 'extajax',
		    	action: 'resend',
		    	id: ivid
		    },
		    success: function(response, opts) {
		        var obj = Ext.decode(response.responseText);
		        //JXP_console.dir(obj);
		        grid.getStore().load({params:{start:0,limit:25}});
		    },
		    failure: function(response, opts) {
		    	JXP_console.log('server-side failure with status code ' + response.status);
		    }
		});
        loadMask.hide();
	}
}, function() {
	// after create ...
});