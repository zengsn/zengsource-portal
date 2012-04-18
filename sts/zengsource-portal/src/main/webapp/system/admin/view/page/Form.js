// Panel.js
Ext.define('JXP.system.view.page.Form', {
    extend: 'Ext.form.Panel',
    alias : 'widget.systempageform',
    
    //id: 'systemuser-form',
    
    constructor: function(config) {
		config = config || {};
		this.config = Ext.apply(config, {
	        title: '编辑页面',
            bodyPadding : 5,
            fieldDefaults : {
                msgTarget  : 'side',
                labelAlign : 'right',
                labelWidth : 75
            },
            standardSubmit: false,
            defaults: {
                anchor : '95%',
                xtype  : 'textfield'
            }, 
	        reader : new Ext.data.XmlReader({
	            record : 'Page',
	            success: '@success'
	        }, [
	            'id', 'username', 'enabled', 'email'
	        ]),
            errorReader: Ext.create('Ext.data.reader.Xml', {
                model: 'XP.FieldError',
                record : 'field',
                successProperty: '@success'
            }),
	        waitMsgTarget: true,
	        buttonAlign: 'center',
	        
	        listeners: {
	        },
		    items: [{
		        name:'id',
		        xtype: 'hidden'
		    },{
		        name:'name',
		        fieldLabel:'页面名称'
		    },new Ext.ux.form.Spinner({
				fieldLabel: '分栏列数',
				name: 'columns',
				strategy: new Ext.ux.form.Spinner.NumberStrategy({
					minValue:'1'//, maxValue:'130'
				})
		  	}),{
		        name:'cls',
		        fieldLabel:'CSS类'
		    },{
		    	xtype: 'textarea',
		    	height: 48,
		        name:'style',
		        fieldLabel:'自定义'
		    },{
		    	xtype: 'textarea',
		    	height: 48,
		        name:'description',
		        fieldLabel:'备注'
		    }],
		
		    buttons: [{
		        text: '保存'
		    },{
		        text: '重置'
		    },{
		        text: '取消'
		    }]
		});
        this.callParent(arguments);
	}
});