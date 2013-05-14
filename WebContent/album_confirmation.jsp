<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ page errorPage="error500.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm New Album</title>
</head>
<body>
	<h1>Confirm the New Album</h1>
	<p>You have entered the following album data:</p>
	<form action="AlbumProcessing" method="post">
		<label for="albumName">Name:</label>
		<input readonly="readonly" type="text" id="albumName" name="albumName" value="${ album.name }" /><br />
			
		<label for="albumInterpreter">Interpreter:</label>
		<input readonly="readonly" type="text" id="albumInterpreter" name="albumInterpreter" value="${ album.interpreter }" /><br />
		
		<label for="albumCover">Cover:</label>
		<img border="0" src="${ sessionScope['cover'] }" alt="Cover of album ${ album.name }" width="100" height="100" /><br />

		<button type="submit" name="albumEdit" id="albumBack">Edit</button>
		<button type="submit" name="albumConfirm" id="albumNext">Confirm</button>
	</form>
</body>
</html>