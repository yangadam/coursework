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
            <a href="/clerk.do">
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
                    <a href="/clerk/building.do">
                        <i class="icon-list-ul"></i>
                        楼宇管理
                    </a>
                </li>
                <li>
                    <a href="/clerk/room.do">
                        <i class="icon-question-sign"></i>
                        房间管理
                    </a>
                </li>
            </ul>
        </li>
        <li class="">
            <a href="">
                <i class="icon-home"></i>
                <span class="title">业主管理</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li class="">
                    <a href="/clerk/owner.do">
                        <i class="icon-list-ul"></i>
                        业主列表
                    </a>
                </li>
                <li>
                    <a href="/clerk/checkin.do">
                        <i class="icon-question-sign"></i>
                        办理入住
                    </a>
                </li>
                <li>
                    <a href="/clerk/change.do">
                        <i class="icon-question-sign"></i>
                        业主变更
                    </a>
                </li>
            </ul>
        </li>
        <li class="">
            <a href="">
                <i class="icon-home"></i>
                <span class="title">费用管理</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li class="">
                    <a href="/clerk/input.do">
                        <i class="icon-list-ul"></i>
                        水电录入计算
                    </a>
                </li>
                <li>
                    <a href="/clerk/share.do">
                        <i class="icon-question-sign"></i>
                        业主公摊信息
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</div>