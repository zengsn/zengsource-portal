// Panel.js
Ext.define('JXP.system.view.admin.DetailPanel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.admindetailpanel',
    
    id: 'admindetail-panel',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {	
	        title: '说明',
	        region: 'center',
	        bodyStyle: 'padding-bottom:15px;background:#eee;',
	        autoScroll: true,
	        html: '<p class="details-info">When you select a layout from the tree, additional details will display here.</p>'
		});
        this.callParent(arguments);
	}
});