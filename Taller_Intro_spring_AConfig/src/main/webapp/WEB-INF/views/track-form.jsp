<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icesi.taller_intro_spring.model.Artist" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Track</title>
</head>
<body>
<h1>Crear Track</h1>
<p><a href="<%= request.getContextPath() %>/">Inicio</a></p>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    }
%>

<form method="post" action="<%= request.getContextPath() %>/tracks/new">
    <label>Titulo: <input type="text" name="title" required></label><br><br>
    <label>Genero: <input type="text" name="genre"></label><br><br>
    <label>Duracion: <input type="text" name="duration"></label><br><br>
    <label>Album: <input type="text" name="albumTitle"></label><br><br>
    <label>Artistas:</label><br>
    <select name="artistIds" multiple size="6" required>
        <%
            List<Artist> artists = (List<Artist>) request.getAttribute("artists");
            if (artists != null) {
                for (Artist artist : artists) {
        %>
        <option value="<%= artist.getId() %>"><%= artist.getName() %></option>
        <%
                }
            }
        %>
    </select><br><br>
    <button type="submit">Guardar</button>
</form>
</body>
</html>

