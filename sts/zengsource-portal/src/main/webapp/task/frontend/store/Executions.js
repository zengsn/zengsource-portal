/**
 * 
 */
Ext.define('JXP.task.frontend.store.Executions', {
    extend: 'Ext.data.Store',
    model: 'JXP.task.frontend.model.Execution', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: ROOT_PATH+'/task/index.jxp',
        //url: ROOT_PATH+'/task/tasks.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Execution',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});