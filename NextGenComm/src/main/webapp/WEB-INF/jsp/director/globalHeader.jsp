<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div class="header navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="/manager.do">
                <img src="../../../global/image/logo.png" alt="logo"/>
            </a>
            <a class="brand" href="/manager.do"><s:property value="#session['USER'].community.name"/></a>
            <a href="javascript:" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <img src="../../../global/image/menu-toggler.png" alt=""/>
            </a>
            <ul class="nav pull-right">
                <li class="dropdown" id="header_notification_bar">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="icon-warning-sign"></i>
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
                            <a href="">
                                <span class="photo"><img src="../../../global/image/avatar2.jpg" alt=""/></span>
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
                            <a href="">
                                <span class="photo"><img src="../../../global/image/avatar3.jpg" alt=""/></span>
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
                            <a href="">
                                <span class="photo"><img src="../../../global/image/avatar1.jpg" alt=""/></span>
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
                            <a href="">See all messages <i class="m-icon-swapright"></i></a>
                        </li>
                    </ul>
                </li>
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
                <li class="dropdown user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img alt="" src="../../../global/image/avatar1_small.jpg"/>
                        <span class="username"><s:property value="#session['USER'].name"/></span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href=""><i class="icon-user"></i> 我的主页</a></li>
                        <li><a href=""><i class="icon-envelope"></i> 社区通知(<span>3</span>)</a></li>
                        <li class="divider"></li>
                        <li><a href=""><i class="icon-lock"></i> 锁定屏幕</a></li>
                        <li><a href="/logout.do"><i class="icon-key"></i> 注销用户</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
