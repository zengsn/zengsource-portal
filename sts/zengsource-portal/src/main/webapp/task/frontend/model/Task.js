/**
 * 
 */
Ext.define('JXP.task.frontend.model.Task', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'name',
		mapping: 'name'
	}, {
		name: 'requestor',
		mapping: 'requestor'
	}, {
		name: 'tags',
		mapping: 'tags'
	}, {
		name: 'status',
		mapping: 'status'
	}, {
		name: 'progress',
		mapping: 'progress'
	}, {
		name: 'feature',
		mapping: 'feature'
	}, {
		name: 'responders',
		mapping: 'responders'
	}, {
		name: 'introduction',
		mapping: 'introduction'
	}, {
		name: 'lastUpdate',
		mapping: 'lastUpdate'
	}, {
		name: 'startTime',
		mapping: 'startTime'
	}, {
		name: 'endTime',
		mapping: 'endTime'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});