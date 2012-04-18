/**
 * 
 */
Ext.define('JXP.system.controller.Permission', {
    extend: 'Ext.app.Controller',
    
    stores: [ 'JXP.system.store.Permissions' ],
 
    views: [
        //'JXP.system.view.permission.Form', 
        'JXP.system.view.role.Combo', 
        'JXP.system.view.permission.Grid', 
        'JXP.system.view.permission.Panel'
    ],
    
    init: function() {
    	JXP_console.log('Initialized Permission! ');
        this.control({
            'systempermissionpanel': {
                activate: this.loadData
            }
        });
    },
    
    loadData : function(panel) {
    	var grid = panel.down('grid');
    	grid.getStore().load({start:0, limit:25});
    }
});