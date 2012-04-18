// admin/ask/Grid.js
Ext.define('XP.system.InvitationGrid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
			'Ext.selection.CheckboxModel' ],

	alias : 'widget.invitationgrid',

	id : 'invitationgrid',
	config : {},

	store : 'Asks',
	constructor : function(config) {
		var me = this;
		config = config || {};
		var sm = Ext.create('Ext.selection.CheckboxModel');
		this.config = Ext.apply(config, {
			selModel: sm,
			columns : [ {
				text : "email",
				width : 200,
				dataIndex : 'email'
			}, {
				text : "partnerEmail",
				width : 200,
				dataIndex : 'partnerEmail'
			}, {
				text : "status",
				width : 60,
				dataIndex : 'status'
			}, {
				text : "introduction",
				width : 300,
				dataIndex : 'introduction'
			}, {
				text : "ip",
				width : 200,
				dataIndex : 'ip'
			}, {
				flex : 1,
				text : "createdTime",
				renderer : Ext.util.Format.dateRenderer('m/d/Y'),
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
				displayMsg : 'Displaying topics {0} - {1} of {2}',
				emptyMsg : "No topics to display",
				items : [ '-' , {
					text: '接受申请',
					scale: 'medium',
					handler: function(btn) {
						
					}
				}, '-', {
					text: '返回首页',
					scale: 'medium',
					target: '_self',
					href: '../'
				}]
			}),
			// collapsible : true,
			// animCollapse : false,
			title : '邀请码申请列表',
			iconCls : 'icon-grid'
		});
		this.callParent(arguments);
	}
}, function() {
	// after create ...
});