<%@ page import="com.pangio.ott.project.TimeSheet" %>

<r:require modules="chosen,timepicker"/>
<r:script>
    $("#task").data("placeholder", "Select Tasks...").chosen();
</r:script>

<r:script>
    var currentDate = new Date();
    Date.prototype.addDays = function(days) {
    this.setDate(this.getDate() - days);
    return this;
    };

    $( "#date" ).datepicker({
        defaultDate: new Date(),
        changeMonth: false,
        numberOfMonths: 1,
        changeMonth:false,
        changeYear:false,
        minDate: currentDate.addDays(7)
    });
</r:script>

<div class="fieldcontain ${hasErrors(bean: timeSheetInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="ott.comments.label" default="Comments" />
		
	</label>
	<g:textField name="comments" value="${timeSheetInstance?.comments}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeSheetInstance, field: 'hours', 'error')} required">
	<label for="hours">
		<g:message code="ott.hours.label" default="Hours" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="hours" type="number" value="${timeSheetInstance.hours}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeSheetInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="ott.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
    <jqueryPicker:date name="date" id="date"  value="${timeSheetInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: timeSheetInstance, field: 'task', 'error')} required">
	<label for="task">
		<g:message code="ott.task.label" default="Task" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="task" name="task.id" from="${com.pangio.ott.project.Task.list()}" optionKey="id" required="" value="${timeSheetInstance?.task?.id}" class="many-to-one"/>
</div>

