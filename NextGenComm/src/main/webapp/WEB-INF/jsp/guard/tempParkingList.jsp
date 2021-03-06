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

            <div class="row-fluid ">
                <div class="span12">

                    <div class="input-append">
                        <s:textfield id="license" type="text" class="m-wrap" placeholder="请输入车牌号"/>
                        <%--<button type="button" class="btn green btn-subscribe" ><span>车辆登记</span></button>--%>
                        <button onclick="tempRegister()" type="submit" class="btn green">
                            车辆登记
                        </button>
                        <label id="isTemp"></label>
                    </div>

                    <!-- BEGIN TAB PORTLET-->
                    <div class="portlet box blue tabbable">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>临时停车</div>
                        </div>
                        <div class="portlet-body">
                            <div class="tabbable portlet-tabs">
                                <ul class="nav nav-tabs">
                                    <li><a href="#portlet_tab2" data-toggle="tab">已缴单</a></li>
                                    <li class="active"><a href="#portlet_tab1" data-toggle="tab">未缴单</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="portlet_tab1">
                                        <div class="portlet-body">
                                            <div class="clearfix">
                                                <table class="table table-striped table-hover table-bordered"
                                                       id="sample_editable_1">
                                                    <thead>
                                                    <tr>
                                                        <th>车牌号</th>
                                                        <th>挂名业主</th>
                                                        <th>进入时间</th>
                                                        <th>离开</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="portlet_tab2">
                                        <div class="portlet-body">
                                            <div class="clearfix">
                                                <table class="table table-striped table-hover table-bordered"
                                                       id="sample_editable_2">
                                                    <thead>
                                                    <tr>
                                                        <th>车牌号</th>
                                                        <th>挂名业主</th>
                                                        <th>进入时间</th>
                                                        <th>离开时间</th>
                                                        <th>费用</th>
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
                    <!-- END TAB PORTLET-->
                </div>
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
    <script src="../../../custom/js/app.js"></script>
        <script src="../../../custom/js/tempChargedParking.js"></script>
        <script src="../../../custom/js/tempUnchargedParking.js"></script>
    <script>
        $.extend({
            StandardPost: function (url, args) {
                var form = $("<form method='post'></form>"),
                        input;
                form.attr({"action": url});
                $.each(args, function (key, value) {
                    input = $("<input type='hidden'>");
                    input.attr({"name": key});
                    input.val(value);
                    form.append(input);
                });
                form.submit();
            }
        });
        jQuery(document).ready(function () {
            App.init();
            $(".page-sidebar-menu .title:contains('停车管理')").closest(
                    "li").addClass("active");
            $(".page-sidebar-menu .sub-menu a:contains('停车列表')").closest(
                    "li").addClass("active");
            FinishedParkBillTale.init();
            UnfinishedParkBillTale.init();
        });
        function tempRegister() {
            if ($("#license").val() == "") {
                return;
            }
            var license_u = $("#license").val();
            license_u = encodeURI(encodeURI(license_u));
            $.getJSON("/tempParkingRegister.do?license=" + license_u, function (data) {
                if (data["TYPE"] == "RENT") {
                    $("#isTemp").text($("#license").val() + "为小区内车辆");
                    $("#license").val("");
                }
                else if (data["TYPE"] == "TEMP") {
                    $.StandardPost('toTempParkingRegiste.do', {license: $("#license").val()});
                }
            });
        }
    </script>
    <!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->
</html>