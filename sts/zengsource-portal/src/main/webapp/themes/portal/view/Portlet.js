/**
 * @class JXP.view.Portlet
 * @extends Ext.panel.Panel
 * A {@link Ext.panel.Panel Panel} class that is managed by {@link Ext.app.PortalPanel}.
 */
Ext.define('JXP._core.view.Portlet', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.portlet',
    layout: 'fit',
    //anchor: '100%',
    frame: true,
    closable: false,
    collapsible: false,
    animCollapse: true,
    //draggable: true, // !!!
    width: 330,
    height: 330,
    cls: 'x-portlet',
    overCls: 'x-portlet-over',
    defaults: {border: false},
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.pinned = config.pinned ? true : false;
    	this.config = Ext.apply(config, {
            tools: [{
            	type: 'help',
            	tooltip: '帮助',
            	handler: function(event, toolEl, panel){
                    me.showHelp(panel);
                }
            }, {
            	type: 'unpin',
            	tooltip: '取消置顶',
            	disabled: !(this.pinned),
            	handler: function(event, toolEl, panel){
                    me.toolPin(false, toolEl);
                }
            }, {
            	type: 'pin',
            	tooltip: '置顶',
            	disabled: this.pinned,
            	handler: function(event, toolEl, panel){
                    me.toolPin(true, toolEl);
                }
            }, {
            	type: 'up',
            	tooltip: '上移',
            	disabled: this.pinned,
            	handler: function(event, toolEl, panel){
                    me.toolMove(true, toolEl);
                }
            }, {
            	type: 'down',
            	tooltip: '下移',
            	disabled: this.pinned,
            	handler: function(event, toolEl, panel){
                    me.toolMove(false, toolEl);
                }
            }],
            listeners: {
            	'indexchanged' : me.doIndexChanged
            }
    	});
        this.callParent(arguments);
        this.on('afterlayout', function() {
        	me.checkTool();
        });
    },
    
    showHelp : function(panel) {
    	var me = this;
    	XP_Msg({
       		corner : 'br',
       		manager: me,
       		title  : '帮助',
       		html   : me.getHelp()
       	});
    },
    
    getHelp : function() {
    	return '此控件无帮助信息！';
    },
    
    doIndexChanged : function(p, oldIdx, newIdx, opts) {
    	JXP_console.log(newIdx);
	},
    
    getToolByType : function(type) {
    	var me = this;
    	var tool = me.header.el.select('div.x-tool-default:has(img.x-tool-'+type+')');
    	//JXP_console.dir(tool);
    	return tool.isComposite?tool.item(0):tool;
    },
    
    toolPin : function(true4Pin, toolEl) {
    	var me = this;
		//Ext.fly(toolEl).remove();
		var portal = me.up('portal');
    	if (true4Pin) {
    		me.pinned = true;
    		Ext.getCmp(me.getToolByType('unpin').id).enable();
    		Ext.getCmp(me.getToolByType('pin').id).disable();
    		var index = me.findIndex();
    		if (index != 0)
    			portal.move(index, 0);
    		me.checkTool();
    		portal.items.getAt(index).checkTool();
    	} else {
    		me.pinned = false;
    		me.checkTool();
    		Ext.getCmp(me.getToolByType('pin').id).enable();
    		Ext.getCmp(me.getToolByType('unpin').id).disable();
    	}
    },
    
    toolMove : function(true4up, toolEl) {
    	var me = this;
    	if (me.pinned==true) {return;}
		var portal = me.up('portal');
		var index = me.findIndex();
    	if(true4up) {
    		if (index > 0) {
    			portal.move(index, index - 1);
    		}
    		me.checkTool();
    		portal.items.getAt(index).checkTool();
    	} else {
    		if (index < portal.items.getCount() - 1);
    			portal.move(index, index + 1);
        	me.checkTool();
    		portal.items.getAt(index).checkTool();
    	}
    },
    
    checkTool : function() {
    	var me = this;
    	var index  = me.findIndex();
    	var portal = me.up('portal');
    	var count  = portal.items.getCount();
    	if (index == 0) {
    		Ext.getCmp(me.getToolByType('up').id).disable();
    		if (count == 1) {
        		Ext.getCmp(me.getToolByType('down').id).disable(); 
    		} else if (!(me.pinned)) {
        		Ext.getCmp(me.getToolByType('down').id).enable();
    		}
    	} else if (index == count - 1) {
    		if (!(me.pinned)) {
        		Ext.getCmp(me.getToolByType('up').id).enable();
    		}
    		Ext.getCmp(me.getToolByType('down').id).disable();
    	} else {
    		var before = portal.items.getAt(index - 1);
    		if (before.pinned) {
    			Ext.getCmp(me.getToolByType('up').id).disable();
    		} else {
    			Ext.getCmp(me.getToolByType('up').id).enable();
    		}
    		var after = portal.items.getAt(index + 1);
    		if (after.pinned) {
    			Ext.getCmp(me.getToolByType('down').id).disable();
    		} else {
    			Ext.getCmp(me.getToolByType('down').id).enable();
    		}
    	}
    },
    
    findIndex : function() {
    	var me = this;
		var portal = me.up('portal');
		if(portal.items) {
			for(var i=0;i<portal.items.getCount();i++) {
				if(portal.items.getAt(i).id==me.id) return i;
			}
		}
    },

    // Override Panel's default doClose to provide a custom fade out effect
    // when a portlet is removed from the portal
    doClose: function() {
    	var me = this;
		var portal = me.up('portal');
        this.el.animate({
            opacity: 0,
            callback: function(){
                this.fireEvent('close', this);
                this[this.closeAction]();
        		portal.checkItemTool();
            },
            scope: this
        });
    }
});
