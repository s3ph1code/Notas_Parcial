package com.icesi.desarrollo_parcial.repository;

import com.icesi.desarrollo_parcial.model.Measurement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class MeasurementRepository {
    private List<Measurement> measurements = new ArrayList<>();


    public void save(Integer id, String timestamp, double value, String unit, Integer deviceId){
        Measurement measurement = new Measurement();
        measurement.setId(id);
        measurement.setTimestamp(timestamp);
        measurement.setValue(value);
        measurement.setUnit(unit);
        measurement.setDeviceId(deviceId);
        measurements.add(measurement);
    }

    public Measurement findById(Integer id){
        if(id == null){
            throw new IllegalArgumentException("id no puede ser null");
        }
        return measurements.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Measurement> findAll(){
        return new ArrayList<>(measurements);
    }


}
