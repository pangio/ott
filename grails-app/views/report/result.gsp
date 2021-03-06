<%@ page import="com.pangio.ott.project.*" %>
<%@ page import="com.pangio.ott.user.*" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'ott.report.label', default: 'Report')}" />
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
                <g:sortableColumn property="user" title="${message(code: 'ott.user.label', default: 'User')}" />
                <g:sortableColumn property="project" title="${message(code: 'ott.project.label', default: 'Project')}" />
                <g:sortableColumn property="task" title="${message(code: 'ott.task.label', default: 'Task')}" />
                <g:sortableColumn property="hours" title="${message(code: 'ott.timesheet.label', default: 'Hours')}" />
                <g:sortableColumn property="extra" title="${message(code: 'ott.extra.label', default: 'Extra')}" />
                <g:sortableColumn property="date" title="${message(code: 'ott.date.label', default: 'Date')}" />
            </tr>
            </thead>
            <tbody>

            <g:each in="${result}" status="i" var="resultInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${fieldValue(bean: resultInstance, field: "user")}</td>
                    <td>${fieldValue(bean: resultInstance, field: "project")}</td>
                    <td>${fieldValue(bean: resultInstance, field: "task")}</td>
                    <td>${fieldValue(bean: resultInstance, field: "hours")}</td>
                    <td>${fieldValue(bean: resultInstance, field: "extra")}</td>
                    <td><g:formatDate date="${resultInstance.date}" type="date" style="SHORT"/></td>
                </tr>
            </g:each>
                <td><strong>Total</strong></td>
                <td></td>
                <td></td>
                <td><strong>${totalHours}</strong></td>
                <td><strong>${totalExtraHours}</strong></td>
                <td></td>
            </tbody>
        </table>
    </div>

    <div class="pagination">
        <g:paginate total="${total}" />
    </div>
    <g:link class="btn btn-info" action="buildBy${params.buildBy}"><g:message code="ott.build.new.report.label"/></g:link>
</div>
</body>
</html>
