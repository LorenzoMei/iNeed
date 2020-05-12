<%@ include file="components/TitleSetter.jsp"%>
<%@ include file="components/AuthChecker.jsp" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="logic.viewanad.ViewAnAdController"
	import="java.util.List" import="java.text.SimpleDateFormat"
	import="java.util.Calendar" import="logic.dao.UserNotFoundException"
	import="logic.login.WrongPasswordException"%>


<jsp:useBean id="viewFlowBean" scope="request"
	class="logic.beans.OrderedAdsBean" />
	

<%
	viewFlowBean.setOrderByTime();
	ViewAnAdController controller = ViewAnAdController.getReference();
	controller.listAllAds(viewFlowBean);

	if (request.getParameter("id") != null) {
		int identify = Integer.parseInt(request.getParameter("id"));
%>

<jsp:useBean id="viewAdBean" scope="request"
	class="logic.beans.ViewAdBean" />

<jsp:setProperty name="viewAdBean" property="title"
	value="<%=viewFlowBean.getTitle(identify)%>" />

<jsp:setProperty name="viewAdBean" property="type"
	value="<%=viewFlowBean.getType(identify)%>" />

<jsp:setProperty property="dateOfPublication" name="viewAdBean"
	value="<%=viewFlowBean.getDateOfPublication(identify)%>" />

<jsp:setProperty name="viewAdBean" property="body"
	value="<%=viewFlowBean.getBody(identify)%>" />

<jsp:setProperty name="viewAdBean" property="author"
	value="<%=viewFlowBean.getOwner(identify)%>" />

<jsp:setProperty name="viewAdBean" property="id"
	value="<%=viewFlowBean.getId(identify)%>" />

<%
	request.setAttribute("bean", viewAdBean);
%>
<jsp:forward page="ViewAd.jsp" />
<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link rel="stylesheet" type="text/css" href="css/style-ViewFlow.css">
</head>
<body>
	<div >
		<jsp:include page="components/ToolBar.jsp"></jsp:include>
	</div>
	<div class="container-fluid">
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Type</th>
					<th>Title</th>
					<th>Author</th>
					<th>Category</th>
					<th>Date</th>
				</tr>
			</thead>
			<tbody>

				<%
					for (int i = 0; i < viewFlowBean.getNumOfAds(); i++) {

						int id = viewFlowBean.getId(i);
						String type = viewFlowBean.getType(i);
						String title = viewFlowBean.getTitle(i);
						String author = viewFlowBean.getOwner(i);
						String category = viewFlowBean.getCategory(i);
						SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
						String date = format.format(viewFlowBean.getDateOfPublication(i).getTime());

						String urlPage = String.format("ViewFlow.jsp?id=%d", i);

						out.print(String.format("<tr>" + "<td>%s</td>" + "<td><a href=%s>%s</a></td>" + "<td>%s</td>"
								+ "<td>%s</td>" + "<td>%s</td>" + "</tr>", type, urlPage, title, author, category, date));
					}
				%>

			</tbody>
		</table>
	</div>

</body>
</html>