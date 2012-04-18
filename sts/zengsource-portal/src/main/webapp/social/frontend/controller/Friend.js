
Ext.define('JXP.social.frontend.controller.Friend', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.social.frontend.store.Friends'
    ],
 
    views: [
        'JXP.social.frontend.view.friend.Grid',
        'JXP.social.frontend.view.friend.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.system.frontend.controller.Friend ... ');
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