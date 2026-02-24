package com.icesi.taller_intro_spring.servlet;

import com.icesi.taller_intro_spring.model.Track;
import com.icesi.taller_intro_spring.service.TrackService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "tracksListServlet", value = "/tracks")
public class TracksListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TrackService trackService = (TrackService) getServletContext().getAttribute("trackService");
        List<Track> tracks = trackService.getAllTracks();
        request.setAttribute("tracks", tracks);
        request.getRequestDispatcher("/WEB-INF/views/tracks-list.jsp").forward(request, response);
    }
}

