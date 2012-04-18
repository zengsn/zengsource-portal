/**
 * 
 */
Ext.define('JXP.system.store.Users', {
    extend: 'Ext.data.Store',
    model: 'JXP.system.model.User', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: 'user.jxp?action=list',
	    params: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'User',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});