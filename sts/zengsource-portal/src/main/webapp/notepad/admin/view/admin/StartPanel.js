// Panel.js
Ext.define('JXP.system.view.admin.StartPanel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.adminstartpanel',
    
    id: 'adminstart-panel',
    
    constructor: function(config) {
		config = config || {};
		//var displayCard = Ext.get('displaycard');
		this.config = Ext.apply(config, {
            title: '管理首页',
            layout: 'fit',
            bodyStyle: 'padding:25px',
            contentEl: 'start-div' 
		});
        this.callParent(arguments);
	}
});