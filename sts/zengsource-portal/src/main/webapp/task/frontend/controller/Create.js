
Ext.define('JXP.task.frontend.controller.Create', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.social.frontend.store.Friends'
    ],
 
    views: [
        'JXP.task.frontend.view.create.Form',
        'JXP.task.frontend.view.create.Portlet',
        'JXP.task.frontend.view.create.file.Form',
        'JXP.task.frontend.view.create.file.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.task.frontend.controller.Create ... ');
        this.control({
//            'userinfoportlet': {
//            	afterlayout: this.loadData
//            }
        });
    },
    
    loadData : function(portlet) {
    }
});