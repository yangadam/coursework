<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div class="page-sidebar nav-collapse collapse">
    <ul class="page-sidebar-menu">
        <li>
            <div class="sidebar-toggler hidden-phone"></div>
        </li>
        <li>
            <form class="sidebar-search">
                <div class="input-box">
                    <a href="javascript:" class="remove"></a>
                    <input type="text" placeholder="搜索..."/>
                    <input type="button" class="submit" value=" "/>
                </div>
            </form>
        </li>
        <li class="start">
            <a href="/guard.do">
                <i class="icon-home"></i>
                <span class="title">主页</span>
            </a>
        </li>

        <li class="">


        </li>

        <li class="">
            <a href="/guard/tempParkingList.do">
                <i class="icon-home"></i>
                <span class="title">停车管理</span>
            </a>
            <ul class="sub-menu">
                <li class="">
                    <a href="/guard/tempParkingList.do">
                        <i class="icon-list-ul"></i>
                        停车列表
                    </a>
                </li>
                <li class="">
                    <a href="">
                        <i class="icon-list-ul"></i>
                        停车缴费
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</div>