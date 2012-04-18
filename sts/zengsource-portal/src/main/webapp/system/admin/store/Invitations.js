/**
 * 
 */
Ext.define('JXP.system.store.Invitations', {
    extend: 'Ext.data.Store',
    model: 'JXP.system.model.Invitation', 
    //autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: 'invite.jxp?action=list',
	    params: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Invitation',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});