<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html>
<head>
    <title>登录</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

<div class="error"></div>
<form id="loginForm" action="login!login" method="post">
    <table>
        <s:actionerror/>
        <tr>
            <td><label>登录名：</label></td>
            <td><s:textfield id="username" name="username" size="16" maxlength="16"/></td>
        </tr>
        <tr>
            <td><label>密&nbsp;&nbsp;码：</label></td>
            <td><s:password id="password" name="password" size="16"/></td>
        </tr>
        <tr>
            <td colspan="2" align="right">
                <s:checkbox name="rememberMe"/>Remember me
                <input value="登录" type="submit" class="button"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>