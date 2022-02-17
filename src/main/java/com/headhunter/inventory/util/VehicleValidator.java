package com.headhunter.inventory.util;

import com.headhunter.inventory.dto.VehicleDto;
import com.headhunter.inventory.enums.ResponseEnum;
import com.headhunter.inventory.model.ValidatorResponse;
import org.apache.commons.lang3.StringUtils;

public class VehicleValidator {

    private VehicleValidator(){

    }

    public static ValidatorResponse validateParams(final VehicleDto vehicleDto){
        final ValidatorResponse validatorResponse = new ValidatorResponse();
        validatorResponse.setDescription(ResponseEnum.VALIDATION_SUCCESSFUL.getDescription());
        validatorResponse.setCode(ResponseEnum.VALIDATION_SUCCESSFUL.getCode());

        if(StringUtils.isAnyEmpty(vehicleDto.getName(), vehicleDto.getColor(), vehicleDto.getInventoryCode(),
                vehicleDto.getModel())){
            validatorResponse.setCode(ResponseEnum.EMPTY_PARAMETER.getCode());
            validatorResponse.setDescription(ResponseEnum.EMPTY_PARAMETER.getDescription());
        }
        return validatorResponse;
    }
}
