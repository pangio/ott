<%@ page import="com.pangio.ott.project.Project; com.pangio.ott.project.Project" %>
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
    <g:each in="${map}" status="i" var="mapEntry">
        <div class="accordion" id="accordion-${mapEntry.key}">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-${mapEntry.key}"
                       href="#collapse-${mapEntry.key}">
                        <div class="message span10" role="status">${Project.get(mapEntry.key).name}</div>
                    </a>
                    <g:link class="btn btn-info" action="create" params="[projectId: mapEntry.key]">
                        <g:message code="ott.button.timesheet.submit.label"/>
                    </g:link>
                </div>
                <div id="collapse-${mapEntry.key}" class="accordion-body collapse in">
                    <div class="accordion-inner">
                        <div class="message" role="status">${Project.get(mapEntry.key).description}</div>
                    </div>
                    <g:if test="${mapEntry.value != null && !((ArrayList)mapEntry.value).isEmpty()}">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <g:sortableColumn property="task" title="${message(code: 'ott.task.label', default: 'Task')}"/>
                                    <g:sortableColumn property="hours" title="${message(code: 'ott.timesheet.label', default: 'Hours')}"/>
                                    <g:sortableColumn property="extra" title="${message(code: 'ott.extra.label', default: 'Extra')}"/>
                                    <g:sortableColumn property="date" title="${message(code: 'ott.date.label', default: 'Date')}"/>
                                    <g:sortableColumn property="comments" title="${message(code: 'ott.comments.label', default: 'Comments')}"/>
                                </tr>
                                </thead>
                                <tbody>

                                <g:each in="${mapEntry.value}" status="ri" var="timesheet">
                                    <tr class="${(ri % 2) == 0 ? 'even' : 'odd'}">
                                        <td>${fieldValue(bean: timesheet, field: "task")}</td>
                                        <td>${fieldValue(bean: timesheet, field: "hours")}</td>
                                        <td>${fieldValue(bean: timesheet, field: "extra")}</td>
                                        <td><g:formatDate date="${timesheet.date}" type="date" style="SHORT"/></td>
                                        <td>${fieldValue(bean: timesheet, field: "comments")}</td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
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
