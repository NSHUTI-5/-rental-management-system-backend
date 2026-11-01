
package com.auca.rentalmanagement.service;

import com.auca.rentalmanagement.model.Caretaker;
import com.auca.rentalmanagement.repository.CaretakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CaretakerService {

    @Autowired
    private CaretakerRepository caretakerRepository;

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

    // Delete caretaker
    public String delete(UUID id) {
        if (!caretakerRepository.existsById(id)) {
            return "The caretaker with that id does not exist";
        }

        caretakerRepository.deleteById(id);
        return "Caretaker deleted successfully";
    }
}

