// admin/security/PermissionGrid.js

/** 权限列表。*/
Ext.define('JXP.system.view.permission.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
			'Ext.selection.CheckboxModel' ],

	alias : 'widget.systempermissiongrid',

	id : 'systempermissiongrid',
	config : {},

	store : 'JXP.system.store.Permissions',
	constructor : function(config) {
		var me = this;
		config = config || {};
		var sm = Ext.create('Ext.selection.CheckboxModel');
		var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
			id: 'permrowediting',
	        clicksToMoveEditor: 1,
	        autoCancel: false
	    });
		this.config = Ext.apply(config, {
			title : '权限',
			//iconCls : 'icon-grid',
			//selModel: sm,
			plugins: [rowEditing],
			columns : [ {
				text : "name",
				width : 200,
				dataIndex : 'name',            
				editor: {
	                allowBlank: false
	            }
			}, {
				text : "roleName",
				width : 200,
				dataIndex : 'roleName',            
				editor: {
	                xtype: 'systemrolecombo'
	            }
			}, {
				flex : 1,
				text : "createdTime",
				//renderer : Ext.util.Format.dateRenderer('m/d/Y'),
				dataIndex : 'createdTime'
			} ],/*
			plugins : [ {
				ptype : 'rowexpander',
				rowBodyTpl : [ '<p><b>Company:</b> {company}</p><br>',
						'<p><b>Summary:</b> {desc}</p>' ]
			} ],*/
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
				items : [ '-' , {
					text: '创建',
					scale: 'medium',
					handler: function(btn) {
						rowEditing.cancelEdit();

				        // Create a model instance
				        var r = Ext.create('JXP.system.model.Permission', {
				            name: 'New Guy'
				        });

				        me.getStore().insert(0, r);
				        rowEditing.startEdit(0, 0);
					}
				}, '-']
			}),
			listeners: {
				'edit' : function(e) {
					var grid = e.grid;
					var newValues = e.newValues; // data
					var record = e.record;
					var store = e.store;
					var value = value;
				    //e.record.commit();
					Ext.Ajax.request({
					    url: 'permission.jxp',
					    params: {
					        client: 'extajax',
					        action: 'save',
					        id    : record.data.pid, 
					        name  : newValues.name,
					        roleId: newValues.roleName					        
					    },
					    success: function(response){
					        //var text = response.responseText;
					        // process server response here
					    	store.load();
					    }
					});
				}
			}
		});
		this.callParent(arguments);
	}
	
}, function() {
	// after create ...
});