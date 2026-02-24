package com.icesi.taller_intro_spring.servlet;

import com.icesi.taller_intro_spring.service.ArtistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "artistDeleteServlet", value = "/artists/delete")
public class ArtistDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/artist-delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idParam = request.getParameter("id");
        ArtistService artistService = (ArtistService) getServletContext().getAttribute("artistService");

        try {
            long id = Long.parseLong(idParam);
            boolean deleted = artistService.deleteArtistById(id);
            request.setAttribute("deleted", deleted);
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Id invalido");
        }

        request.getRequestDispatcher("/WEB-INF/views/artist-delete.jsp").forward(request, response);
    }
}

