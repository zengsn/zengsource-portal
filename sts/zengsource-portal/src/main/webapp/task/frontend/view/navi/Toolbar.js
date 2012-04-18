/**
 * @class Ext.app.HeaderBar
 * @extends Ext.toolbar.Toolbar
 * @see {@link Ext.toolbar.Toolbar Toolbar} 
 */
Ext.define('JXP.task.frontend.view.navi.Toolbar', {
    extend: 'JXP._core.view.NaviBar',
    alias: 'widget.navitoolbar',
    
    requires: ['JXP._core.view.LinkSplitButton'],
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            border: false,
            style: 'background: #fff;padding:3px;border:0px;',
            items: [{
                xtype: 'buttongroup',
                columns: 1,
                items: [{
                	xtype: 'linksplitbutton',
                    text: '任务状态(2)',
                    scale: 'medium',
                    rowspan: 2, 
                    tooltip: '点击查看与我相关的任务',
                    icon: ROOT_PATH+'/task/resources/images/status_24.png',
                    iconAlign: 'top',
                    arrowAlign:'right',
                    //cls: 'x-btn-as-arrow',
                    target: '_self',
                    href  : ROOT_PATH+'/task/index.jxp',
                    menu: [{
                    	xtype: 'linkitem',
                    	text: '紧急任务',
                        tooltip: '点击查看即将到期的任务',
                        target: '_self',
                        href  : ROOT_PATH+'/task/periority.jxp'
                    }]
                }]
            }, {
                xtype: 'buttongroup',
                columns: 2,
                //title: 'Clipboard',
                items: [{
                	xtype: 'linksplitbutton',
                    text: '我的任务(2)',
                    scale: 'medium',
                    rowspan: 2, 
                    tooltip: '点击查看我发起的任务',
                    icon: ROOT_PATH+'/task/resources/images/task_24.png',
                    iconAlign: 'top',
                    arrowAlign:'right',
                    //cls: 'x-btn-as-arrow',
                    target: '_self',
                    href  : ROOT_PATH+'/task/my.jxp',
                    menu: [{
                    	xtype: 'linkitem',
                    	text: '历史任务',
                        tooltip: '点击查看已完成的历史任务',
                        target: '_self',
                        href  : ROOT_PATH+'/task/my.jxp?action=getDone'
                    }]
                }, {
                	xtype: 'linkbutton',
                    text: '草稿箱(4)', //iconCls: 'add16',
                    tooltip: '点击查看编辑中的任务草稿',
                    target: '_self',
                    href  : ROOT_PATH+'/task/my.jxp?action=getDraft'
                }, {
                	xtype: 'linkbutton',
                    text: '进行中(5)', //iconCls: 'add16'
                    tooltip: '点击查看正在进行中的任务',
                    target: '_self',
                    href  : ROOT_PATH+'/task/my.jxp?action=getDoing'
                }]
            }, {
                xtype: 'buttongroup',
                columns: 3,
                //title: 'Clipboard',
                items: [{
                    xtype:'linksplitbutton',
                    text: '发起任务',
                    scale: 'medium',
                    rowspan: 2,
                    tooltip: '点击创建一个新任务',
                    icon: ROOT_PATH+'/task/resources/images/add_24.png',
                    iconAlign: 'top',
                    arrowAlign:'right',
                    target: '_self',
                    href  : ROOT_PATH+'/task/create.jxp',
                    menu: [{
                    	text: '其他任务'
                    }]
                }, {
                    xtype:'linkbutton',
                    text: '收文件(92.0MB)', //iconCls: 'add16'
                    tooltip: '特殊任务：向一个或多个好友收取文件',
                    target: '_self',
                    href  : ROOT_PATH+'/task/create.jxp?feature=file'
                }, {
                    xtype:'linkbutton',
                    text: '提问题(20/5)', //iconCls: 'add16'
                    tooltip: '特殊任务：向某人请教一个问题',
                    target: '_self',
                    href  : ROOT_PATH+'/task/askquestion.jxp'
                }/*, {
                	text: '邀请'
                }*/]
            }]
    	});
        this.callParent(arguments);
    }
});
