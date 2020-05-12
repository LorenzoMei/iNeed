<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Bootstrap CSS -->

	<title>Auth Checker</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/v4-shims.css">
    <link rel="stylesheet" type="text/css" href="css/style_menubar.css">

</head>
<body>
	<nav>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a href="#" class="navbar-brand"><img class="logo" src="image/iNeed_Color.png" alt="logo"></a>
<!--            Bottone per la toogle bar! per gestire il click si usa data-toggle scelto opzione collapse sul target con il tag navbarMenu-->
            <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarMenu">
<!--                usare class="navbar-nav ml-auto" se possibile, più carino tutto a destra-->
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="MakeAnAd.jsp" class="nav-link"><i class="fas fa-ad"></i>Make An Ad</a>
                    </li>
                    <li class="nav-item">
                        <a href="ViewFlow.jsp" class="nav-link"><i class="fas fa-wave-square"></i>View Flow</a>
                    </li>
                    <li class="nav-item">
                        <a href="ValidateAFavor.jsp" class="nav-link"><i class="fas fa-check-square"></i>Validate A Favor</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-address-card"></i>AboutUs</a>
                        <div class="dropdown-menu dropdown-menu-left" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">Mission</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Vision</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Team</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#"class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown"><i class="fas fa-user"></i><%-- username here: --%>
						<% 
						String username = "";
						if (session.getAttribute("username") != null){
							username = session.getAttribute("username").toString();
						}
						%>
						<%= username %> </a>
                        
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">My Profile</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Curriculum</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">LogOut</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>