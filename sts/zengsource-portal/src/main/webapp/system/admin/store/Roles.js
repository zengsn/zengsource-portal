/**
 * 
 */
Ext.define('JXP.system.store.Roles', {
    extend: 'Ext.data.Store',
    model: 'JXP.system.model.Role', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: 'role.jxp?action=list',
	    params: {
	        client: 'extajax',
	        action: 'list'
	    },
        // the return will be XML, so lets set up a reader
        reader: {
            type: 'xml',
            // records will have an "Item" tag
            record: 'Role',
            idProperty: 'rid',
            totalRecords: 'totalCount'
        }
    }
});