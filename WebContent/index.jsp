<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>


<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="loginBean" scope="request"
             class="logic.LoginBean"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="loginBean" property="*"/>

<%
    if (request.getParameter("login") != null) {
        if (loginBean.validate(request.getParameter(("tipoLogin")))) {
            // Passa il controllo alla nuova pagina
            session.setAttribute("myAttribute", "30"); %>
            <jsp:forward page="RiassuntoLogin.jsp"/>
        <%
        } else {
            %>
            <p style="text-color:red;">Dati errati</p>
    <%  }
    } else {%>
        <h1 class="text-info text-center">Esegui l'accesso</h1>
        <%
    }
%>

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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">
<div class="container">
    <form action="index.jsp" name="myform" method="POST">

        <div class="row">
            <div class="col-lg-4 form-group">
                <label for="username">Username</label>
                <input id="username" name="u" type="text">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 form-group">
                <label for="password">Password</label>
                <input id="password" name="p" type="password">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 text-center">
                <input name="login" type="submit"
                       id="login" value="login" class="btn btn-info">
                <input name="reset" type="reset"
                       id="reset" class="btn btn-warning">
                <input name="tipoLogin" type="hidden" value="user"
                       id="tipoLogin" class="btn btn-warning">
            </div>
        </div>
    </form>
</div>
</body>
</html>