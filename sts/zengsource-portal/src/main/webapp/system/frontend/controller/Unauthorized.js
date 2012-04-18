
Ext.define('JXP.system.frontend.controller.Unauthorized', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.system.store.Nodes'
    ],
 
    views: [
        'JXP.system.frontend.view.unauthorized.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.system.frontend.controller.Unauthorized ... ');
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