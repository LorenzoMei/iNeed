<%@ include file="components/TitleSetter.jsp"%>
<%@ include file="components/AuthChecker.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="logic.answeranad.AnswerAnAdController"
	import="logic.answeranad.UserAlreadyAnsweredException"
	import="java.text.DateFormat"%>
<%@ include file="/components/TitleSetter.jsp"%>
<%@ include file="/components/AuthChecker.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<jsp:include page="components/ToolBar.jsp"></jsp:include>
	<%
		if (session.getAttribute("username").toString()
				.compareTo(request.getParameter("answererUsername").toString()) != 0) {
	%>
	<jsp:forward page="Login.jsp"></jsp:forward>
	<%
		} else {
	%>
	<jsp:useBean id="bean" class="logic.beans.AnswerAnAdBean"></jsp:useBean>
	<%
		bean.setId(Integer.parseInt(request.getParameter("adId").toString()));
			bean.setType(request.getParameter("adType").toString());
			bean.setUsername(request.getParameter("answererUsername").toString());
	%>
	<%
		try {
				AnswerAnAdController.getInstance().answer(bean);
	%>
	<div class="container bg-light border border-info">
		<span>
			<div class="row">
				<div class="col-sm-10">
					<h1>Ad Succesfully Answered!</h1>
				</div>
				<div class="col-sm">
					<img src="https://img.icons8.com/cute-clipart/64/000000/ok.png" />
				</div>
			</div>

		</span> <span>
			<p>Now all you have to do is to wait. If the owner of the ad will
				accept your answer they will contact you, so check your contact
				infos for incoming messages.</p>
		</span>
	</div>

	<%
		} catch (UserAlreadyAnsweredException e) {
	%>
	<div class="container bg-light border border-danger">
		<div class="row">
			<div class="col-sm-10">
				<h1>You can't answer more than one time to an ad!</h1>
			</div>
			<div class="col-sm">
				
				<img src="https://img.icons8.com/flat_round/64/000000/error--v1.png"/>
			</div>
		</div>
		<span>
			<p>
				You already answered to this ad in date
				<%=DateFormat.getDateInstance().format(e.getDate().getTime())%></p>
		</span>
	</div>
	<%
		}
	%>
	<%
		}
	%>
</body>
</html>