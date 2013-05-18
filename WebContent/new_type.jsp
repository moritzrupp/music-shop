<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/scripts.js"></script>
<title>Create New Type</title>
</head>
<body>
	<h1>Create New Type</h1>
	
	<form action="TypeProcessing" method="post" enctype="multipart/form-data">
		<p>Type in the data for the type:</p>
		
		<label for="typeName">Name:</label>
		<input type="text" id="typeName" name="typeName" value="${ type.name }" required /><br />
		
		<label for="typeType">Type:</label>
		<select name="typeType" id="typeType">
			<option value="1">Audio</option>
			<option value="0">Video</option>
		</select><br />
		
		<label for="typeIcon">Icon: </label>
		<input type="file" id="typeIcon" name="typeIcon" accept="image/*" required /><br />
		
		<button type="reset" name="typeCancel" id="typeCancel" onclick="resetForm(this.form)">Cancel</button>
		<button type="submit" name="typeSubmit" id="typeSubmit">Next</button>
	</form>
</body>
</html>