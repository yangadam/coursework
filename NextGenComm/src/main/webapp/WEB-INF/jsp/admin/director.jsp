<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8"/>
    <title>小区管理 | 物业管理系统</title>
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
                        社区管理
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/admin.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <span>人员管理</span>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/admin/director.do">物业主任</a>
                            <i class="icon-angle-right"></i>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="portlet box blue">
                    <div class="portlet-title">
                        <div class="caption"><i class="icon-edit"></i>物业主任列表</div>
                        <div class="tools">
                            <a href="javascript:" class="reload"></a>
                            <a href="javascript:" class="collapse"></a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="clearfix">
                            <div class="btn-group">
                                <button id="sample_editable_1_new" class="btn green">
                                    添加物业主任 <i class="icon-plus"></i>
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
                                <th>编号</th>
                                <th>用户名</th>
                                <th>姓名</th>
                                <th>小区</th>
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
        <div id="add-mng" class="portlet box blue hide">
            <div class="portlet-title">
                <div class="caption"><i class="icon-reorder"></i>添加物业主任</div>
                <div class="tools">
                    <a href="javascript:" class="collapse"></a>
                </div>
            </div>
            <div class="portlet-body form">
                <s:form action="addMng" class="form-horizontal">
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
                        <label class="control-label">小区</label>

                        <div class="controls">
                            <label>
                                <s:select id="select" name="commName" class="span6 m-wrap" data-placeholder="请选择"
                                          tabindex="1" list="{'请选择',''}">
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
<%@include file="../global/globalFooter.jsp" %>
<%@include file="../global/globalJS.jsp" %>
<script type="text/javascript" src="../../../global/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../../../global/js/DT_bootstrap.min.js"></script>
<script type="text/javascript" src="../../../global/js/ui-modals.min.js"></script>
<script type="text/javascript" src="../../../global/js/bootstrap-modal.min.js"></script>
<script type="text/javascript" src="../../../global/js/bootstrap-modalmanager.min.js"></script>
<script src="../../../custom/js/dir.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        $(".page-sidebar-menu .title:contains('人员管理')").closest("li").addClass("active");
        $(".page-sidebar-menu .sub-menu a:contains('物业主任')").closest("li").addClass("active");
        TableEditable.init();
        UIModals.init();
    });
</script>
</body>
</html>
