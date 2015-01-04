<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>停车场管理</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>
    <%@include file="globalCSS.jsp" %>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="../../../global/css/select2_metro.css"/>
    <link rel="stylesheet" href="../../../global/css/DT_bootstrap.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="../../../global/image/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<%@include file="globalHeader.jsp" %>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <%@include file="globalSidebar.jsp" %>
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
                            <a href="">停车缴费</a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <div class="row-fluid ">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>停车缴费单</div>
                            <div class="tools">
                                <a href="javascript:" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body form" id="parkBill">
                            <!-- BEGIN FORM-->
                            <div class="form-horizontal form-view">
                                <h3> 停车缴费单 </h3>
                                <%--<h3 class="form-section">车辆信息</h3>--%>

                                <div class="control-group">
                                    <label class="control-label">车牌号：</label>

                                    <div class="controls">
                                        <span class="text">${license}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div class="control-group">
                                    <label class="control-label">缴费单号：</label>

                                    <div class="controls">
                                        <span class="text">#${parkBillId}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group">
                                    <label class="control-label">挂名业主：</label>

                                    <div class="controls">
                                        <span class="text bold">${ownerName}</span>
                                    </div>
                                </div>
                            </div>
                            <!--/span-->
                            <div class="span6 ">
                                <div class="control-group">
                                    <label class="control-label">业主电话：</label>

                                    <div class="controls">
                                        <span class="text bold">${ownerPhone}</span>
                                    </div>
                                </div>
                            </div>
                            <!--/span-->
                        </div>
                        <!--/row-->
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group">
                                    <label class="control-label">进入时间：</label>

                                    <div class="controls">
                                        <span class="text bold">${carInTime}</span>
                                    </div>
                                </div>
                            </div>
                            <!--/span-->
                            <div class="span6 ">
                                <div class="control-group">
                                    <label class="control-label">离开时间：</label>

                                    <div class="controls">
                                        <span class="text bold">${carOutTime}</span>
                                    </div>
                                </div>
                            </div>
                            <!--/span-->
                        </div>
                        <!--/row-->
                        <hr/>
                        <div class="row-fluid">
                            <div class="span8">
                            </div>

                            <div class="span4 ">
                                <div class="control-group" style="margin-bottom: 20px">
                                    <label class="control-label" style="font-size: larger">应缴费用：</label>

                                    <div class="controls">
                                        <span class="text bold" style="font-size: larger">${fee}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button type="button" class="btn blue" onclick="printParkBill()"><i
                                    class="icon-pencil"></i>打印
                            </button>
                            <button type="button" class="btn" onclick="history.back(-1)">返回</button>
                        </div>
                    </div>
                    <!-- END FORM-->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="globalFooter.jsp" %>
<!-- END FOOTER -->
<%@include file="globalJS.jsp" %>
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="../../../global/js/select2.min.js"></script>
<script type="text/javascript" src="../../../global/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../../global/js/DT_bootstrap.js"></script>
<script language="javascript" src="../../../global/js/jquery.jqprint-0.3.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../../../custom/js/app.js"></script>
<script>
    App.init();
    $(".page-sidebar-menu .title:contains('停车管理')").closest(
            "li").addClass("active");
    $(".page-sidebar-menu .sub-menu a:contains('停车缴费')").closest(
            "li").addClass("active");
</script>
<script>
    function printParkBill() {
        $("#parkBill").jqprint();
    }
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>