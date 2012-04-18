
Ext.define('JXP._core.controller.Core', {
    extend: 'Ext.app.Controller',
    
    stores: [ 
        //'JXP.system.store.Nodes'
    ],
 
    views: [
        'JXP._core.view.NaviBar',
        'JXP._core.view.LinkItem',  
        'JXP._core.view.LinkButton',
        'JXP._core.view.LinkSplitButton',
        'JXP._core.view.button.Social', 
        'JXP._core.view.button.Notice', 
        'JXP._core.view.button.User', 
        'JXP._core.view.button.Apps', 
        'JXP._core.view.HeaderBar',   
        'JXP._core.view.Portlet', 
        'JXP._core.view.Portal',
        'JXP._core.view.Panel'
    ],
    
    init: function() {
    	JXP_console.log('Initialized portal core!! ');
        this.control({
            'jxppanel': {
                'afterlayout': this.afterStartup
            }
        });
    },
    
    afterStartup : function(panel) {
    	var callback = function() {
    		return;
        	JXP_Msg({
        		corner  : 'tr',
        		manager : panel.down('portal'),
        		title: '欢迎您',
        		html : '欢迎您来到 LoBoSi.com!'
        	});
        };
		var hideMask = function () {
			if (!(Ext.get('loading'))) return;
            Ext.get('loading').remove();
            Ext.fly('loading-mask').animate({
                opacity:0,
                remove:true,
                callback: function() {
                	Ext.defer(callback, 250);
                }
            });
        };

        Ext.defer(hideMask, 250);
    }
});