Ext.define('JXP.apps.frontend.view.dashboard.Info', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.appsdashboardinfo',
    //id: 'img-detail-panel',
    
	//bodyStyle: 'border-left:1px #DCDCDC solid;',
    tpl: [
        '<div class="details">',
            '<tpl for=".">',
            (!Ext.isIE6? '<img src="'+ROOT_PATH+'/{name}/resources/images/{name}_logo.png" title="{desc}"/>' : 
                    '<div style="width:74px;height:74px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\''+ROOT_PATH+'{origAvatar}\')"></div>'),
                '<div class="details-info">',
                    '<b>昵称：</b>',
                    '<span>{nickname}</span>',
                    '<b>简介：</b>',
                    '<span>{introduction}</span>',
                    '<b>最新更新：</b>',
                    '<span>{updates}</span>',
                    '<b>好友留言：</b>',
                    '<span>{messageA}</span>',
                '</div>',
            '</tpl>',
        '</div>'
    ],
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
    		html: '<p style="margin:30px;text-align:center;color:gray;">点击查看应用信息</p>',
    		buttonAlign: 'center',
			buttons: [{
				//scale: 'medium',
				text: '安装/卸载',
				disabled: true,
				handler: me.handleRequest
			}, {
				//scale: 'medium',
				text: '评论',
				disabled: true,
				handler: me.handleRequest
			}, {
				//scale: 'medium',
				text: '投诉',
				disabled: true,
				handler: me.handleRequest
			}]
    	});
        this.callParent(arguments);
    },

    /**
     * Loads a given image record into the panel. Animates the newly-updated panel in from the left over 250ms.
     */
    loadRecord: function(image) {
        this.body.hide();
        image.data.origAvatar = image.data.avatar.replace('m_', 'l_');
        this.tpl.overwrite(this.body, image.data);
        this.body.slideIn('l', {
            duration: 250
        });
        var bbar = this.getDockedItems()[0];
        bbar.items.each(function(item, index, len) {
        	item.enable();
        	item.bindRec = image;
        }, this);
    },
    
    clearRecord : function() {
        this.body.hide();
        this.body.update('<p style="margin:30px;text-align:center;color:gray;">点击查看应用信息</p>');
        this.body.slideIn('l', {
            duration: 250
        });
        var bbar = this.getDockedItems()[0];
        bbar.items.each(function(item, index, len) {
        	item.disable();
        	item.bindRec = {};
        }, this);
    },
    
    handleRequest : function(btn, e) {
    	var me = this;
    	var panel = btn.up('socialrequestportlet').down('socialrequestinfo');
    	var view = btn.up('socialrequestportlet').down('socialrequestview');
    	var action = 'accept';
    	if ('忽略'==btn.text) action = 'ignore';
    	if ('拒绝'==btn.text) action = 'reject';
    	var rec = btn.bindRec;
		Ext.MessageBox.show({
			title: '备注',
			msg: '写给对方的话：',
			width:300,
			buttons: Ext.MessageBox.OKCANCEL,
			multiline: true,
			animateTarget: btn,
			fn: function(btn, text) {
				if (btn != 'ok') return;
		    	Ext.Ajax.request({
		  		   url: ROOT_PATH+'/social/request.jxp',
		  		   params : {action: action, friend: rec.data.id, message: text},
		  		   success: function(response, opts) {
		  			   var obj = Ext.decode(response.responseText);
		  			   var msg = obj.result.msg;
		  			   XP_Msg({
	 	            		corner : 'bl',
	 	            		manager: view,
	 	            		title  : '操作结果',
	 	            		html   : msg 
	 	            	});
		  			   me.clearRecord();
		  			   view.getStore().load({start:0,limit:25});
		  		   },
		  		   failure: function(response, opts) {
		  			 JXP_console.log('server-side failure with status code ' + response.status);
		  		   }
		  	   });
			}
		});
    }
});