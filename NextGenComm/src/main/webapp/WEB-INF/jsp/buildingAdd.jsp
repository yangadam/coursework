<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>社区管理</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="PumpKing" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="../../global/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="../../global/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="../../global/css/select2_metro.css"/>
    <link rel="stylesheet" href="../../global/css/DT_bootstrap.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="../../global/image/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="navbar-inner">
        <div class="container-fluid">
            <!-- BEGIN LOGO -->
            <a class="brand" href="/index.jsp">
                <img src="../../global/image/logo.png" alt="logo"/>
            </a>
            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a href="javascript:" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <img src="../../global/image/menu-toggler.png" alt=""/>
            </a>
            <!-- END RESPONSIVE MENU TOGGLER -->
            <!-- BEGIN TOP NAVIGATION MENU -->
            <ul class="nav pull-right">
                <!-- BEGIN NOTIFICATION DROPDOWN -->
                <li class="dropdown" id="header_notification_bar">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="icon-warning-sign"></i>
                        <!-- 此处显示消息通知数量 -->
                        <span class="badge">9</span>
                    </a>
                    <ul class="dropdown-menu extended notification">
                        <li>
                            <p>You have 14 new notifications</p>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-success"><i class="icon-plus"></i></span>
                                New user registered.
                                <span class="time">Just now</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-important"><i class="icon-bolt"></i></span>
                                Server #12 overloaded.
                                <span class="time">15 mins</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-warning"><i class="icon-bell-alt"></i></span>
                                Server #2 not respoding.
                                <span class="time">22 mins</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-info"><i class="icon-bullhorn"></i></span>
                                Application error.
                                <span class="time">40 mins</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-important"><i class="icon-bolt"></i></span>
                                Database overloaded 68%.
                                <span class="time">2 hrs</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-important"><i class="icon-bolt"></i></span>
                                2 user IP blocked.
                                <span class="time">5 hrs</span>
                            </a>
                        </li>
                        <li class="external">
                            <a href="#">See all notifications <i class="m-icon-swapright"></i></a>
                        </li>
                    </ul>
                </li>
                <!-- END NOTIFICATION DROPDOWN -->
                <!-- BEGIN INBOX DROPDOWN -->
                <li class="dropdown" id="header_inbox_bar">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="icon-envelope"></i>
                        <span class="badge">5</span>
                    </a>
                    <ul class="dropdown-menu extended inbox">
                        <li>
                            <p>You have 12 new messages</p>
                        </li>
                        <li>
                            <a href="inbox.html?a=view">
                                <span class="photo"><img src="../../global/image/avatar2.jpg" alt=""/></span>
								<span class="subject">
								<span class="from">Lisa Wong</span>
								<span class="time">Just Now</span>
								</span>
								<span class="message">
								Vivamus sed auctor nibh congue nibh. auctor nibh
								auctor nibh...
								</span>
                            </a>
                        </li>
                        <li>
                            <a href="inbox.html?a=view">
                                <span class="photo"><img src="../../global/image/avatar3.jpg" alt=""/></span>
								<span class="subject">
								<span class="from">Richard Doe</span>
								<span class="time">16 mins</span>
								</span>
								<span class="message">
								Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh
								auctor nibh...
								</span>
                            </a>
                        </li>
                        <li>
                            <a href="inbox.html?a=view">
                                <span class="photo"><img src="../../global/image/avatar1.jpg" alt=""/></span>
								<span class="subject">
								<span class="from">Bob Nilson</span>
								<span class="time">2 hrs</span>
								</span>
								<span class="message">
								Vivamus sed nibh auctor nibh congue nibh. auctor nibh
								auctor nibh...
								</span>
                            </a>
                        </li>
                        <li class="external">
                            <a href="inbox.html">See all messages <i class="m-icon-swapright"></i></a>
                        </li>
                    </ul>
                </li>
                <!-- END INBOX DROPDOWN -->
                <!-- BEGIN TODO DROPDOWN -->
                <li class="dropdown" id="header_task_bar">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="icon-tasks"></i>
                        <span class="badge">5</span>
                    </a>
                    <ul class="dropdown-menu extended tasks">
                        <li>
                            <p>You have 12 pending tasks</p>
                        </li>
                        <li>
                            <a href="#">
								<span class="task">
								<span class="desc">New release v1.2</span>
								<span class="percent">30%</span>
								</span>
								<span class="progress progress-success ">
								<span style="width: 30%;" class="bar"></span>
								</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
								<span class="task">
								<span class="desc">Application deployment</span>
								<span class="percent">65%</span>
								</span>
								<span class="progress progress-danger progress-striped active">
								<span style="width: 65%;" class="bar"></span>
								</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
								<span class="task">
								<span class="desc">Mobile app release</span>
								<span class="percent">98%</span>
								</span>
								<span class="progress progress-success">
								<span style="width: 98%;" class="bar"></span>
								</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
								<span class="task">
								<span class="desc">Database migration</span>
								<span class="percent">10%</span>
								</span>
								<span class="progress progress-warning progress-striped">
								<span style="width: 10%;" class="bar"></span>
								</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
								<span class="task">
								<span class="desc">Web server upgrade</span>
								<span class="percent">58%</span>
								</span>
								<span class="progress progress-info">
								<span style="width: 58%;" class="bar"></span>
								</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
								<span class="task">
								<span class="desc">Mobile development</span>
								<span class="percent">85%</span>
								</span>
								<span class="progress progress-success">
								<span style="width: 85%;" class="bar"></span>
								</span>
                            </a>
                        </li>
                        <li class="external">
                            <a href="#">See all tasks <i class="m-icon-swapright"></i></a>
                        </li>
                    </ul>
                </li>
                <!-- END TODO DROPDOWN -->
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <li class="dropdown user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img alt="" src="../../global/image/avatar1_small.jpg"/>
                        <span class="username">杨 猛猛</span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="extra_profile.html"><i class="icon-user"></i> 我的主页</a></li>
                        <!-- <li><a href="page_calendar.html"><i class="icon-calendar"></i> My Calendar</a></li> -->
                        <li><a href="inbox.html"><i class="icon-envelope"></i> 社区通知(<span>3</span>)</a></li>
                        <!-- <li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li> -->
                        <li class="divider"></li>
                        <li><a href="extra_lock.html"><i class="icon-lock"></i> 锁定屏幕</a></li>
                        <li><a href="login.jsp"><i class="icon-key"></i> 注销用户</a></li>
                    </ul>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
            </ul>
            <!-- END TOP NAVIGATION MENU -->
        </div>
    </div>
    <!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar nav-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu">
            <li>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler hidden-phone"></div>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            </li>
            <li>
                <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
                <form class="sidebar-search">
                    <div class="input-box">
                        <a href="javascript:" class="remove"></a>
                        <input type="text" placeholder="搜索..."/>
                        <input type="button" class="submit" value=" "/>
                    </div>
                </form>
                <!-- END RESPONSIVE QUICK SEARCH FORM -->
            </li>
            <li class="start ">
                <a href="../admin/home.do">
                    <i class="icon-home"></i>
                    <span class="title">业主服务</span>
                </a>
            </li>
            <li class="active">
                <a href="../admin/commList.do">
                    <i class="icon-home"></i>
                    <span class="title">社区管理</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li class="">
                <a href="javascript:">
                    <i class="icon-shopping-cart"></i>
                    <span class="title">在线缴费</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="/payment_wait.jsp">
                            <i class="icon-bookmark"></i>
                            待缴清单</a>
                    </li>
                    <li>
                        <a href="/payment_record.jsp">
                            <i class="icon-list-ul"></i>
                            缴费记录</a>
                    </li>
                    <li>
                        <a href="/payment_notice.jsp">
                            <i class="icon-question-sign"></i>
                            缴费须知</a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="/report_loss.jsp">
                    <i class="icon-key"></i>
                    <span class="title">门禁卡挂失</span>
                </a>
            </li>
            <li class="">
                <a href="/car_rent.jsp">
                    <i class="icon-umbrella"></i>
                    <span class="title">车位租用</span>
                </a>
            </li>
            <li class="">
                <a href="/report_repair.jsp">
                    <i class="icon-briefcase"></i>
                    <span class="title">水电报修</span>
                </a>
            </li>
            <li class="">
                <a href="/social_message.jsp">
                    <i class="icon-leaf"></i>
                    <span class="title">社区公告</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="/social_message.jsp">
                            <i class="icon-bell-alt"></i>
                            社区通知</a>
                    </li>
                    <li>
                        <a href="/social_news.jsp">
                            <i class="icon-globe"></i>
                            社区要闻</a>
                    </li>
                    <li>
                        <a href="/social_activity.jsp">
                            <i class="icon-coffee"></i>
                            社区活动</a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="/complaint.jsp">
                    <i class="icon-phone"></i>
                    <span class="title">投诉与建议</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="/complaint.jsp">
                            <i class="icon-group"></i>
                            员工概览</a>
                    </li>
                    <li>
                        <a href="/suggestion.jsp">
                            <i class="icon-comments"></i>
                            业主建言</a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="javascript:">
                    <i class="icon-user"></i>
                    <span class="title">个人主页</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="/owner_file.jsp">
                            <i class="icon-file"></i>
                            我的资料</a>
                    </li>
                    <li>
                        <a href="/owner_inbox.jsp">
                            <i class="icon-envelope"></i>
                            我的信箱</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
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
                        添加楼宇
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="../admin/home.do">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="../admin/commList.do">社区管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#"><s:property value="%{comm.name}"/>添加楼宇</a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12">


                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>添加新楼宇</div>
                            <div class="tools">
                                <a href="javascript:" class="collapse"></a>
                                <a href="javascript:" class="reload"></a>
                                <a href="javascript:" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <s:form action="doBuildingAdd" class="form-horizontal">
                                <div class="row-fluid">
                                    <div class="span12 ">
                                        <div class="control-group">
                                            <label class="control-label">楼宇名称</label>

                                            <div class="controls">
                                                <s:textfield name="buildingName" cssClass="m-wrap span5"
                                                             placeholder="请填写楼宇名"/>
                                            </div>
                                        </div>
                                    </div>

                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span12 ">
                                            <div class="control-group">
                                                <label class="control-label">所属社区</label>

                                                <div class="controls">
                                                    <s:textfield class="m-wrap span5" readonly="true"
                                                                 value="%{comm.id}"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span12 ">
                                            <div class="control-group">
                                                <label class="control-label">Building Number</label>

                                                <div class="controls">
                                                    <s:textfield name="buildingNo" cssClass="m-wrap span12"
                                                                 placeholder="请填写楼宇编号"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <!--/row-->
                                    <div class="form-actions">
                                        <s:submit cssClass="btn green" value="确认"/>
                                        <s:submit cssClass="btn" value="取消"/>
                                            <%--此处添加取消方法，添加弹窗后 用户确认后 可以是初始化表单 或者 返回社区列表页面--%>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>


                </div>
            </div>
            <!-- END PAGE CONTENT-->
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="footer">
    <div class="footer-inner">
        2014 &copy; PumpKing XMU. 智慧社区系统
    </div>
    <div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
    </div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="../../global/js/jquery-2.1.3.js" type="text/javascript"></script>
<script src="../../global/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="../../global/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="../../global/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="../../global/js/select.min.js"></script>
<script type="text/javascript" src="../../global/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../global/js/DT_bootstrap.js"></script>

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../../custom/js/app.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>