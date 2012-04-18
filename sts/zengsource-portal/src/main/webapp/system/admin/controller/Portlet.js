/**
 * 
 */
Ext.define('JXP.system.controller.Portlet', {
    extend: 'Ext.app.Controller',
    
    stores: [ 'JXP.system.store.Portlets' ],
 
    views: [
        'JXP.system.view.portlet.Grid', 
        'JXP.system.view.portlet.Panel'
    ],
    
    init: function() {
    	JXP_console.log('Initialized Page! ');
        this.control({
            'systemportletpanel': {
                activate: this.loadData
            }
        });
    },
    
    loadData : function(panel) {
    	var grid = panel.down('grid');
    	grid.getStore().load({start:0, limit:25});
    }
});