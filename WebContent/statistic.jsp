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
	
	<div style="width:800px">
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
					<option>5</option>
					<option>10</option>
					<option>15</option>
					<option>20</option>
					<option>30</option>
					<option>40</option>
					<option>50</option>
				</select>
				<button type="submit">Show</button>
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
					<option>5</option>
					<option>10</option>
					<option>15</option>
					<option>20</option>
					<option>30</option>
					<option>40</option>
					<option>50</option>
				</select>
				<button type="submit">Show</button>
			</form>
		</div>
	</div>
</body>
</html>