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
<h1>Confirm the New type</h1>
<p>You have entered the following data:</p>
<label for="newType">New type: </label>
<input readonly="readonly" type="text" id="newType" name="newType" value="${ param.newType }"><br>
<label for="icon">Icon: </label>
<img border="0" src="" alt="Icon" width="25" height="25"> <br />

<button type="submit" name="typeBack" id="typeBack">Back</button>
<button type="submit" name="typeNext" id="typeNext">Next</button>
</form>
</body>
</html>