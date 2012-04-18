/**
 * 
 */
Ext.define('JXP.social.frontend.store.Friends', {
    extend: 'Ext.data.Store',
    model: 'JXP.social.frontend.model.Friend', 
    autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: ROOT_PATH+'/social/friend.jxp',//?action=list',
        //url: ROOT_PATH+'/social/friends.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'list'
	    },
        reader: {
            type: 'xml',
            record: 'Friend',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});