<%@ page import="com.pangio.ott.project.Task" %>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="ott.name.label" default="Name" />
	</label>
	<g:textField name="name" value="${taskInstance?.name}"/>
</div>

