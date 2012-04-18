
Ext.define('JXP.user.admin.controller.Info', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.system.store.Nodes'
    ],
 
    views: [
        'JXP.user.admin.view.info.Panel'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.user.frontend.controller.Info ... ');
        this.control({
//            'invitationgrid': {
//                activate: this.loadAsks
//            }
        });
    },
    
    loadAsks : function(grid) {
    	//grid.getStore().load({start:0, limit:25});
    }
});