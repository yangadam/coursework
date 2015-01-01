<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>停车场管理</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>

    <%@include file="admin/globalCSS.jsp" %>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="../../pumpking/css/select2_metro.css"/>
    <link rel="stylesheet" href="../../pumpking/css/DT_bootstrap.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="../../pumpking/image/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<%@include file="admin/globalHeader.jsp" %>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">

    <%@include file="admin/globalSidebar.jsp" %>

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
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        停车列表
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="admin.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="../guard/tempParkingList.do">车辆管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="../guard/tempParkingList.do">停车列表</a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12">

                    <div class="input-append" style="margin-bottom: 20px">
                        <s:textfield cssClass="m-wrap" placeholder="请输入车牌号"/>
                        <button type="button" class="btn green">
                            <span>车辆登记</span>
                        </button>
                    </div>

                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-edit"></i>临时停车列表</div>
                            <div class="tools">
                                <a href="javascript:" class="collapse"></a>
                                <a href="javascript:" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="clearfix">
                                <table class="table table-striped table-hover table-bordered" id="sample_editable_1">
                                    <thead>
                                    <tr>
                                        <th>车牌号</th>
                                        <th>车主姓名</th>
                                        <th>车主身份证</th>
                                        <th>车主行驶证</th>
                                        <th>挂名业主</th>
                                        <th>登出</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>


                                </table>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- END PAGE CONTENT-->
            </div>
            <!-- END PAGE CONTAINER-->
        </div>
        <!-- END PAGE -->
    </div>
    <!-- END CONTAINER -->
    <!-- BEGIN FOOTER -->
    <%@include file="admin/globalFooter.jsp" %>
    <!-- END FOOTER -->


    <%@include file="admin/globalJS.jsp" %>
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script type="text/javascript" src="../../pumpking/js/select2.min.js"></script>
    <script type="text/javascript" src="../../pumpking/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="../../pumpking/js/DT_bootstrap.js"></script>

    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="../../pumpking/js/app.js"></script>
    <script src="../../pumpking/js/json/tempParkingList.js"></script>
    <script>
        jQuery(document).ready(function () {
            App.init();
            TableEditable.init();
            $(".page-sidebar-menu .title:contains('停车场管理')").closest(
                    "li").addClass("active");
        });
    </script>
    <!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->
</html>