// system/portlets/SuccessPortlet.js
Ext.define('XP.system.SuccessPortlet', {
    extend: 'Ext.panel.Panel',

    requires: [
    ],
    
    alias: 'widget.successportlet',

    id:'successportlet',
    config: {
    },
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '操作成功',
            margins: '5 5 5 5',
            //border : false,
            frame: true,
            bodyStyle: {
                background : '#fff',
                padding    : '30px',
                textAlign  : 'center'
            },
            html: '<center><img src="'+ROOT_PATH+'/system/resources/images/success128.png" /></center>'
    	});
        this.callParent(arguments);
    }
    
}, function() {
	// after create ...
});