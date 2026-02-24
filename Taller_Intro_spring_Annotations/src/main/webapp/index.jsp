<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio</title>
</head>
<body>
<h1>Gestion Discografia</h1>
<ul>
    <li><a href="<%= request.getContextPath() %>/artists">Listado de artistas</a></li>
    <li><a href="<%= request.getContextPath() %>/artists/new">Crear artista</a></li>
    <li><a href="<%= request.getContextPath() %>/artists/search">Buscar artista por nombre</a></li>
    <li><a href="<%= request.getContextPath() %>/artists/delete">Eliminar artista</a></li>
    <li><a href="<%= request.getContextPath() %>/tracks">Listado de tracks</a></li>
    <li><a href="<%= request.getContextPath() %>/tracks/new">Crear track</a></li>
    <li><a href="<%= request.getContextPath() %>/tracks/delete">Eliminar track</a></li>
</ul>
</body>
</html>