// Panel.js
Ext.define('JXP.system.view.admin.CenterPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.admincenterpanel',

	id : 'admincenter-panel',

	constructor : function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
			region : 'center',
			layout : 'card',
			frame : true,
			margins : '0 5 5 0',
			activeItem : 0,//components.length-1,
			border : false,
			items : _components
		});
		this.callParent(arguments);
	}
});