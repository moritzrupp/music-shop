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
	<form>
		<h1>Confirm the New Medium</h1>
		<p>You have entered the following medium data:</p>

		<label for="mediumType">Medium type:</label> <select
			id="mediumType" name="mediumType">

			<core:choose>
				<core:when test="${ param.mediumType == 'audio' }">
					<option value="audio" selected>Audio</option>
					<option value="video">Video</option>
				</core:when>
				<core:otherwise>
					<option value="audio">Audio</option>
					<option value="video" selected>Video</option>
				</core:otherwise>
			</core:choose>
		</select> <br /> <label for="mediumTitle">Title:</label> <input
			readonly="readonly" type="text" id="mediumTitle" name="mediumTitle"
			value="${ param.mediumTitle }" /> <br /> <label
			for="mediumInterpreter">Interpreter:</label> <input
			readonly="readonly" type="text" id="mediumInterpreter"
			name="mediumInterpreter" value="${ param.mediumInterpreter }" /> <br />

		<core:choose>
			<core:when test="${ param.mediumIsInAlbum == 'on' }">
				<input readonly="readonly" type="checkbox" id="mediumIsInAlbum"
					name="mediumIsInAlbum" value="checked" checked />
			</core:when>
			<core:otherwise>
				<input readonly="readonly" type="checkbox" id="mediumIsInAlbum"
					name="mediumIsInAlbum" value="checked" />
			</core:otherwise>
		</core:choose>
		Is in album? <br /> <label for="mediumAlbum"></label> <input
			readonly="readonly" type="text" id="mediumAlbum" name="mediumAlbum"
			value="${ param.mediumAlbum }" /><br /> <!-- <label for="mediumFile">Upload:</label> <input
			disabled="disabled" type="file" id="mediumFile" name="mediumFile"
			accept="audio/*, video/*" />  --><br />

		<button type="submit" name="mediumBack" id="mediumBack"
			>Back</button>
		<button type="submit" name="mediumNext" id="mediumNext"
			>Next</button>
	</form>
</body>
</html>