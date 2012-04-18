Ext.define('JXP.system.frontend.view.unauthorized.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
    ],
    
    alias: 'widget.unauthorizedportlet',

    //id:'indexportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '权限不足',
            closable: true,
            html: ''
            	+'<div style="text-align:left;vertical-align: middle;padding:50px;">'
            	+'<img src="system/resources/images/sad_48.png" />'
            	+'<span style="font-size:16px;color:gray;margin-bottom:0px;">奇怪，您竟然没有权限访问本页！</span></div>'
    	});
        this.callParent(arguments);
    }
}, function() {
	// after create ...
});