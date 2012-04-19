Ext.define('JXP.apps.frontend.view.dashboard.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'JXP.apps.frontend.view.dashboard.View',
        'JXP.apps.frontend.view.dashboard.Info'
    ],
    
    alias: 'widget.appsdashboardportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
        this.store = Ext.create('JXP.apps.frontend.store.Online');
    	this.config = Ext.apply(config, {
            title: '应用管理',
            // margins: '5 5 5 5',
            layout: 'fit',
            items: [{
            	layout: 'border',
                items: [{
                    region: 'center',
                    layout: 'fit',
            		margins: '5 5 5 5',
                    border: false,
                    items: [{
                        xtype: 'appsdashboardview',
                        store: this.store
                    }],
                    split: true
                }, {
                    region: 'east',
                    width: 260,
                    minWidth: 260,
                    layout: 'fit',
                    border: false,
            		margins: '5 5 5 5',
                	 items: [{
                    	xtype: 'appsdashboardinfo'
                    }]
                }],
                tbar : ['搜索', {
	               	 xtype: 'textfield',
	               	 name : 'q',
	               	 listeners: {
	               		 'change' : function(field, newValue, oldValue, opts) {
	               			 if (newValue && newValue.length>1) {
	   	            			 me.store.proxy.extraParams.q = newValue;
	   	            			 me.store.load({start: 0, limit: 25});
	   	            		 }
	               		 }
	               	 }
                }],
                bbar : Ext.create('Ext.PagingToolbar', {
     				store : me.store,
     				//displayInfo : true,
     				items : []
     			})
            }]
    	});
        this.callParent(arguments);
    },
    
    focusUsername : function() {
    }
}, function() {
	// after create ...
});