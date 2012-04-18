Ext.define('JXP.task.frontend.view.my.draft.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'JXP.task.frontend.view.my.draft.Grid'
    ],
    
    alias: 'widget.taskmydraftportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '草稿任务',
            items: [{
            	xtype: 'taskmydraftgrid'
            	//html: 'ok'
            }]
    	});
        this.callParent(arguments);
    },
    
    focusUsername : function() {
    }
}, function() {
	// after create ...
});