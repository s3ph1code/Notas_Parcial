package com.icesi.taller_intro_spring.servlet;

import com.icesi.taller_intro_spring.model.Artist;
import com.icesi.taller_intro_spring.service.ArtistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "artistSearchServlet", value = "/artists/search")
public class ArtistSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            ArtistService artistService = (ArtistService) getServletContext().getAttribute("artistService");
            Artist artist = artistService.getArtistByName(name);
            if (artist == null) {
                request.setAttribute("notFound", true);
            } else {
                request.setAttribute("artist", artist);
            }
            request.setAttribute("query", name);
        }

        request.getRequestDispatcher("/WEB-INF/views/artist-search.jsp").forward(request, response);
    }
}

