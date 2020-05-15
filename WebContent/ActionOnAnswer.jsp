<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="logic.checkanswersofanad.CheckAnswersController"
	import="logic.checkanswersofanad.RequestAdHasAlreadyAnAnswerAcceptedException"
	import="logic.checkanswersofanad.AnswerAlreadyAcceptedException"
	import="logic.dao.AnswerNotFoundException"
	import="java.text.DateFormat"%>
    

<%@ include file="components/AuthChecker.jsp" %>
    
<jsp:useBean id="actionOnAnswerBean" scope="request"
	class="logic.beans.ActionOnAnswerBean"/>
	
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
	
	<title>Action On Answer</title>
</head>
<body>
	<div >
		<jsp:include page="components/ToolBar.jsp"></jsp:include>
	</div>
	<%	
	if(request.getParameter("action").contains("accept")){
		int adId = -1;
		String adType = "";
		String answeredUsername = "";
		String answererUsername = "";
		
		if(request.getParameter("action").compareTo("acceptC") == 0){
			adId = Integer.parseInt(request.getParameter("adId"));
			adType = request.getParameter("adType");
			answeredUsername = session.getAttribute("username").toString();
			answererUsername = request.getParameter("author");
			boolean confirmed = true;
			%>
			<jsp:setProperty property="confirmed" name="actionOnAnswerBean" value="<%=confirmed %>"/>
			<%
		}
		else{
			adId = (Integer) request.getAttribute("adId");
			adType = (String) request.getAttribute("adType");
			answeredUsername = session.getAttribute("username").toString();
			answererUsername = (String) request.getAttribute("author");
		}
		%>
		<jsp:setProperty property="adId" name="actionOnAnswerBean" value="<%=adId %>"/>
		<jsp:setProperty property="adType" name="actionOnAnswerBean" value="<%=adType%>"/>
		<jsp:setProperty property="answeredUsername" name="actionOnAnswerBean" value="<%=answeredUsername%>"/>
		<jsp:setProperty property="answererUsername" name="actionOnAnswerBean" value="<%=answererUsername%>"/>
		<%
		
		CheckAnswersController controller = CheckAnswersController.getInstance();
		try{
			controller.acceptAnswer(actionOnAnswerBean);
			%>
			<div id="container">
			
			    <div class="row">
			      <div class="col">
			        <h4 id="header">You successfully accepted this answer, nice!</h4>
			      </div>
			    </div>
			    <div class="row">
			      <div class="col">
			         <p id="body">After <% out.print(answererUsername); %> has done the favor, don't forget to validate it in Validate a Favor section.</p>
			      </div>
			    </div>
			</div>
			<%
		}catch(RequestAdHasAlreadyAnAnswerAcceptedException e){
			%>
			<div id="container">
			
			    <div class="row">
			      <div class="col">
			        <h4 id="header">You already accepted another answer for this Ad!</h4>
			      </div>
			    </div>
			    <div class="row">
			      <div class="col">
			         <p id="body">You already accepted answer from user <% out.print(answererUsername); %> in date <% out.print(DateFormat.getDateInstance().format(e.getDate().getTime())); %>, would you like to accept this one instead?</p>
			      	 <form action="ActionOnAnswer.jsp?action=acceptC" name="acceptConfirm" method="POST">
						 <input type="hidden" id="author" name="author" value="<%= answererUsername%>"/>
						 <input type="hidden" id="adId" name="adId" value="<%= adId%>"/>
						 <input type="hidden" id="adType" name="adType" value="<%= adType%>"/>
						 <button type="submit" class ="btn btn-success">Yes</button>
						 <a href="CheckAnswers.jsp?adId=<%=adId%>&adType=<%=adType%>" class="btn btn-danger">No</a>
					 </form>
			      </div>
			    </div>
			</div>
			<%
		}catch(AnswerAlreadyAcceptedException e){
			%>
			<div id="container">
			
			    <div class="row">
			      <div class="col">
			        <h4 id="header">You can't accept more than one time the same answer!</h4>
			      </div>
			    </div>
			    <div class="row">
			      <div class="col">
			         <p id="body">You already accepted this answer in date: <% out.print(DateFormat.getDateInstance().format(e.getDateOfRequest().getTime())); %></p>
			      </div>
			    </div>
			</div>
			<%
		}
	}
	else if(request.getParameter("action").compareTo("deny") == 0){
		int adId = (Integer) request.getAttribute("adId");
		String adType = (String) request.getAttribute("adType");
		String answererUsername = (String) request.getAttribute("author");
		
		%>
		<div id="container">
		
		    <div class="row">
		      <div class="col">
		        <h4 id="header">Are you sure you want to Deny this Answer?</h4>
		      </div>
		    </div>
		    <div class="row">
		      <div class="col">
		         <p id="body">By confirming you will no longer be able to see this user in the answers list. <br>
						If this answer was first accepted, the respective Favor
						will be deleted, so that you can accept another
						answer.
						Are you sure?</p>
				<form action="ActionOnAnswer.jsp?action=denyC" name="denyConfirm" method="POST">
					<input type="hidden" id="author" name="author" value="<%= answererUsername%>"/>
					<input type="hidden" id="adId" name="adId" value="<%= adId%>"/>
					<input type="hidden" id="adType" name="adType" value="<%= adType%>"/>
					<button type="submit" class ="btn btn-success">Yes</button>
					<a href="CheckAnswers.jsp?adId=<%=adId%>&adType=<%=adType%>" class="btn btn-danger">No</a>
				</form>
		      </div>
		    </div>
		</div>
		<%
	}
	else if(request.getParameter("action").compareTo("denyC") == 0){
		int adId = Integer.parseInt(request.getParameter("adId"));
		String adType = request.getParameter("adType");
		String answeredUsername = session.getAttribute("username").toString();
		String answererUsername = request.getParameter("author");
		%>
		<jsp:setProperty property="adId" name="actionOnAnswerBean" value="<%=adId %>"/>
		<jsp:setProperty property="adType" name="actionOnAnswerBean" value="<%=adType%>"/>
		<jsp:setProperty property="answeredUsername" name="actionOnAnswerBean" value="<%=answeredUsername%>"/>
		<jsp:setProperty property="answererUsername" name="actionOnAnswerBean" value="<%=answererUsername%>"/>
		<%
		
		CheckAnswersController controller = CheckAnswersController.getInstance();
		try{
			controller.denyAnswer(actionOnAnswerBean);
			%>
			<div id="container">
			
			    <div class="row">
			      <div class="col">
			        <h4 id="header">Answer deny successfully!</h4>
			      </div>
			    </div>
			    <div class="row">
			      <div class="col">
			         <p id="body">The answer has been denied correctly, now you can choose another answer if you want.</p>
			      </div>
			    </div>
			</div>
			<%
		}
		catch(AnswerNotFoundException e){
			%>
			<div id="container">
			
			    <div class="row">
			      <div class="col">
			        <h4 id="header">Sorry, answer not found!</h4>
			      </div>
			    </div>
			    <div class="row">
			      <div class="col">
			         <p id="body">Cannot find this answer.</p>
			      </div>
			    </div>
			</div>
			<%
		}
	}
	%>
</body>
</html>