/**
 * 
 */
Ext.define('JXP.social.frontend.store.FeatureUsers', {
    extend: 'Ext.data.Store',
    model: 'JXP.social.frontend.model.User', 
    autoLoad: true,
    proxy: {
        // load using HTTP
        type: 'ajax',
        url: ROOT_PATH+'/social/find.jxp',//?action=list',
        //url: ROOT_PATH+'/social/friends.xml',
        extraParams: {
	        client: 'extajax',
	        action: 'getFeatures'
	    },
        reader: {
            type: 'xml',
            record: 'User',
            idProperty: 'id',
            totalRecords: 'totalCount'
        }
    }
});