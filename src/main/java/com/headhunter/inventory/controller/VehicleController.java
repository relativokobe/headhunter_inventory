package com.headhunter.inventory.controller;

import com.headhunter.inventory.dto.VehicleDto;
import com.headhunter.inventory.enums.ResponseEnum;
import com.headhunter.inventory.model.ValidatorResponse;
import com.headhunter.inventory.service.VehicleService;
import com.headhunter.inventory.util.DtoEntityConverter;
import com.headhunter.inventory.util.VehicleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/vehicle")
    public void insertNewVehicle(final @RequestBody VehicleDto vehicleDto, final HttpServletResponse response) throws IOException {
        final ValidatorResponse validatorResponse = VehicleValidator.validateParams(vehicleDto);
        if(!validatorResponse.getCode().equals(ResponseEnum.VALIDATION_SUCCESSFUL.getCode())){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(validatorResponse.getDescription());
            return;
        }

        //Inventory Code Already Exists
        if(vehicleService.findByInventoryCode(vehicleDto.getInventoryCode()) != null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ResponseEnum.INVENTORY_CODE_EXISTS.getDescription());
            return;
        }

        vehicleService.insertNewVehicle(DtoEntityConverter.convertVehicleDtoToEntity(vehicleDto));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
