<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icesi.desarrollo_parcial.model.Device" %>
<html>
<head>
    <title>Lista de Devices</title>
</head>
<body>
<h1>Lista de Devices</h1>
<p><a href="<%= request.getContextPath() %>/">Inicio</a></p>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    }

    List<Device> devices = (List<Device>) request.getAttribute("devices");
    if (devices == null || devices.isEmpty()) {
%>
<p>No hay dispositivos registrados.</p>
<%
    } else {
%>
<table border="1" cellpadding="6" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Serial</th>
        <th>Ubicacion</th>
        <th>Tipo</th>
        <th>Estado</th>
    </tr>
    <% for (Device device : devices) { %>
    <tr>
        <td><%= device.getId() %></td>
        <td><%= device.getName() %></td>
        <td><%= device.getSerialNumber() %></td>
        <td><%= device.getLocation() %></td>
        <td><%= device.getType() %></td>
        <td><%= device.getStatus() %></td>
    </tr>
    <% } %>
</table>
<%
    }
%>
</body>
</html>

