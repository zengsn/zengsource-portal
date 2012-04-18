/**
 * 
 */
Ext.define('JXP.home.frontend.model.Update', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'text',
		mapping: 'text'
	}, {
		name: 'owner',
		mapping: 'owner'
	}, {
		name: 'ownerId',
		mapping: 'ownerId'
	}, {
		name: 'ownerAvatar',
		mapping: 'ownerAvatar'
	}, {
		name: 'picture',
		mapping: 'picture'
	}, {
		name: 'status',
		mapping: 'status'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});