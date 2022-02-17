package com.headhunter.inventory.enums;

public enum ResponseEnum {
    EMPTY_PARAMETER("4444", "One of the parameters is empty"),
    VALIDATION_SUCCESSFUL("0000", "Validation Succesful"),
    INVALID_SORT_BY("2222", "Invalid Sort by. Please choose one: name, model, color, and inventoryCode"),
    PAGE_START_BIGGER_THAN_PAGE_END("6666", "Page Start should not have a bigger value than page end"),
    PAGE_SHOULD_BE_INTEGER("7777", "Page should be integer"),
    INCOMPLETE_PAGE("8888", "Please Complete page start and end"),
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
