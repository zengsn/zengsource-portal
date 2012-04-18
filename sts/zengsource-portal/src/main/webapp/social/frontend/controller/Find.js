
Ext.define('JXP.social.frontend.controller.Find', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.social.frontend.store.FeatureUsers'
    ],
 
    views: [
        //'JXP.social.frontend.view.request.Grid',
        'JXP.social.frontend.view.find.Grid',
        'JXP.social.frontend.view.find.Form',
        'JXP.social.frontend.view.find.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.system.frontend.controller.Find ... ');
        this.control({
            'socialfindportlet': {
                afterlayout: this.loadData
            },
            'textfield' : {
            	keypress : this.doSearch
            }
        });
    },
    
    loadData : function(portlet) {
    	//porlet.down('socialfriendgrid').getStore().load({start:0, limit:25});
    	setTimeout(function() {
        	portlet.down('socialfindform').focusQuery();
    	}, 500);
    },
    
    doSearch : function(field, event, obj) {
    	if (event.getKey() == Ext.EventObject.ENTER) {
    		var portlet = field.up('socialfindportlet');
    		var form = portlet.down('socialfindform');
    		form.doSearch(field);
    	}
    }
});