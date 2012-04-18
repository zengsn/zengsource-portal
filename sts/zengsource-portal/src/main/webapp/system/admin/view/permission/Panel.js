// Panel.js
Ext.define('JXP.system.view.permission.Panel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.systempermissionpanel',
    
    id: 'systempermission-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
		    layout: 'fit',
		    border: false,
			items: [{
				xtype: 'systempermissiongrid'
				//html: 'ok'
			}]
		});
        this.callParent(arguments);
	}
});