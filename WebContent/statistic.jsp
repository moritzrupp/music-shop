<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Statistics</title>
</head>
<body>
	<h1>Statistics</h1>
	
	<div style="width:800px;">
		<div style="float:left;">
			<h2>Top played media</h2>
			<table>
				<thead>
					<tr>
						<td>Title</td>
						<td>Interpreter</td>
						<td>Played</td>
					</tr>
				</thead>
				<tbody>
					<core:forEach var="medium" items="${ topPlayed }">
						<tr>
							<td>${ medium.title }</td>
							<td>${ medium.interpreter }</td>
							<td>${ medium.listened }</td>
						</tr>
					</core:forEach>
				</tbody>
			</table>
			<form action="ShowStatistic" method="get">
				<select name="num" id="num">
					<option <core:if test="${ num == 5 }"><core:out value="selected"/></core:if> >5</option>
					<option <core:if test="${ num == 10 }"><core:out value="selected"/></core:if> >10</option>
					<option <core:if test="${ num == 15 }"><core:out value="selected"/></core:if> >15</option>
					<option <core:if test="${ num == 20 }"><core:out value="selected"/></core:if> >20</option>
					<option <core:if test="${ num == 30 }"><core:out value="selected"/></core:if> >30</option>
					<option <core:if test="${ num == 40 }"><core:out value="selected"/></core:if> >40</option>
					<option <core:if test="${ num == 50 }"><core:out value="selected"/></core:if> >50</option>
				</select>
				<button type="submit">Show</button>
			</form>
			
			<br />
			<br />
			
			<form action="AllMediaProcessing">
				<button type="submit" name="back" id="back">Back</button>
			</form>
		</div>
	
		<div style="float:right;">
			<h2>Top bought media</h2>
			<table>
				<thead>
					<tr>
						<td>Title</td>
						<td>Interpreter</td>
						<td>Bought</td>
					</tr>
				</thead>
				<tbody>
					<core:forEach var="medium" items="${ topBought }">
						<tr>
							<td>${ medium.title }</td>
							<td>${ medium.interpreter }</td>
							<td>${ medium.sold }</td>
						</tr>
					</core:forEach>
				</tbody>
			</table>
			<form action="ShowStatistic" method="get">
				<select name="limit" id="limit">
					<option <core:if test="${ limit == 5 }"><core:out value="selected"/></core:if> >5</option>
					<option <core:if test="${ limit == 10 }"><core:out value="selected"/></core:if>>10</option>
					<option <core:if test="${ limit == 15 }"><core:out value="selected"/></core:if>>15</option>
					<option <core:if test="${ limit == 20 }"><core:out value="selected"/></core:if>>20</option>
					<option <core:if test="${ limit == 30 }"><core:out value="selected"/></core:if>>30</option>
					<option <core:if test="${ limit == 40 }"><core:out value="selected"/></core:if>>40</option>
					<option <core:if test="${ limit == 50 }"><core:out value="selected"/></core:if>>50</option>
				</select>
				<button type="submit">Show</button>
			</form>
		</div>
	</div>
</body>
</html>
