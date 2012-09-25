<%@include file="taglib_includes.jsp"%>

<html>
<head>
<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />
<script type="text/javascript" src="js/contacts.js"></script>


</head>


<body>
<center>
<%@include file="header.jsp" %>

<form:form action="saveContact.html"
					method="post" commandName="addContact">
			<fieldset>
						<legend>
							<span>Add New  Details</span>
						</legend>
						<dl>
							<dt>
								<label for="first_name">First Name</label>
							</dt>
							<dd>
								<form:input path="firstname" />
								<form:errors path="firstname" cssStyle="color:red" />
							</dd>
						</dl>
						
												<dl>
							<dt>
								<label for="last_Name">Last Name</label>
							</dt>
							<dd>
								<form:input path="lastname" />
								<form:errors path="lastname" cssStyle="color:red" />
							</dd>
						</dl>
						
						
																		<dl>
							<dt>
								<label for="email">Email Addresse</label>
							</dt>
							<dd>
								<form:input path="email" />
								<form:errors path="email" cssStyle="color:red" />
							</dd>
						</dl>
						
							<dl>
							<dt>
								<label for="primary_phone">Primary Phone</label>
							</dt>
							<dd>
								<form:input path="primaryPhone" />
								<form:errors path="primaryPhone" cssStyle="color:red" />
							</dd>
						</dl>
		
							<dl>
							<dt>
								<label for="email">alternate Phone Number</label>
							</dt>
							<dd>
								<form:input path="altPhone" />
								<form:errors path="altPhone" cssStyle="color:red" />
							</dd>
						</dl>

							<dl>
							<dt>
								<label for="role">Role</label>
							</dt>
							<dd>
							<form:select path="role"
									items="${rolesList}" />
								</dd>
						</dl>

						</fieldset>		

<fieldset class="action">

						<input type="submit" name="" value="Save"> <input
							type="reset" name="Reset" id="Reset" value="Reset" />
						&nbsp;&nbsp; <input type="button" value="Back"
							onclick="javascript:go('viewAllContacts.html');">
					</fieldset>
					</form:form>
					
</center>					
					</body>
</html>