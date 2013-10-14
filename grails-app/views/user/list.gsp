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
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="email" title="${message(code: 'default.email.label', default: 'Email')}" />
						<g:sortableColumn property="lastName" title="${message(code: 'default.last.name.label', default: 'Last Name')}" />
						<g:sortableColumn property="name" title="${message(code: 'default.name.label', default: 'Name')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${userInstanceList}" status="i" var="userInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="profile" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "email")}</g:link></td>
						<td>${fieldValue(bean: userInstance, field: "lastName")}</td>
						<td>${fieldValue(bean: userInstance, field: "name")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
