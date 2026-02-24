<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icesi.taller_intro_spring.model.Artist" %>
<!DOCTYPE html>
<html>
<head>
    <title>Artistas</title>
</head>
<body>
<h1>Listado de Artistas</h1>
<p><a href="<%= request.getContextPath() %>/">Inicio</a></p>

<%
    List<Artist> artists = (List<Artist>) request.getAttribute("artists");
    if (artists == null || artists.isEmpty()) {
%>
<p>No hay artistas registrados.</p>
<%
    } else {
%>
<table border="1" cellpadding="6">
    <thead>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Nacionalidad</th>
        <th>Tracks</th>
    </tr>
    </thead>
    <tbody>
    <% for (Artist artist : artists) { %>
    <tr>
        <td><%= artist.getId() %></td>
        <td><%= artist.getName() %></td>
        <td><%= artist.getNationality() %></td>
        <td><%= artist.getTracks().size() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
<%
    }
%>
</body>
</html>

