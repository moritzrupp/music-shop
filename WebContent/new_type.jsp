<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Type</title>
</head>
<body>
	<h1>Create New Type</h1>
	
	<form action="TypeProcessing" method="post" enctype="multipart/form-data">
		<p>Type in the data for the type:</p>
		
		<label for="typeName">Name:</label>
		<input type="text" id="typeName" name="typeName" value="${ type.name }" /><br />
		
		<label for="typeIcon">Icon: </label>
		<input type="file" id="typeIcon" name="typeIcon" accept="image/*" /><br />
		
		<button type="reset" name="typeCancel" id="typeCancel">Reset</button>
		<button type="submit" name="typeSubmit" id="typeSubmit" >Next</button>
	</form>
</body>
</html>