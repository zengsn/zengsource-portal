
Ext.define('JXP.social.frontend.controller.Request', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.social.frontend.store.Requests'
    ],
 
    views: [
        //'JXP.social.frontend.view.request.Grid',
        'JXP.social.frontend.view.request.View',
        'JXP.social.frontend.view.request.Info',
        'JXP.social.frontend.view.request.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.system.frontend.controller.Request ... ');
        this.control({
            'socialfriendportlet': {
                afterrender: this.loadData
            }
        });
    },
    
    loadData : function(porlet) {
    	//porlet.down('socialfriendgrid').getStore().load({start:0, limit:25});
    }
});