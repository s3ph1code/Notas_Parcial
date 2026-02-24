package com.icesi.desarrollo_parcial.repository;

import com.icesi.desarrollo_parcial.model.Device;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceRepository {
    private List<Device> devices = new ArrayList<>();



    public void save(Integer id, String name, String serialNumber, String location, String type, String status){
        Device device = new Device();
        device.setId(id);
        device.setName(name);
        device.setSerialNumber(serialNumber);
        device.setLocation(location);
        device.setType(type);
        device.setStatus(status);
        devices.add(device);
    }

    public void actualizarStatus(Integer id, String status){
        Device device = findById(id);
        device.setStatus(status);
    }

    public Device findById(Integer id){
        if(id == null){
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        return devices.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Device> findAll(){
        return new ArrayList<>(devices);
    }

    public Device findBySerialNumber(String serialNumber){
        String normalized = serialNumber.trim();
        return devices.stream().filter(u -> u.getSerialNumber().trim().equalsIgnoreCase(normalized)).findFirst().orElse(null);
    }

    public void deleteById(Integer id){
        devices.removeIf(u -> u.getId().equals(id));
    }





}
