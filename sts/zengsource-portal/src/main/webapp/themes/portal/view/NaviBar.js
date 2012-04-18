/**
 * @class Ext.app.HeaderBar
 * @extends Ext.toolbar.Toolbar
 * @see {@link Ext.toolbar.Toolbar Toolbar} 
 */
Ext.define('JXP._core.view.NaviBar', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.navibar',
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            border: false
    	});
        this.callParent(arguments);
    }
});
