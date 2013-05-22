<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Basket</title>
</head>
<body>
<h1>Shopping Basket</h1>
        <table>
            <thead>
            	<tr>
            		<td>&nbsp;</td>
            		<td>Title</td>
            		<td>Interpreter</td>
            		<td>&nbsp;</td>
            	</tr>
            </thead>
            <tbody>
            <c:forEach var="medium" items="${shoppingBasket}">
                <tr>
                	<td>
                		<img src="${ medium.mediaType.icon }" width="25" height="25" alt="Medium icon" border="0" />
                	</td>
                    <td>
                        <c:out value="${medium.title}"/>
                    </td>
	                <td>
	                    <c:out value="${medium.interpreter}"/>
	                </td>                
	                <td>
						<form method="post"  action="ShoppingBasket">
						<input type="hidden" name="id" value="${medium.id}">
						<button type="submit" name="delete" id="delete" value="delete">Delete</button>

						<button type="submit" name="details" id="details" value="details">Details</button>

						<button type="submit" name="play" id="play" value="play">Play</button>
						</form>
	                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        
	<form action="AllMediaProcessing">
		<button type="submit" name="back" id="back">Back</button>
	</form>
	<form method="post"  action="ShoppingBasket">	
		<button type="submit" name="clear" id="clear" value="clear">Clear</button>
		<button type="submit" name="print" id="print" value="print">Print</button>
	</form>
</body>
</html>