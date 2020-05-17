<%@ include file="components/TitleSetter.jsp"%>
<%@ include file="components/AuthChecker.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%session.removeAttribute("username"); %>
<jsp:forward page="Login.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>

</body>
</html>