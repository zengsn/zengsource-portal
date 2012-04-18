Ext.define('JXP.task.frontend.view.create.file.Size', {
    extend: 'Ext.form.field.Spinner',
    alias: 'widget.taskfilesizespinner',

    // override onSpinUp (using step isn't neccessary)
    onSpinUp: function() {
        var me = this;
        if (!me.readOnly) {
            var val = me.step; // set the default value to the step value
            if(me.getValue() !== '') {
                val = parseInt(me.getValue().slice(0, -3)); // gets rid of " Pack"
            }
            me.setValue((val + me.step) + ' MB');
        }
    },

    // override onSpinDown
    onSpinDown: function() {
        var val, me = this;
        if (!me.readOnly) {
            if(me.getValue() !== '') {
                val = parseInt(me.getValue().slice(0, -3)); // gets rid of " Pack"
            }
            me.setValue((val - me.step) + ' MB');
        }
    }
});