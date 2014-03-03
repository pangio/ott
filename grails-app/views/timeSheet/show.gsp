
<%@ page import="com.pangio.ott.project.TimeSheet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ott.timesheet.label', default: 'TimeSheet')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-reportItem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list reportItem">
				<g:if test="${timeSheetInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="ott.comments.label" default="Comments" /></span>
						<span class="property-value" aria-labelledby="comments-label"><g:fieldValue bean="${timeSheetInstance}" field="comments"/></span>
				</li>
				</g:if>
				<g:if test="${timeSheetInstance?.hours}">
				<li class="fieldcontain">
					<span id="hours-label" class="property-label"><g:message code="ott.hours.label" default="Hours" /></span>
						<span class="property-value" aria-labelledby="hours-label"><g:fieldValue bean="${timeSheetInstance}" field="hours"/></span>
				</li>
				</g:if>
				<g:if test="${timeSheetInstance?.date}">
				<li class="fieldcontain">
					<span id="releaseDate-label" class="property-label"><g:message code="ott.date.label" default="Date" /></span>
						<span class="property-value" aria-labelledby="releaseDate-label"><g:formatDate date="${timeSheetInstance?.date}" /></span>
				</li>
				</g:if>
				<g:if test="${timeSheetInstance?.task}">
				<li class="fieldcontain">
					<span id="task-label" class="property-label"><g:message code="ott.task.label" default="Task" /></span>
						<span class="property-value" aria-labelledby="task-label"><g:link controller="task" action="show" id="${timeSheetInstance?.task?.id}">${timeSheetInstance?.task?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${timeSheetInstance?.id}" />
					<g:link class="edit" action="edit" id="${timeSheetInstance?.id}"><g:message code="ott.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'ott.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'ott.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
