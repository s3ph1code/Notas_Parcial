<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "PARCIAL T.T" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>

<ul>
    <li><a href="<%= request.getContextPath() %>/device/new">Crear Artista</a></li>
    <li><a href="<%= request.getContextPath() %>/device/change">Cambiar status</a></li>
    <li><a href="<%= request.getContextPath() %>/device/list">Lista de Dispositivos</a></li>

</ul>

</body>
</html>