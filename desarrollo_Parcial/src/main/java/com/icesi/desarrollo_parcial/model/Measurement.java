package com.icesi.desarrollo_parcial.model;

import java.util.List;

public class Measurement {
    private Integer id;
    private String timestamp;
    private double value;
    private String unit;
    private Integer deviceId;

    public Measurement(Integer id, String timestamp, double value, String unit, Integer deviceId) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
        this.unit = unit;
        this.deviceId = deviceId;
    }

    public Measurement(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
