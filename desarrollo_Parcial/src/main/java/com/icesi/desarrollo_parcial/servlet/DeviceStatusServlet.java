package com.icesi.desarrollo_parcial.servlet;

import com.icesi.desarrollo_parcial.service.DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deviceStatusServlet", value = "/device/change")
public class DeviceStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/device-status.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String status = request.getParameter("status");

        DeviceService deviceService = (DeviceService) request.getServletContext().getAttribute("deviceService");

        try {
            if (idParam == null || idParam.isBlank()) {
                throw new IllegalArgumentException("El id no puede estar vacio");
            }
            Integer id = Integer.parseInt(idParam);

            deviceService.changeStatus(id,status);
            response.sendRedirect(request.getContextPath() + "/device/change");
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "El id debe ser numerico");
            request.getRequestDispatcher("/WEB-INF/views/device-status.jsp").forward(request, response);
        }

    }

}
