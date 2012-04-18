/**
 * 
 */
Ext.define('JXP.system.model.MenuNode', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name : 'text',
		mapping : 'text'
	}, {
		name : 'leaf',
		mapping : 'leaf'
	}, {
		name : 'children',
		mapping : 'children'
	}, {
		name : 'cls',
		mapping : 'cls'
	}, {
		name : 'expanded',
		mapping : 'expanded'
	}, {
		name : 'href',
		mapping : 'href'
	}, {
		name : 'hrefTarget',
		mapping : 'hrefTarget'
	}, {
		name: 'widget',
		mapping: 'widget'
	}, {
		name: 'controller',
		mapping: 'controller'
	}]
});