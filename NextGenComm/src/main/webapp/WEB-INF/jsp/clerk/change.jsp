<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]> <html lang="en" class="no-js"> <![endif]-->
<html>
<head>
    <meta charset="utf-8"/>
    <title>业主变更 | 物业管理系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>
    <%@include file="../global/globalCSS.jsp" %>
    <link href="../../../global/css/select2_metro.min.css" rel="stylesheet" type="text/css"/>
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
                        业主变更
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/clerk.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <span>业主管理</span>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/clerk/change.do">业主变更</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box purple">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>业主变更</div>
                        </div>
                        <div class="portlet-body form">
                            <form action="#" id="form_sample_1" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label" for="build">楼宇号</label>

                                    <div class="controls">
                                        <select id="build" class="span6 m-wrap" name="category">
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="floor">楼层号</label>

                                    <div class="controls">
                                        <select id="floor" class="span6 m-wrap" name="category">
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="room">房间号</label>

                                    <div class="controls">
                                        <select id="room" class="span6 m-wrap" name="category">
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="area">面积</label>

                                    <div class="controls">
                                        <input type="text" id="area" class="span6 m-wrap" readonly/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="curOwner">当前业主</label>

                                    <div class="controls">
                                        <input type="text" id="curOwner" class="span6 select2" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="owner">新入住业主</label>

                                    <div class="controls">
                                        <%--<select id="owner" class="span6 m-wrap" name="category">--%>
                                        <%--</select>--%>
                                        <input type="hidden" id="owner" class="span6 select2">
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <input type="submit" class="btn green" id="change" value="确认更改"/>
                                </div>
                            </form>
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
<script src="../../../custom/js/change.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        $(".page-sidebar-menu .title:contains('业主管理')").closest("li").addClass("active");
        $(".page-sidebar-menu .sub-menu a:contains('业主变更')").closest("li").addClass("active");
        Change.init();
    });
</script>
</body>
</html>