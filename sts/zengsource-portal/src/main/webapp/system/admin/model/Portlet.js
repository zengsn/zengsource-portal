/**
 * 
 */
Ext.define('JXP.system.model.Portlet', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'name',
		mapping: 'name'
	}, {
		name: 'singleton',
		mapping: 'singleton'
	}, {
		name: 'index',
		type   : 'int', 
		mapping: 'index'
	}, {
		name: 'rowspan',
		mapping: 'rowspan'
	}, {
		name: 'colspan',
		mapping: 'colspan'
	}, {
		name: 'module',
		mapping: 'module'
	}, {
		name: 'pages',
		mapping: 'pages'
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