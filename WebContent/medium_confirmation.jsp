<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm New Medium</title>
</head>
<body>
	<form action="MediumProcessing" method="post">
		<h1>Confirm the New Medium</h1>
		<p>You have entered the following medium data:</p>

		<label for="mediumType">Medium type:</label>
		<input readonly="readonly" type="text" id="mediumType" name="mediumType" value="${ medium.mediaType.name }" /><br />
		
		<label for="mediumTitle">Title:</label>
		<input readonly="readonly" type="text" id="mediumTitle" name="mediumTitle" value="${ medium.title }" /><br />
		
		<label for="mediumInterpreter">Interpreter:</label>
		<input readonly="readonly" type="text" id="mediumInterpreter" name="mediumInterpreter" value="${ medium.interpreter }" /><br />

		<label for="mediumAlbum">Album name:</label>
		<input readonly="readonly" type="text" id="mediumAlbum" name="mediumAlbum" value="${ medium.album.name }" /><br />

		<button type="submit" name="mediumEdit" id="mediumEdit">Edit</button>
		<button type="submit" name="mediumConfirm" id="mediunComfirm">Confirm</button>
	</form>
</body>
</html>