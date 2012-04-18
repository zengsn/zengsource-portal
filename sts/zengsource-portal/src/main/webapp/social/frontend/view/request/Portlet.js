Ext.define('JXP.social.frontend.view.request.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'Ext.form.field.Text'
    ],
    
    alias: 'widget.socialrequestportlet',

    id:'socialrequestportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
        this.store = Ext.create('JXP.social.frontend.store.Requests');
    	this.config = Ext.apply(config, {
            title: '好友请求',
           // margins: '5 5 5 5',
            layout: 'border',
            items: [{
                region: 'center',
            	xtype: 'socialrequestview',
            	store: this.store,
                split: true
            }, {
                region: 'east',
                split: true,
                width: 260,
                minWidth: 260,
                layout: 'fit',
            	items: [{
            		margins: '5 5 5 5',
                	xtype: 'socialrequestinfo'
                }]
            }],
            bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				//displayInfo : true,
				items : []
			})
    	});
        this.callParent(arguments);
    },
    
    focusUsername : function() {
    }
}, function() {
	// after create ...
});