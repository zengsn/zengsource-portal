Ext.define('JXP.task.frontend.view.my.doing.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'JXP.task.frontend.view.my.doing.Grid'
    ],
    
    alias: 'widget.taskmydoingportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '进行中任务',
            items: [{
            	xtype: 'taskmydoinggrid'
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