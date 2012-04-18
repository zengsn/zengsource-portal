// system/portlets/SuccessPortlet.js
Ext.define('XP.system.LobosiPortlet', {
    extend: 'Ext.panel.Panel',

    requires: [
    ],
    
    alias: 'widget.lobosiportlet',

    id:'lobosiportlet',
    config: {
    },
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: 'Lobosi.com',
            margins: '5 5 5 5',
            //border : false,
            frame: true,
            bodyStyle: {
                background : '#fff',
                padding    : '30px',
                textAlign  : 'center'
            },
            html: '<center><img src="'+ROOT_PATH+'/system/resources/images/lobosi1.jpg" style="width:200px;height:100px;" /></center>'
    	});
        this.callParent(arguments);
    }
    
}, function() {
	// after create ...
});