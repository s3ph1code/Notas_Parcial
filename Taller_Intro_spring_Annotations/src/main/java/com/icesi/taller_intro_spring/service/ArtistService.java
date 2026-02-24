package com.icesi.taller_intro_spring.service;

import com.icesi.taller_intro_spring.model.Artist;
import com.icesi.taller_intro_spring.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public void createArtist(String name, String nationality){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Nombre Requerido");
        }
        Artist artist = new Artist();
        artist.setName(name.trim());
        artist.setNationality(nationality == null ? "" : nationality.trim());
        artistRepository.save(artist);
    }

    public List<Artist> getAllArtists(){
        return artistRepository.findAll();
    }

    public Artist getArtistByName(String name){
        return artistRepository.findByName(name);
    }

    public boolean deleteArtistById(long id){
        return artistRepository.deleteById(id);
    }
}
