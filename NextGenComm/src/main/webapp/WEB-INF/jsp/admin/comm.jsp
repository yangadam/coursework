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
<div>
    <table id="comm" border="1" class="display">
        <thead>
        <tr>
            <th>小区名称</th>
            <th>动作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <s:form action="addComm">
        小区名称：<s:textfield name="commName"/>
        <s:submit value="添加"/>
    </s:form>
</div>
<script src="../../../media/js/jquery-2.1.3.js" type="text/javascript"></script>
<script src="../../../media/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="../../../media/js/DT_bootstrap.js" type="text/javascript"></script>
<script src="../../../media/js/comm/comm.js" type="text/javascript"></script>

</body>
</html>
