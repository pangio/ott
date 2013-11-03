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
			<ul class="property-list task">
                <div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} ">
                    <label for="name">
                        <g:message code="default.name.label" default="Name" />
                    </label>
                    <g:textField name="name" value="${taskInstance?.name}" disabled=""/>
                </div>
			</ul>
		</div>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${taskInstance?.id}" />
            <g:link class="btn btn-warning" action="edit" params="[id:taskInstance?.id]"><g:message code="default.button.edit.label"/></g:link>
            <g:actionSubmit class="btn delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />

        </fieldset>
    </g:form>

    </body>
</html>
