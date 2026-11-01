
package com.auca.rentalmanagement.controller;

import com.auca.rentalmanagement.model.Building;
import com.auca.rentalmanagement.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    // Save building
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Building building) {
        return new ResponseEntity<>(
                buildingService.save(building), HttpStatus.CREATED);
    }

    // Get all buildings
    @GetMapping("/all")
    public ResponseEntity<List<Building>> getAll() {
        return new ResponseEntity<>(buildingService.getAll(), HttpStatus.OK);
    }

    // C2: Get buildings that have no caretaker
    @GetMapping("/free")
    public ResponseEntity<List<Building>> getFreeBuildings() {
        return new ResponseEntity<>(
                buildingService.getFreeBuildings(),
                HttpStatus.OK);
    }

    // Get building by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Building> building = buildingService.getById(id);

        if (building.isPresent()) {
            return new ResponseEntity<>(building.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "The building with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }
    }

    // Update building
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody Building building) {

        String message = buildingService.update(id, building);

        if (message.equals("Building updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // Delete building
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = buildingService.delete(id);

        if (message.equals("Building deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
