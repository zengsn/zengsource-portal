<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="http://zsncc/lib/sencha/extjs-4.0.7/resources/css/ext-all.css">

<script type="text/javascript"
	src="http://zsncc/lib/sencha/extjs-4.0.7/ext-all.js"></script>

<script type="text/javascript" src="app/home.js"></script>
<script type="text/javascript">
	Ext.Loader.setConfig({
		enabled : true,
		disableCaching : true
	});
</script>
</head>
<body>
	<div id="logo"
		style="text-align: left; padding: 15px; font-size: 16px;">
		<h1>2ab.cc - Space of A &amp; B</h1>
	</div>
	<div id="login"
		style="text-align: left; padding: 15px; font-size: 13px;">
		<a href="account/signin.jxp">登录</a><br /> <a
			href="account/signout.jxp">注销</a><br /> <a href="./question/">问题</a>
	</div>
</body>
</html>