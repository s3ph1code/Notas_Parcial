package com.icesi.desarrollo_parcial.servlet;

import com.icesi.desarrollo_parcial.service.DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deviceAddServlet", value = "/device/new")
public class DeviceAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/device-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String serialNumber = request.getParameter("serialNumber");
        String location = request.getParameter("location");
        String type = request.getParameter("type");
        String status = request.getParameter("status");

        DeviceService deviceService = (DeviceService) request.getServletContext().getAttribute("deviceService");
        if (deviceService == null) {
            request.setAttribute("error", "DeviceService no esta disponible");
            request.getRequestDispatcher("/WEB-INF/views/device-form.jsp").forward(request, response);
            return;
        }

        try {
            if (idParam == null || idParam.isBlank()) {
                throw new IllegalArgumentException("El id no puede estar vacio");
            }
            Integer id = Integer.parseInt(idParam);

            deviceService.addDevice(id, name, serialNumber, location, type, status);
            response.sendRedirect(request.getContextPath() + "/device/new");
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "El id debe ser numerico");
            request.getRequestDispatcher("/WEB-INF/views/device-form.jsp").forward(request, response);
        } catch (IllegalArgumentException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/device-form.jsp").forward(request, response);
        }
    }
}
