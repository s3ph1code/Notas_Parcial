package com.icesi.desarrollo_parcial.bootstrap;

import com.icesi.desarrollo_parcial.config.AppConfig;
import com.icesi.desarrollo_parcial.service.DeviceService;
import com.icesi.desarrollo_parcial.service.MeasurementService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContextListener implements ServletContextListener {
    private AnnotationConfigApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce){
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        DeviceService deviceService = applicationContext.getBean(DeviceService.class);
        MeasurementService measurementService = applicationContext.getBean(MeasurementService.class);
        DataInitializer.seed(deviceService, measurementService);

        ServletContext context = sce.getServletContext();
        context.setAttribute("deviceService", deviceService);
        context.setAttribute("measurement", measurementService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){

        if(applicationContext != null){
            applicationContext.close();
        }

    }

}
