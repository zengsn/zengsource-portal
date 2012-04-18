// portal/view/Button.js
Ext.define('JXP._core.view.LinkItem', {
	extend : 'Ext.menu.Item',

	requires : ['JXP._core.view.LinkHandler'],

	alias : 'widget.linkitem',

	//id : 'link-item',
	config : {},
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
		});
		this.callParent(arguments);
		this.on('click', function(item, e, opts) {
			e.preventDefault();
			var handler = Ext.create('JXP._core.view.LinkHandler');
			handler.doClick(item, e);
		});
	}
}, function() {
	// after create ...
});