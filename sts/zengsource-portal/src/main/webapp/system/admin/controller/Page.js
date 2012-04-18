/**
 * 
 */
Ext.define('JXP.system.controller.Page', {
    extend: 'Ext.app.Controller',
    
    stores: [ 'JXP.system.store.Pages' ],
 
    views: [
        'JXP.system.view.page.Grid', 
        'JXP.system.view.page.Panel'
    ],
    
    init: function() {
    	JXP_console.log('Initialized Page! ');
        this.control({
            'systempagepanel': {
                activate: this.loadData
            }
        });
    },
    
    loadData : function(panel) {
    	var grid = panel.down('grid');
    	grid.getStore().load({start:0, limit:25});
    }
});