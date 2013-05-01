<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation New Type</title>
</head>
<body>
<h1>New Type</h1>
<form>
<p>You have entered the following data:</p>
<label for="newType">New type: </label>
<input disabled="disabled" type="text" id="newType" name="newType" value="${ param.newType }"><br>
<label for="icon">Icon: </label>
<img border="0" src="" alt="Icon" width="25" height="25"> <br />

<!-- <button type="button" name="reset" id="reset">Reset</button>
<button type="submit" name="confirmButton" id="confirmButton">Submit</button>  -->
</form>
</body>
</html>