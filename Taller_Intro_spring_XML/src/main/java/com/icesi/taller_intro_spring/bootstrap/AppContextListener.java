package com.icesi.taller_intro_spring.bootstrap;

import com.icesi.taller_intro_spring.service.ArtistService;
import com.icesi.taller_intro_spring.service.TrackService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextListener implements ServletContextListener {
    private ClassPathXmlApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        ArtistService artistService = applicationContext.getBean(ArtistService.class);
        TrackService trackService = applicationContext.getBean(TrackService.class);

        DataInitializer.seed(artistService, trackService);

        ServletContext context = sce.getServletContext();
        context.setAttribute("artistService", artistService);
        context.setAttribute("trackService", trackService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (applicationContext != null) {
            applicationContext.close();
        }
    }
}
