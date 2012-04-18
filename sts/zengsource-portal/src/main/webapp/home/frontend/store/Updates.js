/**
 * 
 */
Ext.define('JXP.home.frontend.store.Updates', {
    extend: 'Ext.data.Store',
    model: 'JXP.home.frontend.model.Update', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: ROOT_PATH+'/social/update.jxp',
        //url: ROOT_PATH+'/task/tasks.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Update',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});