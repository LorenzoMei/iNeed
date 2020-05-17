<%@ include file="components/TitleSetter.jsp"%>
<%@ include file="components/AuthChecker.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="components/ToolBar.jsp"></jsp:include>
	</div>
	<p class="text-danger">Unable to validate favor because this exception occurred: <%=request.getParameter("e") %></p>

</body>
</html>