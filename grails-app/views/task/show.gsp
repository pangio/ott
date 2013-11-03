<%@ page import="com.pangio.ott.project.Task" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'default.task.label', default: 'Task')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-task" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ul class="property-list task">
                <div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} ">
                    <label for="name">
                        <g:message code="default.name.label" default="Name" />
                    </label>
                    <g:textField name="name" value="${taskInstance?.name}" disabled=""/>
                </div>
			</ul>
		</div>
	</body>
</html>
