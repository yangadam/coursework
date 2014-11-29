<%--
  Created by IntelliJ IDEA.
  User: Yummy
  Date: 11/28/2014 0028
  Time: 12:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Spring-4 + Struts-3 + Hibernate Integration Demo</title>
    <style>
        table.list {
            border-collapse: collapse;
            width: 40%;
        }

        table.list, table.list td, table.list th {
            border: 1px solid gray;
            padding: 5px;
        }
    </style>
</head>
<body>

<h3>Energy Records</h3>

<table class="list">
    <tr>
        <th align="left">楼宇号</th>
        <th align="left">楼层号</th>
        <th align="left">房间号</th>
        <th align="left">水表当前读数</th>
        <th align="left">电表当前读数</th>
        <th align="left">录入时间</th>
        <th align="left">编辑</th>
    </tr>
    <s:iterator value="energyRecords" id="record">
        <tr>
            <td><s:property value="buildingNo"/></td>
            <td><s:property value="floorNo"/></td>
            <td><s:property value="roomNo"/></td>
            <td><s:property value="currentWaterValue"/></td>
            <td><s:property value="currentElectricityValue"/></td>
            <td><s:property value="createdDate"/></td>
            <td></td>
        </tr>
    </s:iterator>
    <s:form method="post" action="add">
        <td><s:textfield name="energyRecord.buildingNo"/></td>
        <td><s:textfield name="energyRecord.floorNo"/></td>
        <td><s:textfield name="energyRecord.roomNo"/></td>
        <td><s:textfield name="energyRecord.currentWaterValue"/></td>
        <td><s:textfield name="energyRecord.currentElectricityValue"/></td>
        <td></td>
        <td><s:submit label="add"/></td>
    </s:form>
</table>

</body>
</html>
