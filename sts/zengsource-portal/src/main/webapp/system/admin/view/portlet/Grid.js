// view/user/Grid.js
Ext.define('JXP.system.view.portlet.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
			'Ext.selection.CheckboxModel' ],

	alias : 'widget.systemportletgrid',

	//id : 'systemportlet-grid',
	config : {},

	store : 'JXP.system.store.Portlets',
	constructor : function(config) {
		var me = this;
		config = config || {};
		var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
	        clicksToEdit: 1
	    });
		var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
	        groupHeaderTpl: '模块: {name} ({rows.length} Item{[values.rows.length > 1 ? "s" : ""]})'
	    });
		var singletonRenderer = function(singleton) {
			if(singleton) {
				return '<img src="../resources/icons/silk/gif/application.gif" title="一个页面上只允许出现一个!" />';
			} else {
				return '<img src="../resources/icons/silk/gif/application_double.gif" title="一个页面上可以出现多个! />';
			}
		};
		var sm = Ext.create('Ext.selection.CheckboxModel');
		this.config = Ext.apply(config, {
			title : '区块列表',
			//iconCls : 'icon-grid',
			selModel: sm,
			loadMask: true,
			clicksToEdit:2,
			autoScroll: true,
			features: [groupingFeature],
			columns : [
		       	//new Ext.grid.RowNumberer(), 
		       	//sm, 
		    {	
		    	header: "ID",
		    	dataIndex: 'id',
		    	width: 60
		    },{	
		    	header: "区块名称",
		    	dataIndex: 'name',
		    	width: 120
		    },{	
		    	header: "模块",
		    	dataIndex: 'module',
		    	width: 120
		    },{	
		    	header: "多个?",
		    	align: 'center',
		    	dataIndex: 'singleton',
		    	//renderer: singletonRenderer,
		    	width: 64
		    },{	/*
		    	header: "列宽",
		    	dataIndex: 'colspan',
		    	width: 64
		    },{	
		    	header: "行高",
		    	dataIndex: 'rowspan',
		    	width: 64
		    },{	*/
		    	id: 'pages',
		    	header: "显示",
		    	dataIndex: 'pages',
		    	width: 160
		    },{	
		    	header: "备注",
		    	dataIndex: 'description',
		    	width: 160,
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