// Panel.js
Ext.define('JXP.system.view.config.Panel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.systemconfigpanel',
    
    id: 'systemconfig-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
		    layout: 'fit',
		    //frame: true,
		    border: false,
			items: [{
				xtype: 'systemconfiggrid'
				//html: 'ok'
			}]
		});
        this.callParent(arguments);
	}
});