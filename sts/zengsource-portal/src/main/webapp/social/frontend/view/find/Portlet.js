Ext.define('JXP.social.frontend.view.find.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'Ext.form.field.Text'
    ],
    
    alias: 'widget.socialfindportlet',

    id:'socialfindportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '查找好友',
            margins: '5 5 5 5',
            layout: 'border',
            items: [{
            	region: 'north',
            	height: 60,
            	//border: true,
            	xtype: 'socialfindform'
            }, {
            	//margins: '5 5 5 5',
            	region: 'center',
            	xtype: 'socialfindgrid'
            }]
    	});
        this.callParent(arguments);
    },
    
    focusUsername : function() {
    }
}, function() {
	// after create ...
});