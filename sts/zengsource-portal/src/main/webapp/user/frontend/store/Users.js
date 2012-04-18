/**
 * 
 */
Ext.define('JXP.user.frontend.store.Users', {
    extend: 'Ext.data.Store',
    model: 'JXP.user.frontend.model.User', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: ROOT_PATH+'/user/info.jxp',
        extraParams: {
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