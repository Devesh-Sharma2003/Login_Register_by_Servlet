<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 
	String n = (String) session.getAttribute("names");
	String m = (String) session.getAttribute("mails");
	String c = (String) session.getAttribute("cities");
	String g = (String) session.getAttribute("genders");
	String d = (String) session.getAttribute("dobs");
	%>
	<h1 style="color:black">Welcome: <%= n %></h1>
	<h3>Your Information:</h3><br><br>
	Mail Id: <%= m %><br>
	City: <%= c %><br>
	Gender: <%= g %><br>
	Date of Birth: <%= d %><br>
</body>
</html>