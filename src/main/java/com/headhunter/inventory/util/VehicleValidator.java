package com.headhunter.inventory.util;

import com.headhunter.inventory.dto.VehicleDto;
import com.headhunter.inventory.enums.ResponseEnum;
import com.headhunter.inventory.model.ValidatorResponse;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;

public class VehicleValidator {
    private static final int ZERO = 0;

    private VehicleValidator() {

    }

    public static ValidatorResponse validateParams(final VehicleDto vehicleDto) {
        final ValidatorResponse validatorResponse = new ValidatorResponse();
        validatorResponse.setDescription(ResponseEnum.VALIDATION_SUCCESSFUL.getDescription());
        validatorResponse.setCode(ResponseEnum.VALIDATION_SUCCESSFUL.getCode());

        if (StringUtils.isAnyEmpty(vehicleDto.getName(), vehicleDto.getColor(), vehicleDto.getInventoryCode(),
                vehicleDto.getModel())) {
            validatorResponse.setCode(ResponseEnum.EMPTY_PARAMETER.getCode());
            validatorResponse.setDescription(ResponseEnum.EMPTY_PARAMETER.getDescription());
        }
        return validatorResponse;
    }

    public static ValidatorResponse validateGetAllVehicles(final String sortBy, final String pageStart, final String pageEnd) {
        final ValidatorResponse validatorResponse = new ValidatorResponse();
        validatorResponse.setDescription(ResponseEnum.VALIDATION_SUCCESSFUL.getDescription());
        validatorResponse.setCode(ResponseEnum.VALIDATION_SUCCESSFUL.getCode());

        //Check if there's a wrong combination of pages (one of the page is empty while the other one is not)
        if((StringUtils.isEmpty(pageStart) && !StringUtils.isEmpty(pageEnd))
                || (StringUtils.isEmpty(pageEnd) && !StringUtils.isEmpty(pageStart))){
            validatorResponse.setCode(ResponseEnum.INCOMPLETE_PAGE.getCode());
            validatorResponse.setDescription(ResponseEnum.INCOMPLETE_PAGE.getDescription());
            return validatorResponse;
        }

        //PageStart and PageEnd should not be empty to check if both are numeric
        if ((!StringUtils.isEmpty(pageStart) && !StringUtils.isEmpty(pageEnd))
                && (!StringUtils.isNumeric(pageStart) || !StringUtils.isNumeric(pageEnd))) {
            validatorResponse.setCode(ResponseEnum.PAGE_SHOULD_BE_INTEGER.getCode());
            validatorResponse.setDescription(ResponseEnum.PAGE_SHOULD_BE_INTEGER.getDescription());
            return validatorResponse;
        }

        final int pageStartInt = StringUtils.isEmpty(pageStart) ? ZERO : Integer.parseInt(pageStart);
        final int pageEndInt = StringUtils.isEmpty(pageEnd) ? ZERO : Integer.parseInt(pageEnd);
        if (pageStartInt > pageEndInt) {
            validatorResponse.setCode(ResponseEnum.PAGE_START_BIGGER_THAN_PAGE_END.getCode());
            validatorResponse.setDescription(ResponseEnum.PAGE_START_BIGGER_THAN_PAGE_END.getDescription());
            return validatorResponse;
        }

        final String[] validSortByArray = new String[]{"id", "color", "inventoryCode", "model", "name"};
        if (!StringUtils.isEmpty(sortBy) && !Arrays.asList(validSortByArray).contains(sortBy)) {
            validatorResponse.setCode(ResponseEnum.INVALID_SORT_BY.getCode());
            validatorResponse.setDescription(ResponseEnum.INVALID_SORT_BY.getDescription());
            return validatorResponse;
        }

        return validatorResponse;
    }
}
