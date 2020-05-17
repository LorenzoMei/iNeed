<%@ include file="components/TitleSetter.jsp"%>
<%@ include file="components/AuthChecker.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="logic.validateafavor.ValidateAFavorController"
	import="logic.misc.Order" import="logic.dao.FavorNotFoundException"%>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="components/ToolBar.jsp"></jsp:include>
	</div>
	
	<div>
		<jsp:useBean class="logic.beans.ListAllFavorsToValidateBean" id="bean"></jsp:useBean>
	
		<%
					bean.setQueriedRequesterUsername(session.getAttribute("username").toString());
					bean.setOrder(Order.TIME);
					ValidateAFavorController.getReference().listAllFavorsToValidate(bean);
					%>
		<%
					if (request.getParameter("i") != null
							&& Integer.parseInt(request.getParameter("i")) <= bean.getNumOfFavors()
							&& "y".equals(request.getParameter("confirmed"))) {
				%>
		<jsp:useBean id="validateAFavorBean"
			class="logic.beans.ValidateAFavorBean"></jsp:useBean>
		<jsp:setProperty property="offererUsername" name="validateAFavorBean"
			value="<%=bean.getOffererUsername(Integer.parseInt(request.getParameter(\"i\")))%>" />
		<jsp:setProperty property="requesterUsername" name="validateAFavorBean"
			value="<%=session.getAttribute(\"username\")%>" />
		<jsp:setProperty property="dateOfRequest" name="validateAFavorBean"
			value="<%=bean.getDateOfRequest(Integer.parseInt(request.getParameter(\"i\")))%>" />
		<%
					try {
							ValidateAFavorController.getReference().validateAFavor(validateAFavorBean);
							ValidateAFavorController.getReference().listAllFavorsToValidate(bean);
						} catch (Exception e) {
				%>
	
		<jsp:forward page="ValidationFail.jsp?e=<%=e.toString() %>"></jsp:forward>
	
		<%
					}
				%>
		<%} %>
		<jsp:forward page="ValidationSuccess.jsp"></jsp:forward>
	</div>

</body>
</html>