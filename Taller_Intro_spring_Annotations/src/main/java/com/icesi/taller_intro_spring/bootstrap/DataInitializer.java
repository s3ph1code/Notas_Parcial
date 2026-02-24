package com.icesi.taller_intro_spring.bootstrap;

import com.icesi.taller_intro_spring.model.Artist;
import com.icesi.taller_intro_spring.service.ArtistService;
import com.icesi.taller_intro_spring.service.TrackService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DataInitializer {
    private DataInitializer() {
    }

    public static void seed(ArtistService artistService, TrackService trackService) {
        for (int i = 1; i <= 10; i++) {
            artistService.createArtist("Artist " + i, "Country " + i);
        }

        List<Artist> artists = artistService.getAllArtists();
        if (artists.isEmpty()) {
            return;
        }

        List<Long> artistIds = new ArrayList<>();
        for (Artist artist : artists) {
            artistIds.add(artist.getId());
        }

        int trackIndex = 1;
        for (Long artistId : artistIds) {
            for (int j = 0; j < 5; j++) {
                String title = "Track " + trackIndex;
                String genre = "Genre " + ((trackIndex - 1) % 5 + 1);
                String duration = "3:0" + ((trackIndex - 1) % 10);
                String albumTitle = "Album " + ((trackIndex - 1) % 10 + 1);

                trackService.createTrack(title, genre, duration, albumTitle, Collections.singletonList(artistId));
                trackIndex++;
            }
        }
    }
}
