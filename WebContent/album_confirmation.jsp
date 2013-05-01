<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm New Album</title>
</head>
<body>

	<h1>Confirm the new Album</h1>

	<form>
		<p>You have entered the following album data:</p>
	
		<label for="title">Name: </label> <input disabled="disabled"
			type="text" id="name" name="name" value="${ param.name }"><br> <label
			for="interpreter">Interpreter: </label> <input disabled="disabled"
			type="text" id="interpreter" name="interpreter" value="${ param.interpreter }"><br> <label
			for="coverPicture">Cover picture: </label> <img border="0" src=""
			alt="Cover picture" width="100" height="100"> <br />

		<!-- <button type="reset" name="cancel" id="cancel">Cancel</button>
		<button type="submit" name="confirmButton" id="confirmButton">Submit</button>  -->
	</form>
</body>
</html>