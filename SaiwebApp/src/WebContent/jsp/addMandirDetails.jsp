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

<form:form action="saveMandir.html" method="post" commandName="addMandirDetails" class="niceform">
					<fieldset>
						<legend>
							<span>Add Mandir Details</span>
						</legend>
						<dl>
							<dt>
								<label for="mandir_code">Mandir Code</label>
							</dt>
							<dd>
								<form:input path="mandirCode" size="15" />
								<form:errors path="mandirCode" cssStyle="color:red" />
							</dd>
						</dl>

						<dl>
							<dt>
								<label for="name">Mandir Name</label>
							</dt>
							<dd>
								<form:input path="mandirName"  size="50"/>
								<form:errors path="mandirName" cssStyle="color:red" />
							</dd>
						</dl>

						<dl>
							<dt>
								<label for="description">Description</label>
							</dt>

							<dd>
								<form:textarea  path="details"  rows="5" cols="50"/>
								<form:errors path="details" cssStyle="color:red" />
							</dd>
						</dl>


						<dl>
							<dt>
								<label for="description" >Website</label>
							</dt>

							<dd>
								<form:input path="address.website" size="50" />
								<form:errors path="address.website" cssStyle="color:red" />
							</dd>
						</dl>
					</fieldset>

<table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Age</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="personListContainer">
                    <c:forEach items="${mandir.details}" var="MandirDetails" varStatus="i" begin="0" >
                        <tr class="person">
                            
                            <td><form:input path="details[${i.index}].line" id="line${i.index}" /></td>
                            <td><form:input path="details[${i.index}].line" id="line${i.index}" /></td>
                       
                            <td><a href="#" class="removePerson">Remove Person</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

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
