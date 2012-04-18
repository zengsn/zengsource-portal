/**
 * 
 */
Ext.define('JXP.system.model.Role', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'rid',
		mapping : 'rid'
	}, {
		name: 'name',
		mapping: 'name'
	}, {
		name: 'description',
		mapping: 'description'
	}, {
		name: 'permissions',
		mapping: 'permissions'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});