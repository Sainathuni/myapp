<%@include file="taglib_includes.jsp" %>

<html>
<head>
<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />
<script type="text/javascript" src="js/contacts.js"></script>
<script type="text/javascript" src="js/niceforms.js"></script>
<script src="js/jquery-1.8.0.js"></script>
<script src="js/ui/jquery.ui.core.js"></script>
<script src="js/ui/jquery.ui.widget.js"></script>
<script src="js/ui/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="css/base/jquery.ui.all.css">

<link rel="stylesheet" type="text/css" media="all" href="css/demos.css" />

	<title><spring:message code="App.Title"></spring:message> </title>
</head>
<body style="font-family: Arial; font-size:smaller;">
<div id="container">
<center>
<%@include file="header.jsp" %>

<br/>
<br/>
<br/>
 		<form:form action="updateMandir.html" method="post" commandName="editMandir">
 		<form:hidden path="mandirId"/>
					<fieldset>
						<legend>
							<span>Mandir Details</span>
						</legend>
						<dl>
							<dt>
								<label for="mandir_code">Mandir Code</label>
							</dt>
							<dd>
								<form:input path="code"  readonly="true"/>
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
									<form:textarea  path="description" rows="5" cols="30"/>
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
								<form:select path="mandirAddress.country"
									items="${countryList}" />
							</dd>

						</dl>


						<dl>
							<dt>
								<label for="state">State</label>
							</dt>
							<dd>
								<form:select path="mandirAddress.state" items="${statesList}" />
							</dd>
						</dl>


						<dl>
							<dt>
								<label for="region">Region</label>
							</dt>
							<dd>							
								<form:select path="mandirAddress.region" items="${regionList}" />
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
							<dd>  								<form:input path="infoGatheredOn" id="datepicker" />
								<form:errors path="infoGatheredOn" cssStyle="color:red"></form:errors>
					 
							</dd>
						</dl>
						
												<dl>
							<dt>
								<label for="Latitude">Latitude</label>

							</dt>
							<dd>  							<form:input path="mandirAddress.latitude"/>
								<form:errors path="mandirAddress.latitude" cssStyle="color:red"></form:errors>
					 
							</dd>
						</dl>
						
																		<dl>
							<dt>
								<label for="Latitude">Longitude</label>

							</dt>
							<dd>  							<form:input path="mandirAddress.longitude"/>
								<form:errors path="mandirAddress.longitude" cssStyle="color:red"></form:errors>
					 
							</dd>
						</dl>
								
											
																		<dl>
							<dt>
								<label for="Latitude">Maps Links</label>

							</dt>
							<dd>  							<form:input path="mandirAddress.mapsLink"/>
								<form:errors path="mandirAddress.mapsLink" cssStyle="color:red"></form:errors>
					 
							</dd>
						</dl>
									
											
					
					</fieldset>
					
					<fieldset class="action">

				<input type="button"  value="Delete" onclick="javascript:deleteContact('deleteMandir.html?id=${editContact.mandirId}');">
						&nbsp;&nbsp;
						<input type="submit" name="" value="Save">						
						&nbsp;&nbsp;
						<input type="button"  value="Back" onclick="javascript:go('viewAllMandirs.html');">
					</fieldset>
				</form:form>

</div>
</body>
</html>
