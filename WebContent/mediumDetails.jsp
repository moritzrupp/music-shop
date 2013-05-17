<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ page errorPage="error500.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Medium Details</title>
</head>
<body>
<h1>Medium Details:</h1>
<table>
	<tr>
		<td>Title:
		</td>
		<td>${medium.title}
		</td>
	</tr>
	<tr>
		<td>Interpreter:
		</td>
		<td>${medium.interpreter}
		</td>
	</tr>
		<c:if test="${medium.album !=null}">
			<tr>
				<td>Album:
				</td>
				<td>${medium.album.name}
				</td>
			</tr>
		</c:if>
	<tr>
		<td>Duration:
		</td>
		<td>${medium.duration}
		</td>
	</tr>
	<tr>
		<td>File size:
		</td>
		<td>${medium.fileSize} Bytes
		</td>
	</tr>
</table>
<form action="AllMediaProcessing">
<button type="submit" name="back" id="back">Back</button>
</form>
<form method="post"  action="AllMediaProcessing">
<input type="hidden" name="id" value="${medium.id}">
<button type="submit" name="buy" id="buy" value="buy">Buy</button>
<button type="submit" name="play" id="play" value="play">Play</button>
</form>
</body>
</html>