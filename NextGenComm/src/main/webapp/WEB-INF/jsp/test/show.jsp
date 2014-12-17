<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form class="form-horizontal" role="form">

    <table class="table table-hover table-responsive" id="payment-table">
        <thead>
        <tr>
            <th class="th-checkbox"><input type="checkbox"
                                           id="checkbox-all"></th>
            <th class="th-payItem">缴费项目</th>
            <th class="th-shouldPay">应缴费用</th>
            <th class="th-more">更多</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="bills" id="record">
            <tr>
                <td><input type="checkbox" name="checkbox-singleitem"
                           id="input-1"></td>
                <td><s:property value="name"/>(<s:property
                        value="description"/>)
                </td>
                <td><s:property value="amount"/></td>
                <td></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</form>
</body>
</html>