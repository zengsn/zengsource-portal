/**
 * 
 */
Ext.define('JXP.system.model.Page', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'name',
		mapping: 'name'
	}, {
		name: 'url',
		mapping: 'url'
	}, {
		name: 'status',
		type   : 'int', 
		mapping: 'status'
	}, {
		name: 'module',
		mapping: 'module'
	}, {
		name: 'current',
		mapping: 'current'
	}, {
		name: 'description',
		mapping: 'description'
	}, {
		name: 'updatedTime',
		mapping: 'updatedTime'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});