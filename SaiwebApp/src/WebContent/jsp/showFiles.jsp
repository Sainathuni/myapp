<%@include file="taglib_includes.jsp"%>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" media="all" href="css/sai.css" />

<title><spring:message code="App.Title"></spring:message></title>
<script type="text/javascript" src="js/contacts.js"></script>
<body style="font-family: Arial; font-size: smaller;">
<%@include file="header.jsp" %>
<div id="container">

		<table style="border-collapse: collapse;" border="0"
			bordercolor="#006699" width="500">
			
			<tr>
				<td align="center"><h3>Uploaded Files</h3></td>
				<td><input type="button"
					value="Upload File" onclick="javascript:go('uploadFile.html?id=${MANDIR_ID}&code=${MANDIR_CODE}');" /></td>
			</tr>
		</table>
		
		<table style="border-collapse: collapse;" border="1"
			bordercolor="#006699" width="980">
			
			<tr bgcolor="lightblue">
				<th>File Name</th>
				<th>URL</th>
				<th>Description</th>
				<th></th>
			</tr>
			<c:if test="${empty FILE_LIST}">
				<tr>
					<td colspan="4">No Results found</td>
				</tr>
			</c:if>
			<c:if test="${! empty FILE_LIST}">
				<c:forEach var="fileInfo" items="${FILE_LIST}">
					<tr>
						<td><c:out value="${fileInfo.fileName}"></c:out></td>
						<td><c:out value="${fileInfo.url}"></c:out></td>
						<td><c:out value="${fileInfo.description}"></c:out></td>
						<td><a href="deleteFile.html?id=${fileInfo.id}&code=${MANDIR_CODE}&mandirId=${MANDIR_ID}">Delete</a> 
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
</div>

</body>
</html>