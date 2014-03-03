<%@ page import="com.pangio.ott.project.*" %>
<%@ page import="com.pangio.ott.user.*" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'ott.report.label', default: 'REPORT')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="list-report" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <g:sortableColumn property="name" title="${message(code: 'ott.name.label', default: 'Name')}" />
                <g:sortableColumn property="description" title="${message(code: 'ott.description.label', default: 'Description')}" />
            </tr>
            </thead>
            <tbody>

            <g:each in="${result}" status="i" var="projectInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td><g:link controller="project" action="show" id="${projectInstance.id}">${fieldValue(bean: projectInstance, field: "name")}</g:link></td>
                    <td>${fieldValue(bean: projectInstance, field: "description")}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="pagination">
        <g:paginate total="${resultTotal}" />
    </div>
    <g:link class="btn btn-info" action="build"><g:message code="ott.build.new.report.label"/></g:link>
</div>
</body>
</html>
