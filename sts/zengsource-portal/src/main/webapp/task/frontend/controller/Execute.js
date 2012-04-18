
Ext.define('JXP.task.frontend.controller.Execute', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.task.frontend.store.Notes'
    ],
 
    views: [
        //'JXP.task.frontend.view.create.Form',
        //'JXP.task.frontend.view.create.Portlet',
        'JXP.task.frontend.view.execute.Form',
        'JXP.task.frontend.view.execute.Grid',
        'JXP.task.frontend.view.execute.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.task.frontend.controller.Execute ... ');
        this.control({
//            'userinfoportlet': {
//            	afterlayout: this.loadData
//            }
        });
    },
    
    loadData : function(portlet) {
    }
});