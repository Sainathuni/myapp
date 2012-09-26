<%@include file="taglib_includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<!--[if lt IE 9]><script src=http://html5shiv.googlecode.com/svn/trunk/html5.js></script><![endif]-->
	<link href=css/styles.css rel=stylesheet />
	<!--[if lt IE 9]><link href=css/ie.css rel=stylesheet /><![endif]-->
<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />

<script type="text/javascript" src="js/contacts.js"></script>
<script type="text/javascript" src="js/niceforms.js"></script>


<script src="js/jquery-1.8.0.js"></script>
<script src="js/ui/jquery.ui.core.js"></script>
<script src="js/ui/jquery.ui.widget.js"></script>
<script src="js/ui/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="css/base/jquery.ui.all.css">

<title><spring:message code="App.Title"></spring:message></title>
<script>
	$(function() {
		$( "#datepicker" ).datepicker();
	});
	</script>
</head>
<body style="font-family: Arial; font-size: smaller;">
<header id="banner" class="body">
	<h1>SaiMandirs CMS</h1>
 

 
</header>




<div id="container">
<%@include file="header.jsp" %>

<form:form action="saveMandir.html" method="post" commandName="addMandir" class="niceform">
					<fieldset>
						<legend>
							<span>Mandir Details</span>
						</legend>
						<dl>
							<dt>
								<label for="mandir_code">Mandir Code</label>
							</dt>
							<dd>
								<form:input path="code" size="15" />
								<form:errors path="code" cssStyle="color:red" />
							</dd>
						</dl>

						<dl>
							<dt>
								<label for="name">Mandir Name</label>
							</dt>
							<dd>
								<form:input path="name"  size="50"/>
								<form:errors path="name" cssStyle="color:red" />
							</dd>
						</dl>

						<dl>
							<dt>
								<label for="description">Description</label>
							</dt>

							<dd>
								<form:textarea  path="description"  rows="5" cols="50"/>
								<form:errors path="description" cssStyle="color:red" />
							</dd>
						</dl>


						<dl>
							<dt>
								<label for="description" >Website</label>
							</dt>

							<dd>
								<form:input path="website" size="50" />
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
								<form:input path="mandirAddress.city" size="50" />
								<form:errors path="mandirAddress.city" cssStyle="color:red"></form:errors>
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="line1">Address 1</label>
							</dt>
							<dd>
								<form:input path="mandirAddress.line1" size="50"/>
								<form:errors path="mandirAddress.line1" cssStyle="color:red"></form:errors>
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="line2">Address 2</label>
							</dt>
							<dd>
								<form:input path="mandirAddress.line2" size="50"/>
								<form:errors path="mandirAddress.line2" cssStyle="color:red"></form:errors>
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="line3">Address 3</label>
							</dt>
							<dd>
								<form:input path="mandirAddress.line3" size="50" />
								<form:errors path="mandirAddress.line3" cssStyle="color:red"></form:errors>
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="zipcode">Pincode/ZipCode</label>
							</dt>
							<dd>
								<form:input path="mandirAddress.postalCode" size="15"/>
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

					</fieldset>
<!-- 
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
	 -->
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
				</form:form>
</div>
</body>
</html>
