<%@ page import="com.pangio.ott.project.Report" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'report.label', default: 'Report')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="list-report" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>

            </tr>
            </thead>
            <tbody>
            <g:each in="${reportInstanceList}" status="i" var="reportInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="pagination">
        <g:paginate total="${reportInstanceTotal}"/>
    </div>
</div>
</body>
</html>
