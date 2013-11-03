<%@ page import="com.pangio.ott.user.User" %>

<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'user.profile.label', default: 'Profile')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div id="show-user" class="content scaffold-show" role="main">
    <h1><g:message code="user.profile.label"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="form-horizontal row">
        <div class="span6">
            <g:if test="${userInstance?.name}">
                <div class="control-group">
                    <label class="control-label" for="name"><g:message code="default.name.label"/></label>
                    <div class="controls">
                        <input type="text" id="name" name="name" value="${userInstance?.name}" disabled>
                    </div>
                </div>
            </g:if>
            <g:if test="${userInstance?.lastName}">
                <div class="control-group">
                    <label class="control-label" for="lastname"><g:message code="default.last.name.label"/></label>
                    <div class="controls">
                        <input type="text" id="lastname" name="lastname" value="${userInstance?.lastName}" disabled>
                    </div>
                </div>
            </g:if>
            <g:if test="${userInstance?.email}">
                <div class="control-group">
                    <label class="control-label" for="email"><g:message code="default.email.label"/></label>
                    <div class="controls">
                        <input type="text" id="email" name="email" value="${userInstance?.email}" disabled>
                    </div>
                </div>
            </g:if>
            <g:if test="${userInstance?.username}">
                <div class="control-group">
                    <label class="control-label" for="username"><g:message code="default.username.label"/></label>
                    <div class="controls">
                        <input type="text" id="username" name="username" value="${userInstance?.username}" disabled>
                    </div>
                </div>
            </g:if>
        </div>
    </div>
</body>
</html>
