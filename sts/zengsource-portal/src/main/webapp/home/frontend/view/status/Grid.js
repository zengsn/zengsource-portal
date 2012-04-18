// view/user/Grid.js
Ext.define('JXP.home.frontend.view.status.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander'],

	alias : 'widget.homestatusgrid',

	//id : 'homestatusgrid',
	config : {},

	//store : 'JXP.social.frontend.store.Friends',
	constructor : function(config) {
		var me = this;
		this.store = Ext.create('JXP.home.frontend.store.Updates');
		config = config || {};
		function wrap(str) {
			var str2 = '';
			for(var i=0;i<str.length;i+=120) {
				str2 += str.substring(i, i+119)+' <br />';
			}
			return str2;
		}
		var idRender = function(val, meta, rec, row, col, store, view) {
			var src = ''+rec.data.ownerAvatar=='null'?'/user/resources/images/l_.png':"/"+rec.data.ownerAvatar;
			return '<img src="'+ROOT_PATH+src+'" width="48" height="48" style="border:1px #DCDCDC solid;padding:3px;"/>';
		};
		var textRender = function(val, meta, rec, row, col, store, view) {
			return '<span style="">'+val
			+'</span><br /><p><i>'+rec.data.createdTime+'</i></p>';
		};
		var pictureRender = function(val, meta, rec, row, col, store, view) {
			if (!val || val=='null') return '无图';//'<p><img src="'+ROOT_PATH+'/resources/images/none_48.png" width="48" height="48" /></p>';
			return '<p><img src="'+ROOT_PATH+'/upload/social/'+rec.data.ownerId+'/'+rec.data.picture + '" width="48" height="48" /></p>';
		};
		this.config = Ext.apply(config, {
			autoScroll: true,
			hideHeaders: true,
			columns : [ {
				text : "ID",
				width : 60,
				dataIndex : 'id',
				renderer: idRender
			}, {
				text : "text",
				flex : 1,
				dataIndex : 'text',
				renderer: textRender
			}, {
				text : "picture",
				width : 60,
				dataIndex : 'picture',
				renderer: pictureRender
			}],/*
			plugins: [{
	            ptype: 'rowexpander',
	            rowBodyTpl : [
	                '<p>{text}</p>',
	                '<img src="'+ROOT_PATH+'/upload/social/{ownerId}/{picture}" />'
	            ]
	        }],*/
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
				items : [ ]
			}),
			tbar : ['显示更多更新：', {
				xtype: 'checkbox',
				//labelAlign: 'left',
				//labelWidth: 60,
				//fieldLabel: '新浪微博',
				name: 'weibo'
			}, {
				//text: '新浪微博',
				icon: ROOT_PATH+'/resources/images/weibo_16.png'
			}, ' ', {
				xtype: 'checkbox',
				name: 'tencent'
			}, {
				//text: '腾迅微博',
				icon: ROOT_PATH+'/resources/images/qq_16.png'
			}]
		});
		this.callParent(arguments);
        this.on('afterrender', function(grid) {
        	//JXP_console.dir(grid.getStore());
        	//grid.getStore().on('beforeload', function(store, oper, opt) {
        	//});
        	grid.getStore().on('load', function(store, oper, opt) {
        	});
        	grid.getStore().load({start:0,limit:25});
        });
        this.on('itemdblclick',function(view, rec, item, index, e, opts) {
        	//me.showContextMenu(view, rec, item, index, e, opts);
        });
		this.on('itemcontextmenu', function(view, rec, item, index, e, opts) {
        	me.showContextMenu(view, rec, item, index, e, opts);
		});
	},
	
	showContextMenu : function(view, rec, item, index, e, opts) {
		var me = this;
		me.getSelectionModel().select(rec);
		var records = me.getSelectionModel().getSelection();
		if (!(me.contextMenu)) {
			me.contextMenu = Ext.create('Ext.menu.Menu', {
			    width: 100,
			    floating: true,  
			    renderTo: Ext.getBody(),  
			    items: [{
			        text: '加为好友',
			        handler: function(btn) {
			        	me.addFriend(me.getSelectionModel().getSelection());
			        }
			    }]
			});
		}
		if (me.contextMenu.isVisible()) {
			me.contextMenu.hide();
		}
		me.contextMenu.setPagePosition(e.getXY());
		me.contextMenu.show();
		e.stopEvent();
	}
}, function() {
	// after create ...
});