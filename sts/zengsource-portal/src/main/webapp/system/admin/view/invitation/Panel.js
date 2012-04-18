// Panel.js
Ext.define('JXP.system.view.invitation.Panel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.invitationpanel',
    
    id: 'invitation-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {	
			layout: 'fit',
			border: false,
			items: [{
				xtype: 'invitationgrid'
			}]
		});
        this.callParent(arguments);
	}
});