<%@ page import="com.pangio.ott.project.Task" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'default.task.label', default: 'Task')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-task" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <g:sortableColumn property="name" title="${message(code: 'default.name.label', default: 'Name')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${taskInstanceList}" status="i" var="taskInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td><g:link action="show" id="${taskInstance.id}">${fieldValue(bean: taskInstance, field: "name")}</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
			<div class="pagination">
				<g:paginate total="${taskInstanceTotal}" />
			</div>
            <g:link class="btn btn-info" action="create"><g:message code="default.new.task.label"/></g:link>
        </div>
	</body>
</html>
