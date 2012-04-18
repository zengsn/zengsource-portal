
Ext.define('JXP.system.frontend.controller.SignUp', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.system.store.Nodes'
    ],
 
    views: [
        'JXP.system.frontend.view.signup.Portlet',
        'JXP.system.frontend.view.signup.SuccessPortlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.system.frontend.controller.SignUp ... ');
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