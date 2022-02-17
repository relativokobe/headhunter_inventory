package com.headhunter.inventory.service;

import com.headhunter.inventory.entity.Vehicle;
import com.headhunter.inventory.repository.VehicleRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    private static final int ID_LENGTH = 20;

    public Vehicle findByInventoryCode(final String inventoryCode){
        return vehicleRepository.findByInventoryCode(inventoryCode);
    }

    public void insertNewVehicle(final Vehicle vehicle){
        vehicle.setId(vehicleIdGenerator());
        vehicleRepository.save(vehicle);
    }

    private String vehicleIdGenerator(){
        String proposedVehicleId = null;
        boolean vehicleIdExists = true;
        while(vehicleIdExists){
            proposedVehicleId = RandomStringUtils.random(ID_LENGTH, true, true);
            final Optional<Vehicle> vehicleOptional = vehicleRepository.findById(proposedVehicleId);
            if(!vehicleOptional.isPresent()){
                vehicleIdExists = false;
            }
        }

        return proposedVehicleId;
    }
}
