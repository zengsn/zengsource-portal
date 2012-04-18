Ext.define('JXP.task.frontend.view.dashboard.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'JXP.task.frontend.view.dashboard.Grid'
    ],
    
    alias: 'widget.taskdashboardportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '任务状态',
            items: [{
            	xtype: 'taskdashboardgrid'
            	//html: 'ok'
            }]
    	});
        this.callParent(arguments);
    },
    
    showHelp : function(panel) {
    	var me = this;
    	XP_Msg({
       		corner : 'br',
       		manager: me,
       		title  : '帮助信息',
       		html   : '右键点击记录进行操作。'
       	});
    }
}, function() {
	// after create ...
});