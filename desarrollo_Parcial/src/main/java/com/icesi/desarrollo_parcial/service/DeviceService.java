package com.icesi.desarrollo_parcial.service;

import com.icesi.desarrollo_parcial.model.Device;
import com.icesi.desarrollo_parcial.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository){this.deviceRepository = deviceRepository;}

    public void addDevice(Integer id, String name, String serialNumber, String location, String type, String status){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede estar vacio ni ser null");
        }

        if (deviceRepository.findBySerialNumber(serialNumber) != null || serialNumber.length() < 5){
            throw new IllegalArgumentException("No puede agregarse un dispositivo con serialNumber repetido");
        }


        deviceRepository.save(id, name, serialNumber,location,type,status);
    }

    public void changeStatus(Integer id, String status){
        deviceRepository.actualizarStatus(id,status);
    }

    public List<Device> findAll(){
        return deviceRepository.findAll();
    }
}
