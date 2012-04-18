/**
 * 
 */
Ext.define('JXP.system.store.Portlets', {
    extend: 'Ext.data.Store',
    model: 'JXP.system.model.Portlet', 
    //autoLoad: true,
    groupField: 'module',
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: 'portlet.jxp?action=list',
	    params: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Porlet',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});