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
                        小区管理
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/director.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <span>物业管理</span>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/director/community.do">小区管理</a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span12 ">
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>小区设置</div>
                            <div class="tools">
                                <a href="javascript:" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <s:form action="commInfoUpdate" class="form-horizontal">
                                <h3 class="form-section">小区信息</h3>

                                <div class="row-fluid">
                                    <div class="span1"></div>
                                    <div class="span5 ">
                                        <div class="control-group">
                                            <label class="control-label">小区名称：</label>

                                            <div class="controls span6">
                                                <s:textfield name="commName" cssClass="m-wrap span12"
                                                             value="%{commName}" readonly="true"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row-fluid">
                                    <div class="span1"></div>
                                    <div class="span5 ">
                                        <div class="control-group">
                                            <label class="control-label">垃圾费(元)：</label>

                                            <div class="controls span6">
                                                <s:textfield name="garbageFee" cssClass="m-wrap span12"
                                                             value="%{garbageFee}" placeholder="请输入垃圾费"/>
                                            </div>
                                        </div>
                                    </div>
                                    <!--/span-->
                                    <div class="span5 ">
                                        <div class="control-group">
                                            <label class="control-label">收费方式：</label>

                                            <div class="controls">
                                                <s:bean name="cn.edu.xmu.comm.commons.utils.EnumUtils" id="stu"/>
                                                <s:select name="garbageFeeType" class="span6 select2_category"
                                                          data-placeholder="请选择收费方式"
                                                          tabindex="1" list="%{#stu.garbageFeeType}">
                                                </s:select>
                                            </div>
                                        </div>
                                    </div>
                                    <!--/span-->
                                </div>
                                <!--/row-->
                                <div class="row-fluid">
                                    <div class="span1"></div>
                                    <div class="span5 ">
                                        <div class="control-group">
                                            <label class="control-label">管理费(元)：</label>

                                            <div class="controls span6">
                                                <s:textfield name="manageFee" cssClass="m-wrap span12"
                                                             value="%{manageFee}" placeholder="请输入管理费"/>
                                            </div>
                                        </div>
                                    </div>
                                    <!--/span-->
                                    <div class="span5 ">
                                        <div class="control-group">
                                            <label class="control-label">收费类型：</label>

                                            <div class="controls">
                                                <s:bean name="cn.edu.xmu.comm.commons.utils.EnumUtils" id="stu"/>
                                                <s:select name="manageFeeType" class="span6 select2_category"
                                                          data-placeholder="请选择收费方式"
                                                          tabindex="1" list="%{#stu.manageFeeType}">
                                                </s:select>
                                            </div>
                                        </div>
                                    </div>
                                    <!--/span-->
                                </div>
                                <!--/row-->
                                <div class="row-fluid" style="margin-bottom: 20px;">
                                    <div class="span1"></div>
                                    <div class="span5 ">
                                        <div class="control-group">
                                            <label class="control-label">滞纳金利率：</label>

                                            <div class="controls span6">
                                                <s:textfield name="overDueFeeRate" cssClass="m-wrap span12"
                                                             value="%{overDueFeeRate}" placeholder="请输入滞纳金利率"/>
                                            </div>
                                        </div>
                                    </div>
                                    <!--/span-->
                                    <div class="span5 ">
                                        <div class="control-group">
                                            <label class="control-label">收费类型：</label>

                                            <div class="controls">
                                                <s:bean name="cn.edu.xmu.comm.commons.utils.EnumUtils" id="stu"/>
                                                <s:select name="overDueFeeType" class="span6 select2_category"
                                                          data-placeholder="请选择收费方式"
                                                          tabindex="1" list="%{#stu.overDueFeeType}">
                                                </s:select>
                                            </div>
                                        </div>
                                    </div>
                                    <!--/span-->
                                </div>
                                <div class="form-actions">
                                    <button type="submit" class="btn blue"><i class="icon-ok"></i> 保存</button>
                                    <button type="button" class="btn">取消</button>
                                </div>
                            </s:form>
                            <!-- END FORM-->
                        </div>
                    </div>
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
<script src="../../../custom/js/comm.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        $(".page-sidebar-menu .title:contains('物业管理')").closest("li").addClass("active");
        $(".page-sidebar-menu .title:contains('物业管理')").parent().find(".arrow").addClass("open");
        $(".page-sidebar-menu .sub-menu a:contains('小区管理')").closest("li").addClass("active");
        TableEditable.init();
        UIModals.init();
    });
</script>
</body>
</html>