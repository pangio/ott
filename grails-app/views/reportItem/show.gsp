
<%@ page import="com.pangio.ott.project.ReportItem" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reportItem.label', default: 'ReportItem')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-reportItem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list reportItem">
				<g:if test="${reportItemInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="reportItem.comments.label" default="Comments" /></span>
						<span class="property-value" aria-labelledby="comments-label"><g:fieldValue bean="${reportItemInstance}" field="comments"/></span>
				</li>
				</g:if>
				<g:if test="${reportItemInstance?.hours}">
				<li class="fieldcontain">
					<span id="hours-label" class="property-label"><g:message code="reportItem.hours.label" default="Hours" /></span>
						<span class="property-value" aria-labelledby="hours-label"><g:fieldValue bean="${reportItemInstance}" field="hours"/></span>
				</li>
				</g:if>
				<g:if test="${reportItemInstance?.releaseDate}">
				<li class="fieldcontain">
					<span id="releaseDate-label" class="property-label"><g:message code="reportItem.releaseDate.label" default="Release Date" /></span>
						<span class="property-value" aria-labelledby="releaseDate-label"><g:formatDate date="${reportItemInstance?.releaseDate}" /></span>
				</li>
				</g:if>
				<g:if test="${reportItemInstance?.task}">
				<li class="fieldcontain">
					<span id="task-label" class="property-label"><g:message code="reportItem.task.label" default="Task" /></span>
						<span class="property-value" aria-labelledby="task-label"><g:link controller="task" action="show" id="${reportItemInstance?.task?.id}">${reportItemInstance?.task?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${reportItemInstance?.id}" />
					<g:link class="edit" action="edit" id="${reportItemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
