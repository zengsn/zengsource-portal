Ext.define('JXP.system.frontend.view.index.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
    ],
    
    alias: 'widget.indexportlet',

    //id:'indexportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: 'Lobosi.com',
            closable: true,
            html: ''
            	+'<div style="text-align:left;vertical-align: middle;padding:50px;">'
            	+'<img src="system/resources/images/wait_128.png" />'
            	+'<span style="font-size:16px;color:gray;margin-bottom:0px;">本站仍在内部测试中，您可以申请邀请码，我们会在适当的时候邀请您加入。感谢关注！</span></div>'
    	});
        this.callParent(arguments);
    }
}, function() {
	// after create ...
});