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
    <link href="../../../global/css/select2_metro.min.css" rel="stylesheet" type="text/css">
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
                        欢迎使用本系统
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
                            <a href="/clerk/input.do">录入水电信息</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet-body">
                        <p>
                            <button id="calc" class="btn green big btn-block" onclick="calculate()">计算<span
                                    id="count"></span>
                                <i class="m-icon-big-swapright m-icon-white"></i></button>
                        </p>
                    </div>
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
                                <table class="table table-hover table-bordered" id="sample_editable_1">
                                    <thead>
                                    <tr>
                                        <th>设备号</th>
                                        <th>当前读数</th>
                                        <th>上次读数</th>
                                        <th>设备类型</th>
                                        <th>修改</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                                <div id="static" class="modal hide fade" tabindex="-1" data-backdrop="static"
                                     data-keyboard="false">
                                    <s:form action="updateValue" class="form-horizontal">
                                        <div class="modal-body">
                                            <s:hidden id="modifyId" name="deviceId"/>
                                            <div class="control-group">
                                                <label class="control-label" for="lastValue">输入读数</label>

                                                <div class="controls">
                                                    <s:textfield name="newValue" value="" class="span6 m-wrap"/>
                                                    <span class="help-inline unit"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <s:submit id="inputValue" class="btn blue" value="确认"/>
                                            <button type="button" data-dismiss="modal" class="btn">取消</button>
                                        </div>
                                    </s:form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="input" class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>录入读数</div>
                            <div class="tools">
                                <a href="javascript:" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <s:form action="inputValue" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label" for="device">设备号</label>

                                    <div class="controls">
                                        <input type="hidden" id="device" class="span6 select2">
                                        <span class="help-inline">默认形式：[B + 楼宇号] + [F + 楼层号] + [R + 房间号] + # + 数字</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="lastValue">上次读数</label>

                                    <div class="controls">
                                        <s:textfield id="lastValue" value="" class="span6 m-wrap" readonly="true"/>
                                        <span class="help-inline unit"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">当前读数</label>

                                    <div class="controls">
                                        <s:textfield name="curValue" value="" class="span6 m-wrap"/>
                                        <span class="help-inline unit"></span>
                                    </div>
                                </div>
                                <s:hidden id="deviceId" name="deviceId"/>
                                <div class="form-actions">
                                    <s:submit id="inputValue" class="btn blue" value="确认"/>
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
<%@include file="../global/globalFooter.jsp" %>
<%@include file="../global/globalJS.jsp" %>
<script src="../../../global/js/select2.min.js" type="text/javascript"></script>
<script src="../../../global/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="../../../global/js/DT_bootstrap.min.js" type="text/javascript"></script>
<script src="../../../custom/js/inputDevice.js" type="text/javascript"></script>

<script>
    jQuery(document).ready(function () {
        App.init();
        $.getJSON("/count.do", function (data) {
            if (data["inputCount"] != data["total"]) {
                $("#calc").attr({"disabled": "disabled"});
            } else {
                $("#calc").removeAttr("disabled");
            }
            $("#count").text("  " + data["inputCount"] + "/" + data["total"]);
        });
        $(".page-sidebar-menu .title:contains('费用管理')").closest("li").addClass("active");
        $(".page-sidebar-menu .sub-menu a:contains('水电录入计算')").closest("li").addClass("active");
        TableEditable.init();
    });
</script>
</body>
</html>
