<%@include file="taglib_includes.jsp"%>

<html>
<head>


<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />
<script type="text/javascript" src="js/contacts.js"></script>
<title><spring:message code="App.Title"></spring:message></title>
</head>
<body style="font-family: Arial; font-size: smaller;">

	<table bgcolor="lightblue" width="750" height="500" align="center"
		style="border-collapse: collapse;" border="1" bordercolor="#006699">
		<tr>
			<td align="center"><h3>Add User</h3></td>
		</tr>
		<tr valign="top" align="center">
			<td align="center">
			<form:form action="newUser" method="post" commandName="user">
					<fieldset>
						<legend>
							<span> User Details</span>
						</legend>
						<dl>
							<dt>
								<label for="firstName">First Name</label>
							</dt>
							<dd>
								<form:input path="firstName" />
								<form:errors path="firstName" cssStyle="color:red" />
							</dd>
						</dl>

						<dl>
							<dt>
								<label for="lastName">Last Name</label>
							</dt>
							<dd>
								<form:input path="lastName" />
								<form:errors path="lastName" cssStyle="color:red" />
							</dd>
						</dl>

						<dl>
							<dt>
								<label for="phoneNumber">Phone Number</label>
							</dt>

							<dd>
								<form:input path="phoneNumber" />
								<form:errors path="phoneNumber" cssStyle="color:red" />
							</dd>
						</dl>


						<dl>
							<dt>
								<label for="email">Email Address</label>
							</dt>

							<dd>
								<form:input path="email" />
								<form:errors path="email" cssStyle="color:red" />
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="password">Password</label>
							</dt>

							<dd>
								<form:password path="password" />
								<form:errors path="password" cssStyle="color:red" />
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="confirmPassword">Confirm Password</label>
							</dt>

							<dd>
								<form:password path="confirmPassword" />
								<form:errors path="confirmPassword" cssStyle="color:red" />
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="role">Role</label>
							</dt>
							<dd>
								<form:select path="role.code"
									items="${roleList}" />
							</dd>
							
						</dl>
					</fieldset>			
	 
	 <fieldset class="action">
 
           <input type="submit" name=""
								value="Save">
      <input type="reset" name="Reset" id="Reset" value="Reset" />
    &nbsp;&nbsp; <input type="button"
								value="Back" onclick="javascript:go('/SaiwebApp/home');">
    </fieldset>
		</form:form>
	
	</td>
		</tr>
	</table>
</body>
</html>
