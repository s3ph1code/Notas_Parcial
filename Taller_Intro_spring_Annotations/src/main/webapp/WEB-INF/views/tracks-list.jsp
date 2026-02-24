<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icesi.taller_intro_spring.model.Track" %>
<%@ page import="com.icesi.taller_intro_spring.model.Artist" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tracks</title>
</head>
<body>
<h1>Listado de Tracks</h1>
<p><a href="<%= request.getContextPath() %>/">Inicio</a></p>

<%
    List<Track> tracks = (List<Track>) request.getAttribute("tracks");
    if (tracks == null || tracks.isEmpty()) {
%>
<p>No hay tracks registrados.</p>
<%
    } else {
%>
<table border="1" cellpadding="6">
    <thead>
    <tr>
        <th>Id</th>
        <th>Titulo</th>
        <th>Genero</th>
        <th>Duracion</th>
        <th>Album</th>
        <th>Artistas</th>
    </tr>
    </thead>
    <tbody>
    <% for (Track track : tracks) { %>
    <tr>
        <td><%= track.getId() %></td>
        <td><%= track.getTitle() %></td>
        <td><%= track.getGenre() %></td>
        <td><%= track.getDuration() %></td>
        <td><%= track.getAlbumTitle() %></td>
        <td>
            <% if (track.getArtists() == null || track.getArtists().isEmpty()) { %>
                Sin artistas
            <% } else { %>
                <% for (Artist artist : track.getArtists()) { %>
                    <%= artist.getName() %><br>
                <% } %>
            <% } %>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<%
    }
%>
</body>
</html>

