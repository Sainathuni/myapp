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
			<td align="center"><h3>Upload File</h3></td>
		</tr>
		<tr valign="top" align="center">
			<td align="center">
			<form:form action="uploadFile.html" method="post" commandName="uploadItem" enctype="multipart/form-data">
					<fieldset>
					<form:hidden path="mandirId"/>
					<form:hidden path="mandirCode"/>
						
						<dl>
							<dt>
								<label for="email">File Name</label>
							</dt>
							<dd>
								 <form:input path="name"/>
								<form:errors path="name" cssStyle="color:red" />
							</dd>
						</dl>						
						<dl>
							<dt>
								<label for="description">Description</label>
							</dt>
							<dd>
								 <form:input path="description"/>
								<form:errors path="description" cssStyle="color:red" />
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="fileData">File</label>
							</dt>
							<dd>
								<form:input path="fileData" type="file"/>
								<form:errors path="fileData" cssStyle="color:red" />
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
