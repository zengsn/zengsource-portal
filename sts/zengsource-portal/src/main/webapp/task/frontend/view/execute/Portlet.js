Ext.define('JXP.task.frontend.view.execute.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        //'JXP.task.frontend.view.create.file.Size',
        //'JXP.task.frontend.view.create.Form'
    ],
    
    alias: 'widget.taskexecuteportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	//JXP_console.log(config.pageData);
    	this.config = Ext.apply(config, {
            title: '执行任务',
            items: [{
            	pageData: config.pageData,
            	xtype: 'taskexecutegrid'
            	//html: 'ok'
            }]
    	});
        this.callParent(arguments);
        this.on('afterrender', function(p) {
        	p.addTool({
            	type: 'help',
            	tooltip: '右键点击记录进行操作'
            });
        });
    },
    
    focusUsername : function() {
    }
}, function() {
	// after create ...
});