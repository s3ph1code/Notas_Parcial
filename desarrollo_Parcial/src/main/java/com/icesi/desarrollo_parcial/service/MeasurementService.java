package com.icesi.desarrollo_parcial.service;

import com.icesi.desarrollo_parcial.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository){this.measurementRepository = measurementRepository;}

    public void addMeasurement(Integer id, String timestamp, double value, String unit, Integer deviceId){
        measurementRepository.save(id,timestamp,value,unit,deviceId);
    }
}
