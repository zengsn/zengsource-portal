/**
 * 
 */
Ext.define('JXP.system.store.Pages', {
    extend: 'Ext.data.Store',
    model: 'JXP.system.model.Page', 
    //autoLoad: true,
    groupField: 'module',
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: 'page.jxp?action=list',
	    params: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Page',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});