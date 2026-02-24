package com.icesi.taller_intro_spring.bootstrap;

import com.icesi.taller_intro_spring.repository.ArtistRepository;
import com.icesi.taller_intro_spring.repository.TrackRepository;
import com.icesi.taller_intro_spring.service.ArtistService;
import com.icesi.taller_intro_spring.service.TrackService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ArtistRepository artistRepository() {
        return new ArtistRepository();
    }

    @Bean
    public TrackRepository trackRepository() {
        return new TrackRepository();
    }

    @Bean
    public ArtistService artistService(ArtistRepository artistRepository) {
        return new ArtistService(artistRepository);
    }

    @Bean
    public TrackService trackService(TrackRepository trackRepository, ArtistRepository artistRepository) {
        return new TrackService(trackRepository, artistRepository);
    }
}

