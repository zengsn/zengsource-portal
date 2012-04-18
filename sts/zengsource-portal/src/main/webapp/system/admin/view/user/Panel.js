// Panel.js
Ext.define('JXP.system.view.user.Panel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.systemuserpanel',
    
    id: 'systemuser-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
		    layout: 'fit',
		    //frame: true,
		    border: false,
			items: [{
				xtype: 'systemusergrid'/*
			}, {
				region: 'center',
				xtype: 'systemuserform'*/
			}]
		});
        this.callParent(arguments);
	}
});