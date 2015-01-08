<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>登录 | 物业管理系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="../../global/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/style-metro.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/style.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/style-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../global/css/default.min.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="../../global/css/uniform.default.min.css" rel="stylesheet" type="text/css"/>

    <link href="../../custom/css/login.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../../global/image/favicon.ico"/>
</head>
<body class="login">
<div class="logo">
    <img src="" alt=""/>
</div>
<div class="content">
    <s:form class="form-vertical login-form" action="doLogin" method="POST">
        <h3 class="form-title">登录</h3>

        <div class="control-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名：</label>

            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-user"></i>
                    <s:textfield id="username" cssClass="m-wrap placeholder-no-fix" type="text"
                                 placeholder="请输入用户名" name="username"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label visible-ie8 visible-ie9">密码：</label>

            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-lock"></i>
                    <s:password id="password" cssClass="m-wrap placeholder-no-fix"
                                placeholder="请输入密码" name="password"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox">
                <s:checkbox id="rememberMe" name="rememberMe"/>记住我
            </label>
            <s:submit id="doLogin" cssClass="btn green pull-right" value="登录"/>
        </div>
        <div class="forget-password">
            <p>
                <a href="javascript:" class="" id="forget-password">忘记密码？</a>
            </p>
        </div>
    </s:form>
    <form class="form-vertical forget-form" action="login.do">
        <h3 class="">忘记密码？</h3>

        <p id="forget-text">我们将向您的信箱发送一个链接，您可通过该链接重置您的密码。</p>

        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-envelope"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="请输入您的邮箱" name="email"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn">
                返回
            </button>
            <button type="submit" class="btn green pull-right">
                提交
            </button>
        </div>
    </form>
</div>
<div class="copyright">
    2014 &copy; Pumpking. XMU NextGenComm.
</div>
<script src="../../global/js/jquery-2.1.3.js" type="text/javascript"></script>
<script src="../../global/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="../../global/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="../../global/js/jquery.validate.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="../../global/js/excanvas.min.js"></script>
<script src="../../global/js/respond.min.js"></script>
<![endif]-->

<script src="../../custom/js/app.js" type="text/javascript"></script>
<script src="../../custom/js/login.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        Login.init();
    });
</script>
</body>
</html>
