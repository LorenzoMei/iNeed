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
<title>Login page iNeed</title>
<!-- info about content, e.g.: content type, keywords, charset or description -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- linked CSS -->
<!--<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<link href="css/style-login.css" rel="stylesheet" type="text/css"> -->
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
  <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
  <link rel="stylesheet" type="text/css" href="css/style-login.css">
</head>
<body>
<div class="bg-image"></div>

      <div class="bg-text">
        <p class="title">iNe<span class="ref">e</span>d</p>
          <p class="sub-title">it's all you need...</p>
</div>
 <div class="modal-dialog text-center">

        <div class="col-sm-10 main-section">
            <div class="modal-content">
                <div class="col-12 user-img">
                    <img src="image/Red-Logomark.png" alt="Image" height="110" width="110">
                </div>
                
                <form action="index.jsp" name="myform" method="POST">
                    <div class="col-12 form-input">
                        <p id="error" style="color:red; text:bold">
                                    <b>
                                    <%if(session.getAttribute("error") != null){
                                    out.print(session.getAttribute("error"));
                                    session.removeAttribute("error");
                                    }%>
                                   </b>
                                </p>
                        <div class="form-group">
                            <div class="form-group">
                                
                                <input type="text" class="form-control" id="username" name="username"  placeholder="Enter Username" required>
                                
                            </div> 
                        
                            <div id="pswField" class="form-group">
                                <input name="password" type="password" id="password" class="form-control" placeholder="Enter Password" required>

                            </div>
                        </div>
                        <div >
                            <input type="checkbox" class="check-box" > <span>Remember Password</span>
                        </div>
                        <div id="btn">
                            <button name="login"  type="submit" id="login" value="Login" class="btn"><i class="fas fa-sign-in-alt"></i>Login</button>
                        </div>
                    </div>
                </form>
                <div class="col-12 forgot">
                <a href="SignUp.jsp">Are you new? Click here to SignUp!</a>
            </div>

            </div>
        </div>
    </div>

</body>
</html>