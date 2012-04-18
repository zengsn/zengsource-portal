<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.zengsource.portal.model.User" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	User user = (User)request.getSession(true).getAttribute("user");
	int uid = (user==null ? 0 : user.getId());
	String username = (user==null ? "" : user.getUsername());
	boolean isAdmin = (user==null ? false : user.hasRole("admin"));
	List<?> modules = (List<?>)request.getAttribute("modules");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lobosi.com 后台</title>
	<link rel="shortcut icon" href="${siteUrl}/resources/images/lobosi.ico">	
	<link rel="stylesheet" type="text/css" href="${extUrl}/resources/css/ext-all.css">
    <link rel="stylesheet" type="text/css" href="${extUrl}/../ux/notification.css" />
	<script type="text/javascript" src="${extUrl}/ext-all.js"></script>
    <script type="text/javascript" src="${extUrl}/locale/ext-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="${extUrl}/examples/ux/css/CenterLayout.css" />
    <link rel="stylesheet" type="text/css" href="${siteUrl}/resources/css/ext-overrides.css" />
    <link rel="stylesheet" type="text/css" href="${siteUrl}/system/resources/css/layout-browser.css">

    <!-- page specific -->
    <script type="text/javascript">
	var ROOT_PATH = '${siteUrl}';
    var CURRENT_USER = {
    	id: 'u-<%=uid%>',
    	name: '<%=username%>',
    	isAdmin: <%=isAdmin%>
    };
    var _components = [{xtype: 'adminstartpanel'}];
    var _controllers = [ 
            	    'JXP.system.controller.Admin' 
            	    ,'JXP.system.controller.Invitation'
            	    ,'JXP.system.controller.User'
            	    ,'JXP.system.controller.Role'
            	    ,'JXP.system.controller.Permission'
            	    ,'JXP.system.controller.Module'
            	    ,'JXP.system.controller.Page'
            	    ,'JXP.system.controller.Portlet'
            	    ,'JXP.system.controller.Config'
            	];
    </script>
    <script type="text/javascript" src="${siteUrl}/system/admin/basic.js"></script>
    <script type="text/javascript" src="${siteUrl}/system/admin/custom.js"></script>
    <script type="text/javascript" src="${siteUrl}/system/admin/combination.js"></script>
	<script type="text/javascript">
	Ext.Loader.setConfig({
		enabled : true,
		disableCaching : false
	});
	Ext.Loader.setPath({
		'Ext.ux': '${extUrl}/examples/ux',
		'JXP.system':ROOT_PATH+'/system/admin',<c:forEach var="module" items="${modules}">
		<c:if test="${module.name!='system'}">'JXP.${module.name}': ROOT_PATH+'/${module.name}',</c:if></c:forEach>
		//'JXP.system': ROOT_PATH+'/system/admin',
		'Ext.ux.window.Notification' : '${extUrl}/../ux/Notification.js'
	});
	// 明细变量
	var detailEl;
	// 准备模块 
	<c:forEach var="module" items="${modules}"><c:forEach var="menu" items="${module.menuSet}">
	<c:if test="${! empty menu.controller}">Ext.Array.include(_controllers, '${menu.controller}');
	</c:if>
	Ext.Array.include(_components, {xtype: '${menu.widget}'});
	</c:forEach></c:forEach>
	// 准备菜单 
	var treeNodes= {text:'.',children:[ <% int mdCount=0;%>
	<c:forEach var="module" items="${modules}">
	{text:'${module.title}',children:[ <% mdCount++; int mnCount=0;%>
	<c:forEach var="menu" items="${module.menuSet}">
	{id:'',text:''},<%//=mnCount==module.menuSet.size()?"":","%>
	</c:forEach>{}
	]}<%=mdCount==modules.size()?"":","%>
	</c:forEach>
	]};
	</script>
    <script type="text/javascript" src="${siteUrl}/resources/Utils.js"></script>
	<script type="text/javascript" src="${siteUrl}/system/admin/app.js"></script>
	
</head>
<body>
    <div style="display:none;">

        <!-- Start page content -->
        <div id="start-div">
            <div style="float:left;" ><img src="resources/images/layout-icon.gif" /></div>
            <div style="margin-left:100px;">
                <h2>Welcome!</h2>
                <p>There are many sample layouts to choose from that should give you a good head start in building your own
                application layout.  Just like the combination examples, you can mix and match most layouts as
                needed, so don't be afraid to experiment!</p>
                <p>Select a layout from the tree to the left to begin.</p>
            </div>
        </div>

        <!-- Basic layouts -->
        <div id="default-details">
        	无。
        </div>
        <div id="invitation-details">
            <h2>邀请码</h2>
            <p>This is a simple layout style that allows you to position items within a container using
            CSS-style absolute positioning via XY coordinates.</p>
        </div>
    </div>
</body>
</html>