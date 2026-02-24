<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eliminar Artista</title>
</head>
<body>
<h1>Eliminar Artista</h1>
<p><a href="<%= request.getContextPath() %>/">Inicio</a></p>

<%
    String error = (String) request.getAttribute("error");
    Boolean deleted = (Boolean) request.getAttribute("deleted");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    } else if (deleted != null) {
%>
<p><%= deleted ? "Artista eliminado" : "No se encontro el artista" %></p>
<%
    }
%>

<form method="post" action="<%= request.getContextPath() %>/artists/delete">
    <label>Id: <input type="number" name="id" required></label>
    <button type="submit">Eliminar</button>
</form>
</body>
</html>

