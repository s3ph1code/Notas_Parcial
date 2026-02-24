<%--
  Created by IntelliJ IDEA.
  User: Kevin Cifuentes
  Date: 2/24/2026
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Device</title>
</head>
<body>
<h1>Crear Device</h1>
<p><a href="<%= request.getContextPath() %>/">Inicio</a></p>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    }
%>

<form method="post" action="<%= request.getContextPath() %>/device/new">
    <label>ID: <input type="number" name="id" required></label><br><br>
    <label>Nombre: <input type="text" name="name" required></label><br><br>
    <label>Serial: <input type="text" name="serialNumber" required></label><br><br>
    <label>Ubicacion: <input type="text" name="location" required></label><br><br>
    <label>Tipo: <input type="text" name="type" required></label><br><br>
    <label>Estado: <input type="text" name="status" required></label><br><br>

    <button type="submit">Guardar</button>
</form>
</body>
</html>
