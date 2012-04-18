// portal/view/Button.js
Ext.define('JXP._core.view.LinkSplitButton', {
	extend : 'Ext.button.Split',

	requires : ['JXP._core.view.LinkHandler'],

	alias : 'widget.linksplitbutton',

	//id : 'link-splitbutton',
	config : {},
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
		});
		this.callParent(arguments);
		this.on('click', function(btn, e, opts) {
			var handler = Ext.create('JXP._core.view.LinkHandler');
			handler.doClick(btn, e);
		});
	}
}, function() {
	// after create ...
});