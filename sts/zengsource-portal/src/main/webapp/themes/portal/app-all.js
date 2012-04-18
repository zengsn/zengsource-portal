/*
Copyright(c) 2011 Company Name
*/
Ext.application({name:"JXP",appFolder:ROOT_PATH+"/themes/portal",controllers:["JXP._core.controller.NoLogin","JXP.system.frontend.controller.SignIn"],launch:function(){var a=Ext.create("JXP._core.view.Panel");a.render("ct-portal");a.on("afterlayout",function(){})}});
