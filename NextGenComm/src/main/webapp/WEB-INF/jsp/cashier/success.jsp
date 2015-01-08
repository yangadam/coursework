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

    <%@include file="../global/globalCSS.jsp" %>
    <link href="../../../global/css/jquery.gritter.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../../global/css/daterangepicker.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../../global/css/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../../global/css/jqvmap.min.css" rel="stylesheet" type="text/css" pumpking="screen"/>
    <link href="../../../global/css/jquery.easy-pie-chart.min.css" rel="stylesheet" type="text/css" pumpking="screen"/>
    <link rel="shortcut icon" href="../../../global/image/favicon.ico"/>
</head>

<body class="page-header-fixed">
<%@include file="../global/globalHeader.jsp" %>
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
                            <a href="guard.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/cashier/feeCharge.do">费用缴纳</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>收缴费用</div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <div class="form-horizontal form-view">
                                <h3 style="margin:30px auto; text-align: center"> 成功<i class="icon-ok"></i></h3>
                            </div>

                            <div class="row-fluid">
                                <div class="span12 ">
                                    <div class="span10"></div>
                                    <div class="form-actions">
                                        <a type="button" class="btn blue" href="/cashier.do">确定
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../global/globalFooter.jsp" %>
    <%@include file="../global/globalJS.jsp" %>
    <script src="../../../global/js/select2.min.js" type="text/javascript"></script>
    <script src="../../../custom/js/app.js" type="text/javascript"></script>
    <script>
        jQuery(document).ready(function () {
            App.init();
            $(".page-sidebar-menu .title:contains('费用收取')").closest("li").addClass("active");
        });
    </script>
</body>
</html>
