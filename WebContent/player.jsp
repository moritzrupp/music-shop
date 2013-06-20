<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Media Player</title>
</head>
<body>
	<h1>Play the Medium</h1>
	
	<p>Title: <core:out value="${ medium.title }" /></p>
	<p>Played: <core:out value="${ medium.listened }" /></p>
	
	<core:choose>
		<core:when test="${ medium.mediaType.audio == 1 }">
			<audio controls>
				<source src="${ medium.fileLocation }" />
				Dieser Browser unterstützt HTML5 audio nicht
			</audio>
		</core:when>
		<core:otherwise>
			<video controls height="360" width="640">
				<source src="${ medium.fileLocation }" />
				Dieser Browser unterstützt HTML5 Video nicht
			</video>
		</core:otherwise>
	</core:choose>

<form action="AllMediaProcessing">
<button type="submit" name="back" id="back" value="${back}">Back</button>
</form>

</body>
</html>