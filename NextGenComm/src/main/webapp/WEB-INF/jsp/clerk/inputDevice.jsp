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
                            <a href="/clerk.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <i class="icon-home"></i>
                            <a href="/clerk/input.do">录入水电信息</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-edit"></i>已录入设备</div>
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
                                <table class="table table-hover table-bordered" id="sample_editable_1">
                                    <thead>
                                    <tr>
                                        <th>设备号</th>
                                        <th>当前读数</th>
                                        <th>上次读数</th>
                                        <th>设备类型</th>
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
                    <div id="add-owner" class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>添加读数</div>
                            <div class="tools">
                                <a href="javascript:" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <s:form action="addRoom" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label">设备号</label>

                                    <div class="controls">
                                        <s:textfield id="deviceNo" name="roomNo" value="" class="span6 m-wrap"
                                                     onchange="check(this)"/>
                                        <span class="help-inline">默认形式：CB+楼宇号+F+楼层号+R+房间号</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">上次读数</label>

                                    <div class="controls">
                                        <s:textfield id="lastValue" value="" class="span6 m-wrap" readonly="true"/>
                                        <span class="help-inline unit"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">当前读数</label>

                                    <div class="controls">
                                        <s:textfield value="" class="span6 m-wrap"/>
                                        <span class="help-inline unit"></span>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <s:submit id="record" class="btn blue" value="确认" disabled="true"/>
                                    <button type="button" class="btn">重置</button>
                                </div>
                            </s:form>
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
<script src="../../../custom/js/inputDevice.js" type="text/javascript"></script>

<script>
    jQuery(document).ready(function () {
        App.init();
        $(".page-sidebar-menu .title:contains('录入水电读数')").closest("li").addClass("active");
        TableEditable.init();
    });
</script>
</body>
</html>
