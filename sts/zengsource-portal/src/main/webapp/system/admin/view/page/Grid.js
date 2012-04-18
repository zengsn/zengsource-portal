// view/user/Grid.js
Ext.define('JXP.system.view.page.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
			'Ext.selection.CheckboxModel' ],

	alias : 'widget.systempagegrid',

	//id : 'systempage-grid',
	config : {},

	store : 'JXP.system.store.Pages',
	constructor : function(config) {
		var me = this;
		config = config || {};
		var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
	        clicksToEdit: 1
	    });
		var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
	        groupHeaderTpl: '模块: {name} ({rows.length} Item{[values.rows.length > 1 ? "s" : ""]})'
	    });
		var sm = Ext.create('Ext.selection.CheckboxModel');
		this.config = Ext.apply(config, {
			title : '页面列表',
			//iconCls : 'icon-grid',
			selModel: sm,
			loadMask: true,
			clicksToEdit:2,
			autoScroll: true,
			features: [groupingFeature],
			columns : [{
				header: "ID",
				dataIndex: 'id',
				width: 64,
				sortable: true
			}, {	
				header: "页面",
				dataIndex: 'name',
				width: 80
			}, {	
				header: "地址",
				dataIndex: 'url',
				width: 120/*
			}, {	
				header: "模块",
				dataIndex: 'module',
				width: 100*/
			}, {	
				header: "当前区块",
				dataIndex: 'current',
				width: 200
			}, {	
				header: "说明",
				dataIndex: 'description',
				width: 200,
				editor: {
					allowBlank: true
				}
			}, {	
				header: "创建时间",
				dataIndex: 'updatedTime',
				width: 160
			}, {	
				header: "最后修改",
				dataIndex: 'createdTime',
				width: 160
			}],
			plugins : [ cellEditing/*, {
				ptype : 'rowexpander',
				rowBodyTpl : [ '<p style="padding-left:50px;"><b>介绍:</b> {description}</p>']
			} */],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
				items : [ '-' , {
					text: '配置',
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
	},
	
	accept : function() {
		
	}
}, function() {
	// after create ...
});