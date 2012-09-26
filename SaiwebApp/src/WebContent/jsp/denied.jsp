<%@include file="taglib_includes.jsp"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
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
			<td align="center"><h3>Unauthorized</h3></td>
		</tr>
		<tr valign="top" align="center">
			<td align="center">
	
	<p class="message">Access denied!</p>
	 <input type="button"
								value="Home" onclick="javascript:go('home.html');">
		</td>
		</tr>
	</table>
</body>
</html>

