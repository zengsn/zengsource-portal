Ext.define('JXP.apps.frontend.view.dashboard.View', {
    extend: 'Ext.view.View',
    alias: 'widget.appsdashboardview',
    
    uses: 'JXP.apps.frontend.store.Online',
    
	singleSelect: true,
    overItemCls: 'x-view-over',
    itemSelector: 'div.thumb-wrap',
    tpl: [
        // '<div class="details">',
            '<tpl for=".">',
                '<div class="thumb-wrap">',
                    '<div class="thumb" style="float:left;display:inline;">',
                    (!Ext.isIE6? '<img src="'+ROOT_PATH+'/{name}/resources/images/{name}_logo.png" title="{desc}"/>' : 
                    '<div style="width:74px;height:74px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\'icons/{thumb}\')"></div>'),
                    '</div>',
                    '<span style="display:inline;font-size:16px;padding:10px;line-height:48px;">{title}(8)</span>',
                '</div>',
            '</tpl>'
        // '</div>'
    ],
    
    initComponent: function() {
        this.listeners =  {
            scope: this,
            selectionchange: this.onIconSelect,
            itemdblclick: this.fireImageSelected,
            itemcontextmenu: this.showContextMenu
        };
        this.callParent(arguments);
        //this.store.sort();
    }, 
    /**
     * Called whenever the user clicks on an item in the DataView. This tells the info panel in the east region to
     * display the details of the image that was clicked on
     */
    onIconSelect: function(dataview, selections) {
        var selected = selections[0];
        
        if (selected) {
            this.up('appsdashboardportlet').down('appsdashboardinfo').loadRecord(selected);
        } else { // 清除
        	this.up('appsdashboardportlet').down('appsdashboardinfo').clearRecord();
        }
    },
    
    /**
     * Fires the 'selected' event, informing other components that an image has been selected
     */
    fireImageSelected: function() {
        var selectedImage = this.selModel.getSelection()[0];
        
        if (selectedImage) {
            this.fireEvent('selected', selectedImage);
            this.hide();
        }
    },
    
    showContextMenu : function(view, rec, item, index, e, opts) {
    	var me = this;
    	me.selModel.select(rec);
        //var rec = me.selModel.getSelection()[0];
        
        if (rec) {
			if (me.contextMenu) {
				if (me.contextMenu.isVisible()) {
					me.contextMenu.hide();
					me.contextMenu.destory();
				}
			} 
			me.contextMenu = Ext.create('Ext.menu.Menu', {
			    width: 100,
			    floating: true,  
			    renderTo: Ext.getBody(),  
			    items: [{
			        text: '安装',
			        handler: function() {
			        	JXP_console.dir(rec.data);
			        	//me.edit(rec);
			        }
			    }, {
			        text: '卸载',
			        handler: function() {
			        	//me.editRole(rec);
			        }
			    }, '-', {
			        text: '评价',
			        handler: function() {
			        	//me.editRole(rec.data);
			        }
			    }, '-', {
			        text: '投诉'
			    }]
			});
			me.contextMenu.setPagePosition(e.getXY());
			me.contextMenu.show();
			e.stopEvent();
        }
    	
    }
});