/**
 * 
 */
Ext.define('JXP.system.controller.Role', {
    extend: 'Ext.app.Controller',
    
    stores: [ 'JXP.system.store.Roles' ],
 
    views: [
        'JXP.system.view.role.Grid', 
        'JXP.system.view.role.Panel'
    ],
    
    init: function() {
    	JXP_console.log('Initialized Role! ');
        this.control({
            'systemrolepanel': {
                activate: this.loadData
            }
        });
    },
    
    loadData : function(panel) {
    	var grid = panel.down('grid');
    	grid.getStore().load({start:0, limit:25});
    }
});