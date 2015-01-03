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
            <a href="/director.do">
                <i class="icon-home"></i>
                <span class="title">主页</span>
            </a>
        </li>

        <li class="">
            <a href="">
                <i class="icon-home"></i>
                <span class="title">物业管理</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li class="">
                    <a href="">
                        <i class="icon-list-ul"></i>
                        小区管理
                    </a>
                </li>
                <li class="">
                    <a href="/director/building.do">
                        <i class="icon-list-ul"></i>
                        楼宇管理
                    </a>
                </li>
                <li>
                    <a href="/director/room.do">
                        <i class="icon-question-sign"></i>
                        房间管理
                    </a>
                </li>
                <li>
                    <a href="/director/parkingLot.do">
                        <i class="icon-list-ul"></i>
                        停车场管理
                    </a>
                </li>
            </ul>
        </li>

        <li class="">
            <a href="/director/staff.do">
                <i class="icon-home"></i>
                <span class="title">员工管理</span>
            </a>
        </li>
    </ul>
</div>