<%@ page import="com.pangio.ott.project.ReportItem" %>

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

    $( "#releaseDate" ).datepicker({
        defaultDate: new Date(),
        changeMonth: false,
        numberOfMonths: 1,
        changeMonth:false,
        changeYear:false,
        minDate: currentDate.addDays(7)
    });
</r:script>

<div class="fieldcontain ${hasErrors(bean: reportItemInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="reportItem.comments.label" default="Comments" />
		
	</label>
	<g:textField name="comments" value="${reportItemInstance?.comments}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reportItemInstance, field: 'hours', 'error')} required">
	<label for="hours">
		<g:message code="reportItem.hours.label" default="Hours" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="hours" type="number" value="${reportItemInstance.hours}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: reportItemInstance, field: 'releaseDate', 'error')} required">
	<label for="releaseDate">
		<g:message code="reportItem.releaseDate.label" default="Release Date" />
		<span class="required-indicator">*</span>
	</label>
    <jqueryPicker:date name="releaseDate" id="releaseDate"  value="${reportItemInstance?.releaseDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: reportItemInstance, field: 'task', 'error')} required">
	<label for="task">
		<g:message code="reportItem.task.label" default="Task" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="task" name="task.id" from="${com.pangio.ott.project.Task.list()}" optionKey="id" required="" value="${reportItemInstance?.task?.id}" class="many-to-one"/>
</div>

