<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ page errorPage="error500.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Album Details</title>
</head>
<body>
<h1>Album Details:</h1>
<img border="0" src="${album.coverPicture}" alt="Cover of album ${ album.name }" width="200" height="200" /><br />
<table>
	<tr>
		<td>Name:
		</td>
		<td>${album.name}
		</td>
	</tr>
	<tr>
		<td>Interpreter:
		</td>
		<td>${album.interpreter}
		</td>
	</tr>
</table>
<table>
    <c:forEach var="medium" items="${album.mediaList}">
        <tr>
            <td>
                <c:out value="${medium.title}"/>
            </td>
         <td>
             <c:out value="${medium.interpreter}"/>
           </td>                
           <td>
				<form method="post"  action="AllMediaProcessing">
				<input type="hidden" name="id" value="${medium.id}">
				<button type="submit" name="buy" id="buy" value="buy">Buy</button>
				
				<button type="submit" name="details" id="details" value="details">Details</button>
				
				<button type="submit" name="play" id="play" value="play">Play</button>
				</form>
           </td>
          </tr>
      </c:forEach>
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