package com.icesi.taller_intro_spring.model;

import java.util.ArrayList;
import java.util.List;

public class Track {
    private long id;
    private String title;
    private String genre;
    private String duration;
    private String albumTitle;
    private List<Artist> artists;




    public Track(long id, String title, String duration, String albumTitle, String genre, List<Artist> artists) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.albumTitle = albumTitle;
        this.genre = genre;
        this.artists = artists;
    }

    public Track(){}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getGenre() {return genre;}

    public void setGenre(String genre) {this.genre = genre;}

    public String getDuration() {return duration;}

    public void setDuration(String duration) {this.duration = duration;}

    public String getAlbumTitle() {return albumTitle;}

    public void setAlbumTitle(String albumTitle) {this.albumTitle = albumTitle;}

    public List<Artist> getArtists() {
        if(artists == null){
            artists = new ArrayList<>();
        }
        return artists;
    }

    public void setArtists(List<Artist> artists) {this.artists = artists;}






}
