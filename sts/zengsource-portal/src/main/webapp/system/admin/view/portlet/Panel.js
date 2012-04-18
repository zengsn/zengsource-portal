// Panel.js
Ext.define('JXP.system.view.portlet.Panel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.systemportletpanel',
    
    id: 'systemportlet-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
		    layout: 'fit',
		    border: false,
			items: [{
				xtype: 'systemportletgrid'
				//html: 'ok'
			}]
		});
        this.callParent(arguments);
	}
});