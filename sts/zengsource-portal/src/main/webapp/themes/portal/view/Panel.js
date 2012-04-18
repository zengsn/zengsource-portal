
Ext.define('JXP._core.view.Panel', {
	extend: 'Ext.panel.Panel',
	requires: ['JXP.*'],
    //uses: ['Ext.app.PortalPanel', 'Ext.app.PortalColumn', 'Ext.app.GridPortlet', 'Ext.app.ChartPortlet'],
    alias : 'widget.jxppanel',
    getTools: function(){
        return [{
            xtype: 'tool',
            type: 'gear',
            handler: function(e, target, panelHeader, tool){
                var portlet = panelHeader.ownerCt;
                portlet.setLoading('Working...');
                Ext.defer(function() {
                    portlet.setLoading(false);
                }, 2000);
            }
        }];
    },

    initComponent: function(){
    	var me = this;
        //var content = '<div class="portlet-content">'+Ext.example.shortBogusMarkup+'</div>';
        var ctEl = Ext.get('ct-portal');
        var cfgHeight = ctEl.getStyle('height').replace('px', '');
        if (cfgHeight==0) cfgHeight = 500;
        var cfgWidth = ctEl.getStyle('width').replace('px', '');
        //JXP_console.log(cfgWidth);
        if (cfgWidth==0) cfgWidth = 1020;
        ctEl.setStyle('height', '');
        ctEl.setStyle('width', '');

        Ext.apply(this, {
            id: 'app-viewport',
            height: cfgHeight,
            layout: {
                type: 'hbox',
                pack: 'start',
                align: 'stretch'
            },
            border: false,
            defaults: {
            	border: false
            },
            items: [{
                //title: 'Left',
                flex:1,
                contentEl: 'ct-left'
            },{
                id: 'app-container',
                width: cfgWidth-0,
                //border: false,
                margins: '5,0,0,0',
                layout: {
                    type: 'border',
                    padding: '0 5 5 5' // pad the layout from the window edges
                },
                items: [{
                    id: 'app-header',
                    xtype: 'panel',
                    region: 'north',
                    height: 75,
                    frame: true,
                    margins: '5 5 0 5',
                    layout: 'fit',
                    items: [{
                        xtype: 'headerbar', 
                        margins: '0 5 0 5',
                        border: false
                    }]
                }, {
                    xtype: 'panel',
                    region: 'south',
                    border: false,
                	height: 32,
                	contentEl: 'app-footer'
                }, {
                    xtype: 'container',
                    region: 'center',
                    layout: 'border',
                    margins: '5 0 0 0',
                    items: [{
                        id: 'app-portal',
                        xtype: 'portal',
                        region: 'center',
                        border: true,
                        items: [{
                        	id: 'loader',
                        	colspan:3,
                        	width: 1000,
                        	//title: 'Loading ...',
                        	html: 'Loading ...' 
                        }],
                        listeners : {
                        	'afterlayout' : function(container, layout, opts) {
                        		var items = container.items;//return;
                        		if (items.getCount()==1) {
                        			var item = items.get('loader');
                        			if (item) { // 初始状态
                        				if (!(container.runOnce)) container.runOnce=true;
                        				else return;//只运行一次
                        				// 直接从页面取
                        				if (_portlets && _portlets.length>0) {
                        					me.updatePortlets(_portlets);
                        					return;
                        				}
                        				return;
                        				// 加载配置
                        				Ext.Ajax.request({
                        				    url: Ext.get('portlets').dom.innerHTML,
                        				    success: function(response, opts) {
                        				        var obj = Ext.decode(response.responseText);
                        				        //JXP_console.dir(obj);
                        				        var portalPanel = Ext.getCmp(container.getId());
                        				        if (obj && portalPanel) {
                        				        	portalPanel.remove(0);
                        				        	portalPanel.add(Ext.apply(obj));
                        				        }
                        				    },
                        				    failure: function(response, opts) {
                        				    	JXP_console.log('server-side failure with status code ' + response.status);
                        				    }
                        				});
                        			}
                        		}
                        	}//afterlayout
                        }
                    }]
                }]
            },{
                //title: 'Right',
                flex:1,
                contentEl: 'ct-right'
            }]
        });
        this.callParent(arguments);
    },

    onPortletClose: function(portlet) {
        this.showMsg('"' + portlet.title + '" was removed');
    },

    showMsg: function(msg) {
        var el = Ext.get('app-msg'),
            msgId = Ext.id();

        this.msgId = msgId;
        el.update(msg).show();

        Ext.defer(this.clearMsg, 3000, this, [msgId]);
    },

    clearMsg: function(msgId) {
        if (msgId === this.msgId) {
            Ext.get('app-msg').hide();
        }
    },
    
    updatePortlets : function(obj) {
    	var me = this;
    	var portal = me.down('portal');
        if (obj && portal) {
        	portal.remove(0);
        	portal.add(Ext.apply(obj));
        }
    },
    
    resetHeight : function() {
    	var me = this;
    	var portal = me.down('portal');
    	var newHeight = 250;
    	portal.items.each(function(item, index, len) {
    		newHeight += item.getHeight();
    	});
    	me.setHeight(newHeight);
    }
}, function() {
	// after create ...
});

