Ext.define('JXP.social.frontend.view.request.View', {
    extend: 'Ext.view.View',
    alias: 'widget.socialrequestview',
    
    uses: 'JXP.social.frontend.store.Requests',
    
	singleSelect: true,
    overItemCls: 'x-view-over',
    itemSelector: 'div.thumb-wrap',
    tpl: [
        // '<div class="details">',
            '<tpl for=".">',
                '<div class="thumb-wrap">',
                    '<div class="thumb">',
                    (!Ext.isIE6? '<img src="'+ROOT_PATH+'{avatar}" />' : 
                    '<div style="width:74px;height:74px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\'icons/{thumb}\')"></div>'),
                    '</div>',
                    '<span>{nickname}</span>',
                '</div>',
            '</tpl>'
        // '</div>'
    ],
    
    initComponent: function() {
        this.listeners =  {
            scope: this,
            selectionchange: this.onIconSelect,
            itemdblclick: this.fireImageSelected
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
            this.up('socialrequestportlet').down('socialrequestinfo').loadRecord(selected);
        } else { // 清除
        	this.up('socialrequestportlet').down('socialrequestinfo').clearRecord();
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
    }
});