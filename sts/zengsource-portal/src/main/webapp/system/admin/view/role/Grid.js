// admin/security/RoleGrid.js
Ext.define('JXP.system.view.role.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
			'Ext.selection.CheckboxModel' ],

	alias : 'widget.systemrolegrid',

	id : 'systemrolegrid',
	config : {},

	store : 'JXP.system.store.Roles',
	constructor : function(config) {
		var me = this;
		config = config || {};
		//var sm = Ext.create('Ext.selection.CheckboxModel');
		var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
			id: 'permrowediting',
	        clicksToMoveEditor: 1,
	        autoCancel: false
	    });
		this.config = Ext.apply(config, {
			title : '角色列表',
			iconCls : 'icon-grid',
			//selModel: sm,
			plugins: [rowEditing],
			columns : [ {
				text : "name",
				width : 100,
				dataIndex : 'name',            
				editor: {
	                allowBlank: false
	            }
			}, {
				text : "description",
				width : 200,
				dataIndex : 'description',            
				editor: {
	                allowBlank: true
	            }
			}, {
				text : "permissions",
				width : 200,
				dataIndex : 'permissions'
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
				        var r = Ext.create('JXP.system.model.Role', {
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
					    url: 'role.jxp',
					    params: {
					        client: 'extajax',
					        action: 'save',
					        name: record.data.name,
					        description: record.data.description					        
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