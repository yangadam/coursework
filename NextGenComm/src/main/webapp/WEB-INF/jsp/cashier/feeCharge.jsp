<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]> <html lang="en" class="no-js"> <![endif]-->
<html>
<head>
    <meta charset="utf-8"/>
    <title>办理入住 | 物业管理系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>

    <link href="../../../custom/css/feeCharge.css" rel="stylesheet" type="text/css"/>
    <%@include file="../global/globalCSS.jsp" %>
    <link rel="shortcut icon" href="../../../global/image/favicon.ico"/>
    <link href="../../../global/css/select2_metro.css" rel="stylesheet" type="text/css"/>

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
                        费用收取
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="cashier.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <i class="icon-home"></i>
                            <a href="">费用收取</a>
                            <i class="icon-angle-right"></i>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>缴费列表</div>
                        </div>
                        <div class="portlet-body form">
                            <div class="control-group">
                                <div class="controls">
                                    <input type="hidden" id="owner" class="span2 select2">
                                </div>
                            </div>


                            <table class="table table-striped table-bordered table-hover" id="billItems">
                                <thead>
                                <tr>
                                    <th width="40px">序号</th>
                                    <th>房间号</th>
                                    <th>缴费项</th>
                                    <th>费用明细</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <hr/>
                            <div class="row-fluid">
                                <div class="span12">
                                    <div class="span7"></div>
                                    <div class="span3" style="text-align:center">
                                        <span class="text bold" style="font-size: larger;">应缴费用：￥12.00<s:property
                                            value="totalAmount"/>元</span>
                                    </div>
                                    <div class="span2">
                                        <button class="btn blue" style="vertical-align: middle">去结算<i class="m-icon-swapright m-icon-white"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>

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
<script src="../../../custom/js/feeCharge.js" type="text/javascript"></script>
<script type="text/javascript" src="../../../global/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../../global/js/DT_bootstrap.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        $(".page-sidebar-menu .title:contains('费用收取')").closest("li").addClass("active");
        FeeCharge.init();
    });
</script>
</body>
</html>