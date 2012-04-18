
Ext.define('JXP.system.frontend.controller.ReqInvite', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.system.store.Nodes'
    ],
 
    views: [
        'JXP.system.frontend.view.reqinvite.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.system.frontend.controller.ReqInvite ... ');
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