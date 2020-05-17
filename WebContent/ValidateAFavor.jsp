<%@ include file="components/TitleSetter.jsp"%>
<%@ include file="components/AuthChecker.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="logic.misc.Order"
	import="logic.validateafavor.ValidateAFavorController"
	import="java.text.SimpleDateFormat"
	import="logic.dao.FavorNotFoundException"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<!-- puts all favors infos in a table -->
	
		<div>
			<jsp:include page="components/ToolBar.jsp"></jsp:include>
		</div>
		
		<div>
			<jsp:useBean class="logic.beans.ListAllFavorsToValidateBean" id="bean"></jsp:useBean>
			
			<%
				bean.setQueriedRequesterUsername(session.getAttribute("username").toString());
				bean.setOrder(Order.TIME);
				ValidateAFavorController.getReference().listAllFavorsToValidate(bean);

				if (request.getParameter("i") != null
						&& Integer.parseInt(request.getParameter("i")) <= bean.getNumOfFavors()
						) {
					request.setAttribute("dateOfRequest",
							bean.getDateOfRequest(Integer.parseInt(request.getParameter("i"))));
					request.setAttribute("offererUsername",
							bean.getOffererUsername(Integer.parseInt(request.getParameter("i"))));
			%>
			<jsp:forward
				page="ValidationConfirm.jsp?i=<%=request.getParameter(\"i\")%>"></jsp:forward>
			
			<%
				}
			%>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Date of Request</th>
					<th>Offerer</th>
				</tr>
			</thead>
			<tbody>

				<%
					for (int i = 0; i < bean.getNumOfFavors(); i++) {

						SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
						String date = format.format(bean.getDateOfRequest(i).getTime());
						String offerer = bean.getOffererUsername(i);

						String urlPage = String.format("ViewFlow.jsp?id=%d", i);

						out.print(String.format(
								"<tr>" + "<td>%s</td>" + "<td>%s</td>"
										+ "<td><a href=\"ValidateAFavor.jsp?i=%s\"> Validate! </a></td>" + "</tr>",
								date, offerer, i));
					}
				%>

			</tbody>
		</table>
	</div>
</body>
</html>