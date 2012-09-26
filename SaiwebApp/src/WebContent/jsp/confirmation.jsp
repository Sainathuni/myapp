<%@include file="taglib_includes.jsp"%>

<html>
<head>


<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />
<script type="text/javascript" src="js/contacts.js"></script>
<title><spring:message code="App.Title"></spring:message></title>
</head>
<body style="font-family: Arial; font-size: smaller;">

	<table bgcolor="lightblue" width="750" height="300" align="center"
		style="border-collapse: collapse;" border="1" bordercolor="#006699">
		<tr>
			<td align="center"><h3>Confirmation</h3></td>
		</tr>
		<tr valign="top" align="center">
			<td align="center">
			<form:form action="confirmation.html" method="post" commandName="result">
					<fieldset>
						<form:label path="statusMessage">${result.statusMessage}</form:label>	
					</fieldset>			
	 
	 <fieldset class="action"> 
            <input type="button"
								value="Home" onclick="javascript:go('home.html');">
    </fieldset>
		</form:form>
	
	</td>
		</tr>
	</table>
</body>
</html>
