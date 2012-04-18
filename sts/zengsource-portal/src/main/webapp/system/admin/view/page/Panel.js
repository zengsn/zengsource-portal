// Panel.js
Ext.define('JXP.system.view.page.Panel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.systempagepanel',
    
    id: 'systempage-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
		    layout: 'fit',
		    border: false,
			items: [{
				xtype: 'systempagegrid'
				//html: 'ok'
			}]
		});
        this.callParent(arguments);
	}
});