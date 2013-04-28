<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.lang.String"%> <!DOCTYPE>
<html>
<head>
<title>Erste JSP</title>
</head>
<body>
<form>
<%String value = request.getParameter("name");%>
<p> Das ist eine einfache HTML-Seite mit einem Eingabefeld. Test, test, test. <%if(value != null && value.length() > 0) { %>
<p> Folgender Name wurde eingegeben: <b> <%= value %> </b> <% } %>
<p> Name der Person:
<input type="text" name = "name"/>
<input type = "submit" name = "confirmButton" value="Eingabe"/>
      </form>
   </body>
</html>