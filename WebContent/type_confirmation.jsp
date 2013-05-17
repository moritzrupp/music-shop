<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation New Type</title>
</head>
<body>
	<h1>Confirm the New Type</h1>
	<form action="TypeProcessing" method="post">
		<p>You have entered the following data:</p>
		
		<label for="typeName">Name:</label>
		<input readonly="readonly" type="text" id="typeName" name="typeName" value="${ type.name }"><br>
		<label for="typeIcon">Icon: </label>
		<img border="0" src="${ icon }" alt="Type icon" width="25" height="25" /><br />
		
		<button type="submit" name="typeEdit" id="typeBack">Edit</button>
		<button type="submit" name="typeConfirm" id="typeConfirm">Confirm</button>
	</form>
</body>
</html>