/**
 * @class Ext.app.PortalPanel
 * @extends Ext.panel.Panel
 * A {@link Ext.panel.Panel Panel} class used for providing drag-drop-enabled portal layouts.
 */
Ext.define('JXP._core.view.Portal', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.portal',
    requires: [
        //'Ext.layout.component.Body'
    ],

    cls: 'x-portal',
    bodyCls: 'x-portal-body',
    defaultType: 'portlet',
    //componentLayout: 'body',
    autoScroll: true,

    initComponent : function() {
        var me = this;

        this.layout = {
            type : 'table',//'column'
            columns: _PAGE.columns==0?1:_PAGE.columns
        };
        this.callParent();
        /*
        this.addEvents({
            validatedrop: true,
            beforedragover: true,
            dragover: true,
            beforedrop: true,
            drop: true
        });*/
        //this.on('drop', this.doLayout, this);
        this.on('afterlayout', function(com, layout, opts) {
        	me.up('jxppanel').resetHeight();
        });
    },

    // Set columnWidth, and set first and last column classes to allow exact CSS targeting.
    /*beforeLayout: function() {
        var items = this.layout.getLayoutItems(),
            len = items.length,
            i = 0,
            item;

        for (; i < len; i++) {
            item = items[i];
            item.columnWidth = 1 / len;
            item.removeCls(['x-portal-column-first', 'x-portal-column-last']);
        }
        if(len>0) { // Bug-Fixed - 2012/1/3
            items[0].addCls('x-portal-column-first');
            items[len - 1].addCls('x-portal-column-last');
        }
        return this.callParent(arguments);
    },*/

    // private
    initEvents : function(){
        this.callParent();
        //this.dd = Ext.create('Ext.app.PortalDropZone', this, this.dropConfig);
    },

    // private
    beforeDestroy : function() {/*
        if (this.dd) {
            this.dd.unreg();
        }*/
    	JXP.view.Portal.superclass.beforeDestroy.call(this);
    },
    
    checkItemTool : function() {
    	var me = this;
    	me.items.each(function(item, index, len) {
    		//JXP_console.log(index);
    		item.checkTool();
    	});
    },
    
    insertPortlet : function(index, item) {
    	var me = this;
    	var oldItem = me.items.getAt(index);
    	var count = me.items.getCount();
    	while(oldItem && oldItem.pinned) {
    		index++;
    		if (index<count) {
        		oldItem = me.items.getAt(index);
    		} else {
    			break;
    		}
    	}
    	if (index<count) {
        	me.insert(index, item);
    	} else {
    		me.add(item);
    	}
    }
});

