// Panel.js
Ext.define('JXP.system.view.role.Panel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.systemrolepanel',
    
    id: 'systemrole-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
		    layout: 'fit',
		    border: false,
			items: [{
				xtype: 'systemrolegrid'
				//html: 'ok'
			}]
		});
        this.callParent(arguments);
	}
});