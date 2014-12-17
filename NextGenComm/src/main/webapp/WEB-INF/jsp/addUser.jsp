<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<h4>添加用户</h4>

<form action="user!saveUser" method="post">
    用户名：&nbsp;<input type="text" name="user.userName"/><br/>
    密码：&nbsp;<input type="password" name="user.password"/><br/>
    <input type="submit" value="添加"/><input type="reset" value="重置"/><br/>
</form>
</body>
</html>
