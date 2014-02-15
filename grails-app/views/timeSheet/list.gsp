<%@ page import="com.pangio.ott.project.TimeSheet" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'reportItem.label', default: 'TimeSheet')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="list-reportItem" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:each in="${assignedProjects}" status="i" var="projectInstance">
        <div class="accordion" id="accordion-${projectInstance.name}">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-${projectInstance.id}"
                       href="#collapse-${projectInstance.id}">
                        <div class="message span10" role="status">${projectInstance.name}</div>
                    </a>
                    <g:link class="btn btn-info" action="create" params="[projectId: projectInstance.id]">
                        <g:message code="default.button.hours.submit.label"/>
                    </g:link>
                </div>

                <div id="collapse-${projectInstance.id}" class="accordion-body collapse in">
                    <div class="accordion-inner">
                        <div class="message" role="status">${projectInstance.description}</div>
                    </div>

                    <g:if test="${reportItemInstanceList != null && !reportItemInstanceList.isEmpty()}">
                        <table>
                            <thead>
                            <tr>
                                <g:sortableColumn property="task" title="${message(code: 'default.task.label', default: 'Task')}"/>
                                <g:sortableColumn property="hours" title="${message(code: 'default.hours.label', default: 'Hours')}"/>
                                <g:sortableColumn property="releaseDate" title="${message(code: 'default.release.date.label', default: 'Release Date')}"/>
                                <g:sortableColumn property="comments" title="${message(code: 'default.comments.label', default: 'Comments')}"/>
                            </tr>
                            </thead>
                            <tbody>
                            <g:each in="${reportItemInstanceList}" status="ri" var="reportItemInstance">

                                <g:if test="${reportItemInstance.project.name == projectInstance.name}">
                                    <tr class="${(ri % 2) == 0 ? 'even' : 'odd'}">
                                        <td>${fieldValue(bean: reportItemInstance, field: "task")}</td>
                                        <td>${fieldValue(bean: reportItemInstance, field: "hours")}</td>
                                        <td><g:formatDate date="${reportItemInstance.releaseDate}" type="date" style="SHORT"/></td>
                                        <td>${fieldValue(bean: reportItemInstance, field: "comments")}</td>
                                    </tr>
                                </g:if>
                            </g:each>
                            </tbody>
                        </table>
                    </g:if>
                    <g:else>
                        <div>
                            No submits yet. Please submit your hours
                        </div>
                    </g:else>
                </div>
            </div>
        </div>
    </g:each>
</div>
</body>
</html>
