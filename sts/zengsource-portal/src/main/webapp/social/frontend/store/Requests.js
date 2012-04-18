/**
 * 
 */
Ext.define('JXP.social.frontend.store.Requests', {
    extend: 'Ext.data.Store',
    model: 'JXP.social.frontend.model.Request', 
    autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url : ROOT_PATH+'/social/request.jxp',
        //url: ROOT_PATH+'/social/requests.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Request',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});