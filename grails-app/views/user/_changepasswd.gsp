<%@ page import="com.pangio.ott.user.User" %>

<div id="edit-user" class="content scaffold-edit" role="main">
<fieldset class="form">
	<legend><g:message code="profile.changepass"/></legend>
    <g:if test="${request.message}">
        <g:viewMessage message="${request.message}"/>
    </g:if>
	
    <g:hasErrors bean="${user}">
        <div class="ui-widget">
            <div class="errors message ui-state-error radius" role="alert">
                <g:eachError bean="${user}" var="error">
                    <p class="message-item" <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
                        <span class="ui-icon ui-icon-alert"></span><g:message error="${error}"/>
                    </p>
                </g:eachError>
            </div>
        </div>
    </g:hasErrors>
    
    <g:formRemote name="editForm" update="profile-content" method="post" url="[action:'updatepasswd']">
        <g:hiddenField name="id" value="${user?.id}" />
        <g:hiddenField name="version" value="${user?.version}" />
           <table style="border: none">
             <tr class="fieldcontain ${hasErrors(bean: user, field: 'password', 'error')} required">
                <td class="first-column">
                    <label for="password">
                        <g:message code="default.password.label" default="Password"/>
                        <span class="required-indicator">*</span>
                    </label>
                </td>
                <td>
                    <g:passwordField class="input" name="currentpassword" required="" />
                </td>
            </tr>
            <tr class="fieldcontain ${hasErrors(bean: user, field: 'password', 'error')} required">
                <td class="first-column">
                    <label for="password">
                        <g:message code="default.newpassword.label" default="New Password"/>
                        <span class="required-indicator">*</span>
                    </label>
                </td>
                <td>
                    <g:passwordField class="input" name="password" required="" />
                </td>
            </tr>
            <tr class="fieldcontain ${hasErrors(bean: user, field: 'password', 'error')} required">
                <td class="first-column">
                    <label for="confirmPassword">
                        <g:message code="user.passwordconfirm.label" default="Confirm New Password"/>
                        <span class="required-indicator">*</span>
                    </label>
                </td>
                <td>
                    <g:passwordField class="input" name="confirmPassword" required=""/>
                </td>
            </tr>
          </table>
            <input type="submit" class="button radius save" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </g:formRemote>
</div>
