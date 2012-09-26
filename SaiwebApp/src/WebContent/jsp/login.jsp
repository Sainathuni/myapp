<%@include file="taglib_includes.jsp"%>

<html>
<head>


<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />
<script type="text/javascript" src="js/contacts.js"></script>
<title><spring:message code="App.Title"></spring:message></title>
</head>
<body style="font-family: Arial; font-size: smaller;">

	<table bgcolor="lightblue" width="950" height="300" align="center"
		style="border-collapse: collapse;" border="1" bordercolor="#006699">
		<tr>
			<td align="center"><h3>Login</h3></td>
		</tr>
		<tr valign="top" align="center">
			<td align="center">
			
			<form class="login-form" action="j_spring_security_check" method="post" >
					<fieldset>
						<legend>
							<span> Enter User ID and Password</span>
						</legend>
						<dl>
							<p style="color:red">${message}</p>
						 </dl>
						<dl>
							<dt>
								<label for="j_username">User ID (Email ID)</label>
							</dt>
							<dd>
								<input id="j_username" name="j_username" size="20" maxlength="50" type="text"/>								
							</dd>
						</dl>

						<dl>
							<dt>
								<label for="j_password">Password</label>
							</dt>
							<dd>
								<input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>								
							</dd>
						</dl>
						
					</fieldset>			
	 
	 <fieldset class="action">
 
           <input type="submit" name=""
								value="Login"> 
     
    &nbsp;&nbsp; <input type="button"
								value="Forgot Password" onclick="javascript:go('forgotPassword.html');">
	&nbsp;&nbsp; <input type="button"
								value="Register User" onclick="javascript:go('newUser.html');">
	&nbsp;&nbsp; <input type="button"
								value="Home" onclick="javascript:go('home.html');">
	&nbsp;&nbsp; <input type="reset" name="Reset" id="Reset" value="Reset" />
    </fieldset>
		</form>
	
	</td>
		</tr>
	</table>
</body>
</html>
