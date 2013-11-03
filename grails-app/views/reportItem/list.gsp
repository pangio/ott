<%@ page import="com.pangio.ott.project.ReportItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'reportItem.label', default: 'ReportItem')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="list-reportItem" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="comments" title="${message(code: 'reportItem.comments.label', default: 'Comments')}"/>
            <g:sortableColumn property="hours" title="${message(code: 'reportItem.hours.label', default: 'Hours')}"/>
            <g:sortableColumn property="releaseDate" title="${message(code: 'reportItem.releaseDate.label', default: 'Release Date')}"/>
            <th><g:message code="reportItem.task.label" default="Task"/></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${reportItemInstanceList}" status="i" var="reportItemInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:link action="show" id="${reportItemInstance.id}">${fieldValue(bean: reportItemInstance, field: "comments")}</g:link></td>
                <td>${fieldValue(bean: reportItemInstance, field: "hours")}</td>
                <td><g:formatDate date="${reportItemInstance.releaseDate}"/></td>
                <td>${fieldValue(bean: reportItemInstance, field: "task")}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <g:each in="${assignedProjects}" status="i" var="projectInstance">
        <div class="accordion" id="accordion-${projectInstance.name}">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-${projectInstance.id}"
                       href="#collapse-${projectInstance.id}">
                        <div class="message" role="status">${projectInstance.name}</div>
                    </a>
                </div>

                <div id="collapse-${projectInstance.id}" class="accordion-body collapse in">
                    <div class="accordion-inner">
                        <div class="message" role="status">${projectInstance.description}</div>
                    </div>
                </div>
            </div>
        </div>
    </g:each>
</div>
</body>
</html>
