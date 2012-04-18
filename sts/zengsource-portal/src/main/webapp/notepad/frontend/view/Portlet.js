Ext.define('JXP.notepad.frontend.view.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'Ext.form.field.Text'
    ],
    
    alias: 'widget.notepadportlet',

    //id:'notepadportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '记事本',
            items: [{
            	xtype: 'notepadform'
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