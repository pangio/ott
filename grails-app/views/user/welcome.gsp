<%@ page import="com.pangio.ott.user.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'default.user.label', default: 'User')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<h1><g:message code="welcome.user.label" args="[entityName]"/></h1>
<div>
    <p>Welcome "${userInstance?.name}",</p>
    <p>Your registration was successful.</p>
    <p>Please log in <g:link controller="login"
                             action="auth">HERE</g:link> with your username: "${userInstance?.username}"</p>

    <p>Thanks</p>
</div>
</body>
</html>
