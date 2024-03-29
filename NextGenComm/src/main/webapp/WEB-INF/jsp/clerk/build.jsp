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
                        楼宇管理
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/clerk.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <span>物业管理</span>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/clerk//building.do">楼宇管理</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-edit"></i>楼宇列表</div>
                            <div class="tools">
                                <a href="javascript:" class="reload"></a>
                                <a href="javascript:" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="clearfix">
                                <div class="btn-group">
                                    <button id="sample_editable_1_new" class="btn green">
                                        添加楼宇 <i class="icon-plus"></i>
                                    </button>
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
                                    <th>楼宇号</th>
                                    <th>楼层数</th>
                                    <th>编辑</th>
                                    <th>删除</th>
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
            <div id="add-build" class="portlet box blue hide">
                <div class="portlet-title">
                    <div class="caption"><i class="icon-reorder"></i>添加楼宇</div>
                    <div class="tools">
                        <a href="javascript:" class="collapse"></a>
                    </div>
                </div>
                <div class="portlet-body form">
                    <s:form action="addBuild" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">楼宇号</label>

                            <div class="controls">
                                <s:textfield name="buildNo" value="" class="span6 m-wrap"/>
                                <span class="help-inline"></span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">楼层数</label>

                            <div class="controls">
                                <s:textfield name="floorCount" value="" class="span6 m-wrap"/>
                                <span class="help-inline"></span>
                            </div>
                        </div>
                        <div class="form-actions">
                            <s:submit class="btn blue" value="保存"/>
                            <button type="button" class="btn">重置</button>
                        </div>
                    </s:form>
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
<script src="../../../custom/js/build.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        $(".page-sidebar-menu .title:contains('物业管理')").closest("li").addClass("active");
        $(".page-sidebar-menu .sub-menu a:contains('楼宇管理')").closest("li").addClass("active");
        TableEditable.init();
        UIModals.init();
    });
</script>
</body>
</html>