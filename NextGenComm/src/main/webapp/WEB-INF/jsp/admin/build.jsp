<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>物业管理系统首页</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>
    <jsp:include page="../common/style.jsp"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<jsp:include page="../common/header.jsp"/>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <jsp:include page="../common/sidebar.jsp"/>
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <div id="portlet-config" class="modal hide">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>portlet Settings</h3>
            </div>
            <div class="modal-body">
                <p>Here will be a configuration form</p>
            </div>
        </div>
        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <jsp:include page="../common/pageheder.jsp"/>
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12">
                    <table border="1">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>楼宇号</th>
                            <th>楼宇名称</th>
                        </tr>
                        </thead>
                        <s:iterator value="buildings" status="st" id="build">
                            <tr>
                                <td><s:property value="#st.count"/></td>
                                <td><s:property value="no"/></td>
                                <td><s:property value="name"/></td>
                                    <%--<td><s:form action="addBuild">--%>
                                    <%--<s:label name="selectedComm" value="#st.count"/>--%>
                                    <%--<s:submit value="添加楼宇" cssClass="btn green"/>--%>
                                    <%--</s:form></td>--%>
                            </tr>
                        </s:iterator>
                    </table>

                    <br/>
                    <br/>

                    <s:form action="addBuild">
                        <div>
                            楼宇号：&nbsp;<s:textfield cssClass="m-wrap placeholder-no-fix" name="no" type="text"/>
                            楼宇名称：&nbsp;<s:textfield cssClass="m-wrap placeholder-no-fix" name="name" type="text"/>
                            <s:submit value="添加" cssClass="btn green"/>
                        </div>
                    </s:form>

                </div>
            </div>
            <!-- END PAGE CONTENT-->
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/script.jsp"/>
</body>
<!-- END BODY -->
</html>