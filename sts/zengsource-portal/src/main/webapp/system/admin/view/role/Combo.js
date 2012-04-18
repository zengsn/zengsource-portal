// admin/security/RoleGrid.js
Ext.define('JXP.system.view.role.Combo', {
	extend : 'Ext.form.field.ComboBox',

	requires : [ 'Ext.form.*' ],

	alias : 'widget.systemrolecombo',

	id : 'systemrolecombo',
	config : {},

	store : 'JXP.system.store.Roles',
	constructor : function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {
		    //fieldLabel: 'Choose State',
		    //queryMode: 'local',
		    displayField: 'name',
		    valueField: 'rid'
		});
		this.callParent(arguments);
	}
}, function() {
	// after create ...
});