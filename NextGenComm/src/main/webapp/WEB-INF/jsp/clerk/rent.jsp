<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]> <html lang="en" class="no-js"> <![endif]-->
<html>
<head>
    <meta charset="utf-8"/>
    <title>车位租用 | 物业管理系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>

    <%@include file="globalCSS.jsp" %>
    <link rel="stylesheet" href="../../../global/css/DT_bootstrap.css"/>
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
                            <a href="/clerk.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <span>车位管理</span>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/clerk/share.do">车位租用</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-edit"></i>空闲可租用车位列表</div>
                            <div class="tools">
                                <a href="javascript:" class="reload"></a>
                                <a href="javascript:" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="clearfix">
                                <div class="control-group">
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
                                <table class="table table-striped table-hover table-bordered"
                                       id="sample_editable_1">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>车位位置</th>
                                        <th>车位状态</th>
                                        <th>租用</th>
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
<%@include file="globalFooter.jsp" %>
<%@include file="globalJS.jsp" %>
<script src="../../../global/js/select2.min.js" type="text/javascript"></script>
<script src="../../../global/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="../../../global/js/DT_bootstrap.js" type="text/javascript"></script>
<script src="../../../custom/js/app.js" type="text/javascript"></script>
<script src="../../../custom/js/rent.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        $(".page-sidebar-menu .title:contains('车位管理')").closest("li").addClass("active");
        $(".page-sidebar-menu .sub-menu a:contains('车位租用')").closest("li").addClass("active");
        TableEditable.init();
    });
</script>
</body>
</html>
