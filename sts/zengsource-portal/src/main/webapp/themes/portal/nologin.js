/**
 * 
 */
Ext.application({
	name : 'JXP',

	appFolder : ROOT_PATH + '/themes/portal',
	controllers : ['JXP._core.controller.NoLogin', 'JXP.system.frontend.controller.SignIn'],

	launch : function() {
		//JXP_console.log('App launch ... ');
		var portal = Ext.create('JXP._core.view.Panel');
//		var portal = Ext.create('Ext.panel.Panel', {
//			html: 'ok'
//		});
		portal.render('ct-portal');
//		JXP_console.log(Ext.get('ct-portal'));
		portal.on('afterlayout', function() {
		});
	}
});