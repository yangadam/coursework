<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title>小区详细信息</title>

</head>
<body>
<s:form id="inputForm" action="commform" method="POST">

    <div class="control-group">
        <label class="control-label">名称:</label>

        <div class="controls">
            <s:textfield name="community.name" maxlength="200"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">楼宇数量:</label>

        <div class="controls">
            <s:textfield name="community.buildingList.size" maxlength="200" readonly="true"/>
        </div>
    </div>


    <div class="form-actions">
        <s:submit id="btnSubmit" class="btn btn-primary" value="保 存" method="save"/>&nbsp;
        <s:submit id="btnRemove" class="btn" value="删 除" method="remove"/>&nbsp;&nbsp;
        <s:submit id="btnCancel" class="btn" value="返 回" onclick="history.go(-1)"/>
    </div>

</s:form>
</table>
</body>
</html>


