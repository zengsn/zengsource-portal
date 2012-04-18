/**
 * 
 */
Ext.define('JXP.system.store.Permissions', {
    extend: 'Ext.data.Store',
    model: 'JXP.system.model.Permission', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: 'permission.jxp?action=list',
	    params: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Permission',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});