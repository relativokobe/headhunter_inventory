package com.headhunter.inventory.service;

import com.headhunter.inventory.entity.Vehicle;
import com.headhunter.inventory.repository.VehicleRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    private static final int ID_LENGTH = 20;
    private static final int ZERO = 0;

    public Vehicle findByInventoryCode(final String inventoryCode){
        return vehicleRepository.findByInventoryCode(inventoryCode);
    }

    public void insertNewVehicle(final Vehicle vehicle){
        vehicle.setId(vehicleIdGenerator());
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles(final int pageStart, final int pageEnd, final String sortBy){
        if(StringUtils.isEmpty(sortBy)){
            return vehicleRepository.findAll();
        }

        final Sort sort = Sort.by(sortBy);
        if(pageEnd == ZERO){
            return vehicleRepository.findAll(sort);
        }

        final Pageable pageable = PageRequest.of(pageStart, pageEnd, sort);
        final Page<Vehicle> vehicles = vehicleRepository.findAll(pageable);
        return vehicles.getContent();
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
