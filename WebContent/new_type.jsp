<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Type</title>
</head>
<body>
<h1>New Type</h1>
<p>
Type in the textbox the new type you want to create and click on submit.
</p>
<form action="type_confirmation.jsp">
<label for="newType">New type: </label>
<input type="text" id="newType" name="newType"><br>
<label for="icon">Icon: </label>
<input type="file" id="coverPicture" name="coverPicture" accept="image/*" /> <br />

<button type="reset" name="reset" id="reset">Reset</button>
<button type="submit" name="confirmButton" id="confirmButton">Submit</button>
</form>
</body>
</html>