<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Album</title>
</head>
<body>
	<h1>New Album</h1>
	<p>Fill out the form beneath to create a new album.</p>
	<form action="AlbumProcessing" enctype="multipart/form-data" method="post">
		<label for="albumName">Name: </label> 
		<input type="text" id="albumName" name="albumName" value="${ param.albumName }" required /><br />
		
		<label for="albumInterpreter">Interpreter:</label> 
		<input type="text" id="albumInterpreter" name="albumInterpreter" value="${ param.albumInterpreter }" required /><br />
		
		<label for="albumCover">Cover picture: </label> 
		<input type="file" id="albumCover" name="albumCover" accept="image/*" value="${ param.albumCover }" required /><br />

		<button type="reset" name="albumCancel" id="albumCancel">Cancel</button>
		<button type="submit" name="albumSubmit" id="albumSubmit">Next</button>
	</form>
</body>
</html>