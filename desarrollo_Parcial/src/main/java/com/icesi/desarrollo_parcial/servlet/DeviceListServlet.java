package com.icesi.desarrollo_parcial.servlet;

import com.icesi.desarrollo_parcial.model.Device;
import com.icesi.desarrollo_parcial.service.DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "deviceListServlet", value = "/device/list")
public class DeviceListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeviceService deviceService = (DeviceService) request.getServletContext().getAttribute("deviceService");
        if (deviceService == null) {
            request.setAttribute("error", "DeviceService no esta disponible.");
            request.getRequestDispatcher("/WEB-INF/views/device-list.jsp").forward(request, response);
            return;
        }

        List<Device> devices = deviceService.findAll();
        request.setAttribute("devices", devices);
        request.getRequestDispatcher("/WEB-INF/views/device-list.jsp").forward(request, response);
    }
}
