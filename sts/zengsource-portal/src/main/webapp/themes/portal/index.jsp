<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="org.zengsource.portal.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	User user = (User)request.getSession(true).getAttribute("user");
	int uid = (user==null ? 0 : user.getId());
	String username = (user==null ? "" : user.getUsername());
	String nickname = (user==null || user.getNickname()==null ? username : user.getNickname());
	String avatar = (user==null ? "" : user.getLargeAvatar());
	boolean isAdmin = (user==null ? false : user.hasRole("admin"));
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LoBoSi.com (1.0Beta)</title>
	<link rel="shortcut icon" href="${siteUrl}/resources/images/lobosi.ico">
	<style type="text/css">	
    #loading-mask{background-color:white;height:100%;position:absolute;left:0;top:0;width:100%;z-index:20000;}
    #loading{height:auto;position:absolute;left:45%;top:40%;padding:2px;z-index:20001;}
    #loading a {color:gray;}
    #loading .loading-indicator{background:white;color:#444;font:normal 13px Helvetica, Arial, sans-serif;height:auto;margin:0;padding:10px;}
    #loading-msg {font-size:13px;font-weight: normal;padding-left:5px;}
	</style>
</head>
<body>	
    <div id="loading-mask" style=""></div>
    <div id="loading">
        <div class="loading-indicator">
            <img src="${siteUrl}/themes/portal/resources/images/loading.gif" width="42" height="42" style="margin-right:8px;float:left;vertical-align:top;"/>
            <br /><span id="loading-msg">请稍候...</span>
        </div>
    </div>
    <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '30%';</script>
    <link rel="stylesheet" type="text/css" href="${extUrl}/resources/css/ext-all-gray.css" />
    <link rel="stylesheet" type="text/css" href="${extUrl}/../ux/notification.css" />
    <link rel="stylesheet" type="text/css" href="${extUrl}/examples/ux/css/BoxSelect.css" />
    <link rel="stylesheet" type="text/css" href="${siteUrl}/resources/css/ext-overrides.css" />
    <link rel="stylesheet" type="text/css" href="${siteUrl}/themes/portal/portal.css" />
    <!-- extjs library -->
    <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '50%';</script>
    <script type="text/javascript" src="${extUrl}/ext-all.js"></script>
    <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '80%';</script>
    <!-- constants -->
    <script type="text/javascript">
	var ROOT_PATH = '${siteUrl}'; 
    var CURRENT_USER = {
    	id: 'u-<%=uid%>',
    	name: '<%=username%>',
    	nickname: '<%=nickname%>',
    	avatar: '/<%=avatar%>', 
    	isAdmin: <%=isAdmin%>
    };
    var _PAGE = {columns:'${page.columns}'-0};
	//Ext.QuickTips.init();
	Ext.Loader.setConfig({
		enabled : true, 
		disableCaching : false
	});
    Ext.Loader.setPath({
    	'Ext.ux': '${extUrl}/examples/ux',
    	'Ext.ux.layout': '${extUrl}/examples/ux/layout',
    	'Ext.ux.form': '${extUrl}/examples/ux/form'
    });
    //console.log(_PAGE.columns);
    </script>
    <!-- script type="text/javascript" src="${extUrl}/examples/shared/examples.js1"></script-->
    <!-- script type="text/javascript" src="${extUrl}/examples/ux/form/field/BoxSelect.js1"></script-->
    <script type="text/javascript" src="${siteUrl}/themes/portal/resources/Utils.js"></script>
    <script type="text/javascript" src="${siteUrl}/themes/portal/resources/FieldError.js"></script>
    
    <script type="text/javascript">
        Ext.Loader.setPath({
        	'JXP._core' : '${siteUrl}/themes/portal',<c:forEach var="module" items="${page.usedModules}">
        	'JXP.${module.name}.frontend':'${siteUrl}/${module.name}/frontend',</c:forEach>
        	//<c:if test="${page==null||page.usedModules==null}">'JXP.system.frontend':ROOT_PATH+'/system/frontend',</c:if>
        	'Ext.ux.window.Notification' : '${extUrl}/../ux/Notification.js'
        });

        Ext.require([
            'Ext.layout.container.*',
            'Ext.resizer.Splitter',
            'Ext.fx.target.Element',
            'Ext.fx.target.Component',
            'Ext.window.Window'
        ]);
        // 准备区块
        var _portlets = new Array();
        var _controllers = new Array();
        _controllers[0] = 'JXP._core.controller.Core';
        //_controllers[1] = 'JXP.system.frontend.controller.SignIn';
        <c:forEach var="instance" items="${page.portletInstanceSet}">
        _portlets.push([{xtype:'${instance.portlet.jsWidget}',width:${instance.width},height:${instance.height},colspan:${instance.colspan},rowspan:${instance.rowspan}}]);
        //Ext.require('${instance.portlet.jsClass}');
        //_controllers.push('${instance.portlet.jsController}');
        Ext.Array.include(_controllers, '${instance.portlet.jsController}');
    	</c:forEach>
        //console.dir(_controllers);
    </script>
    <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '90%';</script>
    <script type="text/javascript" src="${siteUrl}/themes/portal/app.js"></script>
    <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '100%';</script>
    <!-- script type="text/javascript" src="${extUrl}/locale/ext-lang-zh_CN1.js"></script-->
	<c:choose>
		<c:when test="${empty page.fittedHeight}"><c:set var="fittedHeight" value="500"></c:set></c:when>
		<c:otherwise><c:set var="fittedHeight" value="${page.fittedHeight}"></c:set></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${empty page.fittedWidth}"><c:set var="fittedWidth" value="1020"></c:set></c:when>
		<c:otherwise><c:set var="fittedWidth" value="${page.fittedWidth}"></c:set></c:otherwise>
	</c:choose>
	<div id="ct-portal" style="height:${fittedHeight}px;width:${fittedWidth}px;"></div>
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