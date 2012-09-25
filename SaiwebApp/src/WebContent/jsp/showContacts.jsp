<%@include file="taglib_includes.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Contacts</title>
<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />

</head>

<body>
<center>
<%@include file="header.jsp" %>
	<table style="border-collapse: collapse;" border="1"
			bordercolor="#006699" width="980">
			<tr bgcolor="lightblue">
				<th>Full Name</th>
				<th>Email address</th>
				<th>Phone #</th>
				<th>Alternate #</th>
				<th></th>
			</tr>
			<c:if test="${empty CONTACT_LIST}">
				<tr>
					<td colspan="4">No Results found</td>
				</tr>
			</c:if>
			<c:if test="${! empty CONTACT_LIST}">
			<tr><td colspan="4"> <a href="addContact.html">Add New Contact</a></td></tr>
				<c:forEach var="contact" items="${CONTACT_LIST}">
					<tr>
						<td><c:out value="${contact.firstname}"></c:out>  <c:out value="${contact.lastname}"></c:out></td>
						<td><c:out value="${contact.email}"></c:out> </td>
						<td><c:out value="${contact.primaryPhone}"></c:out></td>
						<td><c:out value="${contact.role}"></c:out>
						</td>
						<td>&nbsp;<a href="updateContact.html?id=${contact.contactId}">Edit</a>
							 &nbsp;&nbsp;<a
							href="deleteContact.html?id=${contact.contactId}">Delete</a>
						</td>
					</tr>
				</c:forEach>
							<tr><td colspan="4"> <a href="addContact.html">Add New Contact</a></td></tr>
				
			</c:if>
		</table>
		</center>
</body>
</html>