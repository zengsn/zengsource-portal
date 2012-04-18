// system/admin/view/admin/Tree.js
Ext.define('JXP.system.view.admin.TreePanel', {
    extend: 'Ext.tree.Panel',
    alias : 'widget.admintreepanel',
	id : 'admintreepanel',
	
	config : {},

	store : 'JXP.system.store.Nodes',
	
    constructor: function(config) {
		var me = this;
		config = config || {};
		this.config = Ext.apply(config, {	
	        title: '系统模块',
	        region:'north',
	        split: true,
	        height: 360,
	        minSize: 150,
	        rootVisible: false,
	        autoScroll: true,
	        store: me.store,
	        tools:[{
	            type:'refresh',
	            tooltip: '刷新菜单',
	            // hidden:true,
	            handler: function(event, toolEl, panel){
	                me.store.load();
	            }
	        }]
		});
        this.callParent(arguments);
        this.getSelectionModel().on('select', function(selModel, record) {
            if (record.get('leaf')) {
            	//JXP_console.dir(record.data);
            	var centerPanel = Ext.getCmp('admincenter-panel');
            	var layout = centerPanel.layout;
            	var cardId = '';
            	for(var i=0;i<layout.getLayoutItems().length;i++) {
            		if (layout.getLayoutItems()[i].id==record.data.id+'-panel') { cardId = layout.getLayoutItems()[i].id; }
            	}
            	if (!(cardId)) { // 组件未添加
            		//JXP_console.log(record.data.controller);
            		Ext.require(record.data.controller, function() {
            			cardId = record.data.id+'-panel';
            			//JXP_console.log(record.data.widget);
            			centerPanel.add({xtype: record.data.widget});
            			//JXP_console.log(record.data.id);
            			setTimeout(function() {
            				layout.setActiveItem(cardId);
            			}, 500);
                	}, me);
            	} else {
        			layout.setActiveItem(cardId);
            	}
                 if (!detailEl) {
                    var bd = Ext.getCmp('admindetail-panel').body;
                    bd.update('').setStyle('background','#fff');
                    detailEl = bd.createChild(); // create default empty div
                }
                 var panelDetails = Ext.getDom(record.getId() + '-details');
                 if (!panelDetails) {
                	 panelDetails = Ext.getDom('default-details');;
                 }
                 detailEl.hide().update(panelDetails.innerHTML).slideIn('l', {stopAnimation:true,duration: 200});
            }
        });
	}
});