<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
        <li class="">
            <a href="../admin/commList.do">
                <i class="icon-home"></i>
                <span class="title">社区管理</span>
            </a>
        </li>
        <li class="">
            <a href="../guard/tempParkingList.do">
                <i class="icon-home"></i>
                <span class="title">停车场管理</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li>
                    <a href="../guard/tempParkingList.do">
                        <i class="icon-bookmark"></i>
                        停车列表</a>
                </li>
                <li>
                    <a href="../guard/tempParkingRegiste.do">
                        <i class="icon-list-ul"></i>
                        车辆登记</a>
                </li>
            </ul>
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