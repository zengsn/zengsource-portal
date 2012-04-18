
Ext.define('JXP.task.frontend.controller.Dashboard', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.task.frontend.store.Executions'
    ],
 
    views: [
        'JXP.task.frontend.view.dashboard.Grid',
        'JXP.task.frontend.view.dashboard.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.task.frontend.controller.Dashboard ... ');
        this.control({
            'taskdashboardgrid': {
            	render: this.loadData
            }
        });
    },
    
    loadData : function(grid) {
    	grid.getStore().load({start:0,limit:25});
    }
});