<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]> <html lang="en" class="no-js"> <![endif]-->
<html>
<head>
    <meta charset="utf-8"/>
    <title>员工管理 | 物业管理系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>

    <%@include file="globalCSS.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../../global/css/select2_metro.css"/>
    <link rel="stylesheet" href="../../../global/css/DT_bootstrap.css"/>
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
                        员工管理
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/director.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/director/staff.do">员工管理</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-edit"></i>员工列表</div>
                            <div class="tools">
                                <a href="javascript:" class="reload"></a>
                                <a href="javascript:" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="clearfix">
                                <div class="control-group">
                                    <div class="btn-group">
                                        <button id="sample_editable_1_new" class="btn green">
                                            添加员工 <i class="icon-plus"></i>
                                        </button>
                                    </div>
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
                                        <th>序号</th>
                                        <th>用户名</th>
                                        <th>姓名</th>
                                        <th>职位</th>
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
                </div>
                <div id="add-staff" class="portlet box blue hide">
                    <div class="portlet-title">
                        <div class="caption"><i class="icon-reorder"></i>添加员工</div>
                        <div class="tools">
                            <a href="javascript:" class="collapse"></a>
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <s:form action="addStaff" class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">用户名</label>

                                <div class="controls">
                                    <s:textfield name="username" value="" class="span6 m-wrap"/>
                                    <span class="help-inline"></span>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">密码</label>

                                <div class="controls">
                                    <s:password name="password" value="" class="span6 m-wrap"/>
                                    <span class="help-inline"></span>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">姓名</label>

                                <div class="controls">
                                    <s:textfield name="name" value="" class="span6 m-wrap"/>
                                    <span class="help-inline"></span>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">职位</label>

                                <div class="controls">
                                    <label>
                                        <s:bean name="cn.edu.xmu.comm.commons.utils.StaffTypeUtils" id="stu"/>
                                        <s:select id="select" name="position" class="span6 m-wrap"
                                                  data-placeholder="请选择"
                                                  tabindex="1" list="%{#stu.staffType}">
                                        </s:select>
                                    </label>
                                </div>
                            </div>
                            <div class="form-actions">
                                <s:submit class="btn blue" value="保存"/>
                                <button type="button" class="btn">重置</button>
                            </div>
                        </s:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="globalFooter.jsp" %>
    <%@include file="globalJS.jsp" %>
    <script type="text/javascript" src="../../../global/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="../../../global/js/DT_bootstrap.js"></script>
    <script type="text/javascript" src="../../../global/js/ui-modals.js"></script>
    <script type="text/javascript" src="../../../global/js/bootstrap-modal.js"></script>
    <script type="text/javascript" src="../../../global/js/bootstrap-modalmanager.js"></script>
    <script src="../../../custom/js/staff.js" type="text/javascript"></script>
    <script>
        jQuery(document).ready(function () {
            App.init();
            $(".page-sidebar-menu .title:contains('员工管理')").closest("li").addClass("active");
            TableEditable.init();
            UIModals.init();
        });
    </script>
</body>
</html>