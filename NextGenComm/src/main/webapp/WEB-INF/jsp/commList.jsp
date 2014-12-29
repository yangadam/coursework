<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>社区管理</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>

    <%@include file="../jsp/globalCSS.jsp" %>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="../../pumpking/css/select2_metro.css"/>
    <link rel="stylesheet" href="../../pumpking/css/DT_bootstrap.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="../../pumpking/image/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">

<%@include file="globalHeader.jsp" %>

<!-- BEGIN CONTAINER -->
<div class="page-container">

    <%@include file="globalSidebar.jsp" %>
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        社区管理
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="../admin/home.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="../admin/commList.do">社区管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="../admin/commList.do">社区列表</a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-edit"></i>社区列表</div>
                            <div class="tools">
                                <a href="javascript:" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:" class="reload"></a>
                                <a href="javascript:" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="clearfix">
                                <div class="btn-group">
                                    <a href="../admin/commAdd.do" id="sample_editable_1_new" class="btn green">
                                        添加社区 <i class="icon-plus"></i>
                                    </a>
                                </div>
                                <div class="btn-group pull-right">
                                    <button class="btn dropdown-toggle" data-toggle="dropdown">工具 <i
                                            class="icon-angle-down"></i>
                                    </button>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="#">打印</a></li>
                                        <li><a href="#">保存为PDF</a></li>
                                        <li><a href="#">导出Excel表格</a></li>
                                    </ul>
                                </div>
                            </div>
                            <table class="table table-hover table-bordered" id="sample_editable_1">
                                <thead>
                                <tr>
                                    <th>小区编号</th>
                                    <th>小区名称</th>
                                    <th>编辑</th>
                                    <th>删除</th>
                                    <th>详细信息</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>

                            <div id="static" class="modal hide fade" tabindex="-1" data-backdrop="static"
                                 data-keyboard="false">
                                <div class="modal-body">
                                    <p>确定要删除此项吗？</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" data-dismiss="modal" class="btn">取消</button>
                                    <button type="button" data-dismiss="modal" class="btn green">确认</button>
                                </div>
                            </div>

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
<%@include file="globalFooter.jsp" %>

<!-- GLOBAL JS-->
<%@include file="globalJS.jsp" %>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="../../pumpking/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../pumpking/js/DT_bootstrap.js"></script>
<script type="text/javascript" src="../../pumpking/js/ui-modals.js"></script>
<script type="text/javascript" src="../../pumpking/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="../../pumpking/js/bootstrap-modalmanager.js"></script>
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../../pumpking/js/app.js"></script>
<script src="../../pumpking/js/json/commList.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        TableEditable.init();
        UIModals.init();
        $(".page-sidebar-menu .title:contains('社区管理')").closest(
                "li").addClass("active");
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>