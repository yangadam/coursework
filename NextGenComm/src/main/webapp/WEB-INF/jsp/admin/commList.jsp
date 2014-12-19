<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title>小区信息</title>

</head>
<body>
<s:form>
    <s:submit value="添加" onclick="window.location.href('commForm.do?id=-1')"/>
</s:form>
<table id="contentTable" class="table">
    <thead>
    <tr>
        <th>序号</th>
        <th>名称</th>
        <th>楼宇数量</th>
        <th>地址</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="communities" status="st" id="comm">
        <tr>
            <td><s:property value="#st.count"/></td>
            <td><a href="commForm?id=${id}">${name}</a></td>
            <td>${buildingList.size()}</td>
            <td></td>
            <td><s:form action="listBuild">
                <s:hidden name="selectedComm" value="%{#st.count}"/>
                <s:submit value="查看楼宇"/>
            </s:form></td>
        </tr>
    </s:iterator>
    </tbody>
</table>
</body>
</html>


