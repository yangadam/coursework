<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]> <html lang="en" class="no-js"> <![endif]-->
<html>
<head>
    <meta charset="utf-8"/>
    <title>首页 | 物业管理系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>

    <%@include file="globalCSS.jsp" %>
    <link href="../../../global/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
    <link href="../../../global/css/daterangepicker.css" rel="stylesheet" type="text/css"/>
    <link href="../../../global/css/fullcalendar.css" rel="stylesheet" type="text/css"/>
    <link href="../../../global/css/jqvmap.css" rel="stylesheet" type="text/css" pumpking="screen"/>
    <link href="../../../global/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" pumpking="screen"/>
    <link rel="shortcut icon" href="../../../global/image/favicon.ico"/>
</head>

<body class="page-header-fixed">
<%@include file="globalHeader.jsp" %>
<div class="page-container">
    <%@include file="globalSidebar.jsp" %>
    <div class="page-content">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        欢迎使用本系统
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="admin.do">主页</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">

                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="globalFooter.jsp" %>
<%@include file="globalJS.jsp" %>
<script src="../../../global/js/select.min.js" type="text/javascript"></script>
<script src="../../../custom/js/app.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        $(".page-sidebar-menu .title:contains('主页')").closest("li").addClass("active");
    });
</script>
</body>
</html>