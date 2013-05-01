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
		<p>You have entered the following medium data:</p>

		<label for="mediumType">Medium type:</label> <select
			disabled="disabled" id="mediumType" name="mediumType">

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
			disabled="disabled" type="text" id="mediumTitle" name="mediumTitle"
			value="${ param.mediumTitle }" /> <br /> <label
			for="mediumInterpreter">Interpreter:</label> <input
			disabled="disabled" type="text" id="mediumInterpreter"
			name="mediumInterpreter" value="${ param.mediumInterpreter }" /> <br />

		<core:choose>
			<core:when test="${ param.mediumIsInAlbum == 'checked' }">
				<input disabled="disabled" type="checkbox" id="mediumIsInAlbum"
					name="mediumIsInAlbum" value="checked" checked />
			</core:when>
			<core:otherwise>
				<input disabled="disabled" type="checkbox" id="mediumIsInAlbum"
					name="mediumIsInAlbum" value="checked" />
			</core:otherwise>
		</core:choose>
		Is in album? <br /> <label for="mediumAlbum"></label> <input
			disabled="disabled" type="text" id="mediumAlbum" name="mediumAlbum"
			value="${ param.mediumAlbum }" /><br /> <!-- <label for="mediumFile">Upload:</label> <input
			disabled="disabled" type="file" id="mediumFile" name="mediumFile"
			accept="audio/*, video/*" />  --><br />

		<!-- <button type="reset" name="mediumReset" id="mediumReset"
			value="Cancel">Cancel</button>
		<button type="submit" name="mediumSubmit" id="mediumSubmit"
			value="Submit">Submit</button>  -->
	</form>
</body>
</html>