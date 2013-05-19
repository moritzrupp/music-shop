<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Print</title>
</head>
<body>
<h1>Print</h1>
<p>You have bought ${numbers} songs.</p>
	<form method="post"  action="ShoppingBasket">
		<button type="submit" name="back" id="back" value="back">Back</button>
	</form>
</body>
</html>