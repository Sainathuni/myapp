<%@include file="taglib_includes.jsp"%>

<html>
<head>


<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />
<script type="text/javascript" src="js/contacts.js"></script>
<title><spring:message code="App.Title"></spring:message></title>
</head>
<body style="font-family: Arial; font-size: smaller;">

	<table bgcolor="lightblue" width="850" height="300" align="center"
		style="border-collapse: collapse;" border="1" bordercolor="#006699">
		<tr>
			<td align="center"><h3>Forgot Password</h3></td>
		</tr>
		<tr valign="top" align="center">
			<td align="center">
			<form:form action="forgotPassword.html" method="post" commandName="user">
					<fieldset>
						<legend>
							<span> Enter User ID, Password will be sent to email address on profile</span>
						</legend>
						<dl>
							<dt>
								<label for="email">User ID (Email ID)</label>
							</dt>
							<dd>
								<form:input path="email" />
								<form:errors path="email" cssStyle="color:red" />
							</dd>
						</dl>						
						
					</fieldset>			
	 
	 <fieldset class="action">
 
           <input type="submit" name=""
								value="Submit">
      <input type="reset" name="Reset" id="Reset" value="Reset" />
    &nbsp;&nbsp; <input type="button"
								value="Back" onclick="javascript:go('home.html');">
    </fieldset>
		</form:form>
	
	</td>
		</tr>
	</table>
</body>
</html>
