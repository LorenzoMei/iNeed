<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar" import="java.text.SimpleDateFormat"
    import="logic.checkanswersofanad.CheckAnswersController"
    import="logic.checkanswersofanad.RequestAdHasAlreadyAnAnswerAcceptedException"
    import="logic.beans.RetrieveContactInfoBean"
    import="logic.contactuser.ContactUserController"
    import="logic.beans.ContactInfo"%>

<%@ include file="components/AuthChecker.jsp" %>

<jsp:useBean id="checkAnswersBean" scope="request"
	class="logic.beans.CheckAnswersBean" />
	
<% 
	CheckAnswersController controller = CheckAnswersController.getInstance();
	int adId = -1;
	String adType = null;
	
	if(request.getParameter("adId") != null && (request.getParameter("adType").compareTo("RequestAd") == 0
		|| request.getParameter("adType").compareTo("OfferAd") == 0)){
		adId = Integer.parseInt(request.getParameter("adId"));
		adType = request.getParameter("adType");
		String answeredUsername = session.getAttribute("username").toString();
		%>
		<jsp:setProperty property="adId" name="checkAnswersBean" value="<%=adId%>"/>
		<jsp:setProperty property="adType" name="checkAnswersBean" value="<%=adType%>"/>
		<%
		System.out.println(String.format("Sto eseguendo il controller con questi parametri: %d , %s", adId, adType));
		controller.answersList(checkAnswersBean);
		
		if(request.getParameter("index") != null){
			int index = Integer.parseInt(request.getParameter("index"));
			request.setAttribute("author", checkAnswersBean.getAnswerer(index));
			request.setAttribute("adId", adId);
			request.setAttribute("adType", adType);
			String action = request.getParameter("action");
			
			if(action.compareTo("accept") == 0){
				%>
				<jsp:forward page="ActionOnAnswer.jsp?action=<%=action %>"/>
				<%
			}
			else if(action.compareTo("deny") == 0){
				%>
				<jsp:forward page="ActionOnAnswer.jsp?action=<%=action %>"/>
				<%
			}
		}
	}
	else{
		out.println("ERROR DATA!");
	}
%>
		
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
  	<script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<title> Check Answers </title>
</head>
<body>
	<div class="container-fluid">
		<div >
			<jsp:include page="components/ToolBar.jsp"></jsp:include>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Author</th>
					<th>Date</th>
					<th></th>
				</tr>
			</thead>
			<tbody>

				<%

					RetrieveContactInfoBean retrieveContactInfoBean = new RetrieveContactInfoBean();
				
					for (int i = 0; i < checkAnswersBean.getNumOfAnswers(); i++) {

						String author = checkAnswersBean.getAnswerer(i);
						
						retrieveContactInfoBean.setUsername(author);
						ContactUserController.getInstance().retrieveContactUserInfo(retrieveContactInfoBean);
						String email = retrieveContactInfoBean.getInfo(ContactInfo.MAIL);
						
						SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
						String date = format.format(checkAnswersBean.getDate(i).getTime());

						out.print(String.format("<tr>" + "<td id='authorCol'>%s</td>" + "<td>%s</td>", author, date));
						out.print(String.format("<td><button name='contactUser' id='contactUser' value='ContactUser' onClick=" 
								+ "\"contact('%s', '%s')\""
								+ " class='btn btn-primary' data-toggle='modal' data-target='#actionModal'>Contact User</button>"
								, author, email)); 
						out.print(String.format("<a href='CheckAnswers.jsp?adId=%d&adType=%s&index=%d&action=accept' class='btn btn-success'>Accept</a>"
								, adId, adType, i));
						out.print(String.format("<a href='CheckAnswers.jsp?adId=%d&adType=%s&index=%d&action=deny' class='btn btn-danger'>Deny</a>"
								, adId, adType, i));
								
					}
				%>

			</tbody>
		</table>
		
		<div id="actionModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title" id="modalHeader"></h4>
		      </div>
		      <div class="modal-body">
		        <p id="modalBody"></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
				
	</div>
	
	<script type="text/javascript">
		function contact(author, email){
			
			document.getElementById("modalHeader").innerHTML = "Contact User";
			document.getElementById("modalBody").innerHTML = "You can contact " + author + " using this info: " + email;
		}
	</script>
</body>
</html>