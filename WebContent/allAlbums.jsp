<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Albums</title>
</head>
<body>
<form action="AllAlbumsProcessing" method="post" style="float:left;">
	<button type="submit" name="shoppingBasket" id="shoppingBasket" value="shoppingBasket">Shopping Basket</button>
	<button type="submit" name="newType" id="newType" value="newType">New Type</button>
	<button type="submit" name="newAlbum" id="newAlbum" value="newAlbum">New Album</button>
</form>
<form action="MediumProcessing" method="get">
	<button type="submit" name="newMedium" id="newMedium" value="newMedium">New Medium</button>
</form>
<h1>All Albums</h1>
        <table>
            <c:forEach var="album" items="${albums}">
                <tr>
                    <td>
                        <c:out value="${album.name}"/>
                    </td>
	                <td>
	                    <c:out value="${album.interpreter}"/>
	                </td>                
	                <td>
						<form method="post"  action="AllAlbumsProcessing">
						<input type="hidden" name="id" value="${album.id}">
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