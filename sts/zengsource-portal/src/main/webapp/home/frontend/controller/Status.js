
Ext.define('JXP.home.frontend.controller.Status', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.home.frontend.store.Updates'
    ],
 
    views: [
        'JXP.home.frontend.view.status.Form',
        'JXP.home.frontend.view.status.Grid',
        'JXP.home.frontend.view.status.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.home.frontend.controller.Status ... ');
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