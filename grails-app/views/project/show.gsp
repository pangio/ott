<%@ page import="com.pangio.ott.project.Project" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'default.project.label', default: 'Project')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-project" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<ol class="property-list project">

                <div class="form-group">
                    <label for="name" class="control-label"><g:message code="default.name.label" default="Name" /></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" placeholder="${projectInstance?.name}" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="control-label"><g:message code="default.description.label" default="Description" /></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="description" name="description" placeholder="${projectInstance?.description}" disabled>
                    </div>
                </div>

                <g:message code="default.users.label" default="Users" />
                <g:if test="${projectInstance?.members}">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <g:sortableColumn property="name" title="${message(code: 'default.name.label', default: 'Name')}" />
                            </tr>
                            </thead>
                            <tbody>
                            <g:each in="${projectInstance?.members}" status="i" var="member">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                    <td><g:link controller="user" action="publicProfile" id="${member.id}">${fieldValue(bean: member, field: "name")}</g:link></td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>
                </g:if>

                <g:message code="default.tasks.label" default="Tasks" />
                <g:if test="${projectInstance?.tasks}">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <g:sortableColumn property="name" title="${message(code: 'default.name.label', default: 'Name')}" />
                            </tr>
                            </thead>
                            <tbody>
                            <g:each in="${projectInstance?.tasks}" status="i" var="task">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                    <td><g:link controller="task" action="show" id="${task.id}">${fieldValue(bean: task, field: "name")}</g:link></td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>
                </g:if>

            </ol>
			<g:form>

				<fieldset class="buttons">
					<g:hiddenField name="id" value="${projectInstance?.id}" />
                    <g:link class="btn btn-warning" action="edit" params="[id:projectInstance?.id]"><g:message code="default.button.edit.label"/></g:link>
                    <g:actionSubmit class="btn delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />


				</fieldset>
			</g:form>
		</div>
	</body>
</html>
