package com.icesi.taller_intro_spring.servlet;

import com.icesi.taller_intro_spring.model.Artist;
import com.icesi.taller_intro_spring.service.ArtistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "artistsListServlet", value = "/artists")
public class ArtistsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArtistService artistService = (ArtistService) getServletContext().getAttribute("artistService");
        List<Artist> artists = artistService.getAllArtists();
        request.setAttribute("artists", artists);
        request.getRequestDispatcher("/WEB-INF/views/artists-list.jsp").forward(request, response);
    }
}

