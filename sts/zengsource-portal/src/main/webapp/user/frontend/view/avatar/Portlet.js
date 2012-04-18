Ext.define('JXP.user.frontend.view.avatar.Portlet', {
    extend: 'JXP._core.view.Portlet',

    requires: [
        'Ext.form.field.Text'
    ],
    
    alias: 'widget.useravatarportlet',

    //id:'useravatarportlet',
    
    //store: 'Users',
    
    config: {
    },
    
    constructor: function(config) {
    	var me = this;
    	config = config || {};
    	this.config = Ext.apply(config, {
            title: '上传头像',
            items: [{
            	xtype: 'form',
            	border: false,
                bodyPadding : 15,
                autoScroll  : true,
                fieldDefaults : {
                    msgTarget  : 'side',
                    labelAlign : 'right',
                    labelWidth : 275
                },
                standardSubmit: false,
                defaults: {
                    anchor : '95%',
                    xtype  : 'textfield'
                }, 
                reader : Ext.create('Ext.data.reader.Xml', {
                    model: 'JXP.user.frontend.model.User',
                    record : 'User',
                    successProperty: '@success'
                }),
                errorReader: Ext.create('Ext.data.reader.Xml', {
                    model: 'XP.FieldError',
                    record : 'field',
                    successProperty: '@success'
                }),
                items: [{
                	xtype     : 'component',
                    fieldLabel: '头像',
                    html      : '<div id="avatar-preview" style="text-align:center;">'
                    	+'<img src="'+ROOT_PATH+'/user/resources/images/l_.png" width="300" height="300"/></div>'
                }, {
                	xtype     : 'filefield',
                    fieldLabel: '上传头像',
                    name      : 'avatar',
                    allowBlank: false,
                    style     : 'margin-top:15px',
                    emptyText : '选取头像文件',
                    anchor    : '70%',
                    buttonText: '',
                    buttonConfig: {
                        icon: ROOT_PATH+'/user/resources/images/photo_16.png'
                    }
                }]
            }],
            buttonAlign: 'center',
            buttons: [{
            	text: '保存',
            	scale: 'medium',
            	handler: function(btn) {
            		var form = btn.up('useravatarportlet');
            		form.submitForm();
            	}
            }]
    	});
        this.callParent(arguments);
        this.on('afterrender', function(panel) {
        	//panel.focusUsername();
        	panel.preview();
        });
    },
    
    preview : function(file) {
    	var me = this;
    	var form = me.down('form');
    	var preview = Ext.get('avatar-preview');
    	var uid = CURRENT_USER.id.replace('u-','');
    	var imgSrc = ROOT_PATH+CURRENT_USER.avatar;
    	if (file) {
    		imgSrc = ROOT_PATH + file;
    	}
    	var imgObj = new Image();
    	imgObj.src = imgSrc;
    	//JXP_console.log(imgObj);
    	preview.update('<img src="'+imgSrc+'" width="300" height="300"/>').fadeIn({
    		opacity: 1,
            duration: 500
        });;
    },
    
    submitForm : function() {
    	var me = this;
    	var form = this.down('form');
		form.submit({
		    clientValidation: true,
		    url: ROOT_PATH+'/user/avatar.jxp',
		    params: {
		        client: 'extajax'
		    },
		    success: function(form, action) {
		    	var file = action.result.file;
		    	//JXP_console.log(file);
		    	me.preview(file);
//		    	XP_Msg({
//            		corner : 'bl',
//            		manager: me,
//            		title  : '操作成功',
//            		html   : '<font color="green">用户信息已更新！</font>' 
//            	});
		    },
		    failure: function(form, action) {
		        switch (action.failureType) {
		            case Ext.form.action.Action.CLIENT_INVALID:
		            	XP_Msg({
		            		corner : 'bl',
		            		manager: me,
		            		title  : '错误',
		            		html   : '<font color="red">请检查并填写正确的信息！</font>' 
		            	});
		                break;
		            case Ext.form.action.Action.CONNECT_FAILURE:
		                Ext.Msg.alert('错误', '登录错误！');
		                break;
		            case Ext.form.action.Action.SERVER_INVALID:
		               //Ext.Msg.alert('Failure', action.result.msg);
		       }
		    }
		});
    }
}, function() {
	// after create ...
});