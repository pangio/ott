<%@ page import="com.pangio.ott.user.User" %>

<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'default.user.label', default: 'user')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="create-user" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${userInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${userInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
            <g:form controller="user" action="save" >
                <g:render template="form"/>
                <div class="row">
                    <div class="span6">
                        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
                            <label for="password">
                                <g:message code="default.password.label" default="password" />
                            </label>
                            <g:passwordField name="password" value="${userInstance?.password}"/>
                        </div>
                    </div>
                </div>
                <g:actionSubmit name="save" class="btn btn-primary" value="save" />
            </g:form>
        </div>
	</body>
</html>
