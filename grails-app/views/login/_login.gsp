<form id="loginForm" method="POST" action="${resource(file: 'j_spring_security_check')}">
  <table>
    <tr>
      <td><g:textField name="j_username" placeholder="${message(code:'login.username')}"/></td>
    </tr>
    <tr>
      <td><g:passwordField name="j_password" placeholder="${message(code:'login.password')}"/></td>
    </tr>
    <tr>
      <td colspan="2"><g:submitButton name="login" value="${message(code: "springSecurity.login.button")}'"/></td>
    </tr>
  </table>
</form>
<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
