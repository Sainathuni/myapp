<%@include file="taglib_includes.jsp"%>

<html>
<head>


<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />
<script type="text/javascript" src="js/contacts.js"></script>
<script src="js/jquery-1.8.0.js"></script>
<script src="js/ui/jquery.ui.core.js"></script>
<script src="js/ui/jquery.ui.widget.js"></script>
<script src="js/ui/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="css/base/jquery.ui.all.css">

<link rel="stylesheet" type="text/css" media="all" href="css/demos.css" />

<title><spring:message code="App.Title"></spring:message></title>
<script>
	$(function() {
		$( "#datepicker" ).datepicker();
	});
	</script>
</head>
<body style="font-family: Arial; font-size: smaller;">

<center>
<%@include file="header.jsp" %>

	<table bgcolor="lightblue" width="750" height="500" align="center"
		style="border-collapse: collapse;" border="1" bordercolor="#006699">
		<tr>
			<td align="center"><h3>Add Mandir</h3></td>
		</tr>
		<tr valign="top" align="center">
			<td align="center"><form:form action="saveMandir.html"
					method="post" commandName="addMandir">
					<fieldset>
						<legend>
							<span>Mandir Details</span>
						</legend>
						<dl>
							<dt>
								<label for="mandir_code">Mandir Code</label>
							</dt>
							<dd>
								<form:input path="code" />
								<form:errors path="code" cssStyle="color:red" />
							</dd>
						</dl>

						<dl>
							<dt>
								<label for="name">Mandir Name</label>
							</dt>
							<dd>
								<form:input path="name" />
								<form:errors path="name" cssStyle="color:red" />
							</dd>
						</dl>

						<dl>
							<dt>
								<label for="description">Description</label>
							</dt>

							<dd>
								<form:input path="description" />
								<form:errors path="description" cssStyle="color:red" />
							</dd>
						</dl>


						<dl>
							<dt>
								<label for="description">Website</label>
							</dt>

							<dd>
								<form:input path="website" />
								<form:errors path="website" cssStyle="color:red" />
							</dd>
						</dl>
					</fieldset>

					<fieldset>
						<legend>
							<span>Mandir Address</span>
						</legend>


						<dl>
							<dt>
								<label for="country">Country</label>
							</dt>
							<dd>
								<form:select path="mandirAddress.countryId"
									items="${countryList}" />
							</dd>

						</dl>


						<dl>
							<dt>
								<label for="state">State</label>
							</dt>
							<dd>
								<form:select path="mandirAddress.stateId" items="${countryList}" />
							</dd>
						</dl>


						<dl>
							<dt>
								<label for="region">Region</label>
							</dt>
							<dd>							
								<form:select path="mandirAddress.regionId" items="${regionList}" />
							</dd>

						</dl>
						<dl>
							<dt>

								<label for="email">City</label>
							</dt>
							<dd>
								<form:input path="mandirAddress.city" />
								<form:errors path="mandirAddress.city" cssStyle="color:red"></form:errors>
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="line1">Address 1</label>
							</dt>
							<dd>
								<form:input path="mandirAddress.line1" />
								<form:errors path="mandirAddress.line1" cssStyle="color:red"></form:errors>
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="line2">Address 2</label>
							</dt>
							<dd>
								<form:input path="mandirAddress.line2" />
								<form:errors path="mandirAddress.line2" cssStyle="color:red"></form:errors>
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="line3">Address 3</label>
							</dt>
							<dd>
								<form:input path="mandirAddress.line3" />
								<form:errors path="mandirAddress.line3" cssStyle="color:red"></form:errors>
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="zipcode">Pincode/ZipCode</label>
							</dt>
							<dd>
								<form:input path="mandirAddress.postalCode" />
								<form:errors path="mandirAddress.postalCode"
									cssStyle="color:red"></form:errors>
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="zipcode">Info gathered on</label>

							</dt>
							<dd>
								<form:input path="infoGatheredOn" id="datepicker" />
								<form:errors path="infoGatheredOn" cssStyle="color:red"></form:errors>
							</dd>
						</dl>

					</fieldset>

					<fieldset>
						<legend>
							<span> Mandir Contact Details</span>
						</legend>

						<dl>
							<dt>
								<label for="website">Full Name</label>
							</dt>
							<dd></dd>
						</dl>
						<dl>
							<dt>

								<label for="phone">Phone #</label>
							</dt>
							<dd></dd>
						</dl>
						<dl>
							<dt>

								<label for="email">Email Address</label>
							</dt>
							<dd></dd>
						</dl>

					</fieldset>
					<!-- 
<fieldset>
     <legend><span> Info gathered By </span> </legend>
   <dl><dt>
          <label for="events">SaiSEEker Name: </label>
</dt>
            <dd>         <input type="text" name="mandirTeamContact.name" id="saiseekerName" />     
 </dd>
</dl>
   <dl><dt>
          <label for="events">Email: </label>
</dt>
            <dd>         <input type="text" name="mandirTeamContact.email" id="saiseekerEmail" />     
 </dd>
</dl>
   <dl><dt>
          <label for="events">Phone #: </label>
</dt>
            <dd>         <input type="text" name="mandirTeamContact.phonenumber" id="saiseekerPhone" />     
 </dd>

</dl>
<dl><dt>
          <label for="events">Place #: </label>
</dt>
            <dd>         <input type="text" name="mandirTeamContact.place" id="saiseekerPhone" />     
 </dd>

</dl>


 </fieldset>
	 -->

					<fieldset class="action">

						<input type="submit" name="" value="Save"> <input
							type="reset" name="Reset" id="Reset" value="Reset" />
						&nbsp;&nbsp; <input type="button" value="Back"
							onclick="javascript:go('viewAllMandirs.html');">
					</fieldset>
				</form:form></td>
		</tr>
	</table>
	
</center>	
</body>
</html>
