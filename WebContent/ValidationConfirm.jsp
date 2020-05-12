<%@ include file="components/TitleSetter.jsp"%>
<%@ include file="components/AuthChecker.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.text.DateFormat"
    import="java.util.Calendar"
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<jsp:include page="components/ToolBar.jsp"></jsp:include>
<div class="container bg-light border border-warning">
		<span>
			<div class="row">
				<div class="col-sm-10">
					<h1>Are you sure you want to validate this favor?</h1>
				</div>
				<div class="col-sm">
					<img src="https://img.icons8.com/clouds/100/000000/question-mark.png"/>
				</div>
			</div>

		</span> <span>
			<p>You are going to validate a favor made by <%= request.getAttribute("offererUsername").toString() %>
				requested in date <%= DateFormat.getDateInstance().format(((Calendar)request.getAttribute("dateOfRequest")).getTime()) %>
			</p>
			<p><strong>Are you sure?</strong></p>
		</span>
		<div class="btn-group">
			<a href="ValidateAFavor.jsp"><button type="button" class=btn-outline-warning>Cancel</button></a>
			<a href="Validation.jsp?i=<%= request.getParameter("i") %>&confirmed=y"> <button type="button" class=btn-outline-success> Go!</button> </a>
		</div>
	</div>
</body>
</html>