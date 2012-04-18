
Ext.define('JXP.system.controller.Admin', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.system.store.Nodes', 
        'JXP.system.store.Invitations' 
    ],
 
    views: [
        'JXP.system.view.admin.TreePanel', 
        'JXP.system.view.admin.DetailPanel', 
        'JXP.system.view.admin.CenterPanel', 
        'JXP.system.view.admin.HeaderBar', 
        'JXP.system.view.admin.StartPanel'
    ],
    
    init: function() {
    	JXP_console.log('Initialized Admin! ');
        this.control({
            'invitationgrid': {
                activate: this.loadAsks
            }
        });
    },
    
    loadAsks : function(grid) {
    	grid.getStore().load({start:0, limit:25});
    }
});