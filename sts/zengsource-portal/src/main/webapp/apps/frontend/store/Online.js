/**
 * 
 */
Ext.define('JXP.apps.frontend.store.Online', {
    extend: 'Ext.data.Store',
    model: 'JXP.apps.frontend.model.Module', 
    autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        //url : ROOT_PATH+'/social/request.jxp',
        url: ROOT_PATH+'/apps/online.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Module',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});