Ext.define('JXP.task.frontend.view.create.file.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'JXP.task.frontend.view.create.file.Size',
        'JXP.task.frontend.view.create.file.Form'
    ],
    
    alias: 'widget.taskcreatefileportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '收取文件',
            items: [{
            	xtype: 'taskcreatefileform'
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