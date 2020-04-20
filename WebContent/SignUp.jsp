<%@ page language="java" contentType="text/html; charset=UTF-8" import="logic.signup.SignUpController"
	import="logic.signup.UsernameAlreadyTakenException" pageEncoding="UTF-8"%>

<%! SignUpController controller = SignUpController.getInstance(); %>

<jsp:useBean id="signUpBean" scope="request" 
	class="logic.beans.SignUpBean"></jsp:useBean>

<jsp:setProperty property="*" name="signUpBean"/>

<%
if (request.getParameter("signUp") != null) {
	if(request.getParameter("password").equals(request.getParameter("passwVer"))){
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
  <title>iNeed SignUp Page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
  <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
  <link rel="stylesheet" type="text/css" href="css/style-signup.css">
</head>

<body>

<div class="bg-image"></div>

      <div class="bg-text">
        <p class="title">iNe<span class="ref">e</span>d</p>
          <p class="sub-title">it's all you need...</p>
</div>

 <div class="modal-dialog text-center">
 <div class="row">
  
<div id="contact_showcase" >
<div class="col-lg-3"></div>
   <div class="col-lg-6">


        <div class="modal-content">
        <div class="col-12 user-img">
                    <img src="image/Red-Logomark.png" alt="Image" height="110" width="110">
                </div>
        <form action="SignUp.jsp" name="signupform" method="POST">
              <div class="row">
                <div class="col-xl-6 col-md-6">
                    <div class="form-group">
                        <input type="text" id="name" name="name" class="form-control"   placeholder="First name..." required>
                
                    </div>
                  </div>

                  <div class="col-xl-6 col-md-6">
                        <div class="form-group">
                            <input type="text" id="surName" name="surName" class="form-control"  placeholder="Surname" required >
                    </div>
                  </div>
                </div>
                <div class="row">
                    <div class="col-xl-6 col-md-6">

                          <input placeholder="Birthday Date" class="form-control" type="text" onfocus="(this.type='date')" onblur="(this.type='text')" id="bDate" name="bDate" required>

                      </div>

                    <div class="col-xl-6 col-md-6">
                        <div class="form-group">
                            <input type="email" id="email" name="email" class="form-control"  placeholder="Email" required >
                        </div>
                    </div>
                </div>
                <div class="row">
                <div class="col-xl-6 col-md-6">
                    <div class="form-group">
                        <input id="city" name="city" class="form-control"   placeholder="City" required>
                
                    </div>
                  </div>

                  <div class="col-xl-6 col-md-6">
                        <div class="form-group">
                            <input type="text" id="username" name="username" class="form-control"  placeholder="Username" required >
                    </div>
                  </div>
                </div>
              <div class="row">
                  
                <div class="col-lg-6 col-md-6">
                    <div class="form-group">
                          <input type="password" id="password" name="password" class="form-control" placeholder="Password" required >
                      </div>
                  </div>

                <div class="col-lg-6 col-md-6">
                    <div class="form-group">
                        <input type="password" id="passwVer" name="passwVer" class="form-control" placeholder="Re-type your password" required >
                    </div>
                </div>
              </div>
              <input type="submit" id="signUp" name="signUp" value="Register Now" class="btn btn-warning btn-block btn-lg" style="box-shadow: 2px 2px 2px gray;">
              <div class="col-12 login">
              <p id="error" style="color:red">
                <b>
                <%if(session.getAttribute("error") != null){
				out.print(session.getAttribute("error"));
				session.removeAttribute("error");
			     }%>	
    		      </b>
    	       </p>
                <a href="index.jsp">You already have an account? Click here and Login!</a>
              </div>
        </form>
      </div>
    </div>
<div class="col-lg-3"></div>
 </div>
     
</div>
</div>


</body>
</html>