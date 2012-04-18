/*

This file is part of Ext JS 4

Copyright (c) 2011 Sencha Inc

Contact:  http://www.sencha.com/contact

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

*/
/**
 * @class Ext.app.Portal
 * @extends Object
 * A sample portal layout application class.
 */
// TODO: Fill in the content panel -- no AccordionLayout at the moment
// TODO: Fix container drag/scroll support (waiting on Ext.lib.Anim)
// TODO: Fix Ext.Tool scope being set to the panel header
// TODO: Drag/drop does not cause a refresh of scroll overflow when needed
// TODO: Grid portlet throws errors on destroy (grid bug)
// TODO: Z-index issues during drag

Ext.define('Ext.app.Portal', {

    //extend: 'Ext.container.Viewport',
	extend: 'Ext.panel.Panel',

    uses: ['Ext.app.PortalPanel', 'Ext.app.PortalColumn', 'Ext.app.GridPortlet', 'Ext.app.ChartPortlet'],

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
        var content = '<div class="portlet-content">'+Ext.example.shortBogusMarkup+'</div>';
        var cfgHeight = Ext.get('ct-portal').getStyle('height');
        if (cfgHeight) cfgHeight = cfgHeight.replace('px', '');
        else cfgHeight = 1150;

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
                //title: 'Center',
                width: 1020,
                border: false,
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
                        xtype: 'portalpanel',
                        region: 'center',
                        border: true,
                        items: [{
                        	id: 'loader',
                        	colspan:3,
                        	width: 1000,
                        	title: 'Loading ...',
                        	html: 'Loading ...' 
                        }],
                        listeners : {
                        	'afterlayout' : function(container, layout, opts) {
                        		var items = container.items;
                        		if (items.getCount()==1) {
                        			var item = items.get('loader');
                        			if (item) { // 初始状态
                        				if (!(container.runOnce)) container.runOnce=true;
                        				else return;//只运行一次
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
    }
});

