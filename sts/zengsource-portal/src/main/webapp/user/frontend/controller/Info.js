
Ext.define('JXP.user.frontend.controller.Info', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.user.frontend.store.Users'
    ],
 
    views: [
        'JXP.user.frontend.view.info.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.user.frontend.controller.Info ... ');
        this.control({
            'userinfoportlet': {
            	afterlayout: this.loadData
            }
        });
    },
    
    loadData : function(portlet) {
    	portlet.getForm().load({
    		url: ROOT_PATH+'/user/info.jxp',
    		params: {action: 'load', client: 'extajax'},
    		failure: function(form, action) {}
    	});
    }
});