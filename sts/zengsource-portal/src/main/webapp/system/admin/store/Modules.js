/**
 * 
 */
Ext.define('JXP.system.store.Modules', {
    extend: 'Ext.data.Store',
    model: 'JXP.system.model.Module', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: 'module.jxp?action=list',
	    params: {
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