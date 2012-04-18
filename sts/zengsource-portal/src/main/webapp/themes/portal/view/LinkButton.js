// portal/view/Button.js
Ext.define('JXP._core.view.LinkButton', {
	extend : 'Ext.button.Button',

	requires : ['JXP._core.view.LinkHandler'],

	alias : 'widget.linkbutton',

	//id : 'link-button',
	config : {},
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
			handler: function(btn, e) {
				e.preventDefault();
				var handler = Ext.create('JXP._core.view.LinkHandler');
				handler.doClick(btn, e);
			}
		});
		this.callParent(arguments);
	}
}, function() {
	// after create ...
});