// view/user/Grid.js
Ext.define('JXP.system.view.config.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
			'Ext.selection.CheckboxModel' ],

	alias : 'widget.systemconfiggrid',

	id : 'systemconfiggrid',
	config : {},

	store : 'JXP.system.store.Configs',
	constructor : function(config) {
		var me = this;
		config = config || {};
		var italicRenderer = function(v) {
			return '<i>' + v + '</i>';
		};
		var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
			id: 'cfgrowediting',
	        clicksToMoveEditor: 1,
	        autoCancel: false
	    });
		var sm = Ext.create('Ext.selection.CheckboxModel');
		this.config = Ext.apply(config, {
			title : '配置列表',
			iconCls : 'icon-grid',
			//selModel: sm,
			columns : [{/*
				header: "ID",
				dataIndex: 'id',
				width: 80
			},{*/
				header: "名称",
				dataIndex: 'name',
				width: 120,
				editor: {
					allowBlank: false
				}
			},{
				header: "KEY",
				dataIndex: 'key',
				width: 120,
				editor: {
					allowBlank: false
				}
			},{
				header: "VALUE",
				dataIndex: 'value',
				width: 200,
				editor: {
					allowBlank: false
				}
			},{
				header: "分组",
				dataIndex: 'group',
				width: 200,
				editor: {
					allowBlank: false
				}
			},{
				header: "说明",
				dataIndex: 'desc',
				width: 256,
				renderer: italicRenderer,
				editor: {
					allowBlank: false
				}
			}],
			plugins : [ rowEditing ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
				items : [ '-' , {
			    	text: '添加',
			    	iconCls: 'btn-add',
			    	scale: 'medium',
			    	handler : function(){
				      var cfg = new JXP.system.model.Config({
				    	  id: '',
				          name: '新配置',
				          key: 'GROUP.KEY',
				          value: ' ',
				          group: 'GROUP'
				      });
				      rowEditing.cancelEdit();
				      me.store.insert(0, cfg);
				      rowEditing.startEdit(0, 2);
			      }
			    }]
			})
		});
		this.callParent(arguments);
		this.on('itemcontextmenu', function(view, rec, item, index, e, opts) {
			if (!(me.contextMenu)) {
				me.contextMenu = Ext.create('Ext.menu.Menu', {
				    width: 100,
				    //height: 100,
				    //plain: true,
				    floating: true,  // usually you want this set to True (default)
				    renderTo: Ext.getBody(),  // usually rendered by it's containing component
				    items: [{
				        text: '同意申请'
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

		this.on('edit', function(o) {
			var grid = o.grid;
			var record = o.record;
			var field = o.field;
			var value = o.value; //BUG:ExtJS 4.0取修改后值有问题
			value = o.newValues[field];
			var originalValue = o.originalValue;
			var row = o.row;
			var column = o.column;
			var key = record.data.key;
			if (key=='' || key=='GROUP.KEY') {
				grid.startEditing(row, 3);
				return;
			} 
			var group = record.data.group;
			if (group=='' || group=='GROUP') {
				grid.startEditing(row, 5);
				return;
			}
			var id = record.data.id;
			var params = {action: 'save'};
			if (id=='') { // new
				params.name = record.data.name;
				params.key = key;
				params.group = group;
				params.value = record.data.value;
				params.description = record.data.desc;
			} else { // edit
				params.id = id;
				params.field = field;
				params.value = value;
			}
			Ext.Ajax.request({
				url: 'config.jxp',
				success: function() {
					me.store.load({params: {start:0,limit:25}});
				},
				failure: function() {
				},
				headers: {},
				params: params
			});

		});
	},
	
	accept : function() {
		
	}
}, function() {
	// after create ...
});