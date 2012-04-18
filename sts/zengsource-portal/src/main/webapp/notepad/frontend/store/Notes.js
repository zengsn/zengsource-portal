/**
 * 
 */
Ext.define('JXP.notepad.frontend.store.Notes', {
    extend: 'Ext.data.Store',
    model: 'JXP.notepad.frontend.model.Note', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: ROOT_PATH+'/notepad/edit.jxp',
        //url: ROOT_PATH+'/task/tasks.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'recentList'
	    },
        reader: {
            type: 'xml',
            record: 'Note',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});