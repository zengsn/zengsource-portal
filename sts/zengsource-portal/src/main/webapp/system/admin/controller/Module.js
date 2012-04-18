/**
 * 
 */
Ext.define('JXP.system.controller.Module', {
    extend: 'Ext.app.Controller',
    
    stores: [ 'JXP.system.store.Modules' ],
 
    views: [
        'JXP.system.view.module.Grid', 
        'JXP.system.view.module.Panel'
    ],
    
    init: function() {
    	JXP_console.log('Initialized Module! ');
        this.control({
            'systemmodulepanel': {
                activate: this.loadData
            }
        });
    },
    
    loadData : function(panel) {
    	var grid = panel.down('grid');
    	grid.getStore().load({start:0, limit:25});
    }
});