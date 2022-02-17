package com.headhunter.inventory.util;

import com.headhunter.inventory.dto.VehicleDto;
import com.headhunter.inventory.entity.Vehicle;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DtoEntityConverter {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Vehicle convertVehicleDtoToEntity(final VehicleDto vehicleDto){
        return modelMapper.map(vehicleDto, Vehicle.class);
    }

    public static List<VehicleDto> convertVehicleEntityToDtoList(final List<Vehicle> vehicles){
        return vehicles.stream()
                .map(entity -> convertEntityToDto(entity))
                .collect(Collectors.toList());
    }

    public static VehicleDto convertEntityToDto(final Vehicle vehicle){
        return modelMapper.map(vehicle, VehicleDto.class);
    }
}
