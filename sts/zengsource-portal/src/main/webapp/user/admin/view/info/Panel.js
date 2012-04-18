// Panel.js
Ext.define('JXP.user.admin.view.info.Panel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.userinfopanel',
    
    id: 'userinfo-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
		    layout: 'fit',
		    //frame: true,
		    border: false,
			items: [{
				html: 'ok'
			}]
		});
        this.callParent(arguments);
	}
});