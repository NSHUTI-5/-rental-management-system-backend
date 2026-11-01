
package com.auca.rentalmanagement.service;

import com.auca.rentalmanagement.model.Building;
import com.auca.rentalmanagement.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    // Save a building
    public String save(Building building) {
        building.setId(null);
        buildingRepository.save(building);
        return "Building saved successfully";
    }

    // Get all buildings
    public List<Building> getAll() {
        return buildingRepository.findAll();
    }

    // Get building by ID
    public Optional<Building> getById(UUID id) {
        return buildingRepository.findById(id);
    }

    // Update building
    public String update(UUID id, Building body) {
        Optional<Building> found = buildingRepository.findById(id);

        if (found.isEmpty()) {
            return "The building with that id does not exist";
        }

        Building building = found.get();
        building.setBuildingNumber(body.getBuildingNumber());
        building.setName(body.getName());

        buildingRepository.save(building);
        return "Building updated successfully";
    }

    // Delete building
    public String delete(UUID id) {
        if (!buildingRepository.existsById(id)) {
            return "The building with that id does not exist";
        }

        buildingRepository.deleteById(id);
        return "Building deleted successfully";
    }
}
