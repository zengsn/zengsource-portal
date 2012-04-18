// view/user/Grid.js
Ext.define('JXP.notepad.frontend.view.Month', {
	extend : 'Ext.grid.Panel',

	requires : [ 'Ext.grid.*', 'Ext.data.*', 'JXP.notepad.frontend.store.MonthNotes'],

	alias : 'widget.notepadmonthgrid',

	//id : 'homestatusgrid',
	config : {},

	//store : 'JXP.social.frontend.store.Friends',
	constructor : function(config) {
		var me = this;
		config = config || {};
		me.dateInMonth = new Date();
		this.store = Ext.create('JXP.notepad.frontend.store.MonthNotes');
		var cellRender = function(val, meta, rec, row, col, store, view) {
			var b = 1;
			//JXP_console.log(me.dateInMonth.getDate());
			//if(val.indexOf(me.dateInMonth.getDate())>-1) { b = 5; }
			if(val==me.dateInMonth.getDate()) { b = 5; }
			var pos = val.indexOf('[');
			if(pos>-1) {val = val.substring(0, pos) + '<br />*';}
			return '<div style="height:50px;border:'+b+'px #dcdcdc solid;font-weight:bold;color:#464646;padding-top:10px;">'
				+val+'</div>';
		};
		this.noteForm = config.noteForm;
		this.config = Ext.apply(config, {
			//autoScroll : true,
			//hideHeaders: true,
			overCls  : 'pointer',
			columns  : [ {
				text : "日",
				width: 60,
				align: 'center',
				dataIndex : 'sun',
				renderer: cellRender
			}, {
				text : "一",
				width: 60,
				align: 'center',
				dataIndex : 'mon',
				renderer: cellRender
			}, {
				text : "二",
				width: 60,
				align: 'center',
				dataIndex : 'tue',
				renderer: cellRender
			}, {
				text : "三",
				width: 60,
				align: 'center',
				dataIndex : 'wed',
				renderer: cellRender
			}, {
				text : "四",
				width: 60,
				align: 'center',
				dataIndex : 'thr',
				renderer: cellRender
			}, {
				text : "五",
				width: 60,
				align: 'center',
				dataIndex : 'fri',
				renderer: cellRender
			}, {
				text : "六",
				width: 60,
				align: 'center',
				dataIndex : 'sat',
				renderer: cellRender
			}],
			tbar : [{
				xtype: 'datefield',
				value: new Date(),
				format: 'Y-m-d',
				//disabledDates: ["02/15/2012"],
				hideLabel: true,
				listeners: {
					'change': function(field, newValue, oldValue, opts) {
						me.changeMonth(newValue);
					}
				}
			}]
		});
		this.callParent(arguments);
        this.on('afterrender', function(grid) {
        	//JXP_console.dir(grid.getStore());
        	//grid.getStore().on('beforeload', function(store, oper, opt) {
        	//});
        	//grid.getStore().on('load', function(store, oper, opt) {
        	//});
        	grid.getStore().load({start:0,limit:25});
        });
        this.on('itemclick',function(view, rec, item, index, e, opts) {
        	//JXP_console.log(e.getTarget().innerHTML);
        	me.showContextMenu(view, rec, item, index, e, opts);
        });
        this.on('itemdblclick',function(view, rec, item, index, e, opts) {
        	//me.showContextMenu(view, rec, item, index, e, opts);
        });
		this.on('itemcontextmenu', function(view, rec, item, index, e, opts) {
        	me.showContextMenu(view, rec, item, index, e, opts);
		});
	},
	
	changeMonth : function(newDate) {
		var me = this;
		me.dateInMonth = newDate;
		me.store.proxy.extraParams.date = newDate;//Ext.Date,format(newDate, 'Y-M-d');
		me.store.load();
	},
	
	showContextMenu : function(view, rec, item, index, e, opts) {
		var me = this;
    	var markup = e.getTarget().innerHTML;
    	if (markup.indexOf('*')>-1) {
        	//JXP_console.log('has note ... ');
        	var pos = markup.indexOf('<');
        	var dateNum = markup.substring(0, pos);
        	var notesInDate = '';
        	if (rec.data.sun.indexOf(dateNum)==0) {
        		notesInDate = rec.data.sun;
        	} else if (rec.data.mon.indexOf(dateNum)==0) {
        		notesInDate = rec.data.mon;
        	} else if (rec.data.tue.indexOf(dateNum)==0) {
        		notesInDate = rec.data.tue;
        	} else if (rec.data.wed.indexOf(dateNum)==0) {
        		notesInDate = rec.data.wed;
        	} else if (rec.data.thr.indexOf(dateNum)==0) {
        		notesInDate = rec.data.thr;
        	} else if (rec.data.fri.indexOf(dateNum)==0) {
        		notesInDate = rec.data.fri;
        	} else if (rec.data.sat.indexOf(dateNum)==0) {
        		notesInDate = rec.data.sat;
        	} 
        	pos = notesInDate.indexOf('[');
        	notesInDate = notesInDate.substring(pos);
        	var noteArr = Ext.decode(notesInDate);
        	if (me.contextMenu) { // 清除菜单项
        		me.contextMenu.removeAll();
        	} else { // 创建菜单对象
    			me.contextMenu = Ext.create('Ext.menu.Menu', {
    			    floating: true,  
    			    renderTo: Ext.getBody(),  
    			    items: []
    			});
        	}
			if (noteArr.length == 1) { 
				me.noteForm.loadNote(noteArr[0].id);
			} else {// 当天有多个记事
    			Ext.Array.each(noteArr, function(item, index, arr) {
    				me.contextMenu.add({
    					text: item.title,
    					icon: ROOT_PATH+'/notepad/resources/images/pencil_16.png',
    					href: ROOT_PATH+'/notepad/edit.jxp?id='+item.id,
    					target: '_self',
    					handler: function(btn, e, opts) {
    						e.preventDefault();
    						me.noteForm.loadNote(item.id);
    					}
    				});
    			});
			}
    		if (me.contextMenu.isVisible()) {
    			me.contextMenu.hide();
    		}
    		me.contextMenu.setPagePosition(e.getXY());
    		me.contextMenu.show();
    		e.stopEvent();
    	}
	}
}, function() {
	// after create ...
});