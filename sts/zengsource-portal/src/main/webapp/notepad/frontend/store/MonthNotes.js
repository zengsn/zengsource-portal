/**
 * 
 */
Ext.define('JXP.notepad.frontend.store.MonthNotes', {
    extend: 'Ext.data.Store',
    model: 'JXP.notepad.frontend.model.MonthNote', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: ROOT_PATH+'/notepad/view.jxp',
        //url: ROOT_PATH+'/task/tasks.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'month'
	    },
        reader: {
            type: 'xml',
            record: 'Note',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});