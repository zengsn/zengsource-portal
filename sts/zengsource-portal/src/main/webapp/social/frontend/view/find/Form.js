Ext.define('JXP.social.frontend.view.find.Form', {
    extend: 'Ext.form.Panel',

    requires: [
        'Ext.form.*'
    ],
    
    alias: 'widget.socialfindform',

    id:'socialfindform',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
    		//height: 150,
    		//bodyStyle:'padding:5px 5px 5px 5px',
            fieldDefaults: {
                msgTarget: 'side',
                labelWidth: 75
            },
            defaultType: 'textfield',
            defaults: {
                //anchor: '100%'
            },
            layout: {
                type: 'vbox',
                padding:'0',
                align:'stretch'
            },
            items: [{
            	flex: 2,
                xtype: 'panel',
                border: false,
                layout: {
                    type: 'hbox',
                    padding:'0',
                    align:'stretch'
                },
                items: [{
                	flex: 4,
                    xtype: 'textfield',
                    enableKeyEvents: true,
                	name: 'query'
                }, {
                	flex: 1,
                	margins: '0 0 5 0',
                	xtype: 'button',
                	text: '搜索 [ENTER]',
                	handler: function(btn) {
                		var form = btn.up('socialfindform');
                		form.doSearch();
                	}
                }]
            }, {
            	flex : 1,
                xtype: 'component',
                style: 'margin:5px;color:gray;',
                html : '输入好友名称、邮件或兴趣爱好进行搜索。'
            }]
    	});
        this.callParent(arguments);
    },
    
    focusQuery : function() {
    	var me = this;
    	me.down('textfield').focus();
    },
    
    doSearch : function(field) {
    	var me = this;
    	if (!field) {
    		field = me.down('textfield');
    	}
		var portlet = field.up('socialfindportlet');
		var grid = portlet.down('socialfindgrid');
		var store = grid.getStore();
		store.getProxy().extraParams.q = field.getValue();
		store.load({start:0, limit:25, q: field.getValue()});
    }
}, function() {
	// after create ...
});