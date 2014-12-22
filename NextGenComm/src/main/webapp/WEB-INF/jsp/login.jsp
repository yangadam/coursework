<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>物业管理系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="../../static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../static/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../static/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../static/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="../../static/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="../../static/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="../../static/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="../../static/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="../../static/css/login.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="../../static/image/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
    <img src="" alt=""/>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="form-vertical login-form" action="dologin.do" method="post">
        <h3 class="form-title">登录</h3>

        <div class="control-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名：</label>

            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-user"></i>
                    <s:textfield cssClass="m-wrap placeholder-no-fix" type="text" placeholder="请输入用户名" name="username"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label visible-ie8 visible-ie9">密码：</label>

            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-lock"></i>
                    <s:textfield cssClass="m-wrap placeholder-no-fix" type="password" placeholder="请输入密码"
                                 name="password"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox">
                <s:checkbox name="rememberMe" value="rememberMe" fieldValue="false"/>记住密码 
            </label>
            <s:submit cssClass="btn green pull-right" value="登录"/>
            <!--  <i class="m-icon-swapright m-icon-white"></i> -->

        </div>
        <div class="forget-password">
            <p>
                <a href="javascript:" class="" id="forget-password">忘记密码？</a>
            </p>
        </div>
    </form>
    <!-- END LOGIN FORM -->
    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="form-vertical forget-form" action="login.do">
        <h3 class="">忘记密码？</h3>

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
                <!-- <i class="m-icon-swapleft"></i> --> 返回
            </button>
            <button type="submit" class="btn green pull-right">
                提交 <!-- <i class="m-icon-swapright m-icon-white"></i> -->
            </button>
        </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
    2014 &copy; Pumpking. XMU NextGenComm.
</div>
<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="../../static/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="../../static/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="../../static/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="../../static/js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="../../static/js/excanvas.min.js"></script>
<script src="../../static/js/respond.min.js"></script>
<![endif]-->
<script src="../../static/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../../static/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../../static/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="../../static/js/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="../../static/js/jquery.validate.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../../static/js/app.js" type="text/javascript"></script>
<script src="../../static/js/login.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function () {
        App.init();
        Login.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>