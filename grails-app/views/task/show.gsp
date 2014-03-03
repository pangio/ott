<%@ page import="com.pangio.ott.project.Task" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ott.task.label', default: 'Task')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-task" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<ul class="property-list task">
                <div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} ">
                    <label for="name">
                        <g:message code="ott.name.label" default="Name" />
                    </label>
                    <g:textField name="name" value="${taskInstance?.name}" disabled=""/>
                </div>
			</ul>
		</div>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${taskInstance?.id}" />
            <g:actionSubmit class="btn delete" action="delete" value="${message(code: 'ott.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'ott.button.delete.confirm.message', default: 'Are you sure?')}');" />

        </fieldset>
    </g:form>

    </body>
</html>
