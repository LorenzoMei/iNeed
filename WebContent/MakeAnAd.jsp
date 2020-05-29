<%@ include file="components/TitleSetter.jsp"%>
<%@ include file="components/AuthChecker.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="logic.publishanad.PublishAnAdController"%>

   
    
    
    
<jsp:useBean id="publishAnAdBean" scope="request" 
	class="logic.beans.PublishAnAdBean"></jsp:useBean>

<jsp:setProperty property="*" name="publishAnAdBean"/>

<%
	PublishAnAdController controller = PublishAnAdController.getInstance();
	if(request.getParameter("publish") != null){
		String other = request.getParameter("other");
		String username = session.getAttribute("username").toString();
		String typeRequest = "Request";
		
		if(other != null){
			%>
			<jsp:setProperty name="publishAnAdBean" property="category" value="<%= other%>"/>
			<%
		}
		
		if(request.getParameter("type").compareTo(typeRequest) == 0)
			publishAnAdBean.setRequestType();
		else
			publishAnAdBean.setOfferType();
		%>
		<jsp:setProperty name="publishAnAdBean" property="ownerUsername" value="<%= username %>"/>
		<%
		controller.createAd(publishAnAdBean);
		%>
		<jsp:forward page="ViewFlow.jsp"></jsp:forward>
		<%
	}
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	


	
	<title>Make an ad</title>
</head>
<body id="corp">
	
	
	<div class="row">
	<div class="container-fluid">
		<jsp:include page="components/ToolBar.jsp"></jsp:include>
	</div>
		<br>
	
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="row">
				<div class="col-sm-6">
					<h1><b>Make An Ad</b></h1>
				</div>
				<div class="col-sm-4"></div> <!-- bubble -->
				<div class="col-sm-2">
				 <img alt="ad logo" src="image/ad.png">
				 </div>
			</div>
			
			<form action="MakeAnAd.jsp" name="myform" method="POST">
				<div class="form-group">
					<label for="type">Type:</label>
					<select class="form-control" id="type" name="type">
						<option>Request</option>
						<option>Offer</option>
					</select>
				</div>
				
				<div class="form-group">
					<label for="title">Title:</label><br>
					<input type="text" id="title" name="title" required>
				</div>
				
				<div class="form-group">
					<label for="category">Category:</label>
					<select class="form-control" id="category" name="category" onchange="enabledOther()">
				        <option value="Electronics">Electronics</option>
				        <option value="Plumbing">Plumbing</option>
				        <option value="Gardening">Gardening</option>
				        <option value="Informatics">Informatics</option>
				        <option value="Bed Sharing">Bed Sharing</option>
				        <option value="Other">Other...</option>
				    </select>
			    </div>
			    
			    <div class="form-group">
			    	<label for="other">Other:</label><br>
					<input type="text" id="other" name="other" disabled>
				</div>
				
			    <div class="form-group">
			    	<label for="body">Body:</label><br>
					<textarea id="body" name="body" style="resize:none; width:100%" required></textarea>
				</div>
				
				<div>
	            	<button name="publish" type="submit" id="publish" value="Publish" class="btn btn-info">Publish</button>
	            </div>
			</form>
		</div>
		<div class="col-lg-4"></div>
	</div>
	
	<script>
    	function enabledOther(){
    		var value = document.getElementById("category").value;
    		if(value == "Other"){
    			$("#other").prop("disabled", false);
    		}
    		else{
    			$("#other").prop("disabled", true);
    		}
    	}
    </script>
</body>
</html>
