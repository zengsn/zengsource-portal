
Ext.define('JXP.task.frontend.controller.My', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.task.frontend.store.Tasks'
    ],
 
    views: [
        'JXP.task.frontend.view.my.draft.Grid',
        'JXP.task.frontend.view.my.draft.Portlet',
        'JXP.task.frontend.view.my.doing.Grid',
        'JXP.task.frontend.view.my.doing.Portlet',
        'JXP.task.frontend.view.my.Grid',
        'JXP.task.frontend.view.my.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.task.frontend.controller.My ... ');
        this.control({
            'taskmygrid': {
            	render: this.loadData
            }
        });
    },
    
    loadData : function(grid) {
    	grid.getStore().load({start:0,limit:25});
    }
});