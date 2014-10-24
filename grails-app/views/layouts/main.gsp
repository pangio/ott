<%@ page import="com.pangio.ott.user.User" %>
<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Open Time Tracker | <g:layoutTitle/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--[if IE]>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap/jquery.ui.1.8.16.ie.css"/>
    <link rel="stylesheet" type="text/css" href="/css/timetracker.css"/>
    <![endif]-->
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">

    <g:javascript library="jquery" plugin="jquery"/>
    <r:require module="bootstrap"/>
    <r:require module="chosen"/>
    <r:require module="timepicker"/>
    <r:require module="application"/>

    <r:script>
        $(document).ready(function () {
            $('.nav li.dropdown').hover(function () {
                $(this).addClass('open');
            }, function () {
                $(this).removeClass('open');
            });
        });
    </r:script>

    <g:layoutHead/>
    <r:layoutResources/>

</head>

<body>

<div class="container">
    <div class="masthead">
        <h3 class="muted">OpenTimeTracker</h3>

        <div class="navbar">
            <div class="navbar-inner">
                <div class="container">
                    <ul class="nav">

                        <div class="dropdown">
                            <!-- Link or button to toggle dropdown -->
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                <li><a tabindex="-1" href="#">Action</a></li>
                                <li><a tabindex="-1" href="#">Another action</a></li>
                                <li><a tabindex="-1" href="#">Something else here</a></li>
                                <li class="divider"></li>
                                <li><a tabindex="-1" href="#">Separated link</a></li>
                            </ul>
                        </div>

                        <li><g:link uri="/" class="brand"><i class="icon-home icon-white"></i></g:link></li>
                        <li><g:link controller="home" action="home"><g:message code="default.home.label"/></g:link></li>

                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <li><g:link controller="task" action="list">Tasks</g:link></li>
                            <li><g:link controller="project" action="list">Projects</g:link></li>
                            <li><g:link controller="user" action="list">Users</g:link></li>
                            <ul class="nav">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown">Reports <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><g:link controller="report" action="buildByUser">User</g:link></li>
                                        <li><g:link controller="report" action="buildByProject">Project</g:link></li>
                                        <li><g:link controller="report"
                                                    action="buildCriticalProjects">Critical Project</g:link></li>
                                    </ul>
                                </li>
                            </ul>
                        </sec:ifAnyGranted>
                        <li><g:link controller="timeSheet" action="list">Timesheet</g:link></li>
                        <li><g:link controller="home" action="about">About</g:link></li>
                    </ul>

                    <div class="navbar-text pull-right">
                        <sec:ifLoggedIn>
                            <p class="navbar-text pull-right">
                                %{--<ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dLabel">--}%
                            <ul class="nav pull-right">
                                <li class="dropdown pull-right"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <g:message code="ott.welcome.user.label"/> <sec:loggedInUserInfo field='username'/>
                                    <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><g:link controller="user" action="profile"><g:message code="ott.profile.label"/></g:link></li>
                                        <li><g:link controller="logout" action="index"><g:message code="ott.logout.label"/></g:link></li>
                                    </ul>
                                </li>
                            </ul>
                            </p>

                        </sec:ifLoggedIn>
                        <sec:ifNotLoggedIn>
                            <p class="navbar-text pull-right">
                                <g:link controller="login" action="index"><g:message code="ott.login.label"/></g:link>
                                <g:link controller="user" action="register"><g:message code="ott.register.label"/></g:link>
                            </p>
                        </sec:ifNotLoggedIn>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<section class="container">
    <g:if test="${flash.message}">
        <div class="alert alert-info">${flash.message}</div>
        ${flash.clear()}
    </g:if>
    <g:elseif test="${flash.error}">
        <div class="alert alert-error">${flash.error}</div>
        ${flash.clear()}
    </g:elseif>
    <g:elseif test="${flash.success}">
        <div class="alert alert-success">${flash.success}</div>
        ${flash.clear()}
    </g:elseif>
    <g:layoutBody/>
</section>

<footer></footer>

<r:layoutResources/>
</body>
</html>