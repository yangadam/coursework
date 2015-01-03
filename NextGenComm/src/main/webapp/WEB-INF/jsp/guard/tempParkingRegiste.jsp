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

                        <div class="control-group">
                            <label class="control-label">车牌号</label>

                            <div class="controls">
                                <input type="text" class="m-wrap span7"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">业主信息查询</label>

                            <div class="controls">
                                <select class="small m-wrap" tabindex="1">
                                    <option value="Category 1">海韵13</option>
                                    <option value="Category 2">海韵4</option>
                                    <option value="Category 3">海韵2</option>
                                    <option value="Category 4">海韵9</option>
                                </select>
                                <select class="small m-wrap" tabindex="1">
                                    <option value="Category 1">1</option>
                                    <option value="Category 2">2</option>
                                    <option value="Category 3">3</option>
                                    <option value="Category 4">4</option>
                                </select>
                                <select class="small m-wrap" tabindex="1">
                                    <option value="Category 1">01</option>
                                    <option value="Category 2">02</option>
                                    <option value="Category 3">03</option>
                                    <option value="Category 4">04</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <div class="input-append">
                                    <input type="text" class="m-wrap" placeholder="请输入业主姓名">
                                        <%--<button type="button" class="btn green btn-subscribe" ><span>车辆登记</span></button>--%>
                                    <a href="#" class="btn green">
                                        查询</i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- END FORM-->
                </div>
                <div class="span6">
                    <div class="space20"></div>
                    <h3 class="form-section">挂名业主信息</h3>

                    <div class="well">
                        <h4>陆垚杰</h4>
                        <address>
                            <strong>厦门大学</strong><br>
                            海韵13<br>
                            #404<br>
                            <abbr title="Phone">家庭电话:</abbr> (234) 145-1810
                        </address>
                        <address>
                            <strong>联系方式：</strong>
                            <a href="mailto:#">18805929999</a>
                        </address>
                    </div>

                    <div class="span8">
                        <button type="submit" class="btn blue"><i class="icon-ok"></i>确认</button>
                        <button type="button" class="btn">取消</button>
                    </div>
                </div>

                </s:form>
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
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="../../../custom/js/app.js"></script>
    <script src="../../../global/js/form-wizard.js"></script>
        <script>
            jQuery(document).ready(function () {
                App.init();
                $(".page-sidebar-menu .title:contains('停车管理')").closest(
                        "li").addClass("active");
            });
        </script>
    <!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>