Ext.define('JXP.task.frontend.view.my.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'JXP.task.frontend.view.my.Grid'
    ],
    
    alias: 'widget.taskmyportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '我的任务',
            items: [{
            	xtype: 'taskmygrid'
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