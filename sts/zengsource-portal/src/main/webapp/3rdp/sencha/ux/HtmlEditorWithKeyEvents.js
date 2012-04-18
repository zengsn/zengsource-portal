Ext.define('Ext.ux.HtmlEditorWithKeyEvents', {
    extend: 'Ext.form.field.HtmlEditor',

    requires: [
        'Ext.form.field.HtmlEditor'
    ],
    alias: 'widget.htmleditorwithkeyevents',
	frame : true,
	initComponent : function() {
		Ext.ux.HtmlEditorWithKeyEvents.superclass.initComponent.call(this);
		this.addEvents('keypress');
	},
	initEditor : function() {
		Ext.ux.HtmlEditorWithKeyEvents.superclass.initEditor.call(this);
		if (Ext.isGecko) {
			Ext.EventManager.on(this.doc, 'keypress', this.fireKeypress, this);
		}
		if (Ext.isIE || Ext.isWebKit || Ext.isOpera) {
			Ext.EventManager.on(this.doc, 'keydown', this.fireKeypress, this);
		}
	},
	fireKeypress : function(e) {
		//if (e.ctrlKey && Ext.EventObject.ENTER == e.getKey()) {
			this.fireEvent('keypress', this);
		//}
	}
});