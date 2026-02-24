package com.icesi.taller_intro_spring.repository;
import com.icesi.taller_intro_spring.model.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackRepository {
    private List<Track> tracks = new ArrayList<>();
    private long currentId;


    public void save(Track track){
        if(track == null){
            throw new IllegalArgumentException("track no puede estar vacio");
        }
        currentId++;
        track.setId(currentId);
        tracks.add(track);
    }

    public List<Track> findAll(){
        return new ArrayList<>(tracks);
    }

    public Track findById(long id){
        return tracks.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public boolean deleteById(long id){
        return tracks.removeIf(u -> u.getId() == id);
    }

}
