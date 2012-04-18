/**
 * 
 */
Ext.define('JXP.system.store.Configs', {
    extend: 'Ext.data.Store',
    model: 'JXP.system.model.Config', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: 'config.jxp?action=list',
	    params: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Config',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});