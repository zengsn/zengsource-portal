/**
 * 
 */
Ext.define('JXP.task.frontend.store.Notes', {
    extend: 'Ext.data.Store',
    model: 'JXP.task.frontend.model.Note', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: ROOT_PATH+'/task/execute.jxp',
        //url: ROOT_PATH+'/task/tasks.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Note',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});