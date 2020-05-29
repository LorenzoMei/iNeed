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
	<div class="container bg-light border border-success">
		<span>
			<div class="row">
				<div class="col-sm-10">
					<h1>Favor validated successfully !</h1>
				</div>
				<div class="col-sm">
					<img src="https://img.icons8.com/cute-clipart/64/000000/ok.png" />
				</div>
			</div>

		</span> <span>
			<p>Thank you for using iNeed !</p>
		</span>
	</div>
</body>
</html>