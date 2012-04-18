/**
 * 
 */
Ext.define('JXP.task.frontend.store.Tasks', {
    extend: 'Ext.data.Store',
    model: 'JXP.task.frontend.model.Task', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: ROOT_PATH+'/task/my.jxp',
        //url: ROOT_PATH+'/task/tasks.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Task',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});