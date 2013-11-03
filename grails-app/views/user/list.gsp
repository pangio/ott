<%@ page import="com.pangio.ott.user.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'default.user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-user" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>


            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <g:sortableColumn property="name" title="${message(code: 'default.name.label', default: 'Name')}" />
                        <g:sortableColumn property="lastName" title="${message(code: 'default.name.label', default: 'Last Name')}" />
                        <g:sortableColumn property="email" title="${message(code: 'default.name.label', default: 'Email')}" />
                    </tr>
                    </thead>
                    <tbody>

                    <g:each in="${userInstanceList}" status="i" var="userInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td>${fieldValue(bean: userInstance, field: "name")}</td>
                            <td>${fieldValue(bean: userInstance, field: "lastName")}</td>
                            <td>${fieldValue(bean: userInstance, field: "email")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>

            <div class="pagination">
                <g:paginate total="${userInstanceList.size()}" />
            </div>

			<div class="pagination">
				<g:paginate total="${userInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
