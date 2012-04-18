/**
 * 
 */
Ext.define('JXP.system.controller.Invitation', {
    extend: 'Ext.app.Controller',
    
    stores: [ 'JXP.system.store.Invitations' ],
 
    views: [
        'JXP.system.view.invitation.Panel', 
        'JXP.system.view.invitation.Grid'
    ],
    
    init: function() {
    	JXP_console.log('Initialized Invitation! ');
        this.control({
            'invitationpanel': {
                activate: this.loadData
            }
        });
    },
    
    loadData : function(panel) {
    	var grid = panel.down('grid');
    	grid.getStore().load({start:0, limit:25});
    }
});