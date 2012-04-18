// Panel.js
Ext.define('JXP.system.view.module.Panel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.systemmodulepanel',
    
    id: 'systemmodule-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
		    layout: 'fit',
		    border: false,
			items: [{
				xtype: 'systemmodulegrid'
				//html: 'ok'
			}]
		});
        this.callParent(arguments);
	}
});