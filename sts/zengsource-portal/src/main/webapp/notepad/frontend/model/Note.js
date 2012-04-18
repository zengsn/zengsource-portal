/**
 * 
 */
Ext.define('JXP.notepad.frontend.model.Note', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'author',
		mapping: 'author'
	}, {
		name: 'text',
		mapping: 'text'
	}, {
		name: 'title',
		mapping: 'title'
	}, {
		name: 'status',
		mapping: 'status'
	}, {
		name: 'updatedTime',
		mapping: 'updatedTime'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});