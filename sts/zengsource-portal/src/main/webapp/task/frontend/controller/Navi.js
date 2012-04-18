
Ext.define('JXP.task.frontend.controller.Navi', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.user.frontend.store.Users'
    ],
 
    views: [
        'JXP.task.frontend.view.navi.Toolbar',
        'JXP.task.frontend.view.navi.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.task.frontend.controller.Navi ... ');
        this.control({
//            'userinfoportlet': {
//            	afterlayout: this.loadData
//            }
        });
    },
    
    loadData : function(portlet) {
    }
});