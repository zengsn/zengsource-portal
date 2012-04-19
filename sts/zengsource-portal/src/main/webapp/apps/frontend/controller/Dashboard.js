
Ext.define('JXP.apps.frontend.controller.Dashboard', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.home.frontend.store.Updates'
    ],
 
    views: [
        'JXP.apps.frontend.view.dashboard.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.apps.frontend.controller.Dashboard ... ');
        this.control({
//            'taskmygrid': {
//            	render: this.loadData
//            }
        });
    },
    
    loadData : function(grid) {
    	grid.getStore().load({start:0,limit:25});
    }
});