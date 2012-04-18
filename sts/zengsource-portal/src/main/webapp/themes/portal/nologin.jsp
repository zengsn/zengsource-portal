<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LoBoSi.com (1.0Beta)</title>
	<link rel="shortcut icon" href="${siteUrl}/resources/images/lobosi.ico">

	<link rel="stylesheet" type="text/css" href="${extUrl}/resources/css/ext-all-gray.css">
    <link rel="stylesheet" type="text/css" href="${extUrl}/../ux/notification.css" />
    <link rel="stylesheet" type="text/css" href="${extUrl}/examples/ux/css/BoxSelect.css" />
    <link rel="stylesheet" type="text/css" href="${siteUrl}/resources/css/ext-overrides.css" />
    <link rel="stylesheet" type="text/css" href="${siteUrl}/themes/portal/portal.css" />
	
	<script type="text/javascript" src="${extUrl}/ext-debug.js"></script>
	<script type="text/javascript">
		var ROOT_PATH = '${siteUrl}'; 
		var CURRENT_USER = undefined;
		var _PAGE = {columns:1};
		var _portlets = new Array();
		_portlets.push([{xtype:'signinportlet',width:1000,height:270,colspan:1,rowspan:1}]);
		Ext.Loader.setConfig({
			enabled : true,
			disableCaching : true
		});
	    Ext.Loader.setPath({
	    	'Ext.ux': '${extUrl}/examples/ux',
	    	'Ext.ux.layout': '${extUrl}/examples/ux/layout',
	    	'Ext.ux.form': '${extUrl}/examples/ux/form',
	    	'JXP._core' : '${siteUrl}/themes/portal',
	    	'JXP.system.frontend':'http://localhost:8080/zengsource-portal/system/frontend',
	    	'Ext.ux.window.Notification' : '${extUrl}/../ux/Notification.js'
	    });
	</script>
    <script type="text/javascript" src="${siteUrl}/themes/portal/resources/Utils.js"></script>
    <script type="text/javascript" src="${siteUrl}/themes/portal/resources/FieldError.js"></script>	
	<script type="text/javascript" src="${siteUrl}/themes/portal/nologin.js"></script>
</head>
<body>
	<div id="ct-portal" style="height:500px;width:1020px;"></div>
	<div id="ct-left" style="display:none"></div>
	<div id="ct-right" style="display:none"></div>
    <span id="app-msg" style="display:none;"></span>
   	<div id="portlets" style="display:none">${siteUrl}${portlets}</div>
   	<div id="portlets1" style="display:none">${siteUrl}/themes/portal/porlets.json</div>
   	<div style="display:none">
   		<div id="app-logo"><p style="text-align:left;"><font color="gray" style="font-size:.8em;">(1.0Beta)</font><br /><b>LoBoSi</b>.com </p></div>
   		<div id="app-footer">
   			Copyright &copy; 2012 | <a href="http://www.lobosi.com">LoBoSi.com</a> |
   			<a target="_blank" href="http://qun.qq.com/#jointhegroup/gid/149099033">Q群：149099033</a>| 
   			<a href="http://github.com/zengsn">ZengSource Portal</a> 
   			<script type="text/javascript">(function() {var c = document.createElement('script'); c.type = 'text/javascript'; c.async = true;c.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'www.clicki.cn/boot/45046';var h = document.getElementsByTagName('head')[0]; h.appendChild(c);})();</script>
   		</div>
   	</div>
</body>
</html>