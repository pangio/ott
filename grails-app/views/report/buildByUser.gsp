<%@ page import="com.pangio.ott.project.*" %>

<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'default.report.label', default: 'Report')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>

    <r:require modules="chosen, timepicker"/>
    <r:script>
        $("#user").data("placeholder", "Select User...").chosen();
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

    <g:hiddenField name="buildBy" value="user"></g:hiddenField>

    <fieldset class="form">

        %{-- users--}%
        <div class="fieldcontain">
            <label for="user">
                <g:message code="default.user.label" default="User"/>
                <span class="required-indicator">*</span>
            </label>
            <g:select oninvalid="user" name="user" from="${com.pangio.ott.user.User.list()}" required="" optionKey="id" size="5"
                      class="many-to-many chosen"/>
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
        <g:actionSubmit class="btn btn-info" action="buildReport" value="${message(code: 'report.build.label', default: 'Build Report')}"/>
    </fieldset>
</g:form>

</body>
</html>
