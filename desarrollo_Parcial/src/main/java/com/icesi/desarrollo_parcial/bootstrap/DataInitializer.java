package com.icesi.desarrollo_parcial.bootstrap;

import com.icesi.desarrollo_parcial.service.DeviceService;
import com.icesi.desarrollo_parcial.service.MeasurementService;

public class DataInitializer {
    private DataInitializer(){}

    public static void seed(DeviceService deviceService, MeasurementService measurementService){
        deviceService.addDevice(1, "Sensor A", "SN-0001", "Lab-1", "TEMP", "ACTIVE");
        deviceService.addDevice(2, "Sensor B", "SN-0002", "Lab-2", "HUM", "ACTIVE");

        measurementService.addMeasurement(1, "2026-02-24T10:00:00", 22.5, "C", 1);
        measurementService.addMeasurement(2, "2026-02-24T10:05:00", 22.9, "C", 1);
        measurementService.addMeasurement(3, "2026-02-24T10:00:00", 45.0, "%", 2);
        measurementService.addMeasurement(4, "2026-02-24T10:05:00", 46.2, "%", 2);
    }

}
