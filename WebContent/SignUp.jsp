<%@ page language="java" contentType="text/html; charset=UTF-8" import="logic.signup.SignUpController"
	import="logic.signup.UsernameAlreadyTakenException" pageEncoding="UTF-8"%>

<%! SignUpController controller = SignUpController.getInstance(); %>

<jsp:useBean id="signUpBean" scope="request" 
	class="logic.beans.SignUpBean"></jsp:useBean>

<jsp:setProperty property="*" name="signUpBean"/>

<%
if (request.getParameter("signUp") != null) {
	if(request.getParameter("passw").equals(request.getParameter("passwVer"))){
		try{
			controller.signUp(signUpBean);%>
			<jsp:forward page="index.jsp"/>
	<%}
		catch(UsernameAlreadyTakenException e){
	  		session.setAttribute("error", "Username already taken");
		}
	}
	else{
		session.setAttribute("error", "Passwords are not the same");
	}
}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sign Up</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<body>

<div class="jumbotron jumbotron-fluid text-center" style="background-color: rgb(0, 50, 78);">
  <div class="container" style="color:white">
  	<h1><b>iNeed</b></h1>
  	<p>There is all you need for...</p>
  </div>
</div>

<form action="SignUp.jsp" name="signupform" method="POST">

<div class="row">

	<div class="col-lg-6 text-right" style="border-right-style:solid; border-right-width:1px; border-color:lightgrey">

		<div class="row">
          <div class="col form-group">
              <label for="username" style="text:left">Username</label>
                <input id="username" name="username" type="text" required>
            </div>
        </div>
        
        <div class="row">
            <div class="col form-group">
                <label for="email">Email</label>
                <input id="email" name="email" type="text" required>
            </div>
        </div>
        <div class="row">
            <div class="col form-group">
                <label for="passw">Password</label>
                <input id="passw" name="passw" type="password" required>
            </div>
        </div>
        <div class="row">
            <div class="col form-group">
                <label for="passwVer">Password verification</label>
                <input id="passwVer" name="passwVer" type="password" required>
            </div>
        </div>
	</div>
	
	<div class="col-lg-6 text-left">
		
       <div class="row">
            <div class="col form-group">
            	<input id="city" name="city" type="text" required>
            	<label for="city">City</label>
            </div>
        </div>
        <div class="row">
            <div class="col form-group">
                <input id="name" name="name" type="text" required>
                <label for="name">Name</label>
            </div>
        </div>
        <div class="row">
            <div class="col form-group">
                <input id="surName" name="surName" type="text" required>
                <label for="surName">Surname</label>
            </div>
        </div>
        <div class="row">
            <div class="col form-group">
                <input id="bDate" name="bDate" type="date" required>
                <label for="bDate">Birth date</label>
            </div>
        </div>
	</div>
</div>

<div class="row">
    <div class="col text-center">
    	<p id="error" style="color:red">
			<b>
			<%if(session.getAttribute("error") != null){
				out.print(session.getAttribute("error"));
				session.removeAttribute("error");
			}%>	
    		</b>
    	</p>
        <input name="signUp" type="submit"
               id="signUp" value="Sign Up" class="btn btn-info"> <br>
         <small><a href="index.jsp">You already have an account? Login here</a></small>
    </div>
</div>
</form>
</body>
</html>