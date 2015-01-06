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
                            <a href="/owner/billlist.do">费用缴纳</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>费用缴纳</div>
                            <div class="tools">
                                <a href="javascript:" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:" class="reload"></a>
                                <a href="javascript:" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <div class="form-horizontal form-view">
                                <h3> 待缴费清单 </h3>
                                <s:iterator value="billItems" id="bill" status="st">
                                    <h3 class="form-section"><s:property value="name"/>(<s:property
                                            value="description"/>
                                        )</h3>

                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label" for="usage">用量：</label>

                                                <div class="controls">
                                                    <span class="text" id="usage"><s:property value="usage"/> </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label" for="day">滞纳天数：</label>

                                                <div class="controls">
                                                    <span class="text" id="day"><s:property
                                                            value="overDueDays"/> </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label" for="amount">金额：</label>

                                                <div class="controls">
                                                    <span class="text bold" id="amount"><s:property
                                                            value="amount"/> </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">滞纳金：</label>

                                                <div class="controls">
                                                    <span class="text bold"><s:property value="overDueFee"/></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </s:iterator>
                                <div class="row-fluid">
                                    <div class="span8">
                                    </div>
                                    <div class="span4 ">
                                        <div class="control-group" style="margin-bottom: 20px">
                                            <label class="control-label" style="font-size: larger">总额：</label>

                                            <div class="controls">
                                                    <span class="text bold" style="font-size: larger"><s:property
                                                            value="total"/></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <s:form action="makePayment">
                                    <div class="form-actions">
                                        <s:submit cssClass="btn blue big-btn pull-right" value="网上缴费">
                                        </s:submit>
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
    <script src="../../../custom/js/app.js" type="text/javascript"></script>
    <script>
        jQuery(document).ready(function () {
            App.init();
            $(".page-sidebar-menu .title:contains('费用缴纳')").closest("li").addClass("active");
        });
    </script>
</body>
</html>
