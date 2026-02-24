<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icesi.taller_intro_spring.model.Artist" %>
<%@ page import="com.icesi.taller_intro_spring.model.Track" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Artista</title>
</head>
<body>
<h1>Buscar Artista</h1>
<p><a href="<%= request.getContextPath() %>/">Inicio</a></p>

<form method="get" action="<%= request.getContextPath() %>/artists/search">
    <label>Nombre: <input type="text" name="name" value="<%= request.getAttribute("query") == null ? "" : request.getAttribute("query") %>"></label>
    <button type="submit">Buscar</button>
</form>

<%
    Boolean notFound = (Boolean) request.getAttribute("notFound");
    Artist artist = (Artist) request.getAttribute("artist");
    if (Boolean.TRUE.equals(notFound)) {
%>
<p style="color:red;">No se encontro el artista.</p>
<%
    }
    if (artist != null) {
%>
<h2>Datos del Artista</h2>
<p><strong>Id:</strong> <%= artist.getId() %></p>
<p><strong>Nombre:</strong> <%= artist.getName() %></p>
<p><strong>Nacionalidad:</strong> <%= artist.getNationality() %></p>

<h3>Tracks</h3>
<%
        List<Track> tracks = artist.getTracks();
        if (tracks == null || tracks.isEmpty()) {
%>
<p>Este artista no tiene tracks asociados.</p>
<%
        } else {
%>
<ul>
    <% for (Track track : tracks) { %>
    <li><%= track.getTitle() %> (<%= track.getAlbumTitle() %>)</li>
    <% } %>
</ul>
<%
        }
    }
%>
</body>
</html>

