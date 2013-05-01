<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Album</title>
</head>
<body>

	<h1>New Album</h1>
	<p>Fill out the form beneath and click submit, to create a new
		album.</p>
	<form action="/control/JSPController">
		<label for="title">Name: </label> <input type="text" id="name"
			name="name"><br> <label for="interpreter">Interpreter:
		</label> <input type="text" id="interpreter" name="interpreter"><br>

		<label for="coverPicture">Cover picture: </label> <input
			type="file" id="coverPicture" name="coverPicture" accept="image/*" />
		<br />

		<button type="reset" name="albumCancel" id="albumCancel">Cancel</button>
		<button type="submit" name="albumConfirm" id="albumConfirm">Submit</button>
	</form>

</body>
</html>