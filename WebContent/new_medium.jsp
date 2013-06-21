<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/scripts.js"></script>
<title>Create New Medium</title>
</head>
<body>
	<h1>New Medium</h1>
	<core:choose>
		<core:when test="${ types != null }">
			<p>Please enter the data for a new medium.</p>
			<form action="MediumProcessing" enctype="multipart/form-data" method="post">
				<label for="mediumType">Medium type:</label>
				<select id="mediumType" name="mediumType">
					<core:forEach var="type" items="${ types }">
						<option value="${ type.id }"><core:out value="${ type.name }" /></option>
					</core:forEach>
				</select><br />
				
				<label for="mediumTitle">Title:</label>
				<input type="text" id="mediumTitle" name="mediumTitle" value="${ medium.title }" required /><br />
				
				<label for="mediumInterpreter">Interpreter:</label>
				<input type="text" id="mediumInterpreter" name="mediumInterpreter" value="${ medium.interpreter }" required /><br />
				
				<label for="mediumDuration">Duration:</label>
				<input type="text" id ="mediumDuration" name="mediumDuration" value="${ medium.duration }" required /> (Format: <span style="font-style: italic;">mm:ss</span>)<br />
				
				<label for="mediumAlbum">Album:</label>
				
				<core:choose>
					<core:when test="${ include != null && include == true }">
						<select id="mediumAlbum" name="mediumAlbum" disabled="disabled">
							<option><core:out value="${ album.name }" /></option>
						</select>
					</core:when>
					<core:otherwise>
						<select id="mediumAlbum" name="mediumAlbum">
							<option value="-1">No album</option>
							<core:if test="${ albums != null }">
								<core:forEach var="album" items="${ albums }">
									<option value="${ album.id }"
										<core:if test="${ medium.album.name == album.name }">
											<core:out value="selected" />
										</core:if>
									><core:out value="${ album.name }" /></option>
								</core:forEach>
							</core:if>
						</select>
					</core:otherwise>
				</core:choose>
				<br />
				
				<label for="mediumFile">Upload:</label>
				<input type="file" id="mediumFile" name="mediumFile" accept="audio/*, video/*" required /><br />
				
				<button type="reset" name="mediumCancel" id="mediumCancel" onclick="resetForm(this.form)">Cancel</button>
				<button type="submit" name="mediumSubmit" id="mediumSubmit">Next</button>
			</form>
		</core:when>
		<core:otherwise>
			<p>Yet, there aren't any media types in the database. Before you can add a medium, insert media types.</p>
		</core:otherwise>
	</core:choose>
</body>
</html>