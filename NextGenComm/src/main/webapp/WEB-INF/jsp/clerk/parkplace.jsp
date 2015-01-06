<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]> <html lang="en" class="no-js"> <![endif]-->
<html>
<head>
    <meta charset="utf-8"/>
    <title>楼宇管理 | 物业管理系统</title>
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
                        车位管理
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/clerk.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <span>车位管理</span>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/clerk/parkplace.do">车位列表</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12 ">
                    <div class="portlet box blue tabbable">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>车位列表</div>
                        </div>
                        <div class="portlet-body">
                            <div class="tabbable portlet-tabs">
                                <ul class="nav nav-tabs">
                                    <li><a href="#portlet_tab2" data-toggle="tab">临时停车位</a></li>
                                    <li class="active"><a href="#portlet_tab1" data-toggle="tab">租用车位</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="portlet_tab1">
                                        <div class="portlet-body">
                                            <div class="clearfix">
                                                <table class="table table-striped table-hover table-bordered"
                                                       id="sample_editable_1">
                                                    <thead>
                                                    <tr>
                                                        <th>编号</th>
                                                        <th>车位位置</th>
                                                        <th>车位状态</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="portlet_tab2">
                                        <div class="portlet-body">
                                            <div class="clearfix">
                                                <table class="table table-striped table-hover table-bordered"
                                                       id="sample_editable_2">
                                                    <thead>
                                                    <tr>
                                                        <th>编号</th>
                                                        <th>车位位置</th>
                                                        <th>车位状态</th>
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
                </div>
            </div>
        </div>
    </div>
        <%@include file="../global/globalFooter.jsp" %>
        <%@include file="../global/globalJS.jsp" %>
        <script type="text/javascript" src="../../../global/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../../../global/js/DT_bootstrap.min.js"></script>
    <script src="../../../custom/js/parkplace.js" type="text/javascript"></script>
    <script>
        jQuery(document).ready(function () {
            App.init();
            $(".page-sidebar-menu .title:contains('车位管理')").closest("li").addClass("active");
            $(".page-sidebar-menu .sub-menu a:contains('车位列表')").closest("li").addClass("active");
            TableEditable1.init();
            TableEditable2.init();
            UIModals.init();
        });
    </script>
</body>
</html>