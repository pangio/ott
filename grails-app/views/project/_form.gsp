<%@ page import="com.pangio.ott.project.Project" %>

<r:require modules="chosen"/>
<r:script>
    $("#members").data("placeholder", "Select Members...").chosen();
    $("#tasks").data("placeholder", "Select Tasks...").chosen();
</r:script>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="default.name.label" default="Name" />
    </label>
    <g:textField name="name" value="${projectInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="default.description.label" default="Description" />
	</label>
	<g:textField name="description" value="${projectInstance?.description}"/>
</div>

%{--my users--}%
<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'members', 'error')} ">
    <label for="members">
        <g:message code="default.members.label" default="Members" />
    </label>
    <g:select oninvalid="members" name="members" from="${com.pangio.ott.user.User.list()}" multiple="multiple" optionKey="id" size="5" value="${projectInstance?.members*.id}" class="many-to-many chosen"/>
</div>

%{--my tasks--}%
<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'tasks', 'error')} ">
    <label for="tasks">
        <g:message code="default.tasks.label" default="Tasks" />
    </label>
    <g:select name="tasks" from="${com.pangio.ott.project.Task.list()}" multiple="multiple" optionKey="id" size="5" value="${projectInstance?.tasks*.id}" class="many-to-many"/>
</div>
