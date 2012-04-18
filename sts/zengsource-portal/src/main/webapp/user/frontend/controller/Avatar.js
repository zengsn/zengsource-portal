
Ext.define('JXP.user.frontend.controller.Avatar', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.user.frontend.store.Users'
    ],
 
    views: [
        'JXP.user.frontend.view.avatar.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.user.frontend.controller.Avatar ... ');
        this.control({
//            'userinfoportlet': {
//            	afterlayout: this.loadData
//            }
        });
    },
    
    loadData : function(portlet) {
    }
});