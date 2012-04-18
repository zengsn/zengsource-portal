/**
 * 
 */
Ext.define('JXP.system.controller.User', {
    extend: 'Ext.app.Controller',
    
    stores: [ 'JXP.system.store.Users' ],
 
    views: [
        'JXP.system.view.user.Form', 
        'JXP.system.view.user.Grid', 
        'JXP.system.view.user.Panel'
    ],
    
    init: function() {
    	JXP_console.log('Initialized User! ');
        this.control({
            'systemuserpanel': {
                activate: this.loadData
            }
        });
    },
    
    loadData : function(panel) {
    	var grid = panel.down('grid');
    	grid.getStore().load({start:0, limit:25});
    }
});