
package com.auca.rentalmanagement.service;

import com.auca.rentalmanagement.model.Caretaker;
import com.auca.rentalmanagement.model.Building;
import com.auca.rentalmanagement.model.MaintenanceSkill;
import com.auca.rentalmanagement.repository.CaretakerRepository;
import com.auca.rentalmanagement.repository.BuildingRepository;
import com.auca.rentalmanagement.repository.MaintenanceSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CaretakerService {

    @Autowired
    private CaretakerRepository caretakerRepository;

    @Autowired
    private MaintenanceSkillRepository maintenanceSkillRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    // Save caretaker
    public String save(Caretaker caretaker) {
        caretaker.setId(null);
        caretakerRepository.save(caretaker);
        return "Caretaker saved successfully";
    }

    // Get all caretakers
    public List<Caretaker> getAll() {
        return caretakerRepository.findAll();
    }

    // C1: Find caretakers by maintenance skill
    public List<Caretaker> getByMaintenanceSkill(String name) {
        return caretakerRepository.findByMaintenanceSkillName(name);
    }

    // Get caretaker by ID
    public Optional<Caretaker> getById(UUID id) {
        return caretakerRepository.findById(id);
    }

    // Update caretaker
    public String update(UUID id, Caretaker body) {
        Optional<Caretaker> found = caretakerRepository.findById(id);

        if (found.isEmpty()) {
            return "The caretaker with that id does not exist";
        }

        Caretaker caretaker = found.get();

        caretaker.setFirstName(body.getFirstName());
        caretaker.setLastName(body.getLastName());
        caretaker.setPhone(body.getPhone());

        if (body.getBuilding() != null) {
            caretaker.setBuilding(body.getBuilding());
        }

        if (body.getMaintenanceSkills() != null &&
                !body.getMaintenanceSkills().isEmpty()) {
            caretaker.setMaintenanceSkills(body.getMaintenanceSkills());
        }

        caretakerRepository.save(caretaker);
        return "Caretaker updated successfully";
    }

    // D3: Add maintenance skill to caretaker
    @Transactional
    public String addMaintenanceSkill(UUID caretakerId, UUID skillId) {

        Optional<Caretaker> foundCaretaker =
                caretakerRepository.findById(caretakerId);

        if (foundCaretaker.isEmpty()) {
            return "The caretaker with that id does not exist";
        }

        Optional<MaintenanceSkill> foundSkill =
                maintenanceSkillRepository.findById(skillId);

        if (foundSkill.isEmpty()) {
            return "That maintenance skill does not exist";
        }

        Caretaker caretaker = foundCaretaker.get();
        MaintenanceSkill skill = foundSkill.get();

        if (caretaker.getMaintenanceSkills().contains(skill)) {
            return "The caretaker already has that maintenance skill";
        }

        caretaker.getMaintenanceSkills().add(skill);
        caretakerRepository.save(caretaker);

        return "Maintenance skill added to the caretaker";
    }

    // D4: Move caretaker to another building
    public String changeBuilding(UUID caretakerId, int buildingNumber) {

        Optional<Caretaker> foundCaretaker =
                caretakerRepository.findById(caretakerId);

        if (foundCaretaker.isEmpty()) {
            return "The caretaker with that id does not exist";
        }

        Building building =
                buildingRepository.findByBuildingNumber(buildingNumber);

        if (building == null) {
            return "The building with that buildingNumber does not exist";
        }

        Caretaker caretaker = foundCaretaker.get();

        // If the caretaker is already in this building
        if (caretaker.getBuilding() != null &&
                caretaker.getBuilding().getId().equals(building.getId())) {
            return "Building changed successfully";
        }

        // Check whether another caretaker occupies the building
        if (caretakerRepository.existsByBuilding(building)) {
            return "That building is taken by another caretaker";
        }

        caretaker.setBuilding(building);
        caretakerRepository.save(caretaker);

        return "Building changed successfully";
    }

    // Delete caretaker
    public String delete(UUID id) {

        if (!caretakerRepository.existsById(id)) {
            return "The caretaker with that id does not exist";
        }

        caretakerRepository.deleteById(id);
        return "Caretaker deleted successfully";
    }
}



