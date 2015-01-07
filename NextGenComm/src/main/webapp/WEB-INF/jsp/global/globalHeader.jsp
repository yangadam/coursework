<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div class="header navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="">
                <img src="../../../global/image/logo.png" alt="logo"/>
            </a>
            <a class="brand" href="">
                <s:property value="#session['community'].name"/>
            </a>
            <a href="javascript:" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <img src="../../../global/image/menu-toggler.png" alt=""/>
            </a>
            <ul class="nav pull-right">
                <li class="dropdown user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img alt="" src="../../../global/image/avatar1_small.jpg"/>
                        <span class="username"><s:property value="#session['user'].name"/></span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/generateValue.do"><i class="icon-key"></i> 测试</a></li>
                        <li><a href="/logout.do"><i class="icon-key"></i> 注销用户</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
