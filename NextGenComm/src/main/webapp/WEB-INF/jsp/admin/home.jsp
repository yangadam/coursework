<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="user" style="float: right;text-align: right">
    用户：<s:property value="#session['USER'].name"/><br/>
    <a href=" <s:url action="logout"/>">登出</a>
</div>
<div>
    <a href=" <s:url action="admin"/>">主页</a>
    <a href=" <s:url action="admin/community"/>">小区管理</a>
</div>
</body>
</html>
