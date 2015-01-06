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
    <%@include file="../global/globalCSS.jsp" %>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="../../../global/css/select2_metro.min.css"/>
    <link rel="stylesheet" href="../../../global/css/DT_bootstrap.min.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="../../../global/image/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<%@include file="../global/globalHeader.jsp" %>
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
                        访客车辆登记
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
                            <a href="../guard/tempParkingRegiste.do">车辆登记</a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <div class="row-fluid ">
                <div class="span6">
                    <s:form>
                    <div class="space20"></div>
                    <!-- BEGIN FORM-->
                    <form action="#" class="horizontal-form">
                        <h3 class="form-section">访客车辆信息</h3>

                        <div class="control-group span12">
                            <label class="control-label span2">车牌号</label>

                            <div class="controls span7">
                                <s:label type="text" value="%{license}" cssClass="m-wrap" id="license"
                                         cssStyle="padding-top:2px;"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">业主信息查询</label>
                        </div>
                        <div class="portlet-body form">
                            <form action="#" id="form_sample_1" class="form-horizontal">
                                <div class="control-group span12">
                                    <label class="control-label span2" for="build">楼宇号</label>

                                    <div class="controls span7">
                                        <select id="build" class="span6 m-wrap" name="category">
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group span12" style="margin-left: 0px;">
                                    <label class="control-label span2" for="floor">楼层号</label>

                                    <div class="controls span7">
                                        <select id="floor" class="span6 m-wrap" name="category">
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group span12" style="margin-left: 0px;">
                                    <label class="control-label span2" for="room">房间号</label>

                                    <div class="controls span7">
                                        <select id="room" class="span6 m-wrap" name="category">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-actions"
                                     style="background-color: transparent;border-top: transparent;padding-left: 20px;">
                                    <input type="button" class="btn green" id="query_room" value="查询"/>
                                </div>
                            </form>
                        </div>

                        <!--<div class="control-group">
                            <div class="controls">
                                <div class="input-append">
                                    <input type="text" class="m-wrap" placeholder="请输入业主姓名">
                                        <%--<button type="button" class="btn green btn-subscribe" ><span>车辆登记</span></button>--%>
                                    <a href="#" id="query" class="btn green">
                                        <i>查询</i>
                                    </a>
                                </div>
                            </div>
                        </div> -->
                    </form>
                    <!-- END FORM-->
                </div>
                <div class="span6">
                    <div class="space20"></div>
                    <h3 class="form-section">挂名业主信息</h3>

                    <div class="well">
                        <h4 id="owner_name">业主姓名</h4>
                        <address>
                            <strong id="owner_community"><label></label></strong><br>
                            <label id="owner_building">楼栋数</label>
                            <label id="owner_room">房间号</label>
                        </address>
                        <address>
                            <strong>联系方式：</strong>
                            <a id="owner_phone"></a>
                        </address>
                    </div>

                    <div class="span8">
                        <button type="submit" class="btn blue" id="addTempParkingBill"><i class="icon-ok"></i>确认
                        </button>
                        <a href="tempParkingList.do">
                            <button type="button" class="btn">取消</button>
                        </a>
                    </div>
                </div>

                </s:form>
            </div>
        </div>
        <!-- END PAGE -->
    </div>
    <!-- END CONTAINER -->
    <!-- BEGIN FOOTER -->
        <%@include file="../global/globalFooter.jsp" %>
    <!-- END FOOTER -->
        <%@include file="../global/globalJS.jsp" %>
    <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script type="text/javascript" src="../../../global/js/select2.min.js"></script>
        <script type="text/javascript" src="../../../global/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../../../global/js/DT_bootstrap.min.js"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="../../../global/js/form-wizard.min.js"></script>
        <script src="../../../custom/js/tempParkingRegiste.js"></script>
        <script>
            jQuery(document).ready(function () {
                App.init();
                $(".page-sidebar-menu .title:contains('停车管理')").closest(
                        "li").addClass("active");
                TempParkingRegiste.init();
            });
        </script>
    <!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>