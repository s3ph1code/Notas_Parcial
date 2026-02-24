package com.icesi.taller_intro_spring.servlet;

import com.icesi.taller_intro_spring.model.Artist;
import com.icesi.taller_intro_spring.service.ArtistService;
import com.icesi.taller_intro_spring.service.TrackService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "trackCreateServlet", value = "/tracks/new")
public class TrackCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArtistService artistService = (ArtistService) getServletContext().getAttribute("artistService");
        List<Artist> artists = artistService.getAllArtists();
        request.setAttribute("artists", artists);
        request.getRequestDispatcher("/WEB-INF/views/track-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String duration = request.getParameter("duration");
        String albumTitle = request.getParameter("albumTitle");
        String[] artistIdParams = request.getParameterValues("artistIds");

        List<Long> artistIds = new ArrayList<>();
        if (artistIdParams != null) {
            for (String idParam : artistIdParams) {
                try {
                    artistIds.add(Long.parseLong(idParam));
                } catch (NumberFormatException ex) {
                    request.setAttribute("error", "Id de artista invalido: " + idParam);
                }
            }
        }

        TrackService trackService = (TrackService) getServletContext().getAttribute("trackService");
        try {
            trackService.createTrack(title, genre, duration, albumTitle, artistIds);
            response.sendRedirect(request.getContextPath() + "/tracks");
        } catch (IllegalArgumentException ex) {
            ArtistService artistService = (ArtistService) getServletContext().getAttribute("artistService");
            request.setAttribute("artists", artistService.getAllArtists());
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/track-form.jsp").forward(request, response);
        }
    }
}

