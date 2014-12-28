<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<s:form action="doLogin" method="POST">
    用户名：<s:textfield name="username"/><br/>
    密&nbsp;&nbsp;&nbsp;&nbsp;码：<s:password name="password"/><br/>
    <s:checkbox name="rememberMe" value="rememberMe"/>记住我<br/>
    <s:submit value="登录"/><br/>
    <s:actionerror/>
</s:form>
</body>
</html>
