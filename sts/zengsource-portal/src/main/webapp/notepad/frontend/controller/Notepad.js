
Ext.define('JXP.notepad.frontend.controller.Notepad', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        'JXP.notepad.frontend.store.Notes'
    ],
 
    views: [
        'JXP.notepad.frontend.view.Form',
        'JXP.notepad.frontend.view.Portlet'
    ],
    
    init: function() {
    	JXP_console.log('Init JXP.notepad.frontend.controller.Notepad ... ');
        this.control({
//            'userinfoportlet': {
//            	afterlayout: this.loadData
//            }
        });
    },
    
    loadData : function(portlet) {
    }
});