package com.headhunter.inventory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class VehicleDto {
    private String id;
    private String inventoryCode;
    private String name;
    private String model;
    private String color;
}
