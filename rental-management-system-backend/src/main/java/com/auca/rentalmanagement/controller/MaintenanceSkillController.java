
package com.auca.rentalmanagement.controller;

import com.auca.rentalmanagement.model.MaintenanceSkill;
import com.auca.rentalmanagement.service.MaintenanceSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/maintenance-skills")
public class MaintenanceSkillController {

    @Autowired
    private MaintenanceSkillService maintenanceSkillService;

    // Save maintenance skill
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MaintenanceSkill skill) {
        return new ResponseEntity<>(
                maintenanceSkillService.save(skill), HttpStatus.CREATED);
    }

    // Get all maintenance skills
    @GetMapping("/all")
    public ResponseEntity<List<MaintenanceSkill>> getAll() {
        return new ResponseEntity<>(
                maintenanceSkillService.getAll(), HttpStatus.OK);
    }

    // C3: Get maintenance skills that no caretaker holds
    @GetMapping("/unused")
    public ResponseEntity<List<MaintenanceSkill>> getUnusedSkills() {
        return new ResponseEntity<>(
                maintenanceSkillService.getUnusedSkills(),
                HttpStatus.OK);
    }

    // Get maintenance skill by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<MaintenanceSkill> skill = maintenanceSkillService.getById(id);

        if (skill.isPresent()) {
            return new ResponseEntity<>(skill.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "The maintenance skill with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }
    }

    // Update maintenance skill
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody MaintenanceSkill skill) {

        String message = maintenanceSkillService.update(id, skill);

        if (message.equals("Maintenance skill updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // Delete maintenance skill
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = maintenanceSkillService.delete(id);

        if (message.equals("Maintenance skill deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
