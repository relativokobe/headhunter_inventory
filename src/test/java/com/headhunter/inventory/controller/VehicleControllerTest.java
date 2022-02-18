package com.headhunter.inventory.controller;

import com.headhunter.inventory.dto.VehicleDto;
import com.headhunter.inventory.entity.Vehicle;
import com.headhunter.inventory.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {VehicleController.class})
class VehicleControllerTest {
    @Autowired
    VehicleController vehicleController;

    @MockBean
    VehicleService vehicleService;

    @Test
    void test_insert_new_vehicle() throws IOException {
        doNothing().when(vehicleService).insertNewVehicle(any());
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setInventoryCode("4");
        vehicleDto.setName("sample");
        vehicleDto.setColor("sample");
        vehicleDto.setModel("sample");
        vehicleController.insertNewVehicle(vehicleDto, response);
        assertEquals(200, response.getStatus());
    }

    @Test
    void test_insert_new_vehicle_missing_params() throws IOException {
        doNothing().when(vehicleService).insertNewVehicle(any());
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setInventoryCode("4");
        vehicleDto.setName("sample");
        vehicleDto.setColor("sample");
        vehicleDto.setModel(null);
        vehicleController.insertNewVehicle(vehicleDto, response);
        assertEquals(400, response.getStatus());
    }

    @Test
    void test_insert_new_vehicle_inventory_code_exists() throws IOException {
        doNothing().when(vehicleService).insertNewVehicle(any());
        when(vehicleService.findByInventoryCode("4")).thenReturn(new Vehicle());
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setInventoryCode("4");
        vehicleDto.setName("sample");
        vehicleDto.setColor("sample");
        vehicleDto.setModel("sample");
        vehicleController.insertNewVehicle(vehicleDto, response);
        assertEquals(400, response.getStatus());
    }

    @Test
    void test_get_all_vehicles() throws IOException {
        final List<Vehicle> vehicleList = new ArrayList<>();
        when(vehicleService.getAllVehicles(0, 1, "inventoryCode")).thenReturn(vehicleList);
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("sortBy", "inventoryCode");
        request.setParameter("pageStart", "0");
        request.setParameter("pageEnd", "1");
        vehicleController.getAllVehicles(request, response);
        assertEquals(200, response.getStatus());
    }

    @Test
    void test_get_all_vehicles_unsuccessful_validation() throws IOException {
        final List<Vehicle> vehicleList = new ArrayList<>();
        when(vehicleService.getAllVehicles(0, 1, "inventoryCode")).thenReturn(vehicleList);
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("sortBy", "inventoryCode");
        //Page start is bigger than page end
        request.setParameter("pageStart", "2");
        request.setParameter("pageEnd", "1");
        vehicleController.getAllVehicles(request, response);
        assertEquals(400, response.getStatus());
    }

    @Test
    void test_update_vehicle() throws IOException {
        final MockHttpServletResponse response = new MockHttpServletResponse();
        when(vehicleService.findByInventoryCode("4")).thenReturn(new Vehicle());
        doNothing().when(vehicleService).updateVehicle(any());
        final VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setInventoryCode("4");
        vehicleDto.setName("sample");
        vehicleDto.setColor("sample");
        vehicleDto.setModel("sample");
        vehicleController.updateVehicle(vehicleDto, response);
        assertEquals(200, response.getStatus());
    }

    @Test
    void test_update_vehicle_missing_param() throws IOException {
        final MockHttpServletResponse response = new MockHttpServletResponse();
        when(vehicleService.findByInventoryCode("4")).thenReturn(new Vehicle());
        doNothing().when(vehicleService).updateVehicle(any());
        final VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setInventoryCode("4");
        vehicleDto.setName("sample");
        vehicleDto.setColor("sample");
        vehicleDto.setModel(null);
        vehicleController.updateVehicle(vehicleDto, response);
        assertEquals(400, response.getStatus());
    }

    @Test
    void test_update_vehicle_missing_inventory_code() throws IOException {
        final MockHttpServletResponse response = new MockHttpServletResponse();
        when(vehicleService.findByInventoryCode("4")).thenReturn(null);
        doNothing().when(vehicleService).updateVehicle(any());
        final VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setInventoryCode("4");
        vehicleDto.setName("sample");
        vehicleDto.setColor("sample");
        vehicleDto.setModel("sample");
        vehicleController.updateVehicle(vehicleDto, response);
        assertEquals(400, response.getStatus());
    }

    @Test
    void test_delete_vehicle() throws IOException {
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("inventoryCode", "4");
        doNothing().when(vehicleService).deleteVehicle(any());
        when(vehicleService.findByInventoryCode("4")).thenReturn(new Vehicle());
        vehicleController.deleteVehicle(request, response);
        assertEquals(200, response.getStatus());
    }

    @Test
    void test_delete_vehicle_cannot_find_vehicle() throws IOException {
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("inventoryCode", "4");
        doNothing().when(vehicleService).deleteVehicle(any());
        when(vehicleService.findByInventoryCode("4")).thenReturn(null);
        vehicleController.deleteVehicle(request, response);
        assertEquals(400, response.getStatus());
    }

    @Test
    void test_delete_vehicle_missing_param() throws IOException {
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        doNothing().when(vehicleService).deleteVehicle(any());
        when(vehicleService.findByInventoryCode("4")).thenReturn(null);
        vehicleController.deleteVehicle(request, response);
        assertEquals(400, response.getStatus());
    }
}