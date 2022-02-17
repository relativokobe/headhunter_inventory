package com.headhunter.inventory.repository;

import com.headhunter.inventory.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Vehicle findByInventoryCode(String inventoryCode);
}
