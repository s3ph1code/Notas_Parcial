package com.icesi.desarrollo_parcial.model;

import java.util.List;

public class Device {
    private Integer id;
    private String name;
    private String serialNumber;
    private String location;
    private String type;
    private String status;
    private List<Measurement> measurements;

    public Device(Integer id, String name, String serialNumber, String location, String status, List<Measurement> measurements, String type) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.location = location;
        this.status = status;
        this.measurements = measurements;
        this.type = type;
    }

    public Device(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
