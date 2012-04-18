/**
 * 
 */
Ext.define('JXP.system.controller.Config', {
    extend: 'Ext.app.Controller',
    
    stores: [ 'JXP.system.store.Configs' ],
 
    views: [
        //'JXP.system.view.config.Form', 
        'JXP.system.view.config.Grid', 
        'JXP.system.view.config.Panel'
    ],
    
    init: function() {
    	JXP_console.log('Initialized Config! ');
        this.control({
            'systemconfigpanel': {
                activate: this.loadData
            }
        });
    },
    
    loadData : function(panel) {
    	var grid = panel.down('grid');
    	grid.getStore().load({start:0, limit:25});
    }
});