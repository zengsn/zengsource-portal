/**
 * 
 */
Ext.define('JXP.system.model.Permission', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'pid',
		mapping : 'pid'
	}, {
		name: 'name',
		mapping: 'name'
	}, {
		name: 'roleName',
		mapping: 'roleName'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});