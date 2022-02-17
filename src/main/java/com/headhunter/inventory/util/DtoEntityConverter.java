package com.headhunter.inventory.util;

import com.headhunter.inventory.dto.VehicleDto;
import com.headhunter.inventory.entity.Vehicle;

public class DtoEntityConverter {
    public static Vehicle convertVehicleDtoToEntity(final VehicleDto vehicleDto){
        final Vehicle vehicle = new Vehicle();
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setInventoryCode(vehicleDto.getInventoryCode());
        vehicle.setName(vehicleDto.getName());
        vehicle.setModel(vehicleDto.getModel());

        return vehicle;
    }
}
