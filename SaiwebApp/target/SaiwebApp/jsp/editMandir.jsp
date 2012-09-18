<%@include file="taglib_includes.jsp" %>

<html>
<head>
	<script type="text/javascript" src="js/contacts.js"></script>
	<title><spring:message code="App.Title"></spring:message> </title>
</head>
<body style="font-family: Arial; font-size:smaller;">
<center>
<%@include file="header.jsp" %>

<table  bgcolor="lightblue" width="750" height="500" align="center" style="border-collapse: collapse;" border="1" bordercolor="#006699" >
	<tr>
		<td align="center"><h3>Edit Mandir Form</h3></td>
	</tr>
  <tr valign="top" align="center">
    <td align="center">
 		<form:form action="updateMandir.html" method="post" commandName="editMandir">
 		<form:hidden path="mandirId"/>
				<table width="500" style="border-collapse: collapse;" border="0" bordercolor="#006699" cellspacing="2" cellpadding="2">					
					<tr>
						<td width="100" align="right">Mandir Code</td>
						<td width="150">
						<form:input path="code" readonly="true"/></td>
						<td align="left">
						<form:errors path="code" cssStyle="color:red"></form:errors>  </td>
					</tr>
				
					<tr>
						<td width="100" align="right">Mandir Name</td>
						<td width="150">
						<form:input path="name"/></td>
						<td align="left">
						<form:errors path="name" cssStyle="color:red"></form:errors> 
						</td>
					</tr>
					<tr>
						<td width="100" align="right">Description</td>
						<td>						
							<form:input path="description"/>						
						</td>
						<td align="left"><form:errors path="description" cssStyle="color:red"></form:errors>  </td>
						
					</tr>
					<tr>
						<td width="100" align="right">Website</td>
						<td><form:input path="website"/></td>
						<td align="left">
						<form:errors path="website" cssStyle="color:red"></form:errors>  </td>
					</tr> 	
					
						<form:hidden path="mandirAddress.id"/>
					
																<tr>
						<td width="100" align="right">Line 1</td>
						<td><form:input path="mandirAddress.line1"/></td>
						<td align="left">
						<form:errors path="mandirAddress.line1" cssStyle="color:red"></form:errors>  </td>
					</tr>
				
						<tr>
						<td width="100" align="right">Line 2</td>
						<td><form:input path="mandirAddress.line1"/></td>
						<td align="left">
						<form:errors path="mandirAddress.line2" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
						<tr>
						<td width="100" align="right">Line 3</td>
						<td><form:input path="mandirAddress.line3"/></td>
						<td align="left">
						<form:errors path="mandirAddress.line3" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
					
						<tr>
						<td width="100" align="right">City</td>
						<td><form:input path="mandirAddress.city"/></td>
						<td align="left">
						<form:errors path="mandirAddress.city" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
											<tr>
						<td width="100" align="right">Region</td>
						<td><form:input path="mandirAddress.regionId"/></td>
						<td align="left">
						<form:errors path="mandirAddress.regionId" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
											<tr>
						<td width="100" align="right">State</td>
						<td><form:input path="mandirAddress.stateId"/></td>
						<td align="left">
						<form:errors path="mandirAddress.stateId" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
												<tr>
						<td width="100" align="right">Country</td>
						<td><form:input path="mandirAddress.countryId"/></td>
						<td align="left">
						<form:errors path="mandirAddress.countryId" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
												<tr>
						<td width="100" align="right">Postal Code</td>
						<td><form:input path="mandirAddress.postalCode"/></td>
						<td align="left">
						<form:errors path="mandirAddress.postalCode" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
					
					
																<tr>
						<td width="100" align="right">Latitude</td>
						<td><form:input path="mandirAddress.latitude"/></td>
						<td align="left">
						<form:errors path="mandirAddress.latitude" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
					
																<tr>
						<td width="100" align="right">Longitude</td>
						<td><form:input path="mandirAddress.longitude"/></td>
						<td align="left">
						<form:errors path="mandirAddress.longitude" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
					
																<tr>
						<td width="100" align="right">Maps Links</td>
						<td><form:input path="mandirAddress.mapsLink"/></td>
						<td align="left">
						<form:errors path="mandirAddress.mapsLink" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
								<tr>
						<td width="100" align="right">Info gathered on</td>
						<td><form:input path="infoGatheredOn"/></td>
						<td align="left">
						<form:errors path="infoGatheredOn" cssStyle="color:red"></form:errors>  </td>
					</tr>
					
					
					
					<tr valign="bottom">
						<td colspan="3" align="center">
						<input type="button"  value="Delete" onclick="javascript:deleteContact('deleteMandir.html?id=${editContact.mandirId}');">
						&nbsp;&nbsp;
						<input type="submit" name="" value="Save">						
						&nbsp;&nbsp;
						<input type="button"  value="Back" onclick="javascript:go('viewAllMandirs.html');">
						</td>
					</tr>
					
				</table>				
		</form:form>
    </td>    
  </tr>
</table>

</center>
</body>
</html>
