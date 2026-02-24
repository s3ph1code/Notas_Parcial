<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Artista</title>
</head>
<body>
<h1>Crear Artista</h1>
<p><a href="<%= request.getContextPath() %>/">Inicio</a></p>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    }
%>

<form method="post" action="<%= request.getContextPath() %>/artists/new">
    <label>Nombre: <input type="text" name="name" required></label><br><br>
    <label>Nacionalidad: <input type="text" name="nationality"></label><br><br>
    <button type="submit">Guardar</button>
</form>
</body>
</html>

