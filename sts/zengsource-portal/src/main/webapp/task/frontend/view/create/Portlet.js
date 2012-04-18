Ext.define('JXP.task.frontend.view.create.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        //'JXP.task.frontend.view.create.file.Size',
        'JXP.task.frontend.view.create.Form'
    ],
    
    alias: 'widget.taskcreateportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '创建任务',
            items: [{
            	xtype: 'taskcreateform'
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