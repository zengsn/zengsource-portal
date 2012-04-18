/**
 * 
 */
Ext.define('JXP.system.model.Module', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'name',
		mapping: 'name'
	}, {
		name: 'title',
		mapping: 'title'
	}, {
		name: 'index',
		mapping: 'index'
	}, {
		name: 'status',
		type   : 'int', 
		mapping: 'status'
	}, {
		name: 'configFile',
		mapping: 'configFile'
	}, {
		name: 'updatedTime',
		mapping: 'updatedTime'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});