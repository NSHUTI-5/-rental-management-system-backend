
package com.auca.rentalmanagement.service;

import com.auca.rentalmanagement.model.MaintenanceSkill;
import com.auca.rentalmanagement.repository.MaintenanceSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaintenanceSkillService {

    @Autowired
    private MaintenanceSkillRepository maintenanceSkillRepository;

    // Save maintenance skill
    public String save(MaintenanceSkill skill) {
        skill.setId(null);
        maintenanceSkillRepository.save(skill);
        return "Maintenance skill saved successfully";
    }

    // Get all maintenance skills
    public List<MaintenanceSkill> getAll() {
        return maintenanceSkillRepository.findAll();
    }

    // C3: Get maintenance skills that no caretaker holds
    public List<MaintenanceSkill> getUnusedSkills() {
        return maintenanceSkillRepository.findUnusedSkills();
    }

    // Get maintenance skill by ID
    public Optional<MaintenanceSkill> getById(UUID id) {
        return maintenanceSkillRepository.findById(id);
    }

    // Update maintenance skill
    public String update(UUID id, MaintenanceSkill body) {
        Optional<MaintenanceSkill> found = maintenanceSkillRepository.findById(id);

        if (found.isEmpty()) {
            return "The maintenance skill with that id does not exist";
        }

        MaintenanceSkill skill = found.get();
        skill.setName(body.getName());

        maintenanceSkillRepository.save(skill);
        return "Maintenance skill updated successfully";
    }

    // Delete maintenance skill
    public String delete(UUID id) {
        if (!maintenanceSkillRepository.existsById(id)) {
            return "The maintenance skill with that id does not exist";
        }

        maintenanceSkillRepository.deleteById(id);
        return "Maintenance skill deleted successfully";
    }
}
