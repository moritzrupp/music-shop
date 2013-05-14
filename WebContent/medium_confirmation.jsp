<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm New Medium</title>
</head>
<body>
	<form action="MediumProcessing" action="post">
		<h1>Confirm the New Medium</h1>
		<p>You have entered the following medium data:</p>

		<label for="mediumType">Medium type:</label>
		<input readonly="readonly" type="text" id="mediumType" name="mediumType" value="${ medium.mediaType }" /><br />
		
		<label for="mediumTitle">Title:</label>
		<input readonly="readonly" type="text" id="mediumTitle" name="mediumTitle" value="${ medium.title }" /><br />
		
		<label for="mediumInterpreter">Interpreter:</label>
		<input readonly="readonly" type="text" id="mediumInterpreter" name="mediumInterpreter" value="${ medium.interpreter }" /><br />

		<!-- <core:choose>
			<core:when test="${ param.mediumIsInAlbum == 'on' }">
				<input readonly="readonly" type="checkbox" id="mediumIsInAlbum"
					name="mediumIsInAlbum" value="checked" checked />
			</core:when>
			<core:otherwise>
				<input readonly="readonly" type="checkbox" id="mediumIsInAlbum"
					name="mediumIsInAlbum" value="checked" />
			</core:otherwise>
		</core:choose>
		Is in album? <br />
		<label for="mediumAlbum">Album name:</label>
		<input readonly="readonly" type="text" id="mediumAlbum" name="mediumAlbum" value="${ param.mediumAlbum }" /><br />-->

		<button type="submit" name="mediumEdit" id="mediumEdit">Edit</button>
		<button type="submit" name="mediumComfirm" id="mediumComfirm">Confirm</button>
	</form>
</body>
</html>