<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Media</title>
</head>
<body>
<form action="AllMediaProcessing" method="post">
	<button type="submit" name="shoppingBasket" id="shoppingBasket" value="shoppingBasket">Shopping Basket</button>
	<button type="submit" name="newType" id="newType" value="newType">New Type</button>
	<button type="submit" name="newMedium" id="newMedium" value="newMedium">New Medium</button>
	<button type="submit" name="newAlbum" id="newAlbum" value="newAlbum">New Album</button>
</form>
<h1>All Media</h1>
        <table>
            <c:forEach var="medium" items="${media}">
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
</body>
</html>