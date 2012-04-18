/**
 * 
 */
Ext.define('JXP.task.frontend.model.Note', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'executor',
		mapping: 'executor'
	}, {
		name: 'text',
		mapping: 'text'
	}, {
		name: 'attachment',
		mapping: 'attachment'
	}, {
		name: 'status',
		mapping: 'status'
	}, {
		name: 'progress',
		mapping: 'progress'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});