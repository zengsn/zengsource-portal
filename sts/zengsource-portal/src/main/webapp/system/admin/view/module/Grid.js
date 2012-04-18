// view/module/Grid.js
Ext.define('JXP.system.view.module.Grid', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
			'Ext.selection.CheckboxModel' ],

	alias : 'widget.systemmodulegrid',

	//id : 'systemmodule-grid',
	config : {},

	store : 'JXP.system.store.Modules',
	constructor : function(config) {
		var me = this;
		config = config || {};
		var moduleIdRender = function(val, meta, rec, row, col, store, view) {
			return '<img src="'+ROOT_PATH+'/'+rec.data.name+'/resources/images/'+rec.data.name+'_logo.png" />'
				+ '<p>'+rec.data.title+'</p>';
		};
		var moduleStatusRender = function(val) {
			if(val==0) return '<font color="Gray">未初始化</font>';
			if(val==1) return '<font color="Green">已初始化</font>';
			if(val==2) return '<font color="Blue">已激活</font>';
			if(val==3) return '<font color="Black">已关闭</font>';
			return val;
		};
		var sm = Ext.create('Ext.selection.CheckboxModel');
		this.config = Ext.apply(config, {
			selModel: sm,
			loadMask: true,
			clicksToEdit:2,
			title : '模块列表',
			autoScroll: true,
			//iconCls : 'icon-grid',
			columns : [ //new Ext.grid.RowNumberer(),
			{
				header: "ID",
				dataIndex: 'id',
				width: 64,
				sortable: true,
				renderer: moduleIdRender
			}, {
				header: "模块名",
				dataIndex: 'name',
				width: 128,
				sortable: true,
				editor: new Ext.form.TextField({
				   allowBlank: false
				})
			}, {
				header: "状态",
				dataIndex: 'status',
				align: 'center',
				width: 64,
				sortable: true,
				renderer: moduleStatusRender
			}, {
				header: "顺序",
				dataIndex: 'index',
				align: 'center',
				sortable: true,
				width: 64
			}, {
				header: "配置文件",
				dataIndex: 'configFile',
				sortable: true,
				width: 128
			}, {
				header: "创建时间",
				dataIndex: 'createdTime',
				sortable: true,
				width: 128
			}, {
				header: "最后修改",
				dataIndex: 'updatedTime',
				sortable: true,
				flex: 1
			}],/*
			plugins : [ {
				ptype : 'rowexpander',
				rowBodyTpl : [ '<p style="padding-left:50px;"><b>介绍:</b> {introduction}</p>']
			} ],*/
			bbar : Ext.create('Ext.PagingToolbar', {
				store : me.store,
				displayInfo : true,
			    items:['-', {
			    	text: '修改',
			    	//iconCls: 'btn-modify',
			    	scale  : 'medium',
			    	handler: function(btn) {
			    		var grid = btn.up('systemmodulegrid');
			    		var count = grid.getSelectionModel().getCount();
			    		if(count==0) {
			    			alert('Please select one record!');
			    		}else if(count>1) {
			    			alert('Select too many records!');
			    		}else {
			    			var records = grid.getSelectionModel().getSelection();
				    		//editProduct(record.data.id);
				    		alert('Not yet!');
				    	}
			    	}
			    }, {
			    	text: '激活',
			    	//iconCls: 'btn-on',
			    	scale  : 'medium',
			    	handler: function() {
			    		alert('To be continued ...');
			    	}
			    }, '-', {
			    	text: '关闭',
			    	//iconCls: 'btn-off',
			    	scale  : 'medium',
			    	handler: function() {
			    		alert('To be continued ...');
			    	}
			    }, '-', {
			    	text: '重新加载',
			    	//iconCls: 'btn-refresh',
			    	scale  : 'medium',
			    	menu: [{
				        text: '刷新信息',
				        handler: function(btn) {
				    		//var grid = btn.up('systemmodulegrid');
				    		var records = sm.getSelection();
				    		var count = sm.getCount();
				    		if(count==0) {
				    			alert('Please select one record at least!');
				    		}else {
				    			me.reload(records);
					    	}
				        }
				    }, {
				        text: '刷新区块',
				        handler: function(btn) {
				        	me.reload([rec], 'portlet');
				        }
				    }, {
				        text: '刷新页面',
				        handler: function(btn) {
				        	me.reload([rec], 'page');
				        }
				    }, {
				        text: '刷新菜单',
				        handler: function(btn) {
				        	me.reload([rec], 'menu');
				        }
				    }]
			    }]
			})
		});
		this.callParent(arguments);
		this.on('itemcontextmenu', function(view, rec, item, index, e, opts) {
			var count = sm.getCount();
			if (count == 0) {
				//sm.select(rec);
			}
			var records = sm.getSelection();
			if (!(me.contextMenu)) {
				me.contextMenu = Ext.create('Ext.menu.Menu', {
				    //width: 100,
				    floating: true,  
				    renderTo: Ext.getBody(),  
				    items: [{
				        text: '激活',
				        handler: function(btn) {
				        	me.setStatus(sm.getSelection(), 2);
				        }
				    }, {
				        text: '关闭',
				        handler: function(btn) {
				        	me.reload(sm.getSelection(), 'menu');
				        }
				    }, {
				        text: '修改'
				    }, '-', {
				        text: '刷新信息',
				        handler: function(btn) {
				        	me.reload(sm.getSelection());
				        }
				    }, {
				        text: '刷新区块',
				        handler: function(btn) {
				        	me.reload(sm.getSelection(), 'portlet');
				        }
				    }, {
				        text: '刷新页面',
				        handler: function(btn) {
				        	me.reload(sm.getSelection(), 'page');
				        }
				    }, {
				        text: '刷新菜单',
				        handler: function(btn) {
				        	me.reload(sm.getSelection(), 'menu');
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
		});
	},
	
	reload : function(records, option) {
		var me = this;
		var count = records.length;
		var moduleIdArr = new Array(count);
		for(var i=0;i<count;i++) {
			moduleIdArr[i]=records[i].data.id;
		}
		var moduleId = moduleIdArr.toString();
		Ext.MessageBox.confirm('操作确认', '确定重新读取模块配置文件？', function(btn) {
			if('yes'==btn) {
    			Ext.Ajax.request({
						url: 'module.jxp',
						headers: {},
						params: {
							action: 'reload',
							option: option,
							moduleId: moduleId
						},
						success: function(response, opts) {
					        //var obj = Ext.decode(response.responseText);
					        //JXP_console.dir(obj);
							XP_Msg({
			            		corner : 'br',
			            		manager: me,
			            		title  : '操作成功',
			            		html   : '<font color="green">模块配置已刷新！</font>' 
			            	});
							me.getStore().load({start:0, limit:25});
						},
					    failure: function(response, opts) {
					    	JXP_console.log('server-side failure with status code ' + response.status);
					    }
					});
			}
		});
	},
	
	setStatus : function(records, status) {
		var me = this;
		var count = records.length;
		var moduleIdArr = new Array(count);
		for(var i=0;i<count;i++) {
			moduleIdArr[i]=records[i].data.id;
		}
		var moduleId = moduleIdArr.toString();
		var confirmMsg = (2 == status) ? '确定激活此模块？' : '确定停用此模块？';
		Ext.MessageBox.confirm('操作确认', confirmMsg, function(btn) {
			if('yes'==btn) {
    			Ext.Ajax.request({
						url: 'module.jxp',
						headers: {},
						params: {
							action: 'status',
							status: status,
							moduleId: moduleId
						},
						success: function(response, opts) {
					        var obj = Ext.decode(response.responseText);
					        //JXP_console.dir(obj);
							XP_Msg({
			            		corner : 'br',
			            		manager: me,
			            		title  : '操作成功',
			            		html   : obj.result.msg
			            	});
							me.getStore().load({start:0, limit:25});
						},
					    failure: function(response, opts) {
					    	JXP_console.log('server-side failure with status code ' + response.status);
					    }
					});
			}
		});
	}
}, function() {
	// after create ...
});