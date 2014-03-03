<%@ page import="com.pangio.ott.project.*" %>

<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'default.report.label', default: 'Report')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>

    <r:require modules="chosen, timepicker"/>
    <r:script>
        $("#project").data("placeholder", "Select Project...").chosen();
    </r:script>

    <r:script>
        var currentDate = new Date();
        Date.prototype.addDays = function(days) {
            this.setDate(this.getDate() - days);
            return this;
        };

        $( "#dateFrom" ).datepicker({
            changeMonth: true,
            numberOfMonths: 1,
            changeMonth:false,
            changeYear:true
//            minDate: currentDate.addDays(7)
        });

        $( "#dateTo" ).datepicker({
            defaultDate: new Date(),
            changeMonth: true,
            numberOfMonths: 1,
            changeMonth:false,
            changeYear:true
//            minDate: currentDate.addDays(7)
        });
    </r:script>

</head>

<body>

<g:form method="post">

    <g:hiddenField name="buildBy" value="project"></g:hiddenField>

    <fieldset class="form">

        %{-- projects--}%
        <div class="fieldcontain">
            <label for="project">
                <g:message code="ott.project.label" default="Project"/>
                <span class="required-indicator">*</span>
            </label>
            <g:select oninvalid="project" name="project" required="" from="${com.pangio.ott.project.Project.list()}" optionKey="id" size="5" class="many-to-many chosen"/>
        </div>

        %{--date from--}%
        <div class="fieldcontain required">
            <label for="dateFrom">
                <g:message code="default.date.from.label" default="Date From" />
                <span class="required-indicator">*</span>
            </label>
            <jqueryPicker:date name="dateFrom" id="dateFrom" />
        </div>

        %{--dateTo--}%
        <div class="fieldcontain">
            <label for="dateTo">
                <g:message code="default.date.to.label" default="Date To" />
            </label>
            <jqueryPicker:date name="dateTo" id="dateTo" />
        </div>

    </fieldset>
    <fieldset class="buttons">
        <g:actionSubmit class="btn btn-info" action="buildReport" value="${message(code: 'ott.report.build.label', default: 'Build Report')}" />
    </fieldset>
</g:form>

</body>
</html>
