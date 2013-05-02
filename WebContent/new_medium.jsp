<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Medium</title>
</head>
<body>
<h1>New Medium</h1>
<p>Please enter the data for a new medium.</p>

	<form action="control/JSPController">
		<label for="mediumType">Medium type:</label>
		<select id="mediumType" name="mediumType">
			<option value="audio">Audio</option>
			<option value="video">Video</option>
		</select> <br />
		
		<label for="mediumTitle">Title:</label>
		<input type="text" id="mediumTitle" name="mediumTitle" value="" /> <br />
		
		<label for="mediumInterpreter">Interpreter:</label>
		<input type="text" id="mediumInterpreter" name="mediumInterpreter" value="" /> <br />
		
		<input type="checkbox" id="mediumIsInAlbum" name="mediumIsInAlbum" value="checked" />Is in album? <br />
		<label for="mediumAlbum"></label>
		<input type="text" id="mediumAlbum" name="mediumAlbum" value="" /><br />
		
		<label for="mediumFile">Upload:</label>
		<input type="file" id="mediumFile" name="mediumFile" accept="audio/*, video/*" /> <br />
		
		<button type="reset" name="mediumCancel" id="mediumCancel" value="Cancel">Cancel</button>
		<button type="submit" name="mediumConfirm" id="mediumConfirm" value="Submit">Submit</button>
	</form>
</body>
</html>