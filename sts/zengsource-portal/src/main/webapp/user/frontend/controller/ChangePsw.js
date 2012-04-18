
Ext.define('JXP.user.frontend.controller.ChangePsw', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.user.frontend.store.Users'
    ],
 
    views: [
        'JXP.user.frontend.view.changepsw.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.user.frontend.controller.ChangePsw ... ');
        this.control({
//            'userinfoportlet': {
//            	afterlayout: this.loadData
//            }
        });
    },
    
    loadData : function(portlet) {
    }
});