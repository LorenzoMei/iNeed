<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>OK</title>
</head>
<body>
Login effettuato
<br>
<ol>

    <% String[] listaValori =
            new String[]{"Questo", "Potremmo leggerlo", "Da DB",
                                "Oppure riceverlo come attributo di sessione"};
        for (int i=0; i<listaValori.length; i++) {
    %>
    <li><%=listaValori[i] %></li>
    <% } %>

    <li><%= session.getAttribute("myAttribute") %></li>
</ol>
</body>
</html>