<%@ page import="com.pangio.ott.project.*" %>
<%@ page import="com.pangio.ott.user.*" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'default.report.label', default: 'REPORT')}" />
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
                <g:sortableColumn property="user" title="${message(code: 'default.user.label', default: 'User')}" />
                <g:sortableColumn property="project" title="${message(code: 'default.project.label', default: 'Project')}" />
                <g:sortableColumn property="task" title="${message(code: 'default.task.label', default: 'Task')}" />
                <g:sortableColumn property="hours" title="${message(code: 'default.hours.label', default: 'Hours')}" />
                <g:sortableColumn property="releaseDAte" title="${message(code: 'default.release.date.label', default: 'Date')}" />
            </tr>
            </thead>
            <tbody>

            <g:each in="${result}" status="i" var="resultInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${fieldValue(bean: resultInstance, field: "user")}</td>
                    <td>${fieldValue(bean: resultInstance, field: "project")}</td>
                    <td>${fieldValue(bean: resultInstance, field: "task")}</td>
                    <td>${fieldValue(bean: resultInstance, field: "hours")}</td>
                    <td>${fieldValue(bean: resultInstance, field: "releaseDate")}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="pagination">
        <g:paginate total="${resultTotal}" />
    </div>
    <g:link class="btn btn-info" action="build"><g:message code="build.new.report.label"/></g:link>
</div>
</body>
</html>