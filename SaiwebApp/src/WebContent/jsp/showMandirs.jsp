<%@include file="taglib_includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />

<title><spring:message code="App.Title"></spring:message></title>
<script type="text/javascript" src="js/contacts.js"></script>
</head>
<body style="font-family: Arial; font-size: smaller;">
<%@include file="header.jsp" %>
<div id="container">
		<form action="searchMandirs.html" method="post">
			<table style="border-collapse: collapse;" border="0"
				bordercolor="#006699" width="500">
				<tr>
					<td>Enter Location</td>
					<td><input type="text" name="name" /> &nbsp;&nbsp;<input
						type="submit" value="Search" /> &nbsp;&nbsp;<input type="button"
						value="Add Mandir" onclick="javascript:go('addMandir.html');" /></td>
				</tr>
			</table>
		</form>

		<table style="border-collapse: collapse;" border="1"
			bordercolor="#006699" width="980">
			<tr bgcolor="lightblue">
				<th>Code</th>
				<th>Name</th>
				<th>Address</th>
				<th>Status</th>
				<th></th>
			</tr>
			<c:if test="${empty MANDIR_LIST}">
				<tr>
					<td colspan="4">No Results found</td>
				</tr>
			</c:if>
			<c:if test="${! empty MANDIR_LIST}">
				<c:forEach var="mandir" items="${MANDIR_LIST}">
					<tr>
						<td><c:out value="${mandir.code}"></c:out></td>
						<td><c:out value="${mandir.name}"></c:out></td>
						<td><c:out value="${mandir.mandirAddress.line1}"></c:out> ,<c:out
								value="${mandir.mandirAddress.city}"></c:out></td>
						<td><c:out value="${mandir.status.description}"></c:out></td>
						<td>&nbsp;<a href="updateMandir.html?id=${mandir.mandirId}">Edit</a>
							&nbsp;<a href="mandirDataFlow.html?id=${mandir.mandirId}">Data
								WorkFlow</a> &nbsp;&nbsp;<a
							href="deleteMandir.html?id=${mandir.mandirId}">Delete</a>
							&nbsp;&nbsp;<a
							href="showFiles.html?id=${mandir.mandirId}&code=${mandir.code}">Upload Files</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
</div>

</body>
</html>