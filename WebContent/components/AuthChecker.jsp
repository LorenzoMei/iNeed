<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<% if (session.getAttribute("username") == null){%>
	<jsp:forward page="Login.jsp"></jsp:forward>
<% } %>
</body>
</html>