package com.icesi.taller_intro_spring.servlet;

import com.icesi.taller_intro_spring.service.ArtistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "artistCreateServlet", value = "/artists/new")
public class ArtistCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/artist-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String nationality = request.getParameter("nationality");

        ArtistService artistService = (ArtistService) getServletContext().getAttribute("artistService");
        try {
            artistService.createArtist(name, nationality);
            response.sendRedirect(request.getContextPath() + "/artists");
        } catch (IllegalArgumentException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/artist-form.jsp").forward(request, response);
        }
    }
}

