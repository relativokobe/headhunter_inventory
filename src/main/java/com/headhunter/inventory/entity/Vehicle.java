package com.headhunter.inventory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "vehicle")
public class Vehicle {
    @Id
    private String id;
    @Column(name = "inventory_code")
    private String inventoryCode;
    @Column(name = "name")
    private String name;
    @Column(name = "model")
    private String model;
    @Column(name = "color")
    private String color;
}
