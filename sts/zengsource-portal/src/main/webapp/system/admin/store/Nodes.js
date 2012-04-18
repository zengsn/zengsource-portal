/**
 * 
 */
Ext.define('JXP.system.store.Nodes', {
    extend: 'Ext.data.TreeStore',
    uses : 'JXP.system.model.MenuNode',
    model: 'JXP.system.model.MenuNode',
    autoLoad: true,
    root: {
        expanded: true
    },
    proxy: {
        type: 'ajax',
        //url: 'tree-data.json',
        url: ROOT_PATH+'/system/index.jxp?action=listNodes'
    }
});