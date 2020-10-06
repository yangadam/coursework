<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]> <html lang="en" class="no-js"> <![endif]-->
<html>
<head>
    <meta charset="utf-8"/>
    <title>费用管理 | 物业管理系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>

    <%@include file="../global/globalCSS.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../../global/css/select2_metro.min.css"/>
    <link rel="stylesheet" href="../../../global/css/DT_bootstrap.min.css"/>
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
                        费用管理
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/clerk.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <span>费用管理</span>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/clerk/feeReminder.do">费用催缴</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-edit"></i>欠费业主</div>
                            <div class="tools">
                                <a href="javascript:" class="reload"></a>
                                <a href="javascript:" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="clearfix">
                                <table class="table table-hover table-bordered" id="sample_editable_1">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>业主姓名</th>
                                        <th>房间</th>
                                        <th>催缴</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../global/globalFooter.jsp" %>
    <%@include file="../global/globalJS.jsp" %>
    <script type="text/javascript" src="../../../global/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="../../../global/js/DT_bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../global/js/ui-modals.min.js"></script>
    <script type="text/javascript" src="../../../global/js/bootstrap-modal.min.js"></script>
    <script type="text/javascript" src="../../../global/js/bootstrap-modalmanager.min.js"></script>
    <script src="../../../custom/js/feeReminder.js" type="text/javascript"></script>
    <script>
        jQuery(document).ready(function () {
            App.init();
            $(".page-sidebar-menu .title:contains('费用管理')").closest("li").addClass("active");
            $(".page-sidebar-menu .sub-menu a:contains('费用催缴')").closest("li").addClass("active");
            TableEditable.init();
            UIModals.init();
        });
    </script>
</body>
</html>