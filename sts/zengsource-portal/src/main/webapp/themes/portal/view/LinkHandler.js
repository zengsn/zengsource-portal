// portal/view/Button.js
Ext.define('JXP._core.view.LinkHandler', {
	requires : [ ],
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
		});
		this.callParent(arguments);
	},
	
	doClick : function(item, e) {
		var me = this;
    	var jxppanel = item.up('jxppanel');
    	if (!jxppanel) jxppanel = Ext.getCmp('app-viewport');
    	var portal = jxppanel.down('portal');
    	//JXP_console.log(jxppanel.getHeight());
    	var mask = portal.setLoading('加载中……');
    	// 加载配置
		Ext.Ajax.request({
		    //url: 'system/portlets/signin.json',
			url: ROOT_PATH+'/page.jxp',
		    params: {
		    	page: item.href
		    },
		    success: function(response, opts) {
		        var obj = Ext.decode(response.responseText);
		        if (obj) {
		        	var items = portal.items;//return;
		        	var count = items.getCount();
            		if (count==1) {
            			var item = items.get('loader');
            			if (item) {
            				portal.remove(0);
            				count--;
            			}
            		}
            		// 添加新区块
            		me.loadRequires(portal, obj);
            		mask.hide();
		        }
		    },
		    failure: function(response, opts) {
		    	JXP_console.log('server-side failure with status code ' + response.status);
		    }
		});
	},
	
	loadRequires : function(portal, obj) {
		var me = this;
		Ext.Loader.setPath(obj.path);
    	//Ext.require(['JXP.system.frontend.controller.SignIn'], function() {
		Ext.Array.each(obj.requires, function(item, index, arr) {
		    //JXP_console.log(item);
		    Ext.require(item, function() {
		    	if (index == arr.length-1) {
		    		me.addPortlets(portal, obj);
		    	}
        	}, obj);
		});
	},
	
	addPortlets : function(portal, obj) {
		var me = this;
		Ext.Array.each(obj.portlets, function(item, index, arr) {
			//JXP_console.log(i);
			//JXP_console.log(item.xtype);
			var p = portal.down(item.xtype);
			if (obj.data) { item.pageData = obj.data; }
			var item = Ext.apply(item); 
			//if (portal.items.getCount()==0 && index==0) 
			//	item.closable=false;//保存一个不允许关闭
			if (index == 0) { // 插入第一个位置时，判断是否为唯一一个
				if (portal.items.getCount()==0) {
					item.closable=false;
				} else if (portal.items.getCount()==1) {
					item.closable=(portal.items.getAt(0).xtype==item.xtype?false:true);
				}
			}
			if (p) { 
				portal.remove(p);
			}
			portal.insertPortlet(index, item);
			portal.checkItemTool();
		});
	}
}, function() {
	// after create ...
});