<%@page import="control.SQLController"%>
<%@page import="model.MediaType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Medium</title>
</head>
<body>
	<h1>New Medium</h1>
	<%
		SQLController sql = new SQLController();
		List<MediaType> types = sql.getAllMediaTypes();
		
		if(types != null) {
			
	%>
		
		<p>Please enter the data for a new medium.</p>
		<form action="MediumProcessing" enctype="multipart/form-data" method="post">
			<label for="mediumType">Medium type:</label>
			<select id="mediumType" name="mediumType">
				<% 
					for(MediaType type : types) {
				%>
					<option value="<%= type.getId() %>"><%= type.getName() %></option>
				<%
					}
				%>
			</select><br />
			
			<label for="mediumTitle">Title:</label>
			<input type="text" id="mediumTitle" name="mediumTitle" value="${ medium.title }" required /><br />
			
			<label for="mediumInterpreter">Interpreter:</label>
			<input type="text" id="mediumInterpreter" name="mediumInterpreter" value="${ medium.interpreter }" required /><br />
					
			<!-- <core:choose>
				<core:when test="${param.mediumIsInAlbum=='checked'}">
					<input type="checkbox" id="mediumIsInAlbum" name="mediumIsInAlbum" checked />
				</core:when>
				<core:otherwise>
					<input type="checkbox" id="mediumIsInAlbum" name="mediumIsInAlbum"/>
				</core:otherwise>
			</core:choose>
			
			 Is in album? <br />
			<label for="mediumAlbum"></label>
			<input type="text" id="mediumAlbum" name="mediumAlbum" value="${ param.mediumAlbum }" /><br /> -->
			
			<label for="mediumFile">Upload:</label>
			<input type="file" id="mediumFile" name="mediumFile" accept="audio/*, video/*" required /><br />
			
			<button type="reset" name="mediumCancel" id="mediumCancel" value="Cancel">Cancel</button>
			<button type="submit" name="mediumSubmit" id="mediumSubmit" value="Submit">Next</button>
		</form>
	<%
		}
		else {
	%>
		<p>Yet, there aren't any media types in the database. Before you can add a medium, insert media types.</p>
	<%
		}
	%>
</body>
</html>