package com.icesi.taller_intro_spring.service;

import com.icesi.taller_intro_spring.model.Artist;
import com.icesi.taller_intro_spring.model.Track;
import com.icesi.taller_intro_spring.repository.ArtistRepository;
import com.icesi.taller_intro_spring.repository.TrackRepository;

import java.util.List;

public class TrackService {
    private TrackRepository trackRepository;
    private ArtistRepository artistRepository;

    public TrackService(TrackRepository trackRepository, ArtistRepository artistRepository){
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }

    public void createTrack(String title, String genre,String duration, String albumTitle, List<Long> artistIds){
        if(title == null || title.trim().isEmpty()){throw new IllegalArgumentException("El titulo no puede estar vacio");}

        if(artistIds == null || artistIds.isEmpty()){throw new IllegalArgumentException("La lista artistIds no puede estar vacia");}

        Track track = new Track();
        track.setTitle(title.trim());
        track.setGenre(genre == null ? "": genre.trim());
        track.setDuration(duration == null ? "": duration.trim());
        track.setAlbumTitle(albumTitle == null ? "": albumTitle.trim());
        trackRepository.save(track);

        for(Long id : artistIds){
            if(id == null){
                throw new IllegalArgumentException("El id no puede ser null");
            }
            Artist artist = artistRepository.findById(id);
            if (artist == null){
                throw new IllegalArgumentException("No existe artista con id: " + id);
            }
            track.getArtists().add(artist);
            artist.getTracks().add(track);
        }




    }

    public List<Track> getAllTracks(){
        return trackRepository.findAll();
    }

    public boolean deleteTrackById(long id){
        return trackRepository.deleteById(id);
    }
}
