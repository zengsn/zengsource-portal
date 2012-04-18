Ext.define('JXP.home.frontend.view.status.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        //'JXP.task.frontend.view.create.file.Size',
        //'JXP.task.frontend.view.create.Form'
    ],
    
    alias: 'widget.homestatusportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: CURRENT_USER.nickname,
            layout: 'border',
            items: [{
            	region: 'north',
            	height: 100,
            	xtype : 'homestatusform'
            }, {
            	region: 'center',
            	//icon: ROOT_PATH+CURRENT_USER.avatar,
            	xtype: 'homestatusgrid'
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