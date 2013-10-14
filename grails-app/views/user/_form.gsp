<%@ page import="com.pangio.ott.user.User" %>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="default.name.label" default="Name" />
    </label>
    <g:textField name="name" value="${userInstance?.name}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} ">
    <label for="lastName">
        <g:message code="default.last.name.label" default="Last Name" />
    </label>
    <g:textField name="lastName" value="${userInstance?.lastName}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
    <label for="email">
        <g:message code="default.email.label" default="Email" />
    </label>
    <g:textField name="email" value="${userInstance?.email}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} ">
    <label for="username">
        <g:message code="default.username.label" default="username" />
    </label>
    <g:textField name="username" value="${userInstance?.username}"/>
</div>

