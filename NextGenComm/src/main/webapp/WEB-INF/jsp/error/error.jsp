<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>

<%
    Throwable ex = null;
    if (exception != null) {
        ex = exception;
    }
    if (request.getAttribute("javax.servlet.error.exception") != null) {
        ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
    }
    Logger logger = LoggerFactory.getLogger("error.jsp");
    logger.error(ex.getMessage(), ex);
%>

<html>
<body>
<h2>出错</h2>
<s:property value="message"/>
</body>
</html>
