/**
 * 
 */
Ext.define('JXP.system.model.Invitation', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'email',
		mapping: 'email'
	}, {
		name: 'introduction',
		mapping: 'introduction'
	}, {
		name: 'inviter',
		mapping: 'inviter'
	}, {
		name: 'invitee',
		mapping: 'invitee'
	}, {
		name: 'code',
		mapping: 'code'
	}, {
		name: 'socialType',
		mapping: 'socialType'
	}, {
		name: 'status',
		type   : 'int', 
		mapping: 'status'
	}, {
		name: 'ip',
		mapping: 'ip'
	}, {
		name: 'updatedTime',
		mapping: 'updatedTime'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});