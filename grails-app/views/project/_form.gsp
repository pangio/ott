<%@ page import="com.pangio.ott.project.Project" %>



<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="project.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${projectInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'members', 'error')} ">
	<label for="members">
		<g:message code="project.members.label" default="Members" />
		
	</label>
	<g:select name="members" from="${com.pangio.ott.user.User.list()}" multiple="multiple" optionKey="id" size="5" value="${projectInstance?.members*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="project.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${projectInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'tasks', 'error')} ">
	<label for="tasks">
		<g:message code="project.tasks.label" default="Tasks" />
		
	</label>
	<g:select name="tasks" from="${com.pangio.ott.project.Task.list()}" multiple="multiple" optionKey="id" size="5" value="${projectInstance?.tasks*.id}" class="many-to-many"/>
</div>

