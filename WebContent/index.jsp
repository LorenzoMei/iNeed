<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="logic.login.LoginController"
         import="logic.dao.UserNotFoundException" import="logic.login.WrongPasswordException"%>

<%! LoginController controller = LoginController.getInstance(); %>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="loginBean" scope="request"
             class="logic.beans.CredentialsBean"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="loginBean" property="*"/>

<%
if (request.getParameter("login") != null) {
   	try{
   		controller.login(loginBean);
   		session.setAttribute("username", loginBean.getUsername());
   		%>
		<jsp:forward page="HelloWorld.jsp"/>
	<%
	}
	catch(UserNotFoundException | WrongPasswordException e){
		session.setAttribute("error", "Dati errati");
	}
}%>

<!-- HTML 5 -->
<!DOCTYPE html>
<html>
<!-- Container tag for title, style, meta-information, linked resources or scripts -->
<head>
    <!-- Browser title bar, favorites, name for search engines -->
<title>Login page</title>
<!-- info about content, e.g.: content type, keywords, charset or description -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- linked CSS -->
<!--<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<link href="css/style.css" rel="stylesheet" type="text/css"> -->
    
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
	
<div class="row">

  <div class="col">
  </div>
  
  <div class="col">
   <form action="index.jsp" name="myform" method="POST">

     <div class="row">
         <div class="col form-group text-center">
             <label for="username">Username</label> <br>
             <input id="username" name="username" type="text">
         </div>
     </div>
     
     <div class="row">
         <div class="col form-group text-center">
             <label for="password">Password</label> <br>
             <input id="password" name="password" type="password">
         </div>
     </div>
     <div class="row">
         <div class="col text-center">
         	 <p id="error" style="color:red; text:bold">
				<b>
				<%if(session.getAttribute("error") != null){
				out.print(session.getAttribute("error"));
				session.removeAttribute("error");
				}%>
	    	   </b>
         	 </p>
             <input name="login" type="submit"
                    id="login" value="Login" class="btn btn-info">
             <input name="tipoLogin" type="hidden" value="user"
                    id="tipoLogin" class="btn btn-warning">
         </div>
     </div>
   </form>
   
   <div class="row">
   		<div class="col text-center">
   		<small><a href="SignUp.jsp">Don't have an account? Sign Up here</a></small>
   		</div>
   </div>
   
	</div>
 
	<div class="col">
	</div>
  
</div>
</body>
</html>