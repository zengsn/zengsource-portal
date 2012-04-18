/**
 * 
 */
Ext.define('JXP.system.model.Config', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'key',
		mapping: 'key'
	}, {
		name: 'name',
		mapping: 'name'
	}, {
		name: 'value',
		mapping: 'value'
	}, {
		name: 'group',
		mapping: 'group'
	}, {
		name: 'desc',
		mapping: 'desc'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});