Ext.define('JXP.task.frontend.view.navi.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        //'Ext.form.field.Text'
    ],
    
    alias: 'widget.tasknaviportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '任务管理',
            pinned: true,
            bodyStyle: 'background: #fff;',
            items: [{
            	xtype: 'navitoolbar'
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