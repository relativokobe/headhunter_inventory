package com.headhunter.inventory.enums;

public enum ResponseEnum {
    EMPTY_PARAMETER("4444", "One of the parameters is empty"),
    VALIDATION_SUCCESSFUL("0000", "Validation Succesful"),
    INVENTORY_CODE_EXISTS("3333", "Inventory Code already exists");

    private final String code;
    private final String description;

    ResponseEnum(final String code, final String description){
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
