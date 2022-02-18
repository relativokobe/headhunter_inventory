package com.headhunter.inventory.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.headhunter.inventory.dto.VehicleDto;
import com.headhunter.inventory.entity.Vehicle;
import com.headhunter.inventory.enums.ResponseEnum;
import com.headhunter.inventory.model.ValidatorResponse;
import com.headhunter.inventory.service.VehicleService;
import com.headhunter.inventory.util.DtoEntityConverter;
import com.headhunter.inventory.util.VehicleValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    private static final int ZERO = 0;
    private static final String SORT_BY_PARAM = "sortBy";
    private static final String PAGE_START_PARAM = "pageStart";
    private static final String PAGE_END_PARAM = "pageEnd";

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

    @GetMapping("/vehicles")
    public void getAllVehicles(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String sortBy = request.getParameter(SORT_BY_PARAM);
        final String pageStartString = request.getParameter(PAGE_START_PARAM);
        final String pageEndString = request.getParameter(PAGE_END_PARAM);

        final ValidatorResponse validatorResponse = VehicleValidator.validateGetAllVehicles(sortBy, pageStartString, pageEndString);
        if(!validatorResponse.getCode().equals(ResponseEnum.VALIDATION_SUCCESSFUL.getCode())){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(validatorResponse.getDescription());
            return;
        }

        final int pageStart = StringUtils.isEmpty(pageStartString) ? ZERO : Integer.parseInt(pageStartString);
        final int pageEnd = StringUtils.isEmpty(pageEndString) ? ZERO : Integer.parseInt(pageEndString);

        final List<VehicleDto> vehicles = DtoEntityConverter
                .convertVehicleEntityToDtoList(vehicleService.getAllVehicles(pageStart, pageEnd, sortBy));

        final Gson gson = new GsonBuilder().serializeNulls().create();
        final String stringResponse = gson.toJson(vehicles);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(stringResponse);
    }

    @PatchMapping("/vehicle")
    public void updateVehicle(final @RequestBody VehicleDto vehicleDto, final HttpServletResponse response) throws IOException {
        final ValidatorResponse validatorResponse = VehicleValidator.validateParams(vehicleDto);
        if(!validatorResponse.getCode().equals(ResponseEnum.VALIDATION_SUCCESSFUL.getCode())){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(validatorResponse.getDescription());
            return;
        }

        final Vehicle vehicle = vehicleService.findByInventoryCode(vehicleDto.getInventoryCode());
        //Inventory Code Does not exists
        if(vehicle == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ResponseEnum.INVENTORY_CODE_DOES_NOT_EXISTS.getDescription());
            return;
        }

        vehicle.setModel(vehicleDto.getModel());
        vehicle.setName(vehicleDto.getName());
        vehicle.setColor(vehicleDto.getColor());
        vehicleService.updateVehicle(vehicle);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
