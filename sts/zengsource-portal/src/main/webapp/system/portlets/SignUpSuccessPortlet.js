// system/portlets/SignUpSuccessPortlet.js
Ext.define('XP.system.SignUpSuccessPortlet', {
    extend: 'Ext.panel.Panel',

    requires: [
    ],
    
    alias: 'widget.signupsuccessportlet',

    id:'signupsuccessportlet',
    config: {
    },
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	//var url = window.location.toString();
    	var query = window.location.search.substring(1);
    	var params = Ext.Object.fromQueryString(query);
    	this.config = Ext.apply(config, {
            title: '注册成功',
            margins: '5 5 5 5',
            //border : false,
            frame: true,
            bodyStyle: {
                background : '#fff',
                padding    : '10px'
            },
            html: '<p>帐号注册成功！</p><p>系统已经发送一封确认邮件到帐号邮箱：'+params.email+'，请登录邮箱按照提示激活帐号！</p>'
    	});
        this.callParent(arguments);
    }
    
}, function() {
	// after create ...
});